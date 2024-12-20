package shibuyaproyect;

import java.util.ArrayList;
import java.util.List;

/** Clase main que se encarga de la ejecución de todos los hilos y que se
 *  muestre la informacón.
 */
public class ShibuyaProyect {

    public static void main(String[] args) {

        int tiempoVerdeCarrilIzq = 5000; // Tiempo en verde para el carril izquierdo.
        int tiempoVerdeCarrilDerch = 10000; // Tiempo en verde para el carril derecho.
        List<Semaforo> carrilIzqNS = new ArrayList<>(); // Lista de semáforos para carril izquierdo en dirección Norte-Sur.
        List<Semaforo> carrilIzqEW = new ArrayList<>(); // Lista de semáforos para carril izquierdo en dirección Este-Oeste.
        List<Semaforo> carrilDerchNS = new ArrayList<>(); // Lista de semáforos para carril derecho en dirección Norte-Sur.
        List<Semaforo> carrilDerchEW = new ArrayList<>(); // Lista de semáforos para carril derecho en dirección Este-Oeste.
        List<List<Semaforo>> listaGrupos = new ArrayList<>(); // Lista de todos los grupos de semáforos.
        List<Garaje> garajes = new ArrayList<>(); // Lista de garajes.

        // Crear garajes en las 4 direcciones y añadirlos a la lista de garajes.
        Garaje garajeN = new Garaje("N");
        Garaje garajeS = new Garaje("S");
        Garaje garajeE = new Garaje("E");
        Garaje garajeW = new Garaje("W");

        garajes.add(garajeN);
        garajes.add(garajeS);
        garajes.add(garajeE);
        garajes.add(garajeW);

        // Iniciar los hilos de los garajes.
        for (Garaje garaje : garajes) {
            new Thread(garaje).start();
        }
        
        while (true) {
            // Crear semáforos para cada grupo y añadirlos a las listas de semáforos correspondientes.
            carrilIzqNS.clear();
            carrilIzqEW.clear();
            carrilDerchNS.clear();
            carrilDerchEW.clear();
            
            carrilIzqNS.add(new Semaforo(garajeN, garajeE, "NIzq"));
            carrilIzqNS.add(new Semaforo(garajeS, garajeW, "SIzq"));
            carrilIzqEW.add(new Semaforo(garajeE, garajeS, "EIzq"));
            carrilIzqEW.add(new Semaforo(garajeW, garajeN, "WIzq"));
            
            carrilDerchNS.add(new Semaforo(garajeN, garajeS, garajeW, "NDerch"));
            carrilDerchNS.add(new Semaforo(garajeS, garajeN, garajeE, "SDerch"));
            carrilDerchEW.add(new Semaforo(garajeE, garajeW, garajeN, "EDerch"));
            carrilDerchEW.add(new Semaforo(garajeW, garajeE, garajeS, "WDerch"));

            // Rellenar la lista con los grupos de semáforos.
            listaGrupos.add(carrilIzqNS);
            listaGrupos.add(carrilIzqEW);
            listaGrupos.add(carrilDerchNS);
            listaGrupos.add(carrilDerchNS);
            
            //impresion por terminal
            

            // Activar cada grupo de semáforos y mostrar información del estado de los garajes.
            System.out.println("\nGrupo carril Derch NS\n");
            activarGrupo(carrilDerchNS, tiempoVerdeCarrilDerch);
            impresion(garajes);

            System.out.println("\nGrupo carril Derch EW\n");
            activarGrupo(carrilDerchEW, tiempoVerdeCarrilDerch);
            impresion(garajes);

            System.out.println("\nGrupo carril Izq NS\n");
            activarGrupo(carrilIzqNS, tiempoVerdeCarrilIzq);
            impresion(garajes);

            System.out.println("\nGrupo carril Izq EW\n");
            activarGrupo(carrilIzqEW, tiempoVerdeCarrilIzq);
            impresion(garajes);

        }

    }

    /**
     * Método que activa un grupo de semáforos y controla su tiempo en verde.
     * @param grupo
     * @param tiempoVerde 
     */
    private static void activarGrupo(List<Semaforo> grupo, int tiempoVerde) {
        try {

            // Iniciar los semáforos del grupo.
            for (Semaforo semaforo : grupo) {
                semaforo.start();
            }

            // Tiempo en el que el semaforo esta en verde
            Thread.sleep(tiempoVerde);

            // Detenemos los hilos y se esperar a que todos terminen
            for (Semaforo semaforo : grupo) {
                semaforo.semaforoRojo();
            }

            // Espera a que cada hilo termine la ejecucion
            for (Semaforo semaforo : grupo) {
                semaforo.join();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public static void impresion(List<Garaje> garajes){
        for (Garaje garaje : garajes) {
                System.out.println("\nGaraje " + garaje.getNombre() + " con una ocupacion de " + garaje.cantidadCochesGaraje());
                System.out.println(" - Carril izquierdo con ocupacion de " + garaje.colaSemaforoCarrilIzq());
                System.out.println(" - Carril derecho con ocupacion de " + garaje.colaSemaforoCarrilDerch());
            }
    }

}
