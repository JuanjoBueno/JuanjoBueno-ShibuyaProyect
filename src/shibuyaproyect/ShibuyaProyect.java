package shibuyaproyect;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class ShibuyaProyect {

    public static void main(String[] args) {

        //Creacion de los garajes
        Garaje n = new Garaje("N");
        Garaje s = new Garaje("S");
        Garaje e = new Garaje("E");
        Garaje w = new Garaje("W");

        //Creacion de los semaforos
        Semaforo n1 = new Semaforo(n, e, "n1",0);
        Semaforo n2 = new Semaforo(n, s, w, "n2",1);
        Semaforo s1 = new Semaforo(s, w, "s1",0);
        Semaforo s2 = new Semaforo(s, n, e, "s2",1);
        Semaforo e1 = new Semaforo(e, s, "e1",2);
        Semaforo e2 = new Semaforo(e, w, n, "e2",3);
        Semaforo w1 = new Semaforo(w, n, "w1",2);
        Semaforo w2 = new Semaforo(w, e, s, "w2",3);

        //Lanzamos los hilos
        new Thread(n).start();
        new Thread(s).start();
        new Thread(e).start();
        new Thread(w).start();
        new Thread(n1).start();
        new Thread(s1).start();
        new Thread(e1).start();
        new Thread(w1).start();
        new Thread(n2).start();
        new Thread(s2).start();
        new Thread(e2).start();
        new Thread(w2).start();

    }

}
