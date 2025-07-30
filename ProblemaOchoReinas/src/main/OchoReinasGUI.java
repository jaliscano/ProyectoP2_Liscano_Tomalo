package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Interfaz gráfica para visualizar las soluciones del problema de las 8 reinas.
 * Permite navegar entre todas las soluciones encontradas por la clase OchoReinas.
 */
public class OchoReinasGUI extends JFrame {
    private static final int N = 8;
    private OchoReinas solucionador;
    private int indiceActual = 0;

    private JPanel tableroPanel;
    private JLabel solucionLabel;

    public OchoReinasGUI() {
        setTitle("Problema de las 8 Reinas - Interfaz Gráfica");
        setSize(500, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        solucionador = new OchoReinas();
        solucionador.calcularSoluciones();

        tableroPanel = new JPanel(new GridLayout(N, N));
        solucionLabel = new JLabel("", SwingConstants.CENTER);

        JButton btnAnterior = new JButton("Anterior");
        JButton btnSiguiente = new JButton("Siguiente");
        JButton btnInfo = new JButton("¿Cómo se resolvió?");

        btnAnterior.addActionListener(e -> mostrarSolucion(indiceActual - 1));
        btnSiguiente.addActionListener(e -> mostrarSolucion(indiceActual + 1));
        btnInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarInformacion();
            }
        });

        JPanel botonesPanel = new JPanel();
        botonesPanel.add(btnAnterior);
        botonesPanel.add(btnSiguiente);
        botonesPanel.add(btnInfo);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(solucionLabel, BorderLayout.NORTH);
        mainPanel.add(tableroPanel, BorderLayout.CENTER);
        mainPanel.add(botonesPanel, BorderLayout.SOUTH);

        add(mainPanel);

        mostrarSolucion(0);
    }

    /**
     * Muestra la solución número 'indice' en el tablero gráfico.
     */
    private void mostrarSolucion(int indice) {
        int total = solucionador.getCantidadSoluciones();
        if (indice < 0 || indice >= total) return;
        indiceActual = indice;
        int[][] solucion = solucionador.getSolucion(indice);

        tableroPanel.removeAll();

        for (int fila = 0; fila < N; fila++) {
            for (int col = 0; col < N; col++) {
                JLabel cell = new JLabel();
                cell.setOpaque(true);
                cell.setHorizontalAlignment(SwingConstants.CENTER);
                cell.setVerticalAlignment(SwingConstants.CENTER);

                // Colorea el tablero como ajedrez
                if ((fila + col) % 2 == 0) {
                    cell.setBackground(Color.WHITE);
                } else {
                    cell.setBackground(Color.LIGHT_GRAY);
                }

                // Coloca la reina
                if (solucion[fila][col] == 1) {
                    cell.setText("♛");
                    cell.setFont(new Font("Serif", Font.PLAIN, 32));
                    cell.setForeground(Color.RED);
                }
                tableroPanel.add(cell);
            }
        }

        solucionLabel.setText("Solución " + (indiceActual + 1) + " de " + total);
        tableroPanel.revalidate();
        tableroPanel.repaint();
    }
    
    /**
     * Muestra la información paso a paso de cómo se resolvió el problema, en un JDialog modal.
     */
    private void mostrarInformacion() {
        String mensaje = """
        Paso a paso para resolver el problema de las 8 reinas:

        1. El tablero es de 8x8 casillas.
        2. Se busca colocar una reina por columna, de forma que no se ataquen entre sí.
        3. Para cada columna, se prueba cada fila posible. Si la posición es válida 
           (no hay otra reina en la misma fila ni en las diagonales), se coloca la reina.
        4. Se avanza a la siguiente columna y se repite el proceso.
        5. Si no se puede colocar una reina en alguna columna, se retrocede (vuelta atrás) 
           para intentar una posición distinta en la columna anterior.
        6. Cada vez que se colocan las 8 reinas, se almacena la solución.
        7. El algoritmo explora todas las combinaciones posibles usando recursividad y retroceso.

        Representación:
        - Se usa un arreglo donde la posición i representa la columna y el valor en esa posición la fila ocupada por la reina.
        - Se verifica para cada nueva reina que no se encuentre en la misma fila ni diagonales que las anteriores.

        Así se encuentran todas las soluciones posibles al problema.
        """;

        // Crear JDialog modal con JTextArea y JScrollPane
        JDialog dialog = new JDialog(this, "Información paso a paso", true); // true = modal
        JTextArea textArea = new JTextArea(mensaje);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(textArea);
        dialog.add(scrollPane);

        dialog.setSize(500, 350);
        dialog.setLocationRelativeTo(this);

        // Botón para cerrar el diálogo
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dialog.dispose());
        JPanel panelBoton = new JPanel();
        panelBoton.add(btnCerrar);

        dialog.add(panelBoton, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }
}