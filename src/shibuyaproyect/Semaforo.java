package shibuyaproyect;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Semaforo implements Runnable{
    
    private String nombreSemaforo;
    private LinkedBlockingQueue<Vehiculo> carril;
    private int tiempoVerde;
    private int tiempoRojo;
    boolean isVerde;
    
    public Semaforo (String nombreSemaforo){
        super();
        this.nombreSemaforo = nombreSemaforo;
        this.carril = new LinkedBlockingQueue<>();
        this.tiempoVerde = 10000;
        this.tiempoRojo = 10000;
        this.isVerde = true;
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

    public int acumulacionCoches(LinkedBlockingQueue<Vehiculo> getCarril){
        return carril.size();
    }
    
    public void addCoche(Vehiculo coche){
        try {
            this.carril.put(coche);
        } catch (InterruptedException ex) {
            Logger.getLogger(Semaforo.class.getName()).log(Level.SEVERE, null, ex);
        }
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
                while (getCarril().isEmpty()){
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Semaforo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                salida();
            }
        }
        
    }
    
}
