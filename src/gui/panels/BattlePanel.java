package gui.panels;

import gui.events.AttackButtonAction;
import gui.game.GameWindow;
import player.Player;
import util.managers.ImageManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class BattlePanel extends JPanel {

	private Image img;
	private final ActionsPanel actionsPanel;
	private final int tabIndex;
	private final ImageIcon activeIcon;
	private final ImageIcon inactiveIcon;
	private final Player player;
	private enemies.enemy enemy;
	private JPanel mainPanel;
	private JButton attackButton;
	private JButton fleeButton;
	private JScrollPane skillsPanel;
	private JPanel skillBanner;
	private GameWindow window;

	public BattlePanel(ActionsPanel actionsPanel, GameWindow window, int tabIndex, Player player, enemies.enemy enemy) {

		this.window = window;
		this.player = player;
		this.enemy = enemy;
		ImageManager imageManager = ImageManager.getInstance();
		this.img = imageManager.getImage("battlePanel");
		this.tabIndex = tabIndex;
		this.activeIcon = new ImageIcon(imageManager.getImage("battleTabActive"));
		this.inactiveIcon = new ImageIcon(imageManager.getImage("battleTabInactive"));
		this.actionsPanel = actionsPanel;
		actionsPanel.addTab("Battle", this);
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
		attackButton.addActionListener(new AttackButtonAction(player, enemy, window));
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

	private void createUIComponents() {

		skillBanner = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {

				super.paintComponent(g);
				g.drawImage(ImageManager.getInstance().getImage("skillBanner"), 0, 0, null);
			}
		};
		skillsPanel = new SkillPanel(player, enemy, window);
		attackButton = new JButton();
		attackButton.setIcon(new ImageIcon(ImageManager.getInstance().getImage("attackButtonIdle")));
		attackButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		attackButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {

				attackButton.setIcon(new ImageIcon(ImageManager.getInstance().getImage("attackButtonHover")));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {

				attackButton.setIcon(new ImageIcon(ImageManager.getInstance().getImage("attackButtonIdle")));
			}
		});
		fleeButton = new JButton();
		fleeButton.setIcon(new ImageIcon(ImageManager.getInstance().getImage("fleeButtonIdle")));
		fleeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		fleeButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {

				fleeButton.setIcon(new ImageIcon(ImageManager.getInstance().getImage("fleeButtonHover")));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {

				fleeButton.setIcon(new ImageIcon(ImageManager.getInstance().getImage("fleeButtonIdle")));
			}
		});
	}
}
