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
public class CeldaInterseccion extends Celda{
    private String direction;
    private String nextDirection;

    public CeldaInterseccion() {
        super();
    }
    
    public String getDirection(){
        return direction;
    }
    
    public void setNextEstado(boolean nextEstadoTieneCoche, String nextDirection){
        this.nextEstadoTieneCoche = nextEstadoTieneCoche;
        this.nextDirection = nextDirection;
    }
    
    @Override
    public void applyNextEstado(){
        super.applyNextEstado();
        direction = nextDirection;
    }
}