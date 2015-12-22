package Practica3;

public class CeldaSemaforo implements Celda{
    private boolean tieneCoche;
    private boolean nextEstadoTieneCoche;
    private boolean estadoSemaforo;
    private final String direction;
    private boolean estadoCarretera;
    private int aceleraciones;

    public String getDirection() {
        return direction;
    }
    
    public CeldaSemaforo(String direction) {
        super();
        estadoSemaforo = true;
        this.direction = direction;
        estadoCarretera = true;
        aceleraciones = 0;
    }
    
    /*
    * Verde = true, rojo = false
    */
    
    public boolean getEstadoSemaforo(){
        return estadoSemaforo;
    }
    
    public void setEstadoSemaforo(boolean estadoSemaforo){
        this.estadoSemaforo = estadoSemaforo;
    }

    @Override
    public void applyNextEstado(){
        tieneCoche = nextEstadoTieneCoche;
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

    @Override
    public int getAceleraciones() {
        return aceleraciones;
    }
}
