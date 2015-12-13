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
    private final ArrayList<Celda> horizontal1; //road 1
    private final ArrayList<Celda> horizontal2; //road 2
    private final ArrayList<Celda> vertical1; //road 3
    private final ArrayList<Celda> vertical2; //road 4
    
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
        
        this.horizontal1 = createRoad(0, semaforos[0], intersecciones[0], semaforos[4], intersecciones[1]);
        this.horizontal2 = createRoad(1, semaforos[2], intersecciones[2], semaforos[6], intersecciones[3]);
        this.vertical1 = createRoad(2, semaforos[1], intersecciones[0], semaforos[3], intersecciones[2]);
        this.vertical2 = createRoad(3, semaforos[5], intersecciones[1], semaforos[7], intersecciones[3]);
    }

    private ArrayList<Celda> createRoad(int numRoad, CeldaSemaforo semaforo1,
            CeldaInterseccion interseccion1, CeldaSemaforo semaforo2, CeldaInterseccion interseccion2) {
        
        ArrayList<Celda> road = new ArrayList<>();
        
        road.add(0, entradas[numRoad]);
        road.add(1, new CeldaConvencional());
        road.add(2, new CeldaConvencional());
        road.add(3, semaforo1);
        road.add(4, interseccion1);
        road.add(5, new CeldaConvencional());
        road.add(6, new CeldaConvencional());
        road.add(7, new CeldaConvencional());
        road.add(8, semaforo2);
        road.add(9, interseccion2);
        road.add(10, new CeldaConvencional());
        road.add(11, new CeldaConvencional());
        road.add(12, new CeldaConvencional());
        road.add(13, salidas[numRoad]);
        
        return road;
    }
    
    //Introduces los semáforos horizontales, y los verticales se cambian a consecuencia
    public void cambiarSemaforos(boolean arribaIzquierda, boolean abajoIzquierda,
            boolean arribaDerecha, boolean abajoDerecha){
        //arriba izquierda
        semaforos[0].setNextEstadoSemaforo(arribaIzquierda);
        semaforos[1].setNextEstadoSemaforo(!arribaIzquierda);
        //abajo izquierda
        semaforos[2].setNextEstadoSemaforo(abajoIzquierda);
        semaforos[3].setNextEstadoSemaforo(!abajoIzquierda);
        //arriba derecha
        semaforos[4].setNextEstadoSemaforo(arribaDerecha);
        semaforos[5].setNextEstadoSemaforo(!arribaDerecha);
        //abajo derecha
        semaforos[6].setNextEstadoSemaforo(abajoDerecha);
        semaforos[7].setNextEstadoSemaforo(!abajoDerecha);
    }
    
    public void addCoches(int numCoches){
        for (CeldaEntrada entrada : entradas) {
            entrada.incrementarCola(numCoches);
        }
    }
    
    public void avanzarTurno(){
        //1.-generar estado siguiente de cada ArrayList
        //generarNextEstado(horizontal1);
        //generarNextEstado(horizontal2);
        generarNextEstado(vertical1);
        //generarNextEstado(vertical2);
        //2.-aplicarlo
        //aplyNextEstado(horizontal1);
        //aplyNextEstado(horizontal2);
        aplyNextEstado(vertical1);
       // aplyNextEstado(vertical2);
    }

    private void generarNextEstado(ArrayList<Celda> road) {
        //entrada
        CeldaEntrada entrada = (CeldaEntrada) road.get(0);
        Celda siguiente = road.get(1);
        if (!siguiente.tieneCoche() && entrada.tieneCoche())
            entrada.setNextEstado(true);
        else
            entrada.setNextEstado(false);
        
        //3 celdas anteriores a la interseccion y postInterseccion x2
        for (int i = 0; i < 2; i++) {
            //dos primeras celdas
            int indexExtra = 1+i*5;
            for (int j = indexExtra; j < indexExtra + 2; j++) {
                if (road.get(j-1).tieneCoche() //prevCelda
                        || road.get(j).tieneCoche() && road.get(j+1).tieneCoche() //actual & nextCelda
                        )
                    road.get(j).setNextEstado(true);
                else
                    road.get(j).setNextEstado(false);
            }
            
            //semaforo e interseccion
            CeldaSemaforo semaforo = (CeldaSemaforo)(road.get(indexExtra+2));
            CeldaInterseccion interseccion = (CeldaInterseccion) (road.get(indexExtra+3));
            if (semaforo.getEstadoSemaforo()){
                //semaforo
                if (road.get(indexExtra+1).tieneCoche()  //prevCelda
                        || semaforo.tieneCoche() && interseccion.tieneCoche() //semaforo & nextCelda
                        )
                    road.get(indexExtra+2).setNextEstado(true);
                else
                    road.get(indexExtra+2).setNextEstado(false);
                //interseccion
                if (semaforo.tieneCoche()){
                    road.get(indexExtra+3).setNextEstado(true);
                    if (!interseccion.tieneCoche())
                        ((CeldaInterseccion)(road.get(indexExtra+3))).setNextDirection(semaforo.getDirection());
                }
                else if (semaforo.getDirection().equals(interseccion.getDirection())){
                    if (road.get(indexExtra+4).tieneCoche()
                            && interseccion.tieneCoche()
                            )
                        road.get(indexExtra+3).setNextEstado(true);
                    else
                        road.get(indexExtra+3).setNextEstado(false);
                }
            }else if (road.get(indexExtra+1).tieneCoche()  //prevCelda
                    || semaforo.tieneCoche() //semaforo & nextCelda
                    )
                road.get(indexExtra+2).setNextEstado(true);
            else
                road.get(indexExtra+2).setNextEstado(false);
            
            //postInterseccion COMPROBAR == direction?
            if (semaforo.getDirection().equals(interseccion.getDirection()) && interseccion.tieneCoche() //prevCelda
                    || road.get(indexExtra+4).tieneCoche() && road.get(indexExtra+5).tieneCoche() //actual & nextCelda
                    )
                road.get(indexExtra+4).setNextEstado(true);
            else
                road.get(indexExtra+4).setNextEstado(false);
        }
        
        //2 celdas convencionales
        for (int j = 11; j < 13; j++) {
            if (road.get(j-1).tieneCoche() //prevCelda
                    || road.get(j).tieneCoche() && road.get(j+1).tieneCoche() //actual & nextCelda
                    )
                road.get(j).setNextEstado(true);
            else
                road.get(j).setNextEstado(false);
        }
        
        //celda salida
        if (road.get(12).tieneCoche()) //prevCelda
            road.get(13).setNextEstado(true);
        else
            road.get(13).setNextEstado(false);
    }

    private void aplyNextEstado(ArrayList<Celda> road) {
        for (Celda cell : road) {
            cell.applyNextEstado();
        }
    }
    
    public void imprimirRoad(int road){
        ArrayList<Celda> imprimir;
        switch (road){
            case 1: imprimir = horizontal1;
                break;
            case 2: imprimir = horizontal2;
                break;
            case 3: imprimir = vertical1;
                break;
            default: imprimir = vertical2;
                break;
        }
        for (Celda celda : imprimir) {
            System.out.print(celda);
            System.out.print(" ");
        }
        //para semaforos luego
        imprimir.get(0);
        imprimir.get(1);
        imprimir.get(2);
        imprimir.get(3);
        imprimir.get(4);
        imprimir.get(5);
        imprimir.get(6);
        imprimir.get(7);
        imprimir.get(8);
        imprimir.get(9);
        imprimir.get(10);
        imprimir.get(11);
        imprimir.get(12);
        imprimir.get(13);
        System.out.println();
    }
}