package modelo;

import java.util.Arrays;

public class GrafoClinica {
    private int[][] matriz;
    private String[] salas;
    private int numSalas;

    public GrafoClinica(int numSalas) {
        this.numSalas = numSalas;
        matriz = new int[numSalas][numSalas];
        salas = new String[numSalas];
    }

    public void setSala(int i, String nombre) {
        salas[i] = nombre;
    }

    public void conectar(int o, int d, int peso) {
        matriz[o][d] = peso;
        matriz[d][o] = peso;
    }

    public String[] getSalas() {
        return salas;
    }

    public String[] rutaDesde(int inicio) {
        int[] dist = new int[numSalas];
        boolean[] visitado = new boolean[numSalas];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[inicio] = 0;

        for (int i = 0; i < numSalas - 1; i++) {
            int u = minimo(dist, visitado);
            visitado[u] = true;
            for (int v = 0; v < numSalas; v++) {
                if (!visitado[v] && matriz[u][v] != 0 && dist[u] != Integer.MAX_VALUE &&
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

    private int minimo(int[] dist, boolean[] visitado) {
        int min = Integer.MAX_VALUE, index = -1;
        for (int i = 0; i < numSalas; i++) {
            if (!visitado[i] && dist[i] <= min) {
                min = dist[i];
                index = i;
            }
        }
        return index;
    }
}
