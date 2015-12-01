package Practica2;

public class CeldaSalida extends Celda{
    private static int nCochesSalientes=0;
    
    public CeldaSalida() {
        super();
        tipo = 's';
    }
    
    @Override
    public void applyNextEstado(){
        if (super.nextEstadoTieneCoche)
            nCochesSalientes++;
        super.applyNextEstado();
    }
    
    public int getCochesSalientes(){
        return nCochesSalientes;
    }
    
}