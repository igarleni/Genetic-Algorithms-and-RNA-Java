/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practica2;

/**
 *
 * @author Italo
 */
public class CeldaPostInterseccion extends Celda{
    private final String direction;

    
    public CeldaPostInterseccion(Celda prevCelda, Celda nextCelda, String direction) {
        super(prevCelda, nextCelda);
        tipo = 'p';
        this.direction = direction;
    }
    
    @Override
    protected void generarNextEstado(){
        boolean nextCeldaAceptaCoche = nextCelda.nextEstadoAceptaCoche();
        CeldaInterseccion prevCeldaInters = (CeldaInterseccion) prevCelda;
        
        if (tieneCoche()){ //si hay coches en la celda
            if (nextCeldaAceptaCoche)
                if (prevCeldaInters.tieneCoche(direction))
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
            if (prevCeldaInters.tieneCoche(direction))
                nextEstadoAceptaCoche = nextEstadoTieneCoche = true;
            else{
                nextEstadoAceptaCoche = false;
                nextEstadoTieneCoche = false;
            }
        }
        
    }
    
}
