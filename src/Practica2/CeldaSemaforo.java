package Practica2;

public class CeldaSemaforo implements Celda{
    private boolean tieneCoche;
    private boolean nextEstadoTieneCoche;
    private boolean estadoSemaforo;
    private boolean nextEstadoSemaforo;
    private final String direction;

    public String getDirection() {
        return direction;
    }
    
    public CeldaSemaforo(String direction) {
        super();
        nextEstadoSemaforo = estadoSemaforo = true;
        this.direction = direction;
    }
    
    /*
    * Verde = true, rojo = false
    */
    
    public boolean getEstadoSemaforo(){
        return estadoSemaforo;
    }
    
    public void setNextEstadoSemaforo(boolean nextEstadoSemaforo){
        this.nextEstadoSemaforo = nextEstadoSemaforo;
    }

    @Override
    public void applyNextEstado(){
        tieneCoche = nextEstadoTieneCoche;
        nextEstadoTieneCoche = false;
        estadoSemaforo = nextEstadoSemaforo;
    }

    @Override
    public char getTipo() {
        return 's';
    }

    @Override
    public boolean tieneCoche() {
        return tieneCoche;
    }

    @Override
    public void setNextEstado(boolean nextEstadoTieneCoche) {
        this.nextEstadoTieneCoche = nextEstadoTieneCoche;
    }
    
    @Override
    public String toString(){
        if (tieneCoche())
            return "1";
        else
            return "0";
    }
}
