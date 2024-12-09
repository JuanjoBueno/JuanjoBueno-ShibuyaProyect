package shibuyaproyect;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//Clase para distribuir coches desde el semaforo al garaje correspondiente
public class Semaforo implements Runnable {

    private Garaje origen;
    private Garaje destino1, destino2;
    private final String nombre;
    private final boolean carrilDerch;
    private final int grupoSemaforo;

    //Variables para el control de los grupos de semaforos
    private static final int NUM_GRUPOS = 4;
    private static int tiempoTrabajoGrupo1 = 10000;
    private static int tiempoTrabajoGrupo2 = 4000;
    private static final Lock lock = new ReentrantLock();
    private static final Condition[] turnos = new Condition[NUM_GRUPOS];
    private static int grupoActivo = 0;

    public static void setTiempoTrabajoGrupo1(int tiempoTrabajoGrupo1) {
        Semaforo.tiempoTrabajoGrupo1 = tiempoTrabajoGrupo1;
    }

    public static void setTiempoTrabajoGrupo2(int tiempoTrabajoGrupo2) {
        Semaforo.tiempoTrabajoGrupo2 = tiempoTrabajoGrupo2;
    }
    
    //Creamos las condiciones de espera y notificacion para la sincronizacion
    static {
        for (int i = 0; i < NUM_GRUPOS; i++) {
            turnos[i] = lock.newCondition();
        }
    }

    //Constructor para los semaforos del carril izquierdo
    public Semaforo(Garaje origen, Garaje destino1, String nombre, int grupoSemaforo) {
        super();
        this.origen = origen;
        this.destino1 = destino1;
        this.nombre = nombre;
        this.carrilDerch = false;
        this.grupoSemaforo = grupoSemaforo;
    }

    //Constructor sobrecargado para los semaforos del carril derecho que pueden ir los coches en 2 direcciones
    public Semaforo(Garaje origen, Garaje destino1, Garaje destino2, String nombre, int grupoSemaforo) {
        super();
        this.origen = origen;
        this.destino1 = destino1;
        this.destino2 = destino2;
        this.nombre = nombre;
        this.carrilDerch = true;
        this.grupoSemaforo = grupoSemaforo;
    }

    // Cambiar al siguiente grupo de semáforo cíclicamente
    private void cambiarTurno() {
        grupoActivo = (grupoActivo + 1) % NUM_GRUPOS;
        turnos[grupoActivo].signalAll(); // Despierta al siguiente grupo
    }

    //Metodo que distribuyen los coches segun su destino
    public void semaforoVerde() {
        lock.lock();
        try {
            //espera hasta que toque el turno del grupo
            while (grupoSemaforo != grupoActivo) {
                turnos[grupoSemaforo].await();
            }

            //segun el grupo nos quedamos con su tiempo de semaforo en verde
            int tiempoTrabajo = (grupoSemaforo == 0 || grupoSemaforo == 1) ? tiempoTrabajoGrupo1 : tiempoTrabajoGrupo2;

            // Marcar el tiempo de inicio
            long tiempoInicio = System.currentTimeMillis();

            //Garantizamos que se realice la tarea mientras quede tiempo de trabajo
            while (System.currentTimeMillis() - tiempoInicio < tiempoTrabajo) {

                //Dirigiendo los coches a sus destinos
                if (!carrilDerch && !origen.getSemaforoCarrilIzq().isEmpty()) {
                    Coche coche = origen.retirarCoche(origen.getSemaforoCarrilIzq());
                    destino1.agregarCoche(coche);
                    System.out.println("Coche " + coche.getNombre() + " saliendo del semaforo " + this.nombre + " hacia " + destino1.getNombre());
                } else if (carrilDerch && !origen.getSemaforoCarrilDerch().isEmpty()) {
                    Coche coche = origen.retirarCoche(origen.getSemaforoCarrilDerch());
                    int destino = coche.getDestino();
                    if (destino == 1) {
                        destino1.agregarCoche(coche);
                        System.out.println("Coche " + coche.getNombre() + " saliendo del semaforo " + this.nombre + " hacia " + destino1.getNombre());
                    } else {
                        destino2.agregarCoche(coche);
                        System.out.println("Coche " + coche.getNombre() + " saliendo del semaforo " + this.nombre + " hacia " + destino2.getNombre());
                    }
                } else {
                    long tiempoRestante = tiempoTrabajo - (System.currentTimeMillis() - tiempoInicio);
                    //si el tiempo restante es mayor de 1 segundo realizamos una espera de medio segundo cuando no hay coches esperando en el semaforo
                    if (tiempoRestante > 0) {
                        Thread.sleep(Math.min(tiempoRestante, 500));
                    }
                }

            }
            cambiarTurno();
            System.out.println("Cambio de grupo");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    @Override
    public void run() {
        while (true) {
            semaforoVerde();
        }
    }
}
