package main;

import javax.swing.*;
import java.awt.*;
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

        btnAnterior.addActionListener(e -> mostrarSolucion(indiceActual - 1));
        btnSiguiente.addActionListener(e -> mostrarSolucion(indiceActual + 1));

        JPanel botonesPanel = new JPanel();
        botonesPanel.add(btnAnterior);
        botonesPanel.add(btnSiguiente);

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
}