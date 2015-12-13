package Practica2;

public class CeldaSalida implements Celda{
    private static int nCochesSalientes=0;
    private boolean nextEstadoRecibeCoche;

    public CeldaSalida() {
        nextEstadoRecibeCoche = false;
    }
    
    @Override
    public void applyNextEstado(){
        if (nextEstadoRecibeCoche)
            nCochesSalientes++;
        nextEstadoRecibeCoche = false;
    }
    
    public int getCochesSalientes(){
        return nCochesSalientes;
    }

    @Override
    public char getTipo() {
        return 's';
    }

    @Override
    public boolean tieneCoche() {
        return false;
    }

    @Override
    public void setNextEstado(boolean nextEstadoRecibeCoche) {
        this.nextEstadoRecibeCoche = nextEstadoRecibeCoche;
    }
    
    @Override
    public String toString(){
        if (tieneCoche())
            return "1";
        else
            return "0";
    }
}