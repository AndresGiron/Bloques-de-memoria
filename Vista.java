/**
 * JUEGO BLOQUES DE MEMORIA
 * 
 * PROGRAMACION INTERACTIVA
 * 
 * DOCENTE: PAOLA RODRIGUEZ
 *  
 * @author Andres Felipe Galvis Perez
 * @author Lina Marcela Guamanga Meneses
 * @version 3.0 20/11/2019
 * 
 */

package Bloque_de_memoria;

import java.awt.Container;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



// TODO: Auto-generated Javadoc
/**
 *Clase Vista.
 *Clase que genera la ventana donde se desarrolla el juego.
 */
public class Vista extends JFrame {
	
	
	/** The dificultad. */
	private String[] dificultad = {"Basico","Avanzado"};
	
	/** The tema. */
	private String[] tema = {"Terror", "MarioBros"};
	
	/** The respuesta. */
	private String respuesta;
	
	/** The botones. */
	private List<JButton> botones = new ArrayList<JButton>();
	
	/** The visibilidad. */
	private boolean visibilidad = false;
	
	/** The icono. */
	private ImageIcon icono;
	
	/** The control. */
	private Controlador control = new Controlador();
	
	/** The juego. */
	private JPanel juego;
	
	/** The listener. */
	private Listener listener = new Listener();
	
	/** The contador. */
	private int contador=0;
	
	/** The pos tarjeta 1. */
	private int posTarjeta1=0;
	
	/** The pos tarjeta 2. */
	private int posTarjeta2=0;
	
	/** The temporizador. */
	private Temporizador temporizador = new Temporizador();
	
	
	
	/**
	 * Constructor de la clase Vista que establece
	 * la configuracion de la ventana principal
	 * 
	 */
	public Vista() 
	{
		iniciarVista();
		//configuraciones de ventana
		this.setTitle("Bloque de Memoria");
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(visibilidad);
	}
	
	
	/**
	 * 
	 * Crea el container dondese desarrolla el juego,
	 * abre una ventana de dialogo que me permite asignar la tematica del juego y 
	 * justo despues abre otra ventana de dialogo que me permite asignar la dificultad
	 * del juego. despues de ajustar estos parametros vuelve visible la ventana del juego
	 * y llama a asignarCartas  
	 * 
	 */
	private void iniciarVista() 
	{
		Container container = this.getContentPane();
		
		//Bloque de Tema
		
		icono = new ImageIcon("src/imagenes/Menu.png");
		respuesta = (String)JOptionPane.showInputDialog
				(null, "Seleccione el tema","Tema",JOptionPane.DEFAULT_OPTION,icono,tema,tema[0]);
		control.selecTipoCartas(respuesta);
	
		
		//Bloque de dificultad y creacion
		
		icono = new ImageIcon("src/imagenes/medidor.png");
		respuesta = (String)JOptionPane.showInputDialog
				(null, "Seleccione la dificultad","Dificultad",JOptionPane.DEFAULT_OPTION,icono,dificultad,dificultad[0]);
		control.crearTarjetas(respuesta);
		
		//visibilidad de la pantalla de juego
		visibilidad = true;
		
		asignarCartas();
		//bloque que asigna las imagenes del vector a la matriz 
		container.add(juego);
		
	}
	
	/**
	 * Crea el panel juego de la clase GridLayout donde estan los botones y le asigna la imagen "reverso_opt.jpg" a cada boton
	 */
	private void asignarCartas()
	{
		juego = new JPanel();
		juego.setLayout(new GridLayout(control.getFilas(),control.getColumnas()));
		
			for(int x = 0; x <(control.getFilas()*control.getColumnas());x++ ) 
			{
				botones.add(new JButton());
				
				ImageIcon cartas = new ImageIcon("src/imagenes/reverso_opt.jpg");
				botones.get(x).setIcon(cartas);
				
				juego.add(botones.get(x));
			}
			
		
		escuchas();
		
	}
	
