package shibuyaproyect;

import java.util.concurrent.LinkedBlockingQueue;


public class Garaje {
    
    private int id;
    private LinkedBlockingQueue<Vehiculo> garaje;

    public Garaje(int id, LinkedBlockingQueue<Vehiculo> garaje) {
        this.id = id;
        this.garaje = garaje;
    }

    public int getId() {
        return id;
    }

    public LinkedBlockingQueue<Vehiculo> getGaraje() {
        return garaje;
    }
    
    public int getcapacidad(LinkedBlockingQueue<Vehiculo> garaje){
        return garaje.size();
    }
    
}
