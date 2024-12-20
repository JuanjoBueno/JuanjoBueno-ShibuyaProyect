package shibuyaproyect;

import java.util.Random;

// Clase que representa un semáforo que regula el paso de coches entre los garajes.
public class Semaforo extends Thread {

    private volatile boolean running = true; //Bandera para controlar la ejecucion del semáforo    
    private Garaje origen; // Garaje de origen de los coches.
    private Garaje destino1, destino2; // Garajes de destino para los coches.
    private String nombre; // Nombre del semáforo.
    private boolean carrilDerch; // Determina si es un semáforo para el carril derecho.

    /**
     * Constructor para semáforo con un solo destino.
     * @param origen
     * @param destino1
     * @param nombre 
     */
    public Semaforo(Garaje origen, Garaje destino1, String nombre) {
        this.origen = origen;
        this.destino1 = destino1;
        this.nombre = nombre;
        this.carrilDerch = false;
    }

    /**
     * Constructor para semáforo con dos destinos (para el carril derecho).
     * @param origen
     * @param destino1
     * @param destino2
     * @param nombre 
     */
    public Semaforo(Garaje origen, Garaje destino1, Garaje destino2, String nombre) {
        this.origen = origen;
        this.destino1 = destino1;
        this.destino2 = destino2;
        this.nombre = nombre;
        this.carrilDerch = true;
    }

    /**
     * Método que simula el comportamiento del semáforo en verde.
     */
    public void semaforoVerde() {
        Random aleatorio = new Random(System.currentTimeMillis());
        while (running) {
            try {
                Thread.sleep(500 + aleatorio.nextInt(501)); // Tiempo aleatorio de espera.
                // Si es para el carril izquierdo, retira el coche y lo manda al destino.
                if (!this.carrilDerch && !this.origen.getSemaforoCarrilIzq().isEmpty()) {
                    Coche coche = this.origen.retirarCoche(this.origen.getSemaforoCarrilIzq());
                    destino1.agregarCoche(coche);
                    System.out.println("Coche " + coche.getNombre() + " saliendo del semáforo " + this.nombre + " hacia el garaje " + this.destino1.getNombre());

                    // Si es para el carril derecho, realiza la misma operación pero con dos destinos posibles.
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

    /**
     * Método para detener el semáforo (poniéndolo en rojo).
     */
    public void semaforoRojo() {
        running = false;
    }

    // Run que ejecuta el método semaforoVerde()
    @Override
    public void run() {
        semaforoVerde();
    }
}
