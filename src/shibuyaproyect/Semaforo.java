package shibuyaproyect;

import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Semaforo implements Runnable {

    private String nombreSemaforo;
    private LinkedBlockingQueue<Vehiculo> carril;
    private int tiempoVerde;
    private int tiempoRojo;
    private boolean isVerde;
    private Map<String, Garaje> garajes;

    public Semaforo(String nombreSemaforo, Map<String, Garaje> garajes, boolean luz) {
        super();
        this.nombreSemaforo = nombreSemaforo;
        this.carril = new LinkedBlockingQueue<>();
        this.tiempoVerde = 10000;
        this.tiempoRojo = 10000;
        this.isVerde = luz;
        this.garajes = garajes;
    }

    public boolean getIsVerde() {
        return isVerde;
    }

    public void setIsVerde(boolean luzEncendida) {
        this.isVerde = luzEncendida;
    }

    public String getNombreSemaforo() {
        return nombreSemaforo;
    }

    public LinkedBlockingQueue<Vehiculo> getCarril() {
        return carril;
    }

    public int acumulacionCoches() {
        return carril.size();
    }

    public void addCoche(Vehiculo coche) {
        try {
            carril.put(coche);
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

    public void salida() {
        try {
            Vehiculo coche = carril.take();
            if (coche.getDireccion() == 0) {
                switch (coche.getOrigen()) {
                    case "s":
                        garajes.get("w");
                        break;
                    case "e":
                        garajes.get("s");
                        break;
                    case "n":
                        garajes.get("e");
                        break;
                    case "w":
                        garajes.get("n");
                        break;
                }
            } else if (coche.getDireccion() == 1){
                switch (coche.getOrigen()) {
                    case "s":
                        garajes.get("n");
                        break;
                    case "e":
                        garajes.get("w");
                        break;
                    case "n":
                        garajes.get("s");
                        break;
                    case "w":
                        garajes.get("e");
                        break;
                }
            } else if (coche.getDireccion() == 2){
                switch (coche.getOrigen()) {
                    case "s":
                        garajes.get("e");
                        break;
                    case "e":
                        garajes.get("n");
                        break;
                    case "n":
                        garajes.get("w");
                        break;
                    case "w":
                        garajes.get("s");
                        break;
                }
            } 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            if (isVerde) {
                while (!carril.isEmpty()) {
                    salida();
                }
                try {
                    Thread.sleep(tiempoVerde);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    Thread.sleep(tiempoRojo);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            isVerde = !isVerde;
        }

    }

}
