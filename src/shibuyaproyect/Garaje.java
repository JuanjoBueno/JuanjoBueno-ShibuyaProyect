package shibuyaproyect;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Garaje implements Runnable{
    
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
    
    public void salida(){
        
    }

    @Override
    public void run() {
        while (!garaje.isEmpty()){
           salida();
           while (garaje.isEmpty()){
               try {
                   Thread.sleep(2000);
               } catch (InterruptedException ex) {
                   Logger.getLogger(Garaje.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
        }
    }
    
}
