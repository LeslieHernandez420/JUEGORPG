package gui.labels;

import util.managers.FontManager;
import util.managers.ImageManager;

import javax.swing.*;
import java.awt.*;

public class TextLabel extends JLabel {

	private final Image image;
	private String displayText;

	public TextLabel(String displayText, String imageKey) {

		this.displayText = displayText;
		this.image = ImageManager.getInstance().getImage(imageKey);
		Dimension size = new Dimension(image.getWidth(null), image.getHeight(null) + 10);
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		Font font = FontManager.getInstance().getFont("Player");
		setForeground(Color.BLACK);
		setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		setFont(font);
	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		if (image != null) {
			g2d.drawImage(image, 0, 10, null);
			int textPositionY = image.getHeight(null) / 2 + 10 + g2d.getFontMetrics().getHeight() / 4;
			int textPositionX = (image.getWidth(null) - g2d.getFontMetrics().stringWidth(displayText)) / 2;
			g2d.drawString(displayText, textPositionX, textPositionY);
		} else {
			int textPositionY = g2d.getFontMetrics().getHeight() / 4;
			int textPositionX = g2d.getFontMetrics().stringWidth(displayText) / 2;
			g2d.drawString(displayText, textPositionX, textPositionY);
		}
	}

	public Image getImage() {

		return image;
	}

	public void setDisplayText(String displayText) {

		this.displayText = displayText;
	}
}
