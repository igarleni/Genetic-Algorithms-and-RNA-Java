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
    private int coche;
    
    public CeldaEntrada() {
        cola = 0;
        coche = 0;
    }

    @Override
    public int getCoche() {
        return coche;
    }
    
    public int getCola(){
        return cola;
    }
    
    public void incrementarCola(int numCoches){
        cola += numCoches;
    }
    
    private void decrementarCola(){
        cola--;
    }
    
    @Override
    public void setNextEstado(int coche) {
        this.coche = coche;
    }
    
    @Override
    public void applyNextEstado(){
        if (coche != 0){
            decrementarCola();
            coche = 0;
        }
    }
    
    @Override
    public char getTipo() {
        return 'e';
    }

    @Override
    public String toString()
    {
        return Integer.toString(cola);
    }
}