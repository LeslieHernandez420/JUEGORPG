package gui.panels;

import util.managers.FontManager;
import util.managers.ImageManager;

import javax.swing.*;
import java.awt.*;

public class DialogPanel extends JPanel {

	private static DialogPanel instance;
	private final Image img;
	private JPanel backgrounPanel;
	private JTextArea text;
	private JScrollPane scrollPanel;

	public static DialogPanel getInstance() {

		if (instance == null) {

			instance = new DialogPanel();
		}
		return instance;
	}

	private DialogPanel() {

		img = ImageManager.getInstance().getImage("dialogPanel");
		add(backgrounPanel);
		Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
		setPreferredSize(size);
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		scrollPanel.setOpaque(false);
		scrollPanel.getViewport().setOpaque(false);
		scrollPanel.setBorder(BorderFactory.createEmptyBorder());
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		paintBackground(g2d);
	}

	public void paintBackground(Graphics2D g2d) {

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2d.drawImage(img, 0, 0, null);
	}

	public void setText(String text) {

		this.text.setText(text);
	}

	public JTextArea getText() {

		return text;
	}

	private void createUIComponents() {

		text=new JTextArea();
		text.setFont(FontManager.getInstance().getFont("Player"));
		text.setForeground(Color.WHITE);
		text.setOpaque(false);
		text.setLineWrap(true);
		text.setWrapStyleWord(true);
		text.setAutoscrolls(true);
		text.setEditable(true);
		text.setAutoscrolls(true);
		text.setWrapStyleWord(true);
		text.setDropMode(DropMode.INSERT);
		text.requestFocus();
		scrollPanel=new JScrollPane(text);
		scrollPanel.setOpaque(false);
		scrollPanel.getViewport().setOpaque(false);
		scrollPanel.setBorder(BorderFactory.createEmptyBorder());
		scrollPanel.setAutoscrolls(true);
	}
}
