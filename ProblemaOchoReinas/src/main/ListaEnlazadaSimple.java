package main;
/**
 * Clase ListaEnlazadaSimple
 * Implementa una lista enlazada simple para almacenar todas las soluciones encontradas del problema de las 8 reinas.
 * Cada elemento es un objeto Nodo, que contiene una solución en forma de arreglo de filas.
 *
 * Atributos:
 * - cabeza: referencia al primer nodo de la lista.
 * - cola: referencia al último nodo de la lista.
 * - tamaño: cantidad de soluciones almacenadas (nodos en la lista).
 *
 * Métodos:
 * - agregar(int[] solucion): agrega una nueva solución al final de la lista.
 * - getTamaño(): retorna la cantidad de soluciones almacenadas.
 * - getSolucion(int n): retorna el arreglo de la solución ubicada en la posición n.
 */
public class ListaEnlazadaSimple {
    private Nodo cabeza; // Primer nodo de la lista
    private Nodo cola;   // Último nodo de la lista
    private int tamaño;  // Número de elementos en la lista

    /**
     * Constructor: inicializa una lista vacía.
     */
    public ListaEnlazadaSimple() {
        cabeza = null;
        cola = null;
        tamaño = 0;
    }

    /**
     * Agrega una solución al final de la lista.
     * @param solucion arreglo que representa la solución encontrada
     */
    public void agregar(int[] solucion) {
        Nodo nuevo = new Nodo(solucion);
        if (cabeza == null) {
            // Si la lista está vacía, el nuevo nodo es cabeza y cola
            cabeza = nuevo;
            cola = nuevo;
        } else {
            // Si la lista no está vacía, enlaza el nuevo nodo al final
            cola.siguiente = nuevo;
            cola = nuevo;
        }
        tamaño++;
    }

    /**
     * Retorna el número de soluciones almacenadas en la lista.
     * @return tamaño de la lista (soluciones almacenadas)
     */
    public int getTamaño() {
        return tamaño;
    }

    /**
     * Retorna el arreglo de filas correspondiente a la solución n.
     * Si n es inválido, devuelve null.
     * @param n índice de la solución (0-based)
     * @return arreglo de la solución, o null si no existe
     */
    public int[] getSolucion(int n) {
        Nodo actual = cabeza;
        int i = 0;
        // Recorre la lista hasta llegar al nodo n
        while (actual != null && i < n) {
            actual = actual.siguiente;
            i++;
        }
        if (actual != null) return actual.solucion.clone();
        return null;
    }
}
