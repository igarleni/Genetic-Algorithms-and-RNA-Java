/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Practica3;

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
    
    private final ArrayList<Integer> coches;

    public Tablero() {
        coches = new ArrayList<>();
        coches.add(0); //referencia al coche 0, que es la celda vacia
        
        entradas = new CeldaEntrada[]{
            new CeldaEntrada(), new CeldaEntrada(),
            new CeldaEntrada(), new CeldaEntrada() };
        semaforos = new CeldaSemaforo[] {
            new CeldaSemaforo(coches,"Horizontal"), new CeldaSemaforo(coches,"Vertical"), //0-1 semaforos arriba izquierda
            new CeldaSemaforo(coches,"Horizontal"), new CeldaSemaforo(coches,"Vertical"), //2-3 semaforos abajo izquierda
            new CeldaSemaforo(coches,"Horizontal"), new CeldaSemaforo(coches,"Vertical"), //4-5 semaforos arriba derecha
            new CeldaSemaforo(coches,"Horizontal"), new CeldaSemaforo(coches,"Vertical") }; //6-7 semaforos abajo derecha
        //pares+0 horizontal, impares vertical
        
        intersecciones = new CeldaInterseccion[]{
            new CeldaInterseccion(coches), new CeldaInterseccion(coches),
            new CeldaInterseccion(coches), new CeldaInterseccion(coches) };
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
        road.add(1, new CeldaConvencional(coches));
        road.add(2, new CeldaConvencional(coches));
        road.add(3, semaforo1);
        road.add(4, interseccion1);
        road.add(5, new CeldaConvencional(coches));
        road.add(6, new CeldaConvencional(coches));
        road.add(7, new CeldaConvencional(coches));
        road.add(8, semaforo2);
        road.add(9, interseccion2);
        road.add(10, new CeldaConvencional(coches));
        road.add(11, new CeldaConvencional(coches));
        road.add(12, new CeldaConvencional(coches));
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
    
    public float getMediaAceleracion(){
        float resultado = 0;
        //coches en el tablero
        for (int i = 1; i < horizontal1.size(); i++) {
            Celda celda = horizontal1.get(i);
            if (celda.getCoche() != 0){
                float aux = coches.get(celda.getCoche());
                aux = aux/(i*2+1);
                resultado += aux;
                coches.set(celda.getCoche(), 0);
            }
        }
        for (int i = 1; i < horizontal2.size(); i++) {
            Celda celda = horizontal2.get(i);
            if (celda.getCoche() != 0){
                float aux = coches.get(celda.getCoche());
                aux = aux/(i*2+1);
                resultado += aux;
                coches.set(celda.getCoche(), 0);
            }
        }
        for (int i = 1; i < vertical1.size(); i++) {
            Celda celda = vertical1.get(i);
            if (celda.getCoche() != 0 && celda.getTipo() != 'i'){
                float aux = coches.get(celda.getCoche());
                aux = aux/(i*2+1);
                resultado += aux;
                coches.set(celda.getCoche(), 0);
            }
        }
        for (int i = 1; i < vertical2.size(); i++) {
            Celda celda = vertical2.get(i);
            if (celda.getCoche() != 0 && celda.getTipo() != 'i'){
                float aux = coches.get(celda.getCoche());
                aux = aux/(i*2+1);
                resultado += aux;
                coches.add(celda.getCoche(), 0);
            }
        }
        
        //resto de coches
        for (Integer aceleracionCoche : coches) {
            resultado += aceleracionCoche/25;
        }
        //media final
        resultado /= coches.size();
        return resultado;
    }
    
    public void avanzarTurno(){
        //1.-generar estado siguiente de cada ArrayList
        generarNextEstado(horizontal1);
        generarNextEstado(horizontal2);
        generarNextEstado(vertical1);
        generarNextEstado(vertical2);
        //2.-aplicarlo
        aplyNextEstado(horizontal1);
        aplyNextEstado(horizontal2);
        aplyNextEstado(vertical1);
        aplyNextEstado(vertical2);
    }

    private void generarNextEstado(ArrayList<Celda> road) {
        //entrada
        CeldaEntrada entrada = (CeldaEntrada)road.get(0);
        Celda siguiente = road.get(1);
        if (siguiente.getCoche()==0 && entrada.getCola()>0){
            coches.add(1);
            entrada.setNextEstado(coches.size()-1);
        }
        else
            entrada.setNextEstado(0);
        
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
                if (interseccion.getCoche()!=0){
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
                if (semaforo.getCoche()!=0)
                    semaforo.setNextEstado(semaforo.getCoche());
                else if(road.get(indexExtra+1).getCoche()!=0)
                    semaforo.setNextEstado(road.get(indexExtra+1).getCoche());
                else
                    semaforo.setNextEstado(0);
                    
                //interseccion con coche de esta dir y semaforo rojo
                if (interseccion.getCoche()!=0 && semaforo.getDirection().equals(interseccion.getDirection())){
                    if (road.get(indexExtra+4).getCoche()!=0){
                        interseccion.setNextDirection(semaforo.getDirection());
                        interseccion.setNextEstado(interseccion.getCoche());
                    }else
                        interseccion.setNextEstado(0);
                }
                
            }
            
            //postInterseccion COMPROBAR == direction
            if (semaforo.getDirection().equals(interseccion.getDirection()))
                tratarCelda(indexExtra+4, road);
            else if (road.get(indexExtra+4).getCoche()!=0 && road.get(indexExtra+5).getCoche()!=0)
                road.get(indexExtra+4).setNextEstado(0);
        }
        
        //2 celdas convencionales
        for (int j = 11; j < 13; j++) {
            tratarCelda(j, road);
        }
        
        //celda salida
        if (road.get(12).getCoche()!=0) //prevCelda
            road.get(13).setNextEstado(1);
        else
            road.get(13).setNextEstado(0);
        
    }
    
    private void tratarCelda(int i, ArrayList<Celda> road){
        if (road.get(i).getCoche()!=0){
            if (road.get(i+1).getCoche()!=0)
                road.get(i).setNextEstado(road.get(i).getCoche());
            else
                road.get(i).setNextEstado(0);
        }else if ( road.get(i-1).getCoche()!=0)
            road.get(i).setNextEstado(road.get(i-1).getCoche());
        else
            road.get(i).setNextEstado(0);
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