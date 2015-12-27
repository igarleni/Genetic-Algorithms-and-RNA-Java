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
    private final float fitnessSemaforo;
    private final float fitnessAceleracion;
    private final float fintessTotal;

    public Cromosoma(boolean[] cromosoma) {
        this.cromosoma = cromosoma;
        float[] fitness = calcularFitness();
        this.fitnessSemaforo = fitness[0];
        this.fitnessAceleracion = fitness[1];
        this.fintessTotal = fitnessSemaforo*0.5f + fitnessAceleracion*0.5f;
    }

    public float getFitness() {
        return fintessTotal;
    }
    
    public float getFitnessSemaforo() {
        return fitnessSemaforo;
    }

    public float getFitnessAceleracion() {
        return fitnessAceleracion;
    }
    
    private float[] calcularFitness(){
        Tablero tablero = new Tablero();
        //Simulacion
        int cochesEntrantes = 0;
        for (int x = 0; x < 60; x++){
            for (int i = 0; i < cromosoma.length; i+=4) {
                tablero.cambiarSemaforos(cromosoma[i], cromosoma[i+1], cromosoma[i+2], cromosoma[i+3]);
                for (int j = 0; j < 10; j++) {
                    if (j==4 || j==0){
                        tablero.addCoches(1);
                        cochesEntrantes = cochesEntrantes + 4;
                    }
                    tablero.avanzarTurno();
                }
            }
        }
        float resultado1 = tablero.getCochesSalientes();
        resultado1 /= cochesEntrantes;
        
        float resultado2 = tablero.getAceleraciones();
        resultado2 /= 25;
        resultado2 /= cochesEntrantes;
        resultado2 = 1 - resultado2;
        
        return new float[]{resultado1, resultado2};
    }

    public boolean[] getCromosoma() {
        return cromosoma;
    }

    @Override
    public int compareTo(Object cromosoma) {
        if(((Cromosoma)cromosoma).getFitness() == fintessTotal)
            return 0;
        else if (((Cromosoma)cromosoma).getFitness() > fintessTotal)
            return 1;
        else
            return -1;
        
    }
    
}
