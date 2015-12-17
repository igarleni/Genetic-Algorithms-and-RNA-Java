/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Practica3;

/**
 *
 * @author alumno
 */
public class CeldaEntrada implements Celda{
    private int cola;
    private boolean nextEstadoSaleCoche;
    
    public CeldaEntrada() {
        cola = 0;
        nextEstadoSaleCoche = false;
    }

    public int getCola() {
        return cola;
    }
    
    public void incrementarCola(int numCoches){
        cola += numCoches;
    }
    
    private void decrementarCola(){
        cola--;
    }
    
    @Override
    public boolean tieneCoche(){
        return cola > 0;
    }
    
    @Override
    public void applyNextEstado(){
        if (nextEstadoSaleCoche) decrementarCola();
    }

    @Override
    public char getTipo() {
        return 'e';
    }

    @Override
    public void setNextEstado(boolean nextEstadoSaleCoche) {
        this.nextEstadoSaleCoche = nextEstadoSaleCoche;
    }
    
    @Override
    public String toString()
    {
        return Integer.toString(cola);
    }
}