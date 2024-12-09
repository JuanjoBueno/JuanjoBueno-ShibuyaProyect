package shibuyaproyect;

public class Semaforo implements Runnable {

    private Garaje origen;
    private Garaje destino1, destino2;
    private final String nombre;
    private final boolean carrilDerch;

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
        try {
            if (!carrilDerch) {
                Coche coche = origen.retirarCoche(origen.getSemaforoCarrilIzq());
                destino1.agregarCoche(coche);
                System.out.println("Coche " + coche.getNombre() + " saliendo del semaforo hacia " + destino1.getNombre());
            } else {
                Coche coche = origen.retirarCoche(origen.getSemaforoCarrilDerch());
                int destino = coche.getDestino();
                if (destino == 1){
                    destino1.agregarCoche(coche);
                    System.out.println("Coche " + coche.getNombre() + " saliendo del semaforo hacia " + destino1.getNombre());
                } else {
                    destino2.agregarCoche(coche);
                    System.out.println("Coche " + coche.getNombre() + " saliendo del semaforo hacia " + destino2.getNombre());
                }
            }
            int tiempo =  500 + (int) Math.random() * 1500;
            Thread.sleep(tiempo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        semaforoVerde();

    }
}
