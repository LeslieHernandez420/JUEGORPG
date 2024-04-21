package util.interfaces;

import javax.swing.*;

public interface Interactive {

	static void printDialog(String message) {

		JOptionPane.showMessageDialog(null, message);
	}
}
