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

import java.awt.EventQueue;
import java.util.Timer;

import javax.swing.UIManager;


public class Principal {


	public static void main(String[] args) {
		
		try {
			String className = UIManager.getCrossPlatformLookAndFeelClassName();
			UIManager.setLookAndFeel(className);
			}
			catch (Exception e) {}
		
			EventQueue.invokeLater(new Runnable() {
			public void run() {Vista vista = new Vista();
			}});
			
		

	}

}
