/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Practica2;

/**
 *
 * @author usuario
 */
public class Cromosoma implements Comparable{
    private final boolean[] cromosoma;
    private final float fitness;

    public Cromosoma(boolean[] cromosoma) {
        this.cromosoma = cromosoma;
        this.fitness = calcularFitness();
    }

    public float getFitness() {
        return fitness;
    }

    private float calcularFitness(){
        Tablero tablero = new Tablero();
        //Simulacion
        int cochesEntrantes = 0;
        for (int x = 0; x < 60; x++){
            for (int i = 0; i < cromosoma.length; i+=4) {
                tablero.cambiarSemaforos(cromosoma[i], cromosoma[i+1], cromosoma[i+2], cromosoma[i+3]);
                for (int j = 0; j < 10; j++) {
                    tablero.avanzarTurno();
                    if (j==4){
                        tablero.addCoches(1);
                        cochesEntrantes = cochesEntrantes + 4;
                    }
                }
            }
        }
        float resultado = (float)CeldaSalida.getTotalCochesSalientes();
        resultado /= cochesEntrantes;
        CeldaSalida.setTotalCochesSalientes(0);
        return resultado;
    }

    public boolean[] getCromosoma() {
        return cromosoma;
    }

    @Override
    public int compareTo(Object cromosoma) {
        if (((Cromosoma)cromosoma).getFitness() < fitness)
            return 1;
        else
            return -1;
    }
    
}
