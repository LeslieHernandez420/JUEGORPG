package gui;

import gui.buttons.BackGroundButton;
import gui.panels.BackgroundPanel;
import player.Player;
import util.managers.FileManager;
import util.managers.FontManager;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;

public class SelectFileWindow extends JFrame {
	private JPanel rootPane;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JLabel gameName;
	private JLabel icono;
	private final FontManager fontManager;

	public static void main(String[] args) {

		SelectFileWindow selectFileWindow = new SelectFileWindow();
	}

	public SelectFileWindow() {
		//Iniciamos la caché de fuentes
		fontManager = FontManager.getInstance();
		//Título de la ventana
		setTitle("Ventana de selección de archivo");
		//Cambiamos la fuente de los elementos de la ventana
		changeFont();
		//Añadimos los componentes a la ventana
		setContentPane(rootPane);
		//Operación por defecto al cerrar la ventana
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Tamaño de la ventana
		pack();
		//Hacer visible la ventana
		setVisible(true);
		//Ventana no redimensionable
		setResizable(false);
		//Centrar la ventana
		setLocationRelativeTo(null);
	}

	/**
	 * Método que cambia la fuente de los elementos de la ventana
	 */
	private void changeFont() {

		//Colocamos opaco en falso para que se vea el fondo de la ventana
		gameName.setOpaque(false);
		//Colocamos el fondo de la etiqueta en nulo
		gameName.setBackground(null);
		gameName.setText("HOLA");
		//Colocamos el icono de la etiqueta con la imagen 23.png
		gameName.setIcon(new ImageIcon("img\\23.png"));
		//Cambiamos la fuente de la etiqueta
		gameName.setFont(fontManager.getFont("Game Title"));
		//Agregamos a la etiqueta vaciá la imagen 17.png
		icono.setIcon(new ImageIcon("img\\17.png"));
	}

	/**
	 * Método que crea los componentes de la ventana
	 */
	private void createUIComponents() {

		rootPane = new BackgroundPanel(new ImageIcon("img\\back_1.png").getImage());
		File load;
		//Cargar los archivos de guardado, si existen y si no, crear un botón con el texto "--LIBRE--"
		//Tenemos un máximo de 3 archivos de guardados
		for (int i = 1; i <= 3; i++) {
			//Cargamos el archivo de guardado correspondiente al slot i (game1.dat, game2.dat, game3.dat)
			load = new File("files\\game" + i + ".dat");
			if (load.exists()) {
				//Si el archivo existe, tratamos de cargar el archivo y si no se puede, mostramos un mensaje de error
				try {
					Player player = FileManager.loadGame(load);
					switch (i) {
						case 1 -> button1 = new BackGroundButton(this, player.toString(), i);
						case 2 -> button2 = new BackGroundButton(this, player.toString(), i);
						case 3 -> button3 = new BackGroundButton(this, player.toString(), i);
					}
				} catch (FileNotFoundException e) {
					JOptionPane.showMessageDialog(this, "Error al cargar el archivo",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				//Si el archivo no existe, creamos un botón con el texto "--LIBRE--"
				switch (i) {
					case 1 -> button1 = new BackGroundButton(this, "--LIBRE--", i);
					case 2 -> button2 = new BackGroundButton(this, "--LIBRE--", i);
					case 3 -> button3 = new BackGroundButton(this, "--LIBRE--", i);
				}
			}
		}
	}

	public JButton getButton1() {

		return button1;
	}

	public JButton getButton2() {

		return button2;
	}

	public JButton getButton3() {

		return button3;
	}
}
