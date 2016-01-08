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
        semaforos[0].setEstadoSemaforo(arribaIzquierda);
        semaforos[1].setEstadoSemaforo(!arribaIzquierda);
        //abajo izquierda
        semaforos[2].setEstadoSemaforo(abajoIzquierda);
        semaforos[3].setEstadoSemaforo(!abajoIzquierda);
        //arriba derecha
        semaforos[4].setEstadoSemaforo(arribaDerecha);
        semaforos[5].setEstadoSemaforo(!arribaDerecha);
        //abajo derecha
        semaforos[6].setEstadoSemaforo(abajoDerecha);
        semaforos[7].setEstadoSemaforo(!abajoDerecha);
    }
    
    public void addCoches(int numCoches){
        for (CeldaEntrada entrada : entradas) {
            entrada.incrementarCola(numCoches);
        }
    }
    
    public int getCochesSalientes(){
        int resultado = 0;
        for (CeldaSalida salida : salidas) {
            resultado += salida.getCochesSalientes();
        }
        return resultado;
    }
    
    public void avanzarTurno(){
        //1.-generar estado siguiente de cada ArrayList
        generarNextEstado(horizontal1);
        generarNextEstado(horizontal2);
        generarNextEstado(vertical1);
        generarNextEstado(vertical2);
        //2.-aplicarlo
        applyNextEstado(horizontal1);
        applyNextEstado(horizontal2);
        applyNextEstado(vertical1);
        applyNextEstado(vertical2);
    }

    private void generarNextEstado(ArrayList<Celda> road) {
        //entrada
        Celda entrada = road.get(0);
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
                tratarCelda(j, road);
            }
            
            //semaforo e interseccion
            CeldaSemaforo semaforo = (CeldaSemaforo)(road.get(indexExtra+2));
            CeldaInterseccion interseccion = (CeldaInterseccion) (road.get(indexExtra+3));
            if (semaforo.getEstadoSemaforo()){
                //semaforo verde
                tratarCelda(indexExtra+2, road);
                //interseccion
                if (interseccion.tieneCoche()){
                    if(semaforo.getDirection().equals(interseccion.getDirection())){
                        interseccion.setNextDirection(semaforo.getDirection());
                        tratarCelda(indexExtra+3, road);
                    }
                }else{
                    interseccion.setNextDirection(semaforo.getDirection());
                    tratarCelda(indexExtra+3, road);
                }
                    
                    
            }else{
                //semaforo rojo
                if (semaforo.tieneCoche() || road.get(indexExtra+1).tieneCoche())
                    semaforo.setNextEstado(true);
                else
                    semaforo.setNextEstado(false);
                    
                //interseccion con coche de esta dir y semaforo rojo
                if (interseccion.tieneCoche() && semaforo.getDirection().equals(interseccion.getDirection())){
                    if (road.get(indexExtra+4).tieneCoche()){
                        interseccion.setNextDirection(semaforo.getDirection());
                        interseccion.setNextEstado(true);
                    }else
                        interseccion.setNextEstado(false);
                }
                
            }
            
            //postInterseccion COMPROBAR == direction?
            if (semaforo.getDirection().equals(interseccion.getDirection())
                    )
                tratarCelda(indexExtra+4, road);
            else if (road.get(indexExtra+4).tieneCoche() && road.get(indexExtra+5).tieneCoche())
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
    
    private void tratarCelda(int i, ArrayList<Celda> road){
        if (road.get(i).tieneCoche()){
            if (road.get(i+1).tieneCoche())
                road.get(i).setNextEstado(true);
            else
                road.get(i).setNextEstado(false);
        }else if ( road.get(i-1).tieneCoche())
            road.get(i).setNextEstado(true);
        else
            road.get(i).setNextEstado(false);
    }
    
    private void applyNextEstado(ArrayList<Celda> road) {
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
    }
    
    public void imprimirTablero(){
        for (int i = 0; i < 4; i++){
            System.out.print("- - - - ");
            System.out.print(vertical1.get(i));
            System.out.print(" - - - - ");
            System.out.print(vertical2.get(i));
            System.out.println(" - - - - ");
        }
        imprimirRoad(1);
        System.out.println();
        for (int i = 5; i < 9; i++){
            System.out.print("- - - - ");
            System.out.print(vertical1.get(i));
            System.out.print(" - - - - ");
            System.out.print(vertical2.get(i));
            System.out.println(" - - - - ");
        }
        imprimirRoad(2);
        System.out.println();
        for (int i = 10; i < 14; i++){
            System.out.print("- - - - ");
            System.out.print(vertical1.get(i));
            System.out.print(" - - - - ");
            System.out.print(vertical2.get(i));
            System.out.println(" - - - - ");
        }
        
    }

}