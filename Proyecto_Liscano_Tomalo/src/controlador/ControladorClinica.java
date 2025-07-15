package controlador;

import modelo.*;
import java.util.ArrayList;

/**
 * Clase ControladorClinica que gestiona la lógica principal del sistema veterinario.
 * Controla el registro de mascotas y la gestión de las salas internas de la clínica.
 * Actúa como intermediario entre la vista y el modelo.
 */
public class ControladorClinica {

    private ArbolMascotas arbol;
    private GrafoClinica grafo;

    /**
     * Constructor que inicializa el árbol de mascotas y el grafo de la clínica.
     * Se definen las salas principales y sus conexiones internas.
     */
    public ControladorClinica() {
        arbol = new ArbolMascotas();
        grafo = new GrafoClinica(5);

        grafo.setSala(0, "Recepción");
        grafo.setSala(1, "Consultorio");
        grafo.setSala(2, "Laboratorio");
        grafo.setSala(3, "Rayos X");
        grafo.setSala(4, "Quirófano");

        grafo.conectar(0, 1, 2);
        grafo.conectar(1, 2, 3);
        grafo.conectar(1, 3, 4);
        grafo.conectar(2, 4, 5);
        grafo.conectar(3, 4, 2);
    }

    /**
     * Registra una nueva mascota en el árbol binario de búsqueda.
     * 
     * @param codigo  Código único de la mascota.
     * @param nombre  Nombre de la mascota.
     * @param especie Especie de la mascota.
     */
    public void registrarMascota(int codigo, String nombre, String especie) {
        arbol.insertar(new Mascota(codigo, nombre, especie));
    }

    /**
     * Obtiene la lista de todas las mascotas registradas, ordenadas por código.
     * 
     * @return Lista de objetos Mascota ordenados.
     */
    public ArrayList<Mascota> obtenerMascotas() {
        ArrayList<Mascota> lista = new ArrayList<>();
        arbol.inOrden(lista);
        return lista;
    }

    /**
     * Devuelve el arreglo de nombres de las salas registradas en el grafo.
     * 
     * @return Arreglo de nombres de salas.
     */
    public String[] obtenerSalas() {
        return grafo.getSalas();
    }

    /**
     * Calcula las rutas mínimas desde una sala específica a las demás usando el grafo.
     * 
     * @param inicio Índice de la sala desde donde se calcula la ruta.
     * @return Arreglo de Strings con las distancias mínimas hacia cada sala.
     */
    public String[] calcularRuta(int inicio) {
        return grafo.rutaDesde(inicio);
    }
}
