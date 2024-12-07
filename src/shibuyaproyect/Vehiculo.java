package shibuyaproyect;

import java.util.Random;


public class Vehiculo {
    
    private int id;
    private int direccion;

    public Vehiculo(int id) {
        this.id = id;
        this.direccion = new Random(System.currentTimeMillis()).nextInt(3);
    }

    public int getId() {
        return id;
    }    

    public int getDireccion() {
        return direccion;
    }

    public void setDireccion() {
        this.direccion = new Random(System.currentTimeMillis()).nextInt(3);
    }   
    
    @Override
    public String toString() {
        return "Vehiculo{" + "id=" + id + ", direccion=" + direccion + '}';
    }
    
    
}
