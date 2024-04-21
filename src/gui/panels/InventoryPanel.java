package gui.panels;

import gui.game.GameWindow;
import items.Item;
import player.Player;
import util.managers.ImageManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class InventoryPanel extends JPanel{

	private Image img;
	private final ActionsPanel actionsPanel;
	private final int tabIndex;
	private final ImageIcon activeIcon;
	private final ImageIcon inactiveIcon;
	private final Player player;
	private JPanel mainPanel;
	private JScrollPane weapons;

	public InventoryPanel(ActionsPanel actionsPanel, GameWindow window, int tabIndex, Player player) {

		this.player=player;
		ImageManager imageManager = ImageManager.getInstance();
		this.img = imageManager.getImage("battlePanel");
		this.tabIndex = tabIndex;
		this.activeIcon = new ImageIcon(imageManager.getImage("battleTabActive"));
		this.inactiveIcon = new ImageIcon(imageManager.getImage("battleTabInactive"));
		this.actionsPanel = actionsPanel;
		actionsPanel.addTab("Inventory", this);
		setMixingCutoutShape(new Rectangle(0, 0, 0, 0));
		actionsPanel.setTabIcon(tabIndex, isActive() ? activeIcon : inactiveIcon);
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {

				actionsPanel.setTabIcon(tabIndex, activeIcon);
			}

			@Override
			public void componentHidden(ComponentEvent e) {

				actionsPanel.setTabIcon(tabIndex, inactiveIcon);
			}
		});
		add(mainPanel);
		Dimension dimension=new Dimension(990, 380);
		setSize(dimension);
		setMaximumSize(dimension);
		setMinimumSize(dimension);
		setPreferredSize(dimension);
		for (int i=0; i<player.getInventory().getItems().size(); i++){

			weapons.add(new itemDetailPanel(player.getInventory().getItems().get(i)));
		}
	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(img, 0, 0, getWidth(), getHeight(), null);
	}

	private boolean isActive() {

		return actionsPanel.getSelectedIndex() == tabIndex;
	}
}
