/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practica3;

/**
 *
 * @author Italo
 */
public class CeldaInterseccion implements Celda{
    private String direction;
    private String nextDirection;
    private boolean tieneCoche;
    protected boolean nextEstadoTieneCoche;
    private boolean estadoCarretera;
    private int aceleraciones;
    
    public CeldaInterseccion() {
        tieneCoche = false;
        nextEstadoTieneCoche = false;
        direction = "Horizontal";
        estadoCarretera = true;
        aceleraciones = 0;
    }
    
    public String getDirection(){
        return direction;
    }
    
    public void setNextDirection(String nextDirection){
        this.nextDirection = nextDirection;
    }
        @Override
    public char getTipo() {
        return 'i';
    }

    @Override
    public boolean tieneCoche() {
        return tieneCoche;
    }
    
    @Override
    public void setNextEstado(boolean nextEstadoTieneCoche){
        this.nextEstadoTieneCoche = nextEstadoTieneCoche;
    }
    
    @Override
    public void applyNextEstado(){
        tieneCoche = nextEstadoTieneCoche;
        direction = nextDirection;
        if(nextEstadoTieneCoche && tieneCoche && estadoCarretera){
            estadoCarretera = false;
            aceleraciones++;
        }
        else if(!nextEstadoTieneCoche && tieneCoche && !estadoCarretera){
            estadoCarretera = true;
            aceleraciones++;
        }
    }

    @Override
    public String toString(){
        if (tieneCoche())
            return "1";
        else
            return "0";
    }

    @Override
    public int getAceleraciones() {
        return aceleraciones;
    }

}