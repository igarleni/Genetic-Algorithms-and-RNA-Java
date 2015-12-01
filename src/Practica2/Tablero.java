/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Practica2;

import java.util.ArrayList;

/**
 *
 * @author Italo
 */
public class Tablero {
    private final ArrayList<Celda> Horizontal1;
    private final ArrayList<Celda> Horizontal2;
    private final ArrayList<Celda> Vertical1;
    private final ArrayList<Celda> Vertical2;
    private final CeldaEntrada[] entradas;
    private final CeldaSemaforo[] semaforos;
    private final CeldaInterseccion[] intersecciones;
    private final CeldaSalida[] salidas;

    public Tablero() {
        entradas = new CeldaEntrada[]{
            new CeldaEntrada(), new CeldaEntrada(),
            new CeldaEntrada(), new CeldaEntrada() };
        semaforos = new CeldaSemaforo[] {
            new CeldaSemaforo("Horizontal"), new CeldaSemaforo("Vertical"), //0-1 semaforos arriba izquierda
            new CeldaSemaforo("Horizontal"), new CeldaSemaforo("Vertical"), //2-3 semaforos abajo izquierda
            new CeldaSemaforo("Horizontal"), new CeldaSemaforo("Vertical"), //4-5 semaforos arriba derecha
            new CeldaSemaforo("Horizontal"), new CeldaSemaforo("Vertical") }; //6-7 semaforos abajo derecha
        //pares+0 horizontal, impares vertical
        
        intersecciones = new CeldaInterseccion[]{
            new CeldaInterseccion(), new CeldaInterseccion(),
            new CeldaInterseccion(), new CeldaInterseccion() };
        salidas = new CeldaSalida[]{
            new CeldaSalida(), new CeldaSalida(),
            new CeldaSalida(), new CeldaSalida() };
        
        this.Horizontal1 = createRoad(0, semaforos[0], intersecciones[0], semaforos[4], intersecciones[1]);
        this.Horizontal2 = createRoad(1, semaforos[2], intersecciones[2], semaforos[6], intersecciones[3]);
        this.Vertical1 = createRoad(2, semaforos[1], intersecciones[0], semaforos[3], intersecciones[2]);
        this.Vertical2 = createRoad(3, semaforos[5], intersecciones[1], semaforos[7], intersecciones[3]);
    }

    private ArrayList<Celda> createRoad(int numRoad, CeldaSemaforo semaforo1,
            CeldaInterseccion interseccion1, CeldaSemaforo semaforo2, CeldaInterseccion interseccion2) {
        
        ArrayList<Celda> road = new ArrayList<>();
        
        road.add(entradas[numRoad]);
        road.add(new Celda());
        road.add(new Celda());
        road.add(semaforo1);
        road.add(interseccion1);
        road.add(new Celda());
        road.add(new Celda());
        road.add(new Celda());
        road.add(semaforo2);
        road.add(interseccion2);
        road.add(new Celda());
        road.add(new Celda());
        road.add(new Celda());
        road.add(salidas[numRoad]);
        
        return road;
    }
    
    //Introduces los semáforos horizontales, y los verticales se cambian a consecuencia
    public void cambiarSemaforos(boolean arribaIzquierda, boolean abajoIzquierda,
            boolean arribaDerecha, boolean abajoDerecha){
        
        //TODO implementar
    }
    
    public void addCoches(int numCoches){
        //Añadir coches a las salidas
    }
    
    public void avanzarTurno(){
        /**Avanzar turno en el tablero
         * 1.-generar estado siguiente de cada ArrayList (ojo con intersecciones)
         * 2.-aplicarlo
         */
    }
    
}