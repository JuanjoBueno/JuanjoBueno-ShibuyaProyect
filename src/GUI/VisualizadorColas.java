
package GUI;

import shibuyaproyect.Garaje;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class VisualizadorColas {

    private final JFrame frame;
    private final List<Garaje> garajes;

    public VisualizadorColas(List<Garaje> garajes) {
        this.garajes = garajes;
        frame = new JFrame("Visualizador de Estados de las Colas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, garajes.size() * 150);
        frame.setLayout(new GridLayout(garajes.size(), 1));
        inicializarInterfaz();
    }

    private void inicializarInterfaz() {
        for (Garaje garaje : garajes) {
            JPanel panelGaraje = new JPanel();
            panelGaraje.setBorder(BorderFactory.createTitledBorder("Garaje: " + garaje.getNombre()));
            panelGaraje.setLayout(new GridLayout(3, 2));

            JProgressBar garajeBar = crearBarra("Coches en Garaje", garaje.cantidadCochesGaraje());
            JProgressBar izqBar = crearBarra("Carril Izquierdo", garaje.colaSemaforoCarrilIzq());
            JProgressBar derchBar = crearBarra("Carril Derecho", garaje.colaSemaforoCarrilDerch());

            panelGaraje.add(new JLabel("Coches en Garaje"));
            panelGaraje.add(garajeBar);
            panelGaraje.add(new JLabel("Carril Izquierdo"));
            panelGaraje.add(izqBar);
            panelGaraje.add(new JLabel("Carril Derecho"));
            panelGaraje.add(derchBar);

            frame.add(panelGaraje);

            actualizarEstadoEnTiempoReal(garaje, garajeBar, izqBar, derchBar);
        }
    }

    private JProgressBar crearBarra(String texto, int valorMax) {
        JProgressBar barra = new JProgressBar(0, 20); // Máximo inicial configurable
        barra.setStringPainted(true);
        barra.setValue(valorMax);
        barra.setString(texto);
        return barra;
    }

    private void actualizarEstadoEnTiempoReal(Garaje garaje, JProgressBar garajeBar, JProgressBar izqBar, JProgressBar derchBar) {
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> {
                    garajeBar.setValue(garaje.cantidadCochesGaraje());
                    izqBar.setValue(garaje.colaSemaforoCarrilIzq());
                    derchBar.setValue(garaje.colaSemaforoCarrilDerch());
                });
            }
        }, 0, 1000); // Actualiza cada segundo
    }

    public void mostrar() {
        frame.setVisible(true);
    }
}