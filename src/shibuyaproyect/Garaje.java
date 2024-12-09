package shibuyaproyect;

import java.util.concurrent.LinkedBlockingQueue;

public class Garaje{

    private final int cochesIniciales = 20;
    private final String nombre;
    private final LinkedBlockingQueue<Coche> garaje;
    private final LinkedBlockingQueue<Coche> semaforoCarrilIzq;
    private final LinkedBlockingQueue<Coche> semaforoCarrilDerch;

    public Garaje(String nombre) {
        this.nombre = nombre;
        this.garaje = new LinkedBlockingQueue<>();
        this.semaforoCarrilIzq = new LinkedBlockingQueue<>();
        this.semaforoCarrilDerch = new LinkedBlockingQueue<>();
        for (int i = 0; i < cochesIniciales; i++) {
            agregarCoche(new Coche(nombre + (i+1)));          
        }
    }

    public void agregarCoche(Coche coche) {
        garaje.add(coche);
    }

    public Coche retirarCoche() throws InterruptedException {
        return garaje.take();
    }

    public void recibirCoche(Coche coche) throws InterruptedException {
        garaje.put(coche);
    }
    
    public int cantidadCoches() {
        return garaje.size();
    }
    
    private void distribuirCoches(){
        while (!garaje.isEmpty()){
            
        }
    }

    @Override
    public String toString() {
        return nombre;
    }   

}
