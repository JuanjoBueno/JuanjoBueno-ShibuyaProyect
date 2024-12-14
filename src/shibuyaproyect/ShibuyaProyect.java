package shibuyaproyect;

public class ShibuyaProyect {

    public static void main(String[] args) {
        // Crear garajes
        Garaje garaje1 = new Garaje("Garaje 1");
        Garaje garaje2 = new Garaje("Garaje 2");
        Garaje garaje3 = new Garaje("Garaje 3");
        Garaje garaje4 = new Garaje("Garaje 4");

        // Crear instancia del controlador de semáforos
        Semaforo controlador = new Semaforo();

        // Crear hilos para los semáforos
        Thread[] semaforos = new Thread[8];
        semaforos[0] = new Thread(controlador.new Semaforo(garaje1, garaje2, null, 0));
        semaforos[1] = new Thread(controlador.new Semaforo(garaje1, garaje2, garaje3, 1));
        semaforos[2] = new Thread(controlador.new Semaforo(garaje2, garaje3, null, 2));
        semaforos[3] = new Thread(controlador.new Semaforo(garaje2, garaje3, garaje4, 3));
        semaforos[4] = new Thread(controlador.new Semaforo(garaje3, garaje4, null, 4));
        semaforos[5] = new Thread(controlador.new Semaforo(garaje3, garaje4, garaje1, 5));
        semaforos[6] = new Thread(controlador.new Semaforo(garaje4, garaje1, null, 6));
        semaforos[7] = new Thread(controlador.new Semaforo(garaje4, garaje1, garaje2, 7));

        // Crear hilos para los garajes
        Thread garajeHilo1 = new Thread(garaje1);
        Thread garajeHilo2 = new Thread(garaje2);
        Thread garajeHilo3 = new Thread(garaje3);
        Thread garajeHilo4 = new Thread(garaje4);

        // Iniciar los hilos de los garajes
        garajeHilo1.start();
        garajeHilo2.start();
        garajeHilo3.start();
        garajeHilo4.start();

        // Iniciar los hilos de los semáforos
        for (Thread semaforo : semaforos) {
            semaforo.start();
        }

        // Iniciar el hilo del controlador
        Thread controladorHilo = new Thread(controlador.new Controlador());
        controladorHilo.start();
    }

}
