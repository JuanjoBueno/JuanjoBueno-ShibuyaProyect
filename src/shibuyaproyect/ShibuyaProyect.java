package shibuyaproyect;

import java.util.HashMap;
import java.util.Map;

public class ShibuyaProyect {

    public static void main(String[] args) {

        Garaje n = new Garaje("n");
        Garaje s = new Garaje("s");
        Garaje e = new Garaje("e");
        Garaje w = new Garaje("w");

        Map<String, Garaje> garajes = new HashMap<>();
        garajes.put("N", n);
        garajes.put("S", s);
        garajes.put("E", e);
        garajes.put("W", w);

        new Thread(n).start();
        new Thread(s).start();
        new Thread(e).start();
        new Thread(w).start();

        n.setS1(new Semaforo("s1", garajes, false));
        n.setS2(new Semaforo("s2", garajes, true));
        s.setS1(new Semaforo("s1", garajes, false));
        s.setS2(new Semaforo("s2", garajes, true));
        e.setS1(new Semaforo("s1", garajes, false));
        e.setS2(new Semaforo("s1", garajes, false));
        w.setS1(new Semaforo("s1", garajes, false));
        w.setS2(new Semaforo("s1", garajes, false));

        new Thread(n.getS1()).start();
        new Thread(n.getS2()).start();
        new Thread(s.getS1()).start();
        new Thread(s.getS2()).start();
        new Thread(e.getS1()).start();
        new Thread(e.getS2()).start();
        new Thread(w.getS1()).start();
        new Thread(w.getS2()).start();
    }

}
