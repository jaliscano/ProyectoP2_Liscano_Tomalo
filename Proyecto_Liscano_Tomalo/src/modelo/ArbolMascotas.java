package modelo;

import java.util.ArrayList;

/**
 * Clase que representa un Árbol Binario de Búsqueda (ABB) 
 * para gestionar mascotas en la clínica veterinaria.
 * Permite insertar mascotas y recorrer el árbol en inOrden.
 */
public class ArbolMascotas {

    /**
     * Nodo raíz del árbol.
     */
    public NodoMascota raiz;

    /**
     * Inserta una nueva mascota en el árbol.
     * 
     * @param mascota La mascota que se desea insertar.
     */
    public void insertar(Mascota mascota) {
        raiz = insertarRec(raiz, mascota);
    }

    /**
     * Método recursivo para insertar una mascota en el árbol.
     * 
     * @param actual  El nodo actual desde donde se compara.
     * @param mascota La mascota que se desea insertar.
     * @return El nodo actualizado después de la inserción.
     */
    private NodoMascota insertarRec(NodoMascota actual, Mascota mascota) {
        if (actual == null) {
            return new NodoMascota(mascota);
        } else {
            if (mascota.codigo < actual.mascota.codigo) {
                actual.izquierda = insertarRec(actual.izquierda, mascota);
            } else {
                if (mascota.codigo > actual.mascota.codigo) {
                    actual.derecha = insertarRec(actual.derecha, mascota);
                }
            }
            return actual;
        }
    }

    /**
     * Realiza el recorrido inOrden del árbol y almacena 
     * las mascotas ordenadas en una lista.
     * 
     * @param lista Lista donde se almacenarán las mascotas ordenadas.
     */
    public void inOrden(ArrayList<Mascota> lista) {
        inOrdenRec(raiz, lista);
    }

    /**
     * Método recursivo que realiza el recorrido inOrden del árbol.
     * 
     * @param actual Nodo actual del recorrido.
     * @param lista  Lista donde se agregan las mascotas visitadas.
     */
    private void inOrdenRec(NodoMascota actual, ArrayList<Mascota> lista) {
        if (actual != null) {
            inOrdenRec(actual.izquierda, lista);
            lista.add(actual.mascota);
            inOrdenRec(actual.derecha, lista);
        }
    }
}
