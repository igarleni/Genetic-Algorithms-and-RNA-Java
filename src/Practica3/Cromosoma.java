/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Practica3;

/**
 *
 * @author usuario
 */
public class Cromosoma implements Comparable{
    private final boolean[] cromosoma; //length --> 4x12 = 48
    private final float fitnessSemaforos;
    private final float

    public Cromosoma(boolean[] cromosoma) {
        this.cromosoma = cromosoma;
        this.fitnessSemaforos = calcularFitnessSemaforos();
    }

    public float getFitnessSemaforos() {
        return fitnessSemaforos;
    }

    private float calcularFitnessSemaforos(){
        Tablero tablero = new Tablero();
        //Simulacion
        int cochesEntrantes = 0;
        for (int x = 0; x < 60; x++){
            for (int i = 0; i < cromosoma.length; i+=4) {
                tablero.cambiarSemaforos(cromosoma[i], cromosoma[i+1], cromosoma[i+2], cromosoma[i+3]);
                for (int j = 0; j < 10; j++) {
                    if (j==4){
                        tablero.addCoches(1);
                        cochesEntrantes = cochesEntrantes + 4;
                    }
                    tablero.avanzarTurno();
                }
            }
        }
        float resultado = tablero.getCochesSalientes();
        resultado /= cochesEntrantes;
        return resultado;
    }

    public boolean[] getCromosoma() {
        return cromosoma;
    }

    @Override
    public int compareTo(Object cromosoma) {
        if(((Cromosoma)cromosoma).getFitnessSemaforos() == fitnessSemaforos)
            return 0;
        else if (((Cromosoma)cromosoma).getFitnessSemaforos() > fitnessSemaforos)
            return 1;
        else
            return -1;
    }
    
}
