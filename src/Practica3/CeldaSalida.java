package Practica3;

public class CeldaSalida implements Celda{
    private int cochesSalientes;
    private boolean nextEstadoRecibeCoche;

    public CeldaSalida() {
        nextEstadoRecibeCoche = false;
        cochesSalientes = 0;
    }
    
    @Override
    public void applyNextEstado(){
        if (nextEstadoRecibeCoche){
            cochesSalientes++;
        }
        nextEstadoRecibeCoche = false;
    }
    
    public int getCochesSalientes(){
        return cochesSalientes;
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
        return Integer.toString(cochesSalientes);
    }
}