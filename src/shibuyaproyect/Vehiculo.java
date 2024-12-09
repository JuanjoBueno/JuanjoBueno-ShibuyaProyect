package shibuyaproyect;

import java.util.Random;

public class Vehiculo {

    private String id;
    private int direccion;
    private String origen;
    private static final Random random = new Random();

    public Vehiculo(String id) {
        this.id = id;
        this.origen = null;
        this.direccion = random.nextInt(3);
    }

    public String getId() {
        return id;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public int getDireccion() {
        return direccion;
    }

    public void setDireccion() {
        this.direccion = random.nextInt(3);
    }

    @Override
    public String toString() {
        return "Vehiculo{" + "id=" + id + ", direccion=" + direccion + '}';
    }

}
