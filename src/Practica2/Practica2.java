/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Practica2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author usuario
 */
public class Practica2 {

    private static int maximoGeneraciones;
    private static int tamPoblacion;
    private static float probMutacion;
    private static ArrayList<Cromosoma> poblacion;
    private static ArrayList<Cromosoma> poblacionHijo;
    
    private static float [][] datosFitness;
    
    public static void main(String[] args) {
        
        //obtenerPorConsola();
        obtenerPorCodigo();
        //testearTablero();
        //testearCromosoma();
        
        inicializarPoblacion();
        datosFitness = new float [maximoGeneraciones+1][tamPoblacion];
        int dos_tercios = (tamPoblacion*2/6)*2; //dos tercios pares
        
        for (int i = 0; i < maximoGeneraciones; i++) {
            poblacion.sort(null); //ordenamos por fitness
            System.out.println("Generacion " + i + ":= " +poblacion.get(0).getFitness());
            //guardamos datos de fitness
            for (int j = 0; j < datosFitness[i].length; j++) {
                datosFitness[i][j] = poblacion.get(j).getFitness();
                
            }
            //cortar dos tercios
            while (poblacion.size() > dos_tercios){
                poblacion.remove(dos_tercios);
            }
            
            poblacionHijo = new ArrayList<>();
            //Elitismo
            poblacionHijo.add(poblacion.get(0));
            poblacionHijo.add(poblacion.get(1));
            while (poblacionHijo.size()<tamPoblacion){
                Cromosoma[] padres;
                padres = seleccionarRuleta();
                //cromosomas = seleccionarTorneo();
                
                //cruzarTwoPoints(padres);
                cruzarUniforme(padres);
            }
            //por si se crean más de la cuenta
            while (poblacionHijo.size()>tamPoblacion){
                System.out.println("Borrado de cromosoma");
                poblacionHijo.remove(tamPoblacion);
            }
            //Mutar el numero de veces que la probabilidad dice
            for (int j = 0; j < tamPoblacion; j++) {
                if(Math.random()<probMutacion)
                    mutarBigString();
            }
            
            poblacion = new ArrayList<>(poblacionHijo);
        }
        
        poblacion.sort(null); //ordenamos por fitness
        System.out.println("Generacion " + maximoGeneraciones + ":= " +poblacion.get(0).getFitness());
        //añadimos la ultima generacion a los datos
        for (int j = 0; j < datosFitness[0].length; j++){
                datosFitness[maximoGeneraciones][j] = poblacion.get(j).getFitness();
        }
        System.out.println("Guardando en TXT...");
        guardarDatosEnTXT();
        System.out.println("Guardado y Terminado!");
    }
    
    private static void testearTablero() {
        Tablero tablero = new Tablero();
        tablero.addCoches(1);
        boolean continuar = true;
        tablero.cambiarSemaforos(true, true, true, true);
        Scanner sc = new Scanner(System.in);
        while(continuar){
            /**TESTEO AQUI**/
            tablero.imprimirTablero();
            System.out.println("Elige opcion: ");
            System.out.println("Avanzar turno --> a");
            System.out.println("Terminar --> t");
            System.out.println("Add Coches --> c");
            System.out.println("Cambiar Semaforos --> s");
            System.out.println("Total Coches Salientes --> e");
            System.out.println("Resetear --> r");
            String respuesta = sc.next();
            if("t".equals(respuesta))
                continuar = false;
            else if ("c".equals(respuesta)){
                System.out.println("Cuantos coches?");
                tablero.addCoches(sc.nextInt());
            }
            else if ("e".equals(respuesta))
                System.out.println(tablero.getCochesSalientes());
            else if ("a".equals(respuesta))
                tablero.avanzarTurno();
            else if ("r".equals(respuesta)){
                tablero = new Tablero();
                tablero.cambiarSemaforos(true, true, true, true);
            }
            else if ("s".equals(respuesta)){
                System.out.println("Semaforo: ^<");
                boolean arribaIzquierda = sc.nextBoolean();
                System.out.println("Semaforo: ,<");
                boolean abajoIzquierda = sc.nextBoolean();
                System.out.println("Semaforo: ^>");
                boolean arribaDerecha = sc.nextBoolean();
                System.out.println("Semaforo: ,>");
                boolean abajoDerecha = sc.nextBoolean();
                tablero.cambiarSemaforos(arribaIzquierda, abajoIzquierda, arribaDerecha, abajoDerecha);
            }
        }
    }
    
