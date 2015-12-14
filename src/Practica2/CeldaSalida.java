package Practica2;

public class CeldaSalida implements Celda{
    private static int totalCochesSalientes=0;
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
            totalCochesSalientes++;
        }
        nextEstadoRecibeCoche = false;
    }
    
    public int getCochesSalientes(){
        return cochesSalientes;
    }

    public static int getTotalCochesSalientes(){
        return totalCochesSalientes;
    }
    public static void setTotalCochesSalientes(int totalCochesSalientes){
        CeldaSalida.totalCochesSalientes = totalCochesSalientes;
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