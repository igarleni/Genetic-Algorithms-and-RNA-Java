/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practica3;

import java.util.ArrayList;

/**
 *
 * @author Italo
 */
public class CeldaConvencional implements Celda {
    private boolean estadoCarretera;
    private int coche;
    private int nextCoche;
    private final ArrayList<Integer> coches;
    
    public CeldaConvencional(ArrayList<Integer> coches){
        this.coche = 0;
        this.nextCoche = 0;
        this.estadoCarretera = true;
        this.coches = coches;
    }
    
    @Override
    public char getTipo() {
        return 'c';
    }  
    
    @Override
    public int getCoche(){
        return coche;
    }
    
    @Override
    public void setNextEstado(int nextCoche){
        this.nextCoche = nextCoche;
    }

    @Override
    public void applyNextEstado(){
        if(coche != 0){
            if(coche == nextCoche && estadoCarretera){
                estadoCarretera = false;
                coches.set(coche, coches.get(coche)+1);
            }
            else if(coche != nextCoche && !estadoCarretera){
                estadoCarretera = true;
                coches.set(coche, coches.get(coche)+1);
            }
        }
        coche = nextCoche;
    }
    
    @Override
    public String toString(){
        return Integer.toString(coche);
    }

}
