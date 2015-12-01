package Practica2;

public class CeldaSemaforo extends Celda{
    private boolean estadoSemaforo;
    private boolean nextEstadoSemaforo;
    private final String direction;

    public String getDirection() {
        return direction;
    }
    
    public CeldaSemaforo(String direction) {
        super();
        estadoSemaforo = false;
        tipo = 'x';
        this.direction = direction;
    }
    
    /*
    * Verde = true, rojo = false
    */
    
    public boolean getEstadoSemaforo(){
        return estadoSemaforo;
    }
    
    public void setNextEstado(boolean nextEstadoTieneCoche, boolean nextEstadoSemaforo){
        this.nextEstadoTieneCoche = nextEstadoTieneCoche;
        this.nextEstadoSemaforo = nextEstadoSemaforo;
    }

    public void applyNextEstado(){
        super.applyNextEstado();
        estadoSemaforo = nextEstadoSemaforo;
    }
}
