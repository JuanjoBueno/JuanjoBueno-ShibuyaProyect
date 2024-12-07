package shibuyaproyect;

import java.util.concurrent.LinkedBlockingQueue;


public class Semaforo implements Runnable{
    
    private String nombre;
    private LinkedBlockingQueue<Vehiculo> carril;
    private int tiempoVerde;
    private int tiempoRojo;
    boolean isVerde;
    
    public Semaforo (String nombre, LinkedBlockingQueue<Vehiculo> carril, int tiempoVerde, int tiempoRojo, boolean isVerde){
        this.nombre = nombre;
        this.carril = carril;
        this.tiempoVerde = tiempoVerde;
        this.tiempoRojo = tiempoRojo;
        this.isVerde = isVerde;
    }

    public boolean getIsVerde(){
        return isVerde;
    }

    public void setIsVerde(boolean luzEncendida){
        this.isVerde = luzEncendida;
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
    
    public void salida(){
        
    }

    @Override
    public void run() {
        while (!getCarril().isEmpty()){
            while (isVerde == false){
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {                    
                    e.printStackTrace();
                }
            } while (isVerde == true){
                salida();
            }
        }
        
    }
    
}
