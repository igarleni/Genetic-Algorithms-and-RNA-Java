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
public class Tablero {
    
    //Entradas
    private final CeldaEntrada entradaHorizontal1;
    private final CeldaEntrada entradaHorizontal2;
    private final CeldaEntrada entradaVertical1;
    private final CeldaEntrada entradaVertical2;
    private final CeldaEntrada[] listaEntradas;
    
    //Semáforos
    private final CeldaSemaforo semaforoArribaIzquierdaHorizontal;
    private final CeldaSemaforo semaforoArribaIzquierdaVertical;
    
    private final CeldaSemaforo semaforoArribaDerechaHorizontal;
    private final CeldaSemaforo semaforoArribaDerechaVertical;
    
    private final CeldaSemaforo semaforoAbajoIzquierdaHorizontal;
    private final CeldaSemaforo semaforoAbajoIzquierdaVertical;
    
    private final CeldaSemaforo semaforoAbajoDerechaHorizontal;
    private final CeldaSemaforo semaforoAbajoDerechaVertical;
    
    //Salidas
    private final CeldaSalida salidaVertical1;
    private final CeldaSalida salidaVertical2;
    
    private final CeldaSalida salidaHorizontal1;
    private final CeldaSalida salidaHorizontal2;
    
    public Tablero(){
        entradaHorizontal1 = new CeldaEntrada();
        entradaHorizontal2 = new CeldaEntrada();
        entradaVertical1 = new CeldaEntrada();
        entradaVertical2 = new CeldaEntrada();
        listaEntradas = new CeldaEntrada[] {entradaHorizontal1, entradaHorizontal2, entradaVertical1, entradaVertical2};
        
        semaforoArribaIzquierdaHorizontal = new CeldaSemaforo();
        semaforoArribaIzquierdaVertical = new CeldaSemaforo();

        semaforoArribaDerechaHorizontal = new CeldaSemaforo();
        semaforoArribaDerechaVertical = new CeldaSemaforo();

        semaforoAbajoIzquierdaHorizontal = new CeldaSemaforo();
        semaforoAbajoIzquierdaVertical = new CeldaSemaforo();

        semaforoAbajoDerechaHorizontal = new CeldaSemaforo();
        semaforoAbajoDerechaVertical = new CeldaSemaforo();
        
        unirHastaSemáforo();
        
        CeldaPostInterseccion[] auxCeldas = crearIntersecciones();
        
        salidaVertical1 = new CeldaSalida();
        salidaVertical2 = new CeldaSalida();
        salidaHorizontal1 = new CeldaSalida();
        salidaHorizontal2 = new CeldaSalida();
        
        unirHastaSalida(auxCeldas);
    }
    
    private void unirHastaSemáforo() {
        CeldaSemaforo[] auxListaSemaforos = new CeldaSemaforo[] { 
            semaforoArribaIzquierdaHorizontal,
            semaforoAbajoIzquierdaHorizontal,
            semaforoArribaIzquierdaVertical,
            semaforoArribaDerechaVertical
        };
        for (int i = 0 ; i < listaEntradas.length; i++){
            Celda celdaFin = listaEntradas[i];
            for (int j = 0; j < 2; j++){
                Celda celdaAux = new Celda(celdaFin ,null);
                celdaFin.setNextCelda(celdaAux);
                celdaFin = celdaAux;
            }
            celdaFin.setNextCelda(auxListaSemaforos[i]);
        }
    }

    private CeldaPostInterseccion[] crearIntersecciones() {
        CeldaPostInterseccion[] result = new CeldaPostInterseccion[4];
        
        //TODO crear celdas intersecciones y unirlas
        //Meter after inters las postinter y añadirlas
        
        return result;
    }

    private void unirHastaSalida(CeldaPostInterseccion[] auxCeldas) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
