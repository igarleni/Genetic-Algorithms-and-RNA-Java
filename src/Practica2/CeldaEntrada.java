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
    
    
    public CeldaEntrada(Celda prevCelda, Celda nextCelda, int numCars) {
        super(prevCelda, nextCelda);
        tipo = 'e';
        cola = 0;
    }

    public int getCola() {
        return cola;
    }
    
    public void addCoches(int numCoches){
        cola += numCoches;
    }
    
    @Override
    public boolean tieneCoche(){
        return cola > 0;
    }
    
    @Override
    protected void generarNextEstado(){
        boolean nextCeldaAceptaCoche = getNextCelda().nextEstadoAceptaCoche();
        if (nextCeldaAceptaCoche && cola > 0)
            cola--;
    }  
}
