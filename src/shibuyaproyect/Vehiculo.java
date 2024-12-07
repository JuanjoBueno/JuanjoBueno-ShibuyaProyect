package shibuyaproyect;


public class Vehiculo {
    
    private int id;
    private int direccion;

    public Vehiculo(int id, int direccion) {
        this.id = id;
        this.direccion = direccion;
    }

    public int getId() {
        return id;
    }    

    public int getDireccion() {
        return direccion;
    }

    public void setDireccion(int direccion) {
        this.direccion = direccion;
    }
    
    @Override
    public String toString() {
        return "Vehiculo{" + "id=" + id + ", direccion=" + direccion + '}';
    }
    
    
}
