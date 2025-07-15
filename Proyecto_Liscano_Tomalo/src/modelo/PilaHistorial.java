package modelo;

import java.util.Stack;

class PilaHistorial {
    private Stack<String> pila;

    public PilaHistorial() {
        pila = new Stack<>();
    }

    public void push(String dato) {
        pila.push(dato);
    }

    public String toString() {
        return pila.isEmpty() ? "Sin historial." : pila.toString();
    }
}
