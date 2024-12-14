package shibuyaproyect;

public class Semaforo implements Runnable {

    private volatile boolean running = true; //Bandera para controlar la ejecucion    

        private Garaje origen;
        private Garaje destino1, destino2;
        private int nombre;
        private boolean carrilDerch;

        public Semaforo(Garaje origen, Garaje destino1, int nombre) {
            this.origen = origen;
            this.destino1 = destino1;
            this.nombre = nombre;
            this.carrilDerch = false;
        }

        public Semaforo(Garaje origen, Garaje destino1, Garaje destino2, int nombre) {
            this.origen = origen;
            this.destino1 = destino1;
            this.destino2 = destino2;
            this.nombre = nombre;
            this.carrilDerch = true;
        }

        public void semaforoVerde() {
            while (running) {
                try {
                    if (!carrilDerch && !origen.getSemaforoCarrilIzq().isEmpty()) {
                        Coche coche = origen.retirarCoche(origen.getSemaforoCarrilIzq());
                        destino1.agregarCoche(coche);
                        System.out.println("Coche " + coche.getNombre() + " saliendo del semáforo " + this.nombre + " hacia " + destino1.getNombre());
                    } else if (carrilDerch && !origen.getSemaforoCarrilDerch().isEmpty()) {
                        Coche coche = origen.retirarCoche(origen.getSemaforoCarrilDerch());
                        int destino = coche.getDestino();
                        if (destino == 1) {
                            destino1.agregarCoche(coche);
                            System.out.println("Coche " + coche.getNombre() + " saliendo del semáforo " + this.nombre + " hacia " + destino1.getNombre());
                        } else {
                            destino2.agregarCoche(coche);
                            System.out.println("Coche " + coche.getNombre() + " saliendo del semáforo " + this.nombre + " hacia " + destino2.getNombre());
                        }
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        
        public void semaforoRojo(){
            running = false;
        }

        @Override
        public void run() {
            semaforoVerde();
        }        
}
