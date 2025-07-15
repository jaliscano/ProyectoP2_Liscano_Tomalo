package modelo;

import java.util.ArrayList;

public class ArbolMascotas {
    public NodoMascota raiz;

    public void insertar(Mascota mascota) {
        raiz = insertarRec(raiz, mascota);
    }

    private NodoMascota insertarRec(NodoMascota actual, Mascota mascota) {
        if (actual == null) return new NodoMascota(mascota);
        if (mascota.codigo < actual.mascota.codigo) {
            actual.izquierda = insertarRec(actual.izquierda, mascota);
        } else if (mascota.codigo > actual.mascota.codigo) {
            actual.derecha = insertarRec(actual.derecha, mascota);
        }
        return actual;
    }

    public void inOrden(ArrayList<Mascota> lista) {
        inOrdenRec(raiz, lista);
    }

    private void inOrdenRec(NodoMascota actual, ArrayList<Mascota> lista) {
        if (actual != null) {
            inOrdenRec(actual.izquierda, lista);
            lista.add(actual.mascota);
            inOrdenRec(actual.derecha, lista);
        }
    }
}
