package shibuyaproyect;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Garaje implements Runnable {

    private String nombreGaraje;
    private LinkedBlockingQueue<Vehiculo> garajeCola;
    private Semaforo s1;
    private Semaforo s2;

    public Garaje(String nombreGaraje, boolean semaforo) {
        super();
        this.nombreGaraje = nombreGaraje;
        this.garajeCola = new LinkedBlockingQueue<>();
        this.s1 = new Semaforo("s1");
        this.s2 = new Semaforo("s2");
        this.s1.setIsVerde(semaforo);
        this.s2.setIsVerde(semaforo);        
    }

    public String getNombreGaraje() {
        return nombreGaraje;
    }

    public LinkedBlockingQueue<Vehiculo> getGarajeCola() {
        return garajeCola;
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
            Vehiculo coche = getGarajeCola().take();
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
        while (!garajeCola.isEmpty()) {
            salida();
            while (garajeCola.isEmpty()) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Garaje.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
