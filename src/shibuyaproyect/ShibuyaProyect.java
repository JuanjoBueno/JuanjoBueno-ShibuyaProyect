
package shibuyaproyect;

import java.util.HashMap;
import java.util.Map;


public class ShibuyaProyect {

   
    public static void main(String[] args) {
        
        Garaje n = new Garaje("n");
        Garaje s  = new Garaje("s");
        Garaje e  = new Garaje("e");
        Garaje w  = new Garaje("w");
        
        Map<String, Garaje> garajes = new HashMap<>();
        garajes.put("N", n);
        garajes.put("S", s);
        garajes.put("E", e);
        garajes.put("W", w);

        Semaforo s1 = new Semaforo("s1", garajes, false);
        Semaforo s2 = new Semaforo("s2", garajes, true);
        
        n.setS1(s1);
        n.setS2(s2);
        s.setS1(s1);
        s.setS2(s2);
        e.setS1(s1);
        e.setS2(s2);
        e.getS2().setIsVerde(false);
        w.setS1(s1);
        w.setS2(s2);
        w.getS2().setIsVerde(false);
        
    }
    
}
