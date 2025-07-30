package modelo;

/**
 * Clase que representa un nodo dentro del Árbol Binario de Búsqueda (ABB),
 * utilizado para almacenar mascotas en la clínica veterinaria.
 */
public class NodoMascota {

	/**
	 * Mascota almacenada en este nodo.
	 */
	public Mascota mascota;

	/**
	 * Referencia al nodo hijo izquierdo.
	 */
	public NodoMascota izquierda;

	/**
	 * Referencia al nodo hijo derecho.
	 */
	public NodoMascota derecha;

	/**
	 * Constructor que crea un nodo con una mascota dada.
	 * 
	 * @param mascota Mascota que se almacenará en el nodo.
	 */
	public NodoMascota(Mascota mascota) {
		this.mascota = mascota;
	}
}
