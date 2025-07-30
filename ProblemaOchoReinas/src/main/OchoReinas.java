package main;
/**
 * Clase OchoReinas
 * Implementa el algoritmo de vuelta atrás (backtracking) para resolver el problema de las 8 reinas.
 * Utiliza una lista enlazada simple para almacenar todas las soluciones encontradas, siguiendo el modelo propuesto por Joyanes.
 *
 * Atributos:
 * - N: tamaño del tablero (8x8)
 * - n: tamaño real del arreglo de reinas (incluye posición 0 no usada)
 * - reinas: arreglo que representa la fila en la que se colocó cada reina en la columna i (1-based)
 * - listaSoluciones: instancia de la lista enlazada simple donde se almacenan las soluciones
 *
 * Métodos:
 * - calcularSoluciones(): inicializa el proceso y busca todas las soluciones posibles.
 * - buscarReinas(int i): método recursivo para intentar colocar la reina i en cada fila de la columna i.
 * - valido(int i): verifica si la reina en la columna i no es atacada por ninguna reina anterior.
 * - getCantidadSoluciones(): retorna el número de soluciones encontradas.
 * - getSolucion(int n): transforma la solución n en una matriz 8x8 para facilitar la visualización (por ejemplo, en una GUI).
 */
public class OchoReinas {
    final int N = 8;          // Tamaño del tablero (8x8)
    final int n = N + 1;      // Tamaño del arreglo de reinas (posiciones 1..8 usadas)
    int[] reinas = new int[n];// reinas[i]: fila en la que se colocó la reina en columna i

    // Lista enlazada para almacenar todas las soluciones encontradas
    private ListaEnlazadaSimple listaSoluciones = new ListaEnlazadaSimple();

    /**
     * Método principal para calcular todas las soluciones del problema de las 8 reinas.
     * Reinicia la lista de soluciones y comienza la búsqueda recursiva desde la reina 1.
     */
    public void calcularSoluciones() {
        listaSoluciones = new ListaEnlazadaSimple(); // Reinicia la lista
        buscarReinas(1); // Comienza el proceso recursivo con la primera reina
    }

    /**
     * Algoritmo recursivo de vuelta atrás para colocar la reina número i.
     * Para cada fila, intenta colocar la reina y verifica si la colocación es válida.
     * Si se logra colocar todas las reinas, almacena la solución en la lista.
     * @param i número de la reina (columna actual)
     */
    private void buscarReinas(int i) {
        int j = 0;
        do {
            j++;
            reinas[i] = j; // Coloca la reina i en la fila j (columna fija = i)
            if (valido(i)) {
                if (i < N) {
                    buscarReinas(i + 1); // Intentar colocar la siguiente reina
                } else {
                    // Se han colocado todas las reinas: almacenar solución
                    listaSoluciones.agregar(reinas);
                }
            }
            if (i < N) reinas[i] = 0; // Vuelta atrás: reiniciar ubicación para probar otra fila
        } while (j < N);
    }

    /**
     * Verifica si la reina recién colocada en la columna i es atacada por alguna reina anterior.
     * Revisa que no esté en la misma fila ni en ninguna de las dos diagonales.
     * @param i número de la reina recién colocada
     * @return true si la posición es válida, false si es atacada
     */
    private boolean valido(int i) {
        boolean libre = true;
        for (int r = 1; r <= i - 1; r++) {
            // No debe estar en la misma fila
            libre = libre && (reinas[i] != reinas[r]);
            // No debe estar en la misma diagonal (\)
            libre = libre && ((i + reinas[i]) != (r + reinas[r]));
            // No debe estar en la misma diagonal (/)
            libre = libre && ((i - reinas[i]) != (r - reinas[r]));
        }
        return libre;
    }

    /**
     * Retorna la cantidad de soluciones encontradas (nodos en la lista enlazada).
     * @return número de soluciones
     */
    public int getCantidadSoluciones() {
        return listaSoluciones.getTamaño();
    }

    /**
     * Convierte la solución n en una matriz 8x8, donde '1' representa una reina y '0' una celda vacía.
     * Esto facilita la visualización en consola o GUI.
     * @param n índice de la solución
     * @return matriz de 8x8 con la ubicación de las reinas
     */
    public int[][] getSolucion(int n) {
        int[] solucion = listaSoluciones.getSolucion(n);
        if (solucion == null) return null;
        int[][] tablero = new int[N][N];
        for (int col = 1; col <= N; col++) {
            int fila = solucion[col] - 1; // Convertir de 1-based a 0-based
            if (fila >= 0 && fila < N) {
                tablero[fila][col - 1] = 1;
            }
        }
        return tablero;
    }
}
