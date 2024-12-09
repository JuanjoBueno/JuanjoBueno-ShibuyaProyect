package shibuyaproyect;

import java.util.Random;

public class Coche {

        private final String nombre;
        private int destino;

    public Coche(String nombre) {
        this.nombre = nombre;
        this.destino = destino;
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
    
    


