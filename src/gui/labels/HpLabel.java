package gui.labels;

import characters.BasicCharacter;
import enemies.enemy;
import player.Player;
import util.managers.FontManager;
import util.managers.ImageManager;

import javax.swing.*;
import java.awt.*;

public class HpLabel extends JLabel {

	private BasicCharacter character;
	private Image image;
	private String text;

	public HpLabel(Player character) {

		this.character = character;
		init();
		Font font = FontManager.getInstance().getFont("Player");
		Dimension size = new Dimension(image.getWidth(null), image.getHeight(null) + 10);
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		setFont(font);
	}

	public HpLabel(enemy enemy) {

		this.character = enemy;
		init();
		Font font = FontManager.getInstance().getFont("Player");
		Dimension size = new Dimension(image.getWidth(null), image.getHeight(null) + 10);
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		setFont(font);
	}

	private void init() {

		ImageManager imageManager = ImageManager.getInstance();
		text = String.format("%d/%d", character.getHp(), character.getMaxHp());
		double hpPercentage = (double) character.getHp() / character.getMaxHp();
		Color color;
		if (hpPercentage >= .8) {
			image = imageManager.getImage("hp100");
			color = new Color(0, 0, 0, 255);
		} else if (hpPercentage > 0.6) {
			image = imageManager.getImage("hp80");
			color = new Color(0, 0, 0, 255);
		} else if (hpPercentage > 0.4) {
			image = imageManager.getImage("hp60");
			color = new Color(109, 109, 109, 255);
		} else if (hpPercentage > 0.2) {
			image = imageManager.getImage("hp40");
			color = new Color(109, 109, 109, 255);
		} else if (hpPercentage > 0) {
			image = imageManager.getImage("hp20");
			color = new Color(109, 109, 109, 255);
		} else {
			image = imageManager.getImage("hp0");
			color = new Color(255, 255, 255, 255);
		}
		setForeground(color);
	}

	public void updateCharacter(BasicCharacter character) {

		this.character = character;
		init();
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2d.drawImage(image, 0, 10, null);
		int textPositionY = image.getHeight(null) / 2 + 10 + g2d.getFontMetrics().getHeight() / 4;
		int textPositionX = ((image.getWidth(null) - 28) / 2) + 28 - (g2d.getFontMetrics().stringWidth(text) / 2);
		g2d.translate(textPositionX, textPositionY);
		BasicStroke contorno = new BasicStroke(5.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
		g2d.setStroke(contorno);
		g2d.drawString(text, 0, 0);
	}
}
