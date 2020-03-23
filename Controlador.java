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



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.ImageIcon;

// TODO: Auto-generated Javadoc
/**
 *Clase Controlador.
 */
public class Controlador {
	

	/** identificador.
	 * Indica la imagen de la carta por medio de un numero */
	private int identificador=1;
	
	/** momento.
	 * Indica si la persona gano o aun esta jugando */
	private int momento; 
	
	/** filas.
	 * Filas del juego */
	private int filas;
	
	/** columnas. 
	 * Columnas del juego*/
	private int columnas;
	
	/** The dificultadOut.
	 * Me indica la dificultad que despues definira cuantas filas y columnas tiene el juego */
	private int dificultadOut;
	
	
	/** The tarjetas facil. Se crea cuando el juego esta en "Basico"*/
	List<Tarjeta> tarjetasFacil = new ArrayList<Tarjeta>();
	
	/** The tarjetas dificil. se crea cuando el juego esta en "Avanzado" */
	List<Tarjeta> tarjetasDificil = new ArrayList<Tarjeta>();
	
	/** The tarjetas copia las tarjetas dependiendo de la dificultad para simplificar el programa */
	List<Tarjeta> tarjetas = new ArrayList<Tarjeta>();
	
	/*
	 * tipo = 1 -> identificador = 1 ->Cartas de Terror
	 * tipo = 2 -> identificador = 11 ->Cartas de Mario
	 * 
	 * dificultad = "Basico" -> Facil
	 * dificultad = "Avanzado(en realidad otro string cualquiera)" -> Dificil
	 * 
	 * momento = 1 -> Jugando
	 * momento = 2 -> Gano
	 *  
	 */
	
	/**
	 *Cambia el identificador segun el tipo de String que recibe la funcion "Terror" 1 y "MarioBros" 11
	 *@param tipo. Es un String 
	 */
	public void selecTipoCartas(String tipo)
	{
		if ("Terror".equals(tipo)) 
		{
			identificador = 1;
		}else 
		{
			identificador = 11;
		}
		
	}
	
	/**
	 * Crea un ArrayList de 12 tarjetas si la dificultad es basico o 
	 * crea un Array list de 20 tarjetas si la dificultad es avanzado.
	 * Despues copia el ArrayList creado en uno llamado tarjetas para su uso general y en el resto del programa.
	 * 
	 * @param dificultad. La dificultad del juego 
	 */
	public void crearTarjetas(String dificultad) 
	{
		
		if ("Basico".equals(dificultad)) 
		{
			for(int x = 0; x < 12 ;x = x + 2) 
			{
				tarjetasFacil.add(new Tarjeta(identificador));
				tarjetasFacil.add(new Tarjeta(identificador));
				identificador++;
				dificultadOut=1;
			}
			
			filas = 3;
			columnas = 4;
			randomizar(tarjetasFacil);
			
		}else 
		{
			for(int x = 0; x < 20 ;x = x + 2) 
			{
				tarjetasDificil.add(new Tarjeta(identificador));
				tarjetasDificil.add(new Tarjeta(identificador));
				identificador++;
				dificultadOut=2;
			}
			
			filas = 4;
			columnas = 5;
			randomizar(tarjetasDificil);
			
		}
		
		if(dificultadOut==1) 
		{
			for(int x = 0; x <tarjetasFacil.size();x++)
			{
				tarjetas.add(tarjetasFacil.get(x));
			}
			
		}else 
			
		{	
			for(int x = 0; x <tarjetasDificil.size();x++) 
			{
			tarjetas.add(tarjetasDificil.get(x));
			}
			
		} 
		
		
	}
	
	/**
	 * Revuelve las tarjetas de forma aleatoria 
	 * @param tarjetas. Es un ArrayList de tarjetas
	 */
	public void randomizar(List<Tarjeta> tarjetas)
	{
		Collections.shuffle(tarjetas);
	}
	
	/**
	 * 
	 *Revisa si dos identificadores de tarjeta son iguales, si lo son el estado interno de la tarjeta cambia a true
	 * @param posTarjeta1 the pos tarjeta 1
	 * @param posTarjeta2 the pos tarjeta 2
	 */
	public void pareja(int posTarjeta1, int posTarjeta2)
	{
		if (getTarjeta(posTarjeta1)==getTarjeta(posTarjeta2)&&posTarjeta1!=posTarjeta2)
		{
		tarjetas.get(posTarjeta1).setEstado(true);
		tarjetas.get(posTarjeta2).setEstado(true);
		}else 
		{
		tarjetas.get(posTarjeta1).setEstado(false);
		tarjetas.get(posTarjeta2).setEstado(false);
		}

	}
	
	/**
	 * 
	 * 
	 * Revisa si todas las cartas estan boca arriba.Si TODAS estan boca arriba la persona gano el juego,
	 * si ALGUNA esta boca abajo el juego continua
	 * 
	 */
	public void ganar()
	{
		
		for(int y = 0; y<tarjetas.size();y++) 
		{
			if (tarjetas.get(y).getEstado()==false) 
			{
				momento = 1;
				y = tarjetas.size();
				
			}else if (y + 1 == tarjetas.size() && tarjetas.get((tarjetas.size() - 1)).getEstado() == true)
			{
				momento = 2;
			}
			
		}
	}
	
	
	
	/**
	 * 
	 * 
	 * @return momento.Momento hace referncia a cuando la persona ha ganado
	 */
	public int getMomento() 
	{
		return momento;
	}

	/**
	 * 
	 * @return filas.el numero de filas que tiene el juego
	 */
	public int getFilas() {
		return filas;
	}

	/**
	 * 
	 * @return columnas.el numero de columnas que tiene el juego
	 */
	public int getColumnas() {
		return columnas;
	}
	
	/**
	 * 
	 * @return dificultad.me devuelve la dificultad del juego para decidir en un condicional el tamaño del juego
	 */
	public int getDificultad() 
	{
		return dificultadOut;
	}
	
	
	/**
	 * 
	 * 
	 * @param pos. la posicion del arreglo de la tarjeta
	 * @return tarjeta.Me devuelve el identificador de la tarjeta que sera utilizada en la clase vista para ver que imagen posee cada boton
	 */
	public int getTarjeta(int pos) 
	{
		return tarjetas.get(pos).getIdentificador();
	}
	
	/**
	 *  
	 * @param pos. la posicion del arreglo de la tarjeta
	 * @return estado. variable boolean para saber si la carta esta boca arriba(true) o boca abajo(false)
	 */
	public boolean getEstadoTarjeta(int pos) 
	{
		return tarjetas.get(pos).getEstado();
	}
	
	



}
