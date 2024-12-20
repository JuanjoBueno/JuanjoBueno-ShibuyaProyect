package shibuyaproyect;

public class Coche {

        private final String nombre;
        private int destino;

    public Coche(String nombre) {
        this.nombre = nombre;
        this.destino = destino; // Destino del coche (izquierda = 0, recto = 1 o derecha = 2).
    }

    public String getNombre() {
        return nombre;
    }
    

    public int getDestino() {
        return destino;
    }

    public void setDestino(int destino) {
        this.destino = destino;
    }
    
    @Override
    public String toString() {
        return nombre;
    }
}
    
    


