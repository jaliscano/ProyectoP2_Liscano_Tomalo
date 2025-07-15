package modelo;

public class Mascota {
    public int codigo;
    public String nombre;
    public String especie;

    public Mascota(int codigo, String nombre, String especie) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.especie = especie;
    }

    public String toString() {
        return codigo + " - " + nombre + " (" + especie + ")";
    }
}
