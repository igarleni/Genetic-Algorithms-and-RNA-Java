package Practica3;

import java.util.ArrayList;

public class CeldaSemaforo implements Celda{
    private boolean estadoSemaforo;
    private final String direction;
    
    private int coche;
    private int nextCoche;
    private boolean estadoCarretera;
    private final ArrayList<Integer> coches;
    
    public String getDirection() {
        return direction;
    }
    
    public CeldaSemaforo(ArrayList<Integer> coches, String direction) {
        this.coche = 0;
        this.nextCoche = 0;
        this.coches = coches;
        estadoCarretera = true;
        estadoSemaforo = true;
        this.direction = direction;
    }
    
    /*
    * Verde = true, rojo = false
    */
    
    public boolean getEstadoSemaforo(){
        return estadoSemaforo;
    }
    
    public void setEstadoSemaforo(boolean estadoSemaforo){
        this.estadoSemaforo = estadoSemaforo;
    }

    @Override
    public int getCoche(){
        return coche;
    }
    
    @Override
    public void setNextEstado(int nextCoche) {
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
    public char getTipo() {
        return 's';
    }

    @Override
    public String toString(){
        return Integer.toString(coche);
    }
}
