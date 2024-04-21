package gui.panels;

import items.Item;

import javax.swing.*;

public class itemDetailPanel extends JPanel{

	private Item item;
	private JPanel backgroundPanel;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JLabel nameLabel;

	public itemDetailPanel(Item item) {
		this.item = item;
		nameLabel.setText(item.getName());
	}
}