    private static void testearCromosoma(){
        
        inicializarPoblacion();
        poblacion.sort(null);
        System.out.println("Fin de busqueda de cromosoma, Fitness: " + poblacion.get(0).getFitness());
        
        //Cromosoma a testear
        boolean[] cromosoma = poblacion.get(0).getCromosoma();
        Tablero tablero = new Tablero();
        
        //Simulacion
        int cochesEntrantes = 0;
        for (int x = 0; x < 60; x++){
            for (int i = 0; i < cromosoma.length; i+=4) {
                tablero.cambiarSemaforos(cromosoma[i], cromosoma[i+1], cromosoma[i+2], cromosoma[i+3]);
                for (int j = 0; j < 10; j++) {
                    if (j==4 || j ==0){
                        tablero.addCoches(1);
                        cochesEntrantes = cochesEntrantes + 4;
                    }
                    tablero.avanzarTurno();
                    tablero.imprimirTablero();
                    System.out.print("Fitness: " + poblacion.get(0).getFitness());
                    System.out.print(", Coches entrantes: " + cochesEntrantes);
                    System.out.println(", Coches salientes: " + tablero.getCochesSalientes());
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException ex) {
                        System.out.println("Fallo de sueño");
                    }
                }
            }
        }
        tablero.imprimirTablero();
        float resultado = tablero.getCochesSalientes();
        System.out.println("Coches salientes: " + resultado);
        resultado /= cochesEntrantes;
        System.out.println("Fitness: " + resultado);
    }
    
    private static void obtenerPorConsola() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Numero maximo de Generaciones: ");
        maximoGeneraciones = sc.nextInt();
        
        System.out.println("Tamaño de la poblacion: ");
        tamPoblacion = sc.nextInt();
        
        System.out.println("Probabilidad de mutacion (1.0-0.0): ");
        probMutacion = sc.nextFloat();
    }

    private static void obtenerPorCodigo(){
        maximoGeneraciones = 20;
        tamPoblacion = 100;
        probMutacion =0.05f;
    }
    
    private static void inicializarPoblacion() {
        poblacion = new ArrayList<>();
        for (int i = 0; i < tamPoblacion; i++) {
            boolean[] datos = new boolean[48];
            for (int j = 0; j < datos.length; j++) {
                datos[j] = Math.random()<0.5;
            }
            poblacion.add(new Cromosoma(datos));
        }
    }

    private static Cromosoma[] seleccionarRuleta() {
        
        float probabilidades[] = calcularProbabilidades();
        Cromosoma[] resultado = new Cromosoma[2];
        
        //Seleccion del padre
        float suma = 0;
        float padre = (float) Math.random();
        for (int i = 0; i < probabilidades.length; i++) {
            suma += probabilidades[i];
            if( suma > padre){
                resultado[0] = poblacion.get(i);
                break;
            }
        }
        
        //Seleccion de la madre
        suma = 0;
        float madre = (float)Math.random();
        for (int i = 0; i < probabilidades.length; i++) {
            suma += probabilidades[i];
            if( suma > madre){
                resultado[1] = poblacion.get(i);
                break;
            }
        }
        
        return resultado;
    }

    private static float[] calcularProbabilidades() {
        //cálculo de probabilidad de seleccion dependiendo de su fitness
        float sumFitness = 0;
        for (Cromosoma cromosoma : poblacion) {
            sumFitness += cromosoma.getFitness();
        }
        
        float[] probabilidades = new float[poblacion.size()];
        for (int i = 0; i < probabilidades.length; i++) {
            probabilidades[i] = poblacion.get(i).getFitness()/sumFitness;
        }
        return probabilidades;
    }

    private static Cromosoma[] seleccionarTorneo() {
        Cromosoma[] resultado = new Cromosoma[2];
        Cromosoma[] luchadores = new Cromosoma[2];
        //PADRE
        //seleccionar dos aleatorios
        luchadores[0] = poblacion.get(Math.round((float)((tamPoblacion-1)*Math.random())));
        luchadores[1] = poblacion.get(Math.round((float)((tamPoblacion-1)*Math.random())));
        //luchar con probabilidad de ganador el peor
        if (Math.random() < 0.7)
            resultado[0] = (luchadores[0].getFitness() > luchadores[1].getFitness()) ? luchadores[0] : luchadores[1];
        else
            resultado[0] = (luchadores[0].getFitness() > luchadores[1].getFitness()) ? luchadores[0] : luchadores[1];
            
        //MADRE
        luchadores[0] = poblacion.get(Math.round((float)((tamPoblacion-1)*Math.random())));
        luchadores[1] = poblacion.get(Math.round((float)((tamPoblacion-1)*Math.random())));
        if (Math.random() < 0.7)
            resultado[1] = (luchadores[0].getFitness() > luchadores[1].getFitness()) ? luchadores[0] : luchadores[1];
        else
            resultado[1] = (luchadores[0].getFitness() > luchadores[1].getFitness()) ? luchadores[0] : luchadores[1];
        
        return resultado;
    }

    private static void cruzarTwoPoints(Cromosoma[] padres) {
        //Indices
        int indice1 = Math.round((float)(Math.random()*47));
        int indice2 = Math.round((float)(Math.random()*47));
        while (indice1 == indice2){
            indice2 = Math.round((float)(Math.random()*47));
        }
        if (indice1 >indice2){
            int aux =indice1;
            indice1 = indice2;
            indice2 = aux;
        }
        
        //Hijo
        boolean[] datosProgenitor = new boolean[48];
        boolean[] datosPadre = padres[0].getCromosoma();
        boolean[] datosMadre = padres[1].getCromosoma();
        for (int i = 0; i <indice1; i++)
            datosProgenitor[i] = datosPadre[i];
        for (int i = indice1; i <indice2; i++)
            datosProgenitor[i] = datosMadre[i];
        for (int i = indice2; i <48; i++)
            datosProgenitor[i] = datosPadre[i];
        poblacionHijo.add(new Cromosoma(datosProgenitor));
        
        //Hija
        datosProgenitor = new boolean[48];
        for (int i = 0; i <indice1; i++)
            datosProgenitor[i] = datosMadre[i];
        for (int i = indice1; i <indice2; i++)
            datosProgenitor[i] = datosPadre[i];
        for (int i = indice2; i <48; i++)
            datosProgenitor[i] = datosMadre[i];
        poblacionHijo.add(new Cromosoma(datosProgenitor));
    }

    private static void cruzarUniforme(Cromosoma[] padres) {
        boolean[] datosHijo = new boolean[48];
        boolean[] datosHija = new boolean[48];
        boolean[] datosPadre = padres[0].getCromosoma();
        boolean[] datosMadre = padres[1].getCromosoma();
        
        for (int i = 0; i < 48; i++){
            if (Math.random()<0.5){
                datosHijo[i] = datosPadre[i];
                datosHija[i] = datosMadre[i];
            }
            else{
                datosHija[i] = datosPadre[i];
                datosHijo[i] = datosMadre[i];
            }
        }
        poblacionHijo.add(new Cromosoma(datosHijo));
        poblacionHijo.add(new Cromosoma(datosHija));
    }

    private static void mutarBigString() {
        int indice = Math.round((float)((tamPoblacion-1)*Math.random()));
        Cromosoma cromosoma = poblacionHijo.get(indice);
        boolean[] datos = cromosoma.getCromosoma();
        poblacionHijo.remove(indice);
        
        indice = Math.round((float)(47*Math.random()));
        datos[indice] = !datos[indice];
        cromosoma = new Cromosoma(datos);
        poblacionHijo.add(cromosoma);
    }
    
    private static void guardarDatosEnTXT() {
        File f = new File("DatosFitness.txt");
        try{
            //Limpiamos el fichero de datos antiguos
            FileWriter w = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(w);
            bw.write("");
            bw.close();
            w.close();
            
            w = new FileWriter(f, true);
            bw = new BufferedWriter(w);
            for (int i = 0; i < datosFitness.length; i++) {
                //Generacion i
                for (int j = 0; j < datosFitness[i].length; j++) {
                    bw.write(String.valueOf(datosFitness[i][j]) + ", ");
                }
                bw.newLine();
            }
            bw.close();
            w.close();
        }catch (IOException e){
            System.out.println("Fallo en escritura!");
        };
    }
}
