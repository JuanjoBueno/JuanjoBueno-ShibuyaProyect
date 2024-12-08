package shibuyaproyect;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Garaje implements Runnable {

    private String nombreGaraje;
    private LinkedBlockingQueue<Vehiculo> garajeCola;
    private Semaforo s1;
    private Semaforo s2;
    private int numeroCoches = 20;

    public Garaje(String nombreGaraje) {
        super();
        this.nombreGaraje = nombreGaraje;
        this.garajeCola = new LinkedBlockingQueue<>();
        this.s1 = s1;
        this.s2 = s2;        
        
        for (int i = 0; i < numeroCoches; i++) {
            Vehiculo coche = new Vehiculo(i+1);
            addCoche(coche);
        }
    }

    public void setS1(Semaforo s1) {
        this.s1 = s1;
    }

    public void setS2(Semaforo s2) {
        this.s2 = s2;
    }

    public int getNumeroCoches() {
        return numeroCoches;
    }

    public void setNumeroCoches(int numeroCoches) {
        this.numeroCoches = numeroCoches;
    }
    
    

    public String getNombreGaraje() {
        return nombreGaraje;
    }

    public LinkedBlockingQueue<Vehiculo> getGarajeCola() {
        return garajeCola;
    }
    
    public void addCoche(Vehiculo coche){
        try {
            this.garajeCola.put(coche);
        } catch (InterruptedException ex) {
            Logger.getLogger(Garaje.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getcapacidad() {
        return garajeCola.size();
    }

    public Semaforo getS1() {
        return s1;
    }

    public Semaforo getS2() {
        return s2;
    }

    public void salida() {
        try {
            Vehiculo coche = garajeCola.take();
            coche.setDireccion();
            coche.setOrigen(this.nombreGaraje);
            if (coche.getDireccion()==0){
                s1.addCoche(coche);
            } else {
                s2.addCoche(coche);                
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (!garajeCola.isEmpty()) {
            salida();
            try {
                Thread.sleep(2000); // Simular tiempo entre salidas
            } catch (InterruptedException ex) {
                Logger.getLogger(Garaje.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
