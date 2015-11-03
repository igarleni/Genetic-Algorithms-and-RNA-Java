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
    
    private String direction;
    private String nextDirection;

    public CeldaInterseccion(CeldaSemaforo prevCeldaHorizontal, CeldaPostInterseccion nextCeldaHorizontal, CeldaSemaforo prevCeldaVertical, CeldaPostInterseccion nextCeldaVertical) {
        super(prevCeldaHorizontal, nextCeldaHorizontal);
        direction = "Horizontal";
        this.nextCeldaHorizontal = nextCeldaHorizontal;
        this.prevCeldaHorizontal = prevCeldaHorizontal;
        this.prevCeldaVertical = prevCeldaVertical;
        this.nextCeldaVertical = nextCeldaVertical;
        
    }
    
    @Override 
    public void generarNextEstado(){
        if(prevCeldaHorizontal.getEstadoSemaforo()){
            nextCeldaVertical.generarNextEstado(); //genero el estado de la celda siguiente antes de cambiar la dirección
            prevCelda = prevCeldaHorizontal;
            nextCelda = nextCeldaHorizontal;
            nextDirection = "Horizontal";
        }else{
            nextCeldaHorizontal.generarNextEstado(); //idem de arriba
            prevCelda = prevCeldaVertical;
            nextCelda = nextCeldaVertical;
            nextDirection = "Vertical";
        }
        super.generarNextEstado();
    }
    
    public boolean tieneCoche(String direction){
        if (this.direction.equals(direction)) return super.tieneCoche();
        return false;
    }
    
    @Override
    public void applyNextState(){
        super.applyNextState();
        direction = nextDirection;
    }
}
