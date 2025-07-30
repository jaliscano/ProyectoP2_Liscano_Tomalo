package main;
/**
 * Clase Nodo
 * Representa un nodo en una lista enlazada simple.
 * Cada nodo almacena una solución del problema de las 8 reinas como un arreglo de enteros (reinas[1..8]), 
 * donde cada posición indica la fila donde se colocó la reina correspondiente en la columna i.
 *
 * Atributos:
 * - solucion: arreglo que representa la ubicación de las reinas en el tablero.
 * - siguiente: referencia al siguiente nodo en la lista.
 */
public class Nodo {
    // Almacena la solución como arreglo de filas (1-based: reinas[1]..reinas[8])
    int[] solucion;
    // Apunta al siguiente nodo en la lista enlazada
    Nodo siguiente;

    /**
     * Constructor que clona el arreglo recibido y lo almacena en el nodo.
     * @param solucion arreglo de posiciones de las reinas
     */
    public Nodo(int[] solucion) {
        this.solucion = solucion.clone();
        this.siguiente = null;
    }
}