	/**
	 * Agrega los escuchas "listener" a los botones del juego. Estos botones estan en un vector de botones
	 */
	private void escuchas() 
	{
		for(int x = 0; x < (control.getFilas()*control.getColumnas()); x++) 
		{
			botones.get(x).addActionListener(listener);	
		}
		
	}
	
	/**
	 *Con el primer clic cambia la imagen de "reverso_opt.jpg" a la imagen
	 *del boton segun el identificador de cartas en la clase control, con el segundo clic realiza la misma accion, 
	 *despues de dos segundos, compara si los botones tienen el mismo identificador, de ser asi cambia la imagen a "check.jpg"
	 *y les remueve los listener a los botones
	 *@param x indica el boton que fue presionado
	 */
	private void voltear(int x) 
	{ 
		ImageIcon cartas; 
		
		
		if(contador==0 && control.getEstadoTarjeta(x)==false) {

			cartas = new ImageIcon("src/imagenes/"+control.getTarjeta(x)+".jpg");
			botones.get(x).setIcon(cartas);
			botones.get(x).removeActionListener(listener);
			posTarjeta1=x;
			contador++;
		} 
		else if(contador==1 && control.getEstadoTarjeta(x)==false) {


			cartas = new ImageIcon("src/imagenes/"+control.getTarjeta(x)+".jpg");
			botones.get(x).setIcon(cartas);
			botones.get(x).removeActionListener(listener);
			posTarjeta2=x;
			contador++;
			
			control.pareja(posTarjeta1,posTarjeta2);
			
			temporizador.tiempo();

		}
	
}

/**
 * Clase de la escucha de los botones
 */
private class Listener implements ActionListener
{


	/**
	 * Cuando le da clic a un boton verifica que boton fue pulsado por medio de un for y ejecuta la funcion voltear
	 *
	 * @param Recibe un evento 
	 */

	public void actionPerformed(ActionEvent evento) {
		

		for(int x = 0; x< (control.getFilas()*control.getColumnas());x++) {
			
			if(evento.getSource()==botones.get(x)) {
				
				voltear(x);
			}

		}
		
	}
}

/**
 * vuelve a construir una vista para reiniciar el juego 
 * 
 */
public void reset() 
{

Vista vista = new Vista();
	
}

/**
 * Es la clase del timer para producir una demora de dos segundos
 */
public class Temporizador 
{
	
	/**
	 * verifica si las cartas tienen el mismo identificador para dejarlas boca arriba.
	 * Todo esto despues de dos segundos del clic de la segunda carta presionada
	 */
	public void tiempo() 
	{
		Timer timer = new Timer();

		TimerTask voltearTiempo = new TimerTask() 
		{

			public void run() {
 
				ImageIcon cartas;
				if (control.getEstadoTarjeta(posTarjeta1)==false && control.getEstadoTarjeta(posTarjeta2)==false && contador==2)
				{
					cartas = new ImageIcon("src/imagenes/reverso_opt.jpg");
					botones.get(posTarjeta1).setIcon(cartas);
					botones.get(posTarjeta2).setIcon(cartas);
					contador = 0;
					botones.get(posTarjeta1).addActionListener(listener);	
					botones.get(posTarjeta2).addActionListener(listener);	

				} else {

					cartas = new ImageIcon("src/imagenes/check.png");
					botones.get(posTarjeta1).setIcon(cartas);
					botones.get(posTarjeta2).setIcon(cartas);
					contador=0;
					control.ganar();
					
					/*Cuando la persona gane el juego si presiona que quiere volver a 
					 * jugar se ejecuta un dispose() y se llama al garbage colector para
					 *  poder limpiar la memoria y se llama reset().si la persona no quiere jugar mas se cierra el programa
					 *  */
					if(control.getMomento() == 2) 
					{
						int opcion = JOptionPane.showConfirmDialog(null, "¿Quieres Jugar de nuevo?", "Ganaste",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
							
							if(opcion == 0) {
								System.gc();
								dispose();
								reset();	
							}else 
							{
								System.exit(0);
							}
							
						
					}	
				}
			}
		};
		timer.schedule(voltearTiempo,1000);
		}

	}


}




   	 
    
    
	

