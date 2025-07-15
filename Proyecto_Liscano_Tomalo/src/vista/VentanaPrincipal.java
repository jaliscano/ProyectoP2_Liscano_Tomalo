package vista;

import controlador.ControladorClinica;
import modelo.Mascota;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VentanaPrincipal extends JFrame {

    private ControladorClinica controlador;
    private JTextArea areaDatos;

    public VentanaPrincipal() {
        controlador = new ControladorClinica();
        setTitle("Clínica Veterinaria");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        areaDatos = new JTextArea();
        areaDatos.setEditable(false);

        JButton btnRegistrar = new JButton("Registrar Mascota");
        JButton btnVerMascotas = new JButton("Mostrar Mascotas");
        JButton btnSalas = new JButton("Mostrar Salas");
        JButton btnRuta = new JButton("Calcular Ruta");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));
        panel.add(btnRegistrar);
        panel.add(btnVerMascotas);
        panel.add(btnSalas);
        panel.add(btnRuta);

        add(new JScrollPane(areaDatos), BorderLayout.CENTER);
        add(panel, BorderLayout.EAST);

        btnRegistrar.addActionListener(e -> registrarMascota());
        btnVerMascotas.addActionListener(e -> mostrarMascotas());
        btnSalas.addActionListener(e -> mostrarSalas());
        btnRuta.addActionListener(e -> calcularRuta());
    }

    private void registrarMascota() {
        int codigo = Integer.parseInt(JOptionPane.showInputDialog("Código de la mascota:"));
        String nombre = JOptionPane.showInputDialog("Nombre de la mascota:");
        String especie = JOptionPane.showInputDialog("Especie:");
        controlador.registrarMascota(codigo, nombre, especie);
        areaDatos.setText("Mascota registrada con éxito.");
    }

    private void mostrarMascotas() {
        ArrayList<Mascota> lista = controlador.obtenerMascotas();
        StringBuilder sb = new StringBuilder("Mascotas registradas:\n");
        for (Mascota m : lista) {
            sb.append(m).append("\n");
        }
        areaDatos.setText(sb.toString());
    }

    private void mostrarSalas() {
        String[] salas = controlador.obtenerSalas();
        StringBuilder sb = new StringBuilder("Salas de la clínica:\n");
        for (int i = 0; i < salas.length; i++) {
            sb.append(i).append(": ").append(salas[i]).append("\n");
        }
        areaDatos.setText(sb.toString());
    }

    private void calcularRuta() {
        String[] salas = controlador.obtenerSalas();
        String seleccion = (String) JOptionPane.showInputDialog(this, "Seleccione sala inicial:",
                "Ruta Interna", JOptionPane.PLAIN_MESSAGE, null, salas, salas[0]);
        int indice = java.util.Arrays.asList(salas).indexOf(seleccion);
        String[] resultado = controlador.calcularRuta(indice);

        StringBuilder sb = new StringBuilder("Ruta desde " + seleccion + ":\n");
        for (String linea : resultado) {
            sb.append(linea).append("\n");
        }
        areaDatos.setText(sb.toString());
    }
}
