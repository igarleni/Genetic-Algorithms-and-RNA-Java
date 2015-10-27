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
public class CeldaSemaforo extends Celda{

    public CeldaSemaforo(Celda prevCelda, Celda nextCelda) {
        super(prevCelda, nextCelda);
        tipo = 's';
    }

    boolean getEstadoSemaforo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
