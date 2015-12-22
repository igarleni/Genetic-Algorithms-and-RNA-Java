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
    private int aceleraciones;
    
    public CeldaConvencional(){
        this.tieneCoche = false;
        this.nextEstadoTieneCoche = false;
        this.aceleraciones = 0;
        this.estadoCarretera = true;
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
    public void setNextEstado(boolean nextEstadoTieneCoche){
        this.nextEstadoTieneCoche = nextEstadoTieneCoche;
    }

    @Override
    public void applyNextEstado(){
        tieneCoche = nextEstadoTieneCoche;
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
    public int getAceleraciones(){
        return aceleraciones;
    } 
    
    @Override
    public String toString(){
        if (tieneCoche())
            return "1";
        else
            return "0";
    }

}
