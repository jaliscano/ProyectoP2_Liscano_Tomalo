package modelo;

import java.util.LinkedList;
import java.util.Queue;

class ColaAtencion {
    private Queue<Mascota> cola;

    public ColaAtencion() {
        cola = new LinkedList<>();
    }

    public void agregar(Mascota mascota) {
        cola.offer(mascota);
    }

    public Mascota atender() {
        return cola.poll();
    }

    public String verCola() {
        return cola.isEmpty() ? "Sin pacientes en espera." : cola.toString();
    }
}
