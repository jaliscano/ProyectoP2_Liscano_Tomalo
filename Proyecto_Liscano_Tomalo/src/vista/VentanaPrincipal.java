package vista;

import controlador.ControladorClinica;
import modelo.Mascota;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Clase VentanaPrincipal que representa la interfaz gráfica (GUI) 
 * de la clínica veterinaria. Permite gestionar el registro de mascotas, 
 * visualizar información y consultar rutas internas entre salas.
 */
public class VentanaPrincipal extends JFrame {

    private ControladorClinica controlador;
    private JTextArea areaDatos;

    /**
     * Constructor que inicializa la ventana principal con todos sus componentes.
     */
    public VentanaPrincipal() {
        controlador = new ControladorClinica();
        setTitle("🐾 Clínica Veterinaria 🐶");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(new Color(230, 245, 250));
        panelBotones.setLayout(new GridLayout(5, 1, 10, 10));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnRegistrar = crearBoton(" Registrar Mascota");
        JButton btnVerMascotas = crearBoton(" Ver Mascotas");
        JButton btnSalas = crearBoton(" Ver Salas");
        JButton btnRuta = crearBoton(" Calcular Ruta");
        JButton btnSalir = crearBoton(" Salir");

        panelBotones.add(btnRegistrar);
        panelBotones.add(btnVerMascotas);
        panelBotones.add(btnSalas);
        panelBotones.add(btnRuta);
        panelBotones.add(btnSalir);

        areaDatos = new JTextArea();
        areaDatos.setEditable(false);
        areaDatos.setFont(new Font("Monospaced", Font.PLAIN, 14));
        areaDatos.setBackground(new Color(250, 250, 250));
        areaDatos.setBorder(BorderFactory.createTitledBorder("Información"));

        JScrollPane scroll = new JScrollPane(areaDatos);

        add(scroll, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.WEST);

        // Eventos de botones
        btnRegistrar.addActionListener(e -> registrarMascota());
        btnVerMascotas.addActionListener(e -> mostrarMascotas());
        btnSalas.addActionListener(e -> mostrarSalas());
        btnRuta.addActionListener(e -> calcularRuta());
        btnSalir.addActionListener(e -> System.exit(0));
    }

    /**
     * Crea un botón personalizado con estilo predeterminado.
     * 
     * @param texto Texto del botón.
     * @return Botón con estilo aplicado.
     */
    private JButton crearBoton(String texto) {
        JButton btn = new JButton(texto);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setBackground(new Color(30, 60, 120));
        btn.setForeground(Color.WHITE);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return btn;
    }

    /**
     * Método que solicita los datos de una mascota al usuario y la registra.
     */
    private void registrarMascota() {
        try {
            int codigo = Integer.parseInt(JOptionPane.showInputDialog("Código:"));
            String nombre = JOptionPane.showInputDialog("Nombre:");
            String especie = JOptionPane.showInputDialog("Especie:");
            controlador.registrarMascota(codigo, nombre, especie);
            areaDatos.setText("✅ Mascota registrada con éxito.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al registrar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Muestra la lista de mascotas registradas en el área de datos.
     */
    private void mostrarMascotas() {
        ArrayList<Mascota> lista = controlador.obtenerMascotas();
        if (lista.isEmpty()) {
            areaDatos.setText("⚠️ No hay mascotas registradas.");
            return;
        }
        StringBuilder sb = new StringBuilder("🐾 Mascotas registradas:\n\n");
        for (Mascota m : lista) {
            sb.append(m).append("\n");
        }
        areaDatos.setText(sb.toString());
    }

    /**
     * Muestra la lista de salas de la clínica en el área de datos.
     */
    private void mostrarSalas() {
        String[] salas = controlador.obtenerSalas();
        StringBuilder sb = new StringBuilder(" Salas de la clínica:\n\n");
        for (int i = 0; i < salas.length; i++) {
            sb.append(i).append(". ").append(salas[i]).append("\n");
        }
        areaDatos.setText(sb.toString());
    }

    /**
     * Solicita al usuario una sala inicial y muestra la ruta interna calculada.
     */
    private void calcularRuta() {
        String[] salas = controlador.obtenerSalas();
        String seleccion = (String) JOptionPane.showInputDialog(this, "Selecciona sala inicial:",
                "Ruta Interna", JOptionPane.PLAIN_MESSAGE, null, salas, salas[0]);
        if (seleccion == null) return;
        int indice = java.util.Arrays.asList(salas).indexOf(seleccion);

        String[] resultado = controlador.calcularRuta(indice);
        StringBuilder sb = new StringBuilder(" Ruta desde " + seleccion + ":\n\n");
        for (String linea : resultado) {
            sb.append(linea).append("\n");
        }
        areaDatos.setText(sb.toString());
    }
}
