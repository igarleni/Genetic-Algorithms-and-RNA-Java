package Practica3;

public class CeldaSalida implements Celda{
    private int cochesSalientes;
    private int entraCoche;

    public CeldaSalida() {
        entraCoche = 0;
        cochesSalientes = 0;
    }
    
    @Override
    public int getCoche(){
        return 0;
    }
    
    @Override
    public void applyNextEstado(){
        if (entraCoche == 1){
            cochesSalientes++;
        }
    }
    
    public int getCochesSalientes(){
        return cochesSalientes;
    }

    @Override
    public char getTipo() {
        return 's';
    }

    @Override
    public void setNextEstado(int entraCoche) {
        this.entraCoche = entraCoche;
    }
    
    @Override
    public String toString(){
        return Integer.toString(cochesSalientes);
    }
}