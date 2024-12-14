package shibuyaproyect;

import java.util.ArrayList;
import java.util.List;

public class ShibuyaProyect {

    public static void main(String[] args) {

        int tiempoVerdeCarrilIzq = 5000;
        int tiempoVerdeCarrilDerch = 10000;
        List<Semaforo> carrilIzqNS = new ArrayList<>();
        List<Semaforo> carrilIzqEW = new ArrayList<>();
        List<Semaforo> carrilDerchNS = new ArrayList<>();
        List<Semaforo> carrilDerchEW = new ArrayList<>();
        List<List<Semaforo>> listaGrupos = new ArrayList<>();
        List<Garaje> garajes = new ArrayList<>();

        // Crear garajes
        Garaje garajeN = new Garaje("N");
        Garaje garajeS = new Garaje("S");
        Garaje garajeE = new Garaje("E");
        Garaje garajeW = new Garaje("W");

        garajes.add(garajeN);
        garajes.add(garajeS);
        garajes.add(garajeE);
        garajes.add(garajeW);

        //Creacion de semaforos y add a cada grupo
        carrilIzqNS.add(new Semaforo(garajeN, garajeE, "NIzq"));
        carrilIzqNS.add(new Semaforo(garajeS, garajeW, "SIzq"));
        carrilIzqEW.add(new Semaforo(garajeE, garajeS, "EIzq"));
        carrilIzqEW.add(new Semaforo(garajeW, garajeN, "WIzq"));
        carrilDerchNS.add(new Semaforo(garajeN, garajeS, garajeW, "NDerch"));
        carrilDerchNS.add(new Semaforo(garajeS, garajeN, garajeE, "SDerch"));
        carrilDerchEW.add(new Semaforo(garajeE, garajeW, garajeN, "EDerch"));
        carrilDerchEW.add(new Semaforo(garajeW, garajeE, garajeS, "WDerch"));

        //rellenar lista con los grupos
        listaGrupos.add(carrilIzqNS);
        listaGrupos.add(carrilIzqEW);
        listaGrupos.add(carrilDerchNS);
        listaGrupos.add(carrilDerchNS);

        for (Garaje garaje : garajes) {
            new Thread(garaje).start();
        }

        while (true) {
            System.out.println("Grupo carril Derch NS");
            activarGrupo(carrilDerchNS, tiempoVerdeCarrilDerch);
            for (Garaje garaje : garajes) {
                System.out.println(garaje.cantidadCochesGaraje());
                System.out.println(garaje.colaSemaforoCarrilIzq());
                System.out.println(garaje.colaSemaforoCarrilDerch());
            }
            

            System.out.println("Grupo carril Derch EW");
            activarGrupo(carrilDerchEW, tiempoVerdeCarrilDerch);
            for (Garaje garaje : garajes) {
                System.out.println(garaje.cantidadCochesGaraje());
                System.out.println(garaje.colaSemaforoCarrilIzq());
                System.out.println(garaje.colaSemaforoCarrilDerch());
            }

            System.out.println("Grupo carril Izq NS");
            activarGrupo(carrilIzqNS, tiempoVerdeCarrilIzq);
            for (Garaje garaje : garajes) {
                System.out.println(garaje.cantidadCochesGaraje());
                System.out.println(garaje.colaSemaforoCarrilIzq());
                System.out.println(garaje.colaSemaforoCarrilDerch());
            }

            System.out.println("Grupo carril Izq EW");
            activarGrupo(carrilIzqEW, tiempoVerdeCarrilIzq);
            for (Garaje garaje : garajes) {
                System.out.println(garaje.cantidadCochesGaraje());
                System.out.println(garaje.colaSemaforoCarrilIzq());
                System.out.println(garaje.colaSemaforoCarrilDerch());
            }

        }

    }

    private static void activarGrupo(List<Semaforo> grupo, int tiempoVerde) {
        try {

            //Inicio los semaforos del grupo
            for (Semaforo semaforo : grupo) {
                semaforo.start();
            }

            //Tiempo en el que el semaforo esta en verde
            Thread.sleep(tiempoVerde);

            //Detenemos los hilos y se esperar a que todos terminen
            for (Semaforo semaforo : grupo) {
                semaforo.semaforoRojo();
            }

            //Espera a que cada hilo termine la ejecucion
            for (Semaforo semaforo : grupo) {
                semaforo.join();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void almacenesCoches() {

    }

}
