package modelo;

/**
 * Clase que representa una mascota en la clínica veterinaria. Contiene los
 * atributos básicos identificativos de la mascota.
 */
public class Mascota {
	/**
	 * Código único de la mascota.
	 */
	public int codigo;
	/**
	 * Nombre de la mascota.
	 */
	public String nombre;
	/**
	 * Especie a la que pertenece la mascota (perro, gato, etc.).
	 */
	public String especie;
	/**
	 * Constructor de la clase Mascota.
	 * 
	 * @param codigo  Código único de la mascota.
	 * @param nombre  Nombre de la mascota.
	 * @param especie Especie de la mascota.
	 */
	public Mascota(int codigo, String nombre, String especie) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.especie = especie;
	}

	/**
	 * Retorna una representación textual de la mascota.
	 * 
	 * @return String con los datos principales de la mascota.
	 */
	public String toString() {
		return codigo + " - " + nombre + " (" + especie + ")";
	}
}
