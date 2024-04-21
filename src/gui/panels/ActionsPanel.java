package gui.panels;

import gui.tabs.GameTab;

import javax.swing.*;
import java.awt.*;

public class ActionsPanel extends JTabbedPane {

	private static ActionsPanel instance;

	public static ActionsPanel getInstance() {

		if (instance == null) {

			instance = new ActionsPanel();
		}
		return instance;
	}

	private ActionsPanel() {

		super();
		Dimension size = new Dimension(1280, 338);
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		setOpaque(false);
		setBorder(null);
		setUI(new GameTab());
	}

	public void setTabIcon(int index, ImageIcon icon) {

		JLabel label = new JLabel(icon);
		Dimension size = new Dimension(icon.getIconWidth(), icon.getIconHeight());
		label.setPreferredSize(size);
		label.setMinimumSize(size);
		label.setMaximumSize(size);
		label.setSize(size);
		setTabComponentAt(index, label);
		addChangeListener(e -> {
			if (getSelectedIndex() == index) {
				label.setIcon(icon);
			}
		});
	}
}
