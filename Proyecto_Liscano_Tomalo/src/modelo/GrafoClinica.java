package modelo;

import java.util.Arrays;

/**
 * Clase que representa el grafo de la clínica veterinaria.
 * Usa matriz de adyacencia para modelar las conexiones entre salas,
 * aplicando el algoritmo de Dijkstra para calcular rutas mínimas.
 */
public class GrafoClinica {

    private int[][] matriz;
    private String[] salas;
    private int numSalas;

    /**
     * Constructor del grafo.
     * 
     * @param numSalas Número total de salas en la clínica.
     */
    public GrafoClinica(int numSalas) {
        this.numSalas = numSalas;
        matriz = new int[numSalas][numSalas];
        salas = new String[numSalas];
    }

    /**
     * Define el nombre de una sala en la posición indicada.
     * 
     * @param i      Índice de la sala.
     * @param nombre Nombre que se asignará a la sala.
     */
    public void setSala(int i, String nombre) {
        salas[i] = nombre;
    }

    /**
     * Conecta dos salas en el grafo con un peso (distancia o pasos).
     * 
     * @param o    Índice de la sala origen.
     * @param d    Índice de la sala destino.
     * @param peso Valor del peso entre las salas (cantidad de pasos).
     */
    public void conectar(int o, int d, int peso) {
        matriz[o][d] = peso;
        matriz[d][o] = peso;
    }

    /**
     * Retorna el arreglo de salas registradas.
     * 
     * @return Un arreglo con los nombres de las salas.
     */
    public String[] getSalas() {
        return salas;
    }

    /**
     * Calcula las rutas mínimas desde una sala origen usando el algoritmo de Dijkstra.
     * 
     * @param inicio Índice de la sala desde la cual se calcularán las rutas.
     * @return Un arreglo de Strings indicando la distancia mínima desde la sala origen a cada sala.
     */
    public String[] rutaDesde(int inicio) {
        int[] dist = new int[numSalas];
        boolean[] visitado = new boolean[numSalas];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[inicio] = 0;

        for (int i = 0; i < numSalas - 1; i++) {
            int u = minimo(dist, visitado);
            visitado[u] = true;
            for (int v = 0; v < numSalas; v++) {
                if (!visitado[v] && matriz[u][v] != 0 &&
                    dist[u] != Integer.MAX_VALUE &&
                    dist[u] + matriz[u][v] < dist[v]) {
                    dist[v] = dist[u] + matriz[u][v];
                }
            }
        }

        String[] resultado = new String[numSalas];
        for (int i = 0; i < numSalas; i++) {
            resultado[i] = "A " + salas[i] + ": " + dist[i] + " pasos.";
        }
        return resultado;
    }

    /**
     * Método auxiliar para encontrar el nodo no visitado con menor distancia actual.
     * 
     * @param dist     Arreglo con las distancias calculadas hasta el momento.
     * @param visitado Arreglo que indica si una sala ya fue visitada.
     * @return El índice del nodo con menor distancia no visitado.
     */
    private int minimo(int[] dist, boolean[] visitado) {
        int min = Integer.MAX_VALUE;
        int index = -1;
        for (int i = 0; i < numSalas; i++) {
            if (!visitado[i] && dist[i] <= min) {
                min = dist[i];
                index = i;
            }
        }
        return index;
    }
}
