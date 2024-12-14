package shibuyaproyect;

import java.util.ArrayList;
import java.util.List;

public class ShibuyaProyect {

    public static void main(String[] args) {

        int tiempoVerdeCarrilIzq = 5000;
        int tiempoVerdeCarrilDerch = 12000;
        List<Semaforo> carrilIzqNS = new ArrayList<>();
        List<Semaforo> carrilIzqEW = new ArrayList<>();
        List<Semaforo> carrilDerchNS = new ArrayList<>();
        List<Semaforo> carrilDerchEW = new ArrayList<>();
        List<List<Semaforo>> listaGrupos = new ArrayList<>();
        List<Garaje> garajes = new ArrayList<>();

        // Crear garajes
        Garaje garajeN = new Garaje("Garaje 1");
        Garaje garajeS = new Garaje("Garaje 2");
        Garaje garajeE = new Garaje("Garaje 3");
        Garaje garajeW = new Garaje("Garaje 4");
        
        garajes.add(garajeN);
        garajes.add(garajeS);
        garajes.add(garajeE);
        garajes.add(garajeW);

        //Creacion de semaforos y add a cada grupo
        carrilIzqNS.add(new Semaforo(garajeN, garajeE, "NIzq"));
        carrilIzqNS.add(new Semaforo(garajeN, garajeE, "SIzq"));
        carrilIzqEW.add(new Semaforo(garajeN, garajeE, "EIzq"));
        carrilIzqEW.add(new Semaforo(garajeN, garajeE, "WIzq"));
        carrilDerchNS.add(new Semaforo(garajeN, garajeE, "NDerch"));
        carrilDerchNS.add(new Semaforo(garajeN, garajeE, "SDerch"));
        carrilDerchEW.add(new Semaforo(garajeN, garajeE, "EDerch"));
        carrilDerchEW.add(new Semaforo(garajeN, garajeE, "WDerch"));

        //rellenar lista con los grupos
        listaGrupos.add(carrilIzqNS);
        listaGrupos.add(carrilIzqEW);
        listaGrupos.add(carrilDerchNS);
        listaGrupos.add(carrilDerchNS);
        
        for (Garaje garaje : garajes) {
            new Thread(garaje).start();
        }
        
        while (true) {
            System.out.println("Grupo 1");
            activarGrupo(carrilIzqNS, tiempoVerdeCarrilIzq);
            
            System.out.println("Grupo 2");
            activarGrupo(carrilDerchNS, tiempoVerdeCarrilDerch);
            
            System.out.println("Grupo 3");
            activarGrupo(carrilIzqEW, tiempoVerdeCarrilIzq);
            
            System.out.println("Grupo 4");
            activarGrupo(carrilDerchEW, tiempoVerdeCarrilDerch);
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
            for(Semaforo semaforo : grupo){
                semaforo.join();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
