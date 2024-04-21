package gui.panels;

import gui.labels.StatLabel;
import gui.labels.TextLabel;
import player.Player;
import util.managers.ImageManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class StatusPanel extends JPanel {

	private final Image img;
	private Player player;
	private static StatusPanel instance;
	private final ActionsPanel actionsPanel;
	private final int tabIndex;
	private final ImageIcon activeIcon;
	private final ImageIcon inactiveIcon;
	private JPanel backgroundPanel;
	private JLabel expLabel;
	private JLabel strLabel;
	private JLabel defLabel;
	private JLabel goldLabel;
	private JLabel intLabel;
	private JLabel resLabel;
	private JLabel luckLabel;
	private JLabel desLabel;
	private JLabel velLabel;
	private JLabel headArmorLabel;
	private JLabel chestArmorLabel;
	private JLabel legsArmorLabel;
	private JLabel foodArmorLabel;
	private JLabel handsArmorLabel;
	private JLabel weaponArmorLabel;

	private StatusPanel(ActionsPanel actionsPanel, int tabIndex, Player player) {

		this.player = player;
		ImageManager imageManager = ImageManager.getInstance();
		img = imageManager.getImage("statusPanel");
		this.tabIndex = tabIndex;
		this.activeIcon = new ImageIcon(imageManager.getImage("statusTabActive"));
		this.inactiveIcon = new ImageIcon(imageManager.getImage("statusTabInactive"));
		this.actionsPanel = actionsPanel;
		add(backgroundPanel);
		setOpaque(false);
		setBackground(null);
		setMixingCutoutShape(new Rectangle(0, 0, 0, 0));
		actionsPanel.addTab("Status", this);
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
	}

	public void update(Player player) {

		this.player = player;
		repaint();
	}

	private boolean isActive() {

		return actionsPanel.getSelectedIndex() == tabIndex;
	}

	public static StatusPanel getInstance(ActionsPanel actionsPanel, int tabIndex, Player player) {

		if (instance == null) {

			instance = new StatusPanel(actionsPanel, tabIndex, player);
		}
		return instance;
	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}

	public void update() {

		((StatLabel)expLabel).setDisplayText("EXP: " + player.getExperience());
		((StatLabel)strLabel).setDisplayText(player.getTotalAttack());
		((StatLabel)defLabel).setDisplayText(player.getTotalDefense());
		((StatLabel)goldLabel).setDisplayText("ORO: " + player.getGold());
		((StatLabel)intLabel).setDisplayText(player.getTotalIntelligence());
		((StatLabel)resLabel).setDisplayText(player.getTotalResistance());
		((StatLabel)luckLabel).setDisplayText(player.getTotalLuck());
		((StatLabel)desLabel).setDisplayText(player.getTotalDexterity());
		((StatLabel)velLabel).setDisplayText(player.getTotalSpeed());
		String weaponName = player.getHeadArmor() != null ? player.getHeadArmor().getName() : "Ninguno";
		String headArmorName = player.getHeadArmor() != null ? player.getHeadArmor().getName() : "Ninguno";
		String chestArmorName = player.getChestArmor() != null ? player.getChestArmor().getName() : "Ninguno";
		String legsArmorName = player.getLegArmor() != null ? player.getLegArmor().getName() : "Ninguno";
		String footArmorName = player.getFootArmor() != null ? player.getFootArmor().getName() : "Ninguno";
		String handArmorName = player.getHandArmor() != null ? player.getHandArmor().getName() : "Ninguno";
		((StatLabel)weaponArmorLabel).setDisplayText(weaponName);
		((StatLabel)headArmorLabel).setDisplayText(headArmorName);
		((StatLabel)chestArmorLabel).setDisplayText(chestArmorName);
		((StatLabel)legsArmorLabel).setDisplayText(legsArmorName);
		((StatLabel)foodArmorLabel).setDisplayText(footArmorName);
		((StatLabel)handsArmorLabel).setDisplayText(handArmorName);
		repaint();
	}

	private void createUIComponents() {

		backgroundPanel = new JPanel();
		backgroundPanel.setOpaque(false);
		backgroundPanel.setBackground(null);
		backgroundPanel.setMixingCutoutShape(new Rectangle(0, 0, 0, 0));
		expLabel = new StatLabel("EXP: " + player.getExperience(),
				ImageManager.getInstance().getImage("expHolder"));
		strLabel = new StatLabel(player.getTotalAttack(),
				ImageManager.getInstance().getImage("attackHolder"));
		defLabel = new StatLabel(player.getTotalDefense(),
				ImageManager.getInstance().getImage("defenseHolder"));
		goldLabel = new StatLabel("ORO: " + player.getGold(),
				ImageManager.getInstance().getImage("goldHolder"));
		intLabel = new StatLabel(player.getTotalIntelligence(),
				ImageManager.getInstance().getImage("intelligenceHolder"));
		resLabel = new StatLabel(player.getTotalResistance(),
				ImageManager.getInstance().getImage("resHolder"));
		luckLabel = new StatLabel(player.getTotalLuck(),
				ImageManager.getInstance().getImage("luckHolder"));
		desLabel = new StatLabel(player.getTotalDexterity(),
				ImageManager.getInstance().getImage("dexterityHolder"));
		velLabel = new StatLabel(player.getTotalSpeed(),
				ImageManager.getInstance().getImage("velHolder"));
		String weaponName = player.getHeadArmor() != null ? player.getHeadArmor().getName() : "Ninguno";
		String headArmorName = player.getHeadArmor() != null ? player.getHeadArmor().getName() : "Ninguno";
		String chestArmorName = player.getChestArmor() != null ? player.getChestArmor().getName() : "Ninguno";
		String legsArmorName = player.getLegArmor() != null ? player.getLegArmor().getName() : "Ninguno";
		String footArmorName = player.getFootArmor() != null ? player.getFootArmor().getName() : "Ninguno";
		String handArmorName = player.getHandArmor() != null ? player.getHandArmor().getName() : "Ninguno";
		weaponArmorLabel = new StatLabel("ARMA: " + weaponName,
				ImageManager.getInstance().getImage("weaponHolder"));
		headArmorLabel = new StatLabel("CABEZA: " + headArmorName,
				ImageManager.getInstance().getImage("headArmorHolder"));
		chestArmorLabel = new StatLabel("PECHO: " + chestArmorName,
				ImageManager.getInstance().getImage("chestArmorHolder"));
		legsArmorLabel = new StatLabel("PIERNAS: " + legsArmorName,
				ImageManager.getInstance().getImage("legArmorHolder"));
		foodArmorLabel = new StatLabel("PIES: " + footArmorName,
				ImageManager.getInstance().getImage("feetArmorHolder"));
		handsArmorLabel = new StatLabel("MANOS: " + handArmorName,
				ImageManager.getInstance().getImage("handArmorHolder"));
	}
}
