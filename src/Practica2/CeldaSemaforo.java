package Practica2;

public class CeldaSemaforo extends Celda{
    private boolean estadoSemaforo;
    
    public CeldaSemaforo() {
        super();
        estadoSemaforo = false;
        tipo = 'x';
    }
    
    /*
    * Verde = true, rojo = false
    */
    @Override
    protected void generarNextEstado(){
        if(estadoSemaforo){
            super.generarNextEstado();
        }else{
        boolean nextCeldaAceptaCoche = nextCelda.nextEstadoAceptaCoche();
            if (tieneCoche()){ //si hay coches en la celda
                    nextEstadoAceptaCoche = false;
                    nextEstadoTieneCoche = true;
            }else{ //si no hay coches en la celda
                if (prevCelda.tieneCoche())
                    nextEstadoAceptaCoche = nextEstadoTieneCoche = true;
                else{
                    nextEstadoAceptaCoche = true;
                    nextEstadoTieneCoche = false;
                }
            }
        }
    }
    
    public boolean getEstadoSemaforo(){
        return estadoSemaforo;
    }
    
    public void actualizarSemaforo(boolean nuevoEstado){
        this.estadoSemaforo = nuevoEstado;
    }
    
}
