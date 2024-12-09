package shibuyaproyect;

public class ShibuyaProyect {

    public static void main(String[] args) {

        Garaje n = new Garaje("N");
        Garaje s = new Garaje("S");
        Garaje e = new Garaje("E");
        Garaje w = new Garaje("W");

        new Thread(n).start();
        new Thread(s).start();
        new Thread(e).start();
        new Thread(w).start();
        


    }

}
