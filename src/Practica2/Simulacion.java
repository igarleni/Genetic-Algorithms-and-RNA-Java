/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Practica2;

import java.util.Scanner;

/**
 *
 * @author alumno
 */
public class Simulacion {
    public static void main(String[] args) {
        /**
         * 0.- Crear tablero
         * 1.- Leer datos de cromosoma
         * 2.- Actualizar estado semáforos para el estado siguiente, (el actual no se cambia)
         * 3.- avanzar turno
         * 5.- volver a 1/3 hasta fin de cromosoma
         */
        
        //testearPorConsola();
        
    }

    private static void testearPorConsola() {
        Tablero tablero = new Tablero();
        tablero.addCoches(1);
        boolean continuar = true;
        tablero.cambiarSemaforos(true, true, true, true);
        Scanner sc = new Scanner(System.in);
        while(continuar){
            /**TESTEO AQUI**/
            
            /****/
            tablero.imprimirTablero();
            System.out.println("Elige opcion: ");
            System.out.println("Avanzar turno --> a");
            System.out.println("Terminar --> t");
            System.out.println("Add Coches --> c");
            System.out.println("Cambiar Semaforos --> s");
            System.out.println("Resetear --> r");
            String respuesta = sc.next();
            if("t".equals(respuesta))
                continuar = false;
            else if ("c".equals(respuesta)){
                System.out.println("Cuantos coches?");
                tablero.addCoches(sc.nextInt());
            }
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
        }    }
    
}
