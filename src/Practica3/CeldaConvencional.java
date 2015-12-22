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
public class CeldaConvencional implements Celda {
    private boolean tieneCoche;
    private boolean nextEstadoTieneCoche;
    private boolean estadoCarretera;
    private int coche;
    private boolean aceleracion;
    
    public CeldaConvencional(){
        this.tieneCoche = false;
        this.nextEstadoTieneCoche = false;
        this.estadoCarretera = true;
        this.aceleracion = false;
    }
    
    @Override
    public char getTipo() {
        return 'c';
    }  
    
    @Override
    public boolean tieneCoche(){
        return tieneCoche;
    }
    
    @Override
    public void setNextEstado(boolean nextEstadoTieneCoche, int coche){
        this.nextEstadoTieneCoche = nextEstadoTieneCoche;
        this.coche = coche;
    }

    @Override
    public void applyNextEstado(){
        tieneCoche = nextEstadoTieneCoche;
        if(nextEstadoTieneCoche && tieneCoche && estadoCarretera){
            estadoCarretera = false;
            aceleracion = true;
        }
        else if(!nextEstadoTieneCoche && tieneCoche && !estadoCarretera){
            estadoCarretera = true;
            aceleracion = true;
        }
        else
            aceleracion = false;
    }
    
    @Override
    public boolean getAceleracion(){
        return aceleracion;
    } 
    
    @Override
    public String toString(){
        if (tieneCoche())
            return "1";
        else
            return "0";
    }

    @Override
    public int getCoche() {
        return coche;
    }

}
