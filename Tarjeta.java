/**
 * JUEGO BLOQUES DE MEMORIA
 * 
 * PROGRAMACION INTERACTIVA
 * 
 * DOCENTE: PAOLA RODRIGUEZ
 *  
 * @author Andres Felipe Galvis Perez
 * @author Lina Marcela Guamanga Meneses
 * @version 3.0 22/11/2019
 * 
 */

package Bloque_de_memoria;

// TODO: Auto-generated Javadoc
/**@author LINA MARCELA
 */
public class Tarjeta {
	
	/** The identificador.define la imagen de la carta */
	private int identificador;
	
	/** The estado.define si la carta esta boca arriba o boca abajo */
	private boolean estado;// saber si esta boca arriba o boca abajo
	
	/**
	 * 
	 * constructor de tarjeta
	 *
	 * @param entrante. Le entra un entero desde control y lo asigna como identificador de la tarjeta
	 */
	public Tarjeta(int entrante){
		estado = false;
		identificador = entrante;
	}



	/**
	 * Devuelve el estado
	 *
	 * @return estado
	 */
	public boolean getEstado() 
	{
		return estado;
	}

	/**
	 * 
	 *
	 * @param in. boolean Cambia le estado que entra desde control
	 */
	public void setEstado(boolean in) {
		estado = in;
	}

	/**
	 * devuelve el  identificador.
	 *
	 * @return identificador
	 */
	public int getIdentificador() {
		return identificador;
	}

}
