package shibuyaproyect;

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

public class Garaje {

    private final int cochesIniciales = 20;
    private final String nombre;
    private final LinkedBlockingQueue<Coche> garaje;
    private final LinkedBlockingQueue<Coche> semaforoCarrilIzq;
    private final LinkedBlockingQueue<Coche> semaforoCarrilDerch;

    public Garaje(String nombre) {
        this.nombre = nombre;
        this.garaje = new LinkedBlockingQueue<>();
        this.semaforoCarrilIzq = new LinkedBlockingQueue<>();
        this.semaforoCarrilDerch = new LinkedBlockingQueue<>();
        for (int i = 0; i < cochesIniciales; i++) {
            agregarCoche(new Coche(nombre + (i + 1)));
        }
    }

    public void agregarCoche(Coche coche) {
        garaje.add(coche);
    }

    public Coche retirarCoche(LinkedBlockingQueue<Coche> almacen) throws InterruptedException {
        return almacen.take();
    }

    public void recibirCoche(Coche coche, LinkedBlockingQueue<Coche> almacen) throws InterruptedException {
        almacen.put(coche);
    }

    public int cantidadCochesGaraje() {
        return garaje.size();
    }

    public int colaSemaforoCarrilIzq() {
        return semaforoCarrilIzq.size();
    }

    public int colaSemaforoCarrilDerch() {
        return semaforoCarrilDerch.size();
    }

    public void distribuirCoches() throws InterruptedException {
        while (!garaje.isEmpty()) {
            Random aleatorio = new Random(System.currentTimeMillis());
            int numero = aleatorio.nextInt(3);
            Coche coche;
            switch (numero) {
                case 0:
                    coche = retirarCoche(garaje);
                    coche.setDestino(numero);
                    recibirCoche(coche, semaforoCarrilIzq);
                    break;
                case 1:
                    coche = retirarCoche(garaje);
                    coche.setDestino(numero);
                    recibirCoche(coche, semaforoCarrilDerch);
                    break;
                case 2:
                    coche = retirarCoche(garaje);
                    coche.setDestino(numero);
                    recibirCoche(coche, semaforoCarrilDerch);
                    break;
            }
            Thread.sleep(1000);
            while (garaje.isEmpty()) {
                Thread.sleep(2000);
            }
        }
    }

    @Override
    public String toString() {
        return nombre;
    }

}
