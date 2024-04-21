package gui.panels;

import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {
	private final Image img;

	public BackgroundPanel(Image img) {

		this.img = img;
		Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
	}

	public void paintComponent(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(img, 0, 0, null);
		g2d.setColor(Color.ORANGE);
		g2d.setStroke(new BasicStroke(5));
		g2d.drawRect((img.getWidth(null)/2)-(112/2), 7, 114,114);
	}
}
