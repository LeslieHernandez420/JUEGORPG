package gui.labels;

import javax.swing.*;
import java.awt.*;

public class SpriteLabel extends JLabel {

	private Image img;

	public SpriteLabel(Image img) {

		this.img = img;
		Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		setAlignmentX(CENTER_ALIGNMENT);
		setAlignmentY(CENTER_ALIGNMENT);
		setVerticalAlignment(CENTER);
		setHorizontalAlignment(CENTER);
	}

	public void updateImage(Image img) {

		this.img= img;
		Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		setAlignmentX(CENTER_ALIGNMENT);
		setAlignmentY(CENTER_ALIGNMENT);
		setVerticalAlignment(CENTER);
		setHorizontalAlignment(CENTER);
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(img, 0, 0, null);
	}
}
