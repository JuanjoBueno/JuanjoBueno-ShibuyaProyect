# Shibuya Traffic Simulation Project

## **Nombre del Proyecto**

Shibuya Traffic Simulation Project

## **Componentes**

- **Main**: Controla la lógica principal del programa y la alternancia entre grupos de semáforos.
- **Semaforo**: Clase que representa un semáforo, encargada de gestionar el flujo de coches.
- **Garaje**: Clase que modela los garajes de entrada y salida, incluyendo la asignación de destinos.
- **Coche**: Clase que representa a un coche en la simulación.

## **Estructura Concurrente**

En este proyecto, se utiliza la clase LinkedBlockingQueue de la biblioteca estándar de Java para gestionar la concurrencia de forma eficiente y segura. Esta estructura de datos nos permite coordinar el flujo de vehículos entre los garajes y los semáforos, asegurando que las operaciones de acceso sean thread-safe.

### **Descripción de la Estructura**

- **Semáforos**: Se organizan en cuatro grupos, cada uno compuesto por dos semáforos. Los grupos alternan el control del cruce de manera cíclica, garantizando que todos los carriles tengan su turno de paso.
- **Garajes**: Los garajes funcionan como depósitos de coches. Los coches se distribuyen aleatoriamente entre diferentes destinos, utilizando sincronización para evitar conflictos entre hilos.
- **Coches**: Modelan vehículos que atraviesan el cruce según las señales de los semáforos.

## **Introducción**

El proyecto simula el flujo de tráfico en un cruce concurrido similar al de Shibuya. El objetivo es modelar cómo los semáforos y garajes interactúan para regular el paso de vehículos de manera concurrente y eficiente.

## **Casos de Uso**

- Simulación de tráfico en cruces concurridos.
- Análisis de eficiencia en la alternancia de semáforos.
- Modelado de sistemas concurrentes en proyectos educativos.

## **Métodos de Interés**

| Clase    | Método                      | Descripción                                                     |
| -------- | --------------------------- | --------------------------------------------------------------- |
| Semaforo | `semaforoVerde()`           | Gestiona el paso de coches mientras el semáforo está en verde.  |
| Semaforo | `semaforoRojo()`            | Detiene la ejecución del semáforo.                              |
| Semaforo | `start()`                   | Inicia la ejecución del hilo.                                   |
| Garaje   | `asignarDestinoAleatorio()` | Asigna un destino único para cada coche.                        |
| Main     | `activarGrupo()`            | Activa los semáforos de un grupo durante un tiempo determinado. |

## **Interfaz Gráfica**

La interfaz gráfica del proyecto está implementada con Java Swing. Ofrece una representación visual en tiempo real del estado de los garajes y semáforos mediante barras de progreso y texto descriptivo.

**Funcionalidades de la GUI**

- Visualización dinámica: Barras de progreso que muestran la capacidad actual de cada garaje y semáforo.

- Texto descriptivo: Indicadores con nombres y cantidades para identificar el estado actual.

**Diseño**

- Cada garaje y semáforo tiene un JProgressBar asociado.

- El estado de las colas se actualiza automáticamente mediante eventos disparados por los garajes y semáforos.

## **Descripción de los Ficheros del Proyecto**

- **ShibuyaProyect.java**: Contiene el método principal y gestiona la alternancia de los grupos de semáforos.
- **Semaforo.java**: Define la lógica de los semáforos y su comportamiento como hilos concurrentes.
- **Garaje.java**: Modelo de garaje que administra los coches y sus destinos.
- **Coche.java**: Representación de un coche, incluyendo su identificación y destino.
- **CruceGUI**: Clase que gestiona la interfaz gráfica del proyecto.

---

Este proyecto permite analizar la interacción de múltiples elementos en un sistema concurrente, proporcionando una base para simulaciones más complejas, como la inclusion de mas carriles, unificar el control de mas de un cruce, etc...

