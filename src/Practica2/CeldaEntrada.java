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
public class CeldaEntrada extends Celda{
    private int cola;
    
    
    public CeldaEntrada() {
        super();
        tipo = 'e';
        cola = 0;
    }

    public int getCola() {
        return cola;
    }
    
    public void incrementarCola(){
        cola++;
    }
    
    public void decrementarCola(){
        cola--;
    }
    
}
