# Shibuya Traffic Simulation Project

## **Nombre del Proyecto**

Shibuya Traffic Simulation Project

## **Componentes**

- **Main**: Controla la lógica principal del programa y la alternancia entre grupos de semáforos.
- **Semaforo**: Clase que representa un semáforo, encargada de gestionar el flujo de coches.
- **Garaje**: Clase que modela los garajes de entrada y salida, incluyendo la asignación de destinos.
- **Coche**: Clase que representa a un coche en la simulación.

## **Estructura Concurrente**

El proyecto utiliza la clase `Thread` de Java para modelar la concurrencia. Cada semáforo y garaje se implementa como un hilo independiente que ejecuta su lógica concurrentemente.

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

## **Descripción de los Ficheros del Proyecto**

- **ShibuyaProyect.java**: Contiene el método principal y gestiona la alternancia de los grupos de semáforos.
- **Semaforo.java**: Define la lógica de los semáforos y su comportamiento como hilos concurrentes.
- **Garaje.java**: Modelo de garaje que administra los coches y sus destinos.
- **Coche.java**: Representación de un coche, incluyendo su identificación y destino.

---

Este proyecto permite analizar la interacción de múltiples elementos en un sistema concurrente, proporcionando una base para simulaciones más complejas, como la inclusion de mas carriles, unificar el control de mas de un cruce, etc...

