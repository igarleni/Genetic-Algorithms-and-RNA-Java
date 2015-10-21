/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Practica2;

/**
 *
 * @author alumno
 */
public class Celda {
    private static int idSeed = 0;
    private final int id;
    protected char tipo;
    private boolean tieneCoche;
    
    protected boolean nextEstadoTieneCoche;
    protected boolean nextEstadoAceptaCoche;
    
    private final Celda prevCelda;
    private final Celda nextCelda;

    public Celda(Celda prevCelda, Celda nextCelda) {
        this.tieneCoche = false;
        
        this.nextEstadoTieneCoche = false;
        this.nextEstadoAceptaCoche = true;
        
        this.prevCelda = prevCelda;
        this.nextCelda = nextCelda;
        idSeed++;
        id = idSeed;
        tipo = 'c';
    }

    public int getId() {
        return id;
    }

    public char getTipo() {
        return tipo;
    }  
    
    public boolean tieneCoche(){
        return tieneCoche;
    }
    
    protected void generarNextEstado(){
        boolean nextCeldaAceptaCoche = nextCelda.nextEstadoAceptaCoche();
        if (tieneCoche){ //si hay coches en la celda
            if (nextCeldaAceptaCoche)
                if (prevCelda.tieneCoche())
                    nextEstadoAceptaCoche = nextEstadoTieneCoche = true;
                else {
                    nextEstadoAceptaCoche = true;
                    nextEstadoTieneCoche = false;
                }
            else{
                nextEstadoAceptaCoche = false;
                nextEstadoTieneCoche = true;
            }
        }else{ //si no hay coches en la celda
            if (prevCelda.tieneCoche)
                nextEstadoAceptaCoche = nextEstadoTieneCoche = true;
            else{
                nextEstadoAceptaCoche = false;
                nextEstadoTieneCoche = false;
            }
        }
    }
    
    public boolean nextEstadoAceptaCoche(){
        generarNextEstado();
        return nextEstadoAceptaCoche;
    }
    
    public void applyNextState(){
        tieneCoche = nextEstadoTieneCoche;
    }

    public Celda getPrevCelda() {
        return prevCelda;
    }

    public Celda getNextCelda() {
        return nextCelda;
    }
    
    
}
