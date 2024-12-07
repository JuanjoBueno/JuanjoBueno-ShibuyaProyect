package shibuyaproyect;

import java.util.concurrent.LinkedBlockingQueue;


public class Semaforo implements Runnable{
    
    private String nombre;
    private LinkedBlockingQueue<Vehiculo> carril;
    private int tiempoVerde;
    private int tiempoRojo;
    
    public Semaforo (String nombre, LinkedBlockingQueue<Vehiculo> carril, int tiempoVerde, int tiempoRojo){
        this.nombre = nombre;
        this.carril = carril;
        this.tiempoVerde = tiempoVerde;
        this.tiempoRojo = tiempoRojo;
    }

    public String getNombre() {
        return nombre;
    }   

    public LinkedBlockingQueue<Vehiculo> getCarril() {
        return carril;
    }

    public int cargaCarril(LinkedBlockingQueue<Vehiculo> getCarril){
        return carril.size();
    }

    public int getTiempoVerde() {
        return tiempoVerde;
    }

    public void setTiempoVerde(int tiempoVerde) {
        this.tiempoVerde = tiempoVerde;
    }

    public int getTiempoRojo() {
        return tiempoRojo;
    }

    public void setTiempoRojo(int tiempoRojo) {
        this.tiempoRojo = tiempoRojo;
    }    

    @Override
    public void run() {
        
    }
    
}
