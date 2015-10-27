/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practica2;

/**
 *
 * @author Italo
 */
public class CeldaInterseccion extends Celda{
    private final CeldaPostInterseccion nextCeldaHorizontal;
    private final CeldaSemaforo prevCeldaHorizontal;
    private final CeldaPostInterseccion nextCeldaVertical;
    private final CeldaSemaforo prevCeldaVertical;

    public CeldaInterseccion(CeldaSemaforo prevCeldaHorizontal, CeldaPostInterseccion nextCeldaHorizontal, CeldaSemaforo prevCeldaVertical, CeldaPostInterseccion nextCeldaVertical) {
        super(prevCeldaHorizontal, nextCeldaHorizontal);
        this.nextCeldaHorizontal = nextCeldaHorizontal;
        this.prevCeldaHorizontal = prevCeldaHorizontal;
        this.prevCeldaVertical = prevCeldaVertical;
        this.nextCeldaVertical = nextCeldaVertical;
        
    }
    
    /**
     * Cuando el estado del semáforo cambia, el coche puede cambiar de direccion,
     * ya que la dirección se define por el estado actual de los semáforos, que se
     * cambia antes de calcular el siguiente estado.
     * 
     * Es decir, si hay un coche en la intersección y los estados de los semáforos cambian,
     * la dirección del coche cambia también, por lo que hay que guardar la dirección del coche.
     */
    @Override 
    public void generarNextEstado(){
        if(prevCeldaHorizontal.getEstadoSemaforo()){
            prevCelda = prevCeldaHorizontal;
            nextCelda = nextCeldaHorizontal;
        }else{ 
            prevCelda = prevCeldaVertical;
            nextCelda = nextCeldaVertical;
        }
        super.generarNextEstado();
    }
    
    public boolean tieneCoche(int id){
        if (nextCelda.getId() == id) return super.tieneCoche();
        return false;
    }
}
