package shibuyaproyect;

public class Coche {

        private final String nombre;
        private int destino;

    public Coche(String nombre) {
        this.nombre = nombre;
        this.destino = destino; // Destino del coche (izquierda = 1, recto = 2 o derecha = 3).
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
    
    


