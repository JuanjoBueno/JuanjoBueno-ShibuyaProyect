package shibuyaproyect;

public class ShibuyaProyect {

    public static void main(String[] args) {

        Garaje n = new Garaje("N");
        Garaje s = new Garaje("S");
        Garaje e = new Garaje("E");
        Garaje w = new Garaje("W");

        try {
            n.distribuirCoches();
            s.distribuirCoches();
            e.distribuirCoches();
            w.distribuirCoches();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }


    }

}
