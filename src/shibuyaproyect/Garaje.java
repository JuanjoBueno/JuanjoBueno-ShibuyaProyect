package shibuyaproyect;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Garaje implements Runnable {

    private String nombreGaraje;
    private LinkedBlockingQueue<Vehiculo> garaje;
    private Semaforo s1;
    private Semaforo s2;

    public Garaje(String nombre, LinkedBlockingQueue<Vehiculo> garaje, Semaforo s1, Semaforo s2) {
        this.nombreGaraje = nombre;
        this.garaje = garaje;
        this.s1 = s1;
        this.s2 = s2;
    }

    public String getNombreGaraje() {
        return nombreGaraje;
    }

    public LinkedBlockingQueue<Vehiculo> getGaraje() {
        return garaje;
    }

    public int getcapacidad(LinkedBlockingQueue<Vehiculo> garaje) {
        return garaje.size();
    }

    public Semaforo getS1() {
        return s1;
    }

    public Semaforo getS2() {
        return s2;
    }

    public void salida() {
        try {
            Vehiculo coche = getGaraje().take();
            coche.setDireccion();
            coche.setOrigen(this.nombreGaraje);
            if (coche.getDireccion()==0){
                s1.getCarril().put(coche);
            } else {
                s2.getCarril().put(coche);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (!garaje.isEmpty()) {
            salida();
            while (garaje.isEmpty()) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Garaje.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
