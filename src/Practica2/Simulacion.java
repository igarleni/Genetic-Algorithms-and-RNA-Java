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
         * 2.- Actualizar estado semáforos (con intersecciones tambien)
         * 3.- avanzar turno
         * 5.- volver a 1/3 hasta fin de cromosoma
         */
        Tablero tablero = new Tablero();
        tablero.addCoches(1);
        boolean continuar = true;
        while(continuar){
            tablero.imprimirRoad(3);
            System.out.println("Continuar? y/n");
            Scanner sc = new Scanner(System.in);
            if("n".equals(sc.next()))
                continuar = false;
            else
                tablero.avanzarTurno();
        }
    }
    
}
