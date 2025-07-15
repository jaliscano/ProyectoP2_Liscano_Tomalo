package modelo;

public class NodoMascota {
    public Mascota mascota;
    public NodoMascota izquierda, derecha;

    public NodoMascota(Mascota mascota) {
        this.mascota = mascota;
    }
}
