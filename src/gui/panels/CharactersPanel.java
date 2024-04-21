package gui.panels;

import enemies.bats.TinyBat;
import gui.game.GameWindow;
import gui.labels.SpriteLabel;
import player.Player;
import util.managers.ImageManager;

import javax.swing.*;
import java.awt.*;

public class CharactersPanel extends JPanel {

	private static CharactersPanel instance;
	private final Image img;
	private Player player;
	private enemies.enemy enemy;
	private JPanel backgroundPanel;
	private JPanel dialogPanel;
	private JPanel playerSpritePanel;
	private JPanel enemySpritePanel;
	private JLabel playerSprite;
	private JLabel enemySprite;
	private final GameWindow window;

	public static CharactersPanel getInstance(Player player, enemies.enemy enemy, GameWindow window) {


		if (instance == null) {

			instance = new CharactersPanel(player, enemy, window);
		}else {
			instance.setEnemy(enemy);
			instance.setPlayer(player);
		}
		return instance;
	}

	private CharactersPanel(Player player, enemies.enemy enemy, GameWindow window) {

		this.player = player;
		this.enemy = enemy;
		this.window = window;
		this.img = ImageManager.getInstance().getImage("charactersPanel");
		Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		add(backgroundPanel);
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		paintBackground(g2d);
	}

	public void paintBackground(Graphics2D g2d) {

		g2d.drawImage(img, 0, 0, null);
	}

	public void updateEnemy(enemies.enemy enemy) {

		this.enemy = enemy;
		((SpriteLabel) enemySprite).updateImage(enemy.getImage());
		enemySpritePanel.repaint();
		dialogPanel.repaint();
		playerSpritePanel.repaint();
		repaint();
	}

	private void createUIComponents() {

		playerSprite = new SpriteLabel(player.getImage());
		enemySprite = new SpriteLabel(enemy.getImage());
		dialogPanel = DialogPanel.getInstance();
	}

	public Image getImg() {

		return img;
	}

	public Player getPlayer() {

		return player;
	}

	public enemies.enemy getEnemy() {

		return enemy;
	}

	public void setEnemy(enemies.enemy enemy) {

		this.enemy = enemy;
	}

	public JPanel getBackgroundPanel() {

		return backgroundPanel;
	}

	public void setBackgroundPanel(JPanel backgroundPanel) {

		this.backgroundPanel = backgroundPanel;
	}

	public JPanel getDialogPanel() {

		return dialogPanel;
	}

	public void setDialogPanel(JPanel dialogPanel) {

		this.dialogPanel = dialogPanel;
	}

	public JPanel getPlayerSpritePanel() {

		return playerSpritePanel;
	}

	public void setPlayerSpritePanel(JPanel playerSpritePanel) {

		this.playerSpritePanel = playerSpritePanel;
	}

	public JPanel getEnemySpritePanel() {

		return enemySpritePanel;
	}

	public void setEnemySpritePanel(JPanel enemySpritePanel) {

		this.enemySpritePanel = enemySpritePanel;
	}

	public JLabel getPlayerSprite() {

		return playerSprite;
	}

	public void setPlayerSprite(JLabel playerSprite) {

		this.playerSprite = playerSprite;
	}

	public JLabel getEnemySprite() {

		return enemySprite;
	}

	public void setEnemySprite(JLabel enemySprite) {

		this.enemySprite = enemySprite;
	}

	public GameWindow getWindow() {

		return window;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}
