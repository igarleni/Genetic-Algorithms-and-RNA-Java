package Practica2;

public class CeldaSalida extends Celda{
    private static int nCochesSalientes=0;
    
    public CeldaSalida() {
        super();
        tipo = 's';
    }
    
    @Override
    protected void generarNextEstado(){
        if (tieneCoche())nCochesSalientes++ ;
        if (prevCelda.tieneCoche())
            nextEstadoAceptaCoche = nextEstadoTieneCoche = true;
        else {
            nextEstadoAceptaCoche = true;
            nextEstadoTieneCoche = false;
        }
    }
    
    @Override
    public void applyNextState(){
        generarNextEstado();
    }
    public int getCochesSalientes(){
        return nCochesSalientes;
    }
    
}