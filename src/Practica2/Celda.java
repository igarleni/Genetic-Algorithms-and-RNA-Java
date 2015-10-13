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
public class Celda {
    protected boolean libre;
    private static int idSeed = 0;
    private final int id;
    protected char tipo;

    public Celda() {
        this.libre = true;
        idSeed++;
        id = idSeed;
        tipo = 'c';
    }

    public int getId() {
        return id;
    }

    public char getTipo() {
        return tipo;
    }

    public boolean isLibre() {
        return libre;
    }

    public void setLibre() {
        libre = true;
    }
    
    public void setOcupado(){
        libre = false;
    }
    
    
}
