package shibuyaproyect;

import java.util.concurrent.LinkedBlockingQueue;


public class Semaforo implements Runnable{
    
    private String nombreSemaforo;
    private LinkedBlockingQueue<Vehiculo> carril;
    private int tiempoVerde;
    private int tiempoRojo;
    boolean isVerde;
    
    public Semaforo (String nombreSemaforo, LinkedBlockingQueue<Vehiculo> carril, int tiempoVerde, int tiempoRojo, boolean isVerde){
        this.nombreSemaforo = nombreSemaforo;
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

    public String getNombreSemaforo() {
        return nombreSemaforo;
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
        try {
            Vehiculo coche = getCarril().take();
            if(coche.getDireccion() == 0){
                switch (coche.getOrigen()){
                    case "s":
                    
                }
            }
        } catch (InterruptedException e) {            
            e.printStackTrace();
        }
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
