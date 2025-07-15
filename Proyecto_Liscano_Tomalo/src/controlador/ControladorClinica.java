package controlador;

import modelo.*;

import java.util.ArrayList;

public class ControladorClinica {
    private ArbolMascotas arbol;
    private GrafoClinica grafo;

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

    public void registrarMascota(int codigo, String nombre, String especie) {
        arbol.insertar(new Mascota(codigo, nombre, especie));
    }

    public ArrayList<Mascota> obtenerMascotas() {
        ArrayList<Mascota> lista = new ArrayList<>();
        arbol.inOrden(lista);
        return lista;
    }

    public String[] obtenerSalas() {
        return grafo.getSalas();
    }

    public String[] calcularRuta(int inicio) {
        return grafo.rutaDesde(inicio);
    }
}
