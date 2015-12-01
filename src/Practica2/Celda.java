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
    
    public Celda(){
        this.tieneCoche = false;
        
        this.nextEstadoTieneCoche = false;
        
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
    
    public void setNextEstado(boolean nextEstadoTieneCoche){
        this.nextEstadoTieneCoche = nextEstadoTieneCoche;
    }

    public void applyNextEstado(){
        tieneCoche = nextEstadoTieneCoche;
        nextEstadoTieneCoche = false;
    }

}
