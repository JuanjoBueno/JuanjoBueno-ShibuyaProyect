package shibuyaproyect;

import java.util.Random;

public class Semaforo extends Thread {

    private volatile boolean running = true; //Bandera para controlar la ejecucion
    //private static CyclicBarrier barrera = new CyclicBarrier(2);

    private Garaje origen;
    private Garaje destino1, destino2;
    private String nombre;
    private boolean carrilDerch;

    public Semaforo(Garaje origen, Garaje destino1, String nombre) {
        this.origen = origen;
        this.destino1 = destino1;
        this.nombre = nombre;
        this.carrilDerch = false;
    }

    public Semaforo(Garaje origen, Garaje destino1, Garaje destino2, String nombre) {
        this.origen = origen;
        this.destino1 = destino1;
        this.destino2 = destino2;
        this.nombre = nombre;
        this.carrilDerch = true;
    }

    public void semaforoVerde() {
        Random aleatorio = new Random(System.currentTimeMillis());
        while (running) {
            try {
                Thread.sleep(500 + aleatorio.nextInt(501));
                if (!this.carrilDerch && !this.origen.getSemaforoCarrilIzq().isEmpty()) {
                    Coche coche = this.origen.retirarCoche(this.origen.getSemaforoCarrilIzq());
                    destino1.agregarCoche(coche);
                    System.out.println("Coche " + coche.getNombre() + " saliendo del semáforo " + this.nombre + " hacia el garaje " + this.destino1.getNombre());

                } else if (this.carrilDerch && !this.origen.getSemaforoCarrilDerch().isEmpty()) {
                    Coche coche = this.origen.retirarCoche(this.origen.getSemaforoCarrilDerch());
                    int destino = coche.getDestino();
                    if (destino == 1) {
                        this.destino1.agregarCoche(coche);
                        System.out.println("Coche " + coche.getNombre() + " saliendo del semáforo " + this.nombre + " hacia el garaje " + this.destino1.getNombre());

                    } else {
                        this.destino2.agregarCoche(coche);
                        System.out.println("Coche " + coche.getNombre() + " saliendo del semáforo " + this.nombre + " hacia el garaje " + this.destino2.getNombre());

                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void semaforoRojo() {
        running = false;
    }

    @Override
    public void run() {
        semaforoVerde();
    }
}
