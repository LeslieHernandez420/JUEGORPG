package gui.panels;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

	private final Image img;

	public MainPanel() {

		this.img = new ImageIcon("img\\ui\\actionPanel.png").getImage();
		Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(img, 0, 0, null);
	}
}
