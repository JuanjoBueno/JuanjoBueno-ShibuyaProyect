package shibuyaproyect;


import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

//Clase que ubica tanto un garaje como 2 carriles
public class Garaje implements Runnable {

    private final int cochesIniciales = 20; // Número inicial de coches en el garaje
    private final String nombre; // Nombre del garaje (N, S, E, W).
    private final LinkedBlockingQueue<Coche> garaje; // Cola que almacena los coches en el garaje.
    private final LinkedBlockingQueue<Coche> semaforoCarrilIzq; // Cola para los coches que van al carril izquierdo.
    private final LinkedBlockingQueue<Coche> semaforoCarrilDerch; // Cola para los coches que van al carril derecho.
    private final Lock lock = new ReentrantLock(); // Lock para controlar el acceso para generar un destino único
    private static final Random random = new Random();
    

    /**
     * Constructor que crea el garaje y le asigna coches, inicializa los carriles vacios
     * @param nombre 
     */
    public Garaje(String nombre) {
        super();
        this.nombre = nombre;
        this.garaje = new LinkedBlockingQueue<>();
        this.semaforoCarrilIzq = new LinkedBlockingQueue<>();
        this.semaforoCarrilDerch = new LinkedBlockingQueue<>();
        for (int i = 0; i < cochesIniciales; i++) {
            agregarCoche(new Coche("Coche " + (i + 1) + " del garaje " + nombre));
        }
    }

    public String getNombre() {
        return nombre;
    }

    public LinkedBlockingQueue<Coche> getSemaforoCarrilIzq() {
        return semaforoCarrilIzq;
    }

    public LinkedBlockingQueue<Coche> getSemaforoCarrilDerch() {
        return semaforoCarrilDerch;
    }

    public LinkedBlockingQueue<Coche> getGaraje() {
        return garaje;
    }

    /**
     * Método para agregar un coche al garaje.
     * @param coche 
     */
    public void agregarCoche(Coche coche) {
        garaje.add(coche);
    }

    /**
     * Método para retirar un coche de una cola específica.
     * @param almacen
     * @return Coche || Devuelve un objeto coche de la cola
     * @throws InterruptedException 
     */
    public Coche retirarCoche(LinkedBlockingQueue<Coche> almacen) throws InterruptedException {
        return almacen.take();
    }

    /**
     * Método para introducir un coche de una cola específica.
     * @param coche
     * @param almacen
     * @throws InterruptedException 
     */
    public void recibirCoche(Coche coche, LinkedBlockingQueue<Coche> almacen) throws InterruptedException {
        
        almacen.put(coche);
    }

    /**
     * Método que devuelvce la catidad de coches en el garaje
     * @return 
     */
    public int cantidadCochesGaraje() {
        return garaje.size();
    }

    /**
     * Métodos para obtener la cantidad de coches espeando en los semáforos
     * @return int || Devuelve la cantidad de coches esperando en la respectiva cola
     */
    public int colaSemaforoCarrilIzq() {
        return semaforoCarrilIzq.size();
    }
    
    public int colaSemaforoCarrilDerch() {
        return semaforoCarrilDerch.size();
    }

    /**
     * Método que genera un destino aleatorio para el coche utilizando lock
     * @param n
     * @return Devuelve un int que señaliza el destino del coche
     */
    public int generarDestinoUnico(int n) {
        lock.lock();
        try {
            int destino = random.nextInt(n);
            return destino;
        } finally {
            lock.unlock();
        }
    }
    
    




    /**
     * Metodo que asigna un destino a los coches y segun el cual lo ubica en el carril correspondiente
     * destino 0 el coche girara a la izquierda, 1 seguira recto, 2 girara a la derecha
     * Si el garaje se vacia se le asigna un tiempo de espera hasta que vuelva a haber coches y siga distribuyendo
     * 
     * @throws InterruptedException 
     */
    public void distribuirCoches() throws InterruptedException {

        while (!garaje.isEmpty()) {

            int numero = generarDestinoUnico(3); // Genera un destino aleatorio (0, 1 o 2).
            Coche coche;
            switch (numero) {
                case 0:
                    coche = retirarCoche(garaje);
                    
                    coche.setDestino(numero);
                    recibirCoche(coche, semaforoCarrilIzq);
                    System.out.println(coche.getNombre() + " esperando en el semaforo " + nombre + "Izq con destino " + coche.getDestino());
                    break;
                case 1, 2:
                    coche = retirarCoche(garaje);
                    
                    coche.setDestino(numero);
                    recibirCoche(coche, semaforoCarrilDerch);
                    System.out.println(coche.getNombre() + " esperando en el semaforo " + nombre + "Derch con destino " + coche.getDestino());
                    break;
            }
            // Tiempo entre un coche y otro
            int tiempo = random.nextInt(1500) + 500;
            Thread.sleep(tiempo);
            while (garaje.isEmpty()) {
                Thread.sleep(2000); // Si el garaje está vacío, espera 2 segundos antes de seguir.
            }
        }
    }

    @Override
    public String toString() {
        return nombre;
    }

    // Run que ejecuta el método distribuirCoches()
    @Override
    public void run() {
        try {
            distribuirCoches();
        } catch (InterruptedException ex) {
            Logger.getLogger(Garaje.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
