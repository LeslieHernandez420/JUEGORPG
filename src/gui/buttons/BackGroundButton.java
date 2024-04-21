package gui.buttons;

import game.Game;
import gui.SelectFileWindow;
import util.managers.FontManager;

import javax.swing.*;
import java.awt.*;

public class BackGroundButton extends JButton {

	private final String text;
	private final int slot;
	private final SelectFileWindow window;
	private final Font myfont;

	public BackGroundButton(SelectFileWindow window, String text, int slot) {

		this.window = window;
		this.text = text;
		this.slot = slot;
		FontManager fontManager = FontManager.getInstance();
		myfont = fontManager.getFont("Game File");
		setContentAreaFilled(false);
		setBorderPainted(false);
		setFocusPainted(true);
		setOpaque(false);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		addActionListener(e -> {

			window.dispose();
			new Game(slot);
		});
	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		Image imagen = new ImageIcon("img\\23.png").getImage();
		int width = (int) (g.getFontMetrics(myfont).stringWidth(text) * 1.5);
		int height = g.getFontMetrics(myfont).getHeight() + 5;
		Dimension size = new Dimension(Math.max(width, 286), height);
		setSize(size);
		setPreferredSize(size);
		setMaximumSize(size);
		setMinimumSize(size);
		g.drawImage(imagen, 0, 0, size.width, size.height, null);
		g.setColor(Color.BLACK);
		g.setFont(myfont);
		g.drawString(text, ((getWidth() / 2) - (g.getFontMetrics(myfont).stringWidth(text) / 2)), getHeight() - (g.getFontMetrics(myfont).getHeight() / 2) + 4);
	}

	@Override
	public String getText() {

		return text;
	}

	public int getSlot() {

		return slot;
	}

	public Font getMyfont() {

		return myfont;
	}

	public SelectFileWindow getWindow() {

		return window;
	}
}
