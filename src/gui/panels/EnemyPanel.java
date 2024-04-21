package gui.panels;

import gui.game.GameWindow;
import gui.labels.HpLabel;
import gui.labels.TextLabel;
import util.managers.FontManager;
import util.managers.ImageManager;

import javax.swing.*;
import java.awt.*;

public class EnemyPanel extends JPanel {

	private static EnemyPanel instance;
	private final Image background;
	private enemies.enemy enemy;
	private JPanel backgroundPanel;
	private JLabel enemyName;
	private JLabel enemyHp;
	private JLabel attackLabel;
	private JLabel defenseLabel;
	private JLabel expLabel;
	private JLabel golldLabel;

	public static EnemyPanel getInstance(enemies.enemy enemy) {

		if (instance == null) {

			instance = new EnemyPanel(enemy);
		}
		return instance;
	}

	private EnemyPanel(enemies.enemy enemy) {

		this.enemy = enemy;
		background = ImageManager.getInstance().getImage("enemyPanel");
		Font font = FontManager.getInstance().getFont("Player");
		enemyName.setFont(font);
		enemyName.setForeground(new Color(95, 0, 0, 255));
		enemyName.setText(enemy.getName());
		add(backgroundPanel);
		setPreferredSize(backgroundPanel.getPreferredSize());
		Dimension size = new Dimension(background.getWidth(null), background.getHeight(null));
		setPreferredSize(size);
		setMaximumSize(size);
		setMinimumSize(size);
		setOpaque(false);
	}

	public void updateEnemy() {

		this.enemy = GameWindow.getInstance().getEnemy();
		((HpLabel) enemyHp).updateCharacter(this.enemy);
		enemyName.setText(enemy.getName());
		attackLabel.repaint();
		defenseLabel.repaint();
		expLabel.repaint();
		golldLabel.repaint();
		paintComponents(getGraphics());
	}

	public void updateEnemy(enemies.enemy enemy) {

		this.enemy = enemy;
		((HpLabel) enemyHp).updateCharacter(this.enemy);
		enemyName.setText(enemy.getName());
		((TextLabel) attackLabel).setDisplayText(String.format("FUE: %d", this.enemy.getAdjustedAttack()));
		((TextLabel) defenseLabel).setDisplayText(String.format("DEF: %d", this.enemy.getAdjustedDefense()));
		((TextLabel) expLabel).setDisplayText(String.format("EXP: %d", this.enemy.getExperience()));
		((TextLabel) golldLabel).setDisplayText(String.format("ORO: %d", this.enemy.getGold()));
		attackLabel.repaint();
		defenseLabel.repaint();
		expLabel.repaint();
		golldLabel.repaint();
		repaint();
	}

	private void createUIComponents() {

		enemyHp = new HpLabel(enemy);
		attackLabel = new TextLabel(String.format("FUE: %d", enemy.getAdjustedAttack()), "textHolder");
		defenseLabel = new TextLabel(String.format("DEF: %d", enemy.getAdjustedDefense()), "textHolder");
		expLabel = new TextLabel(String.format("EXP: %d", enemy.getExperience()), "textHolder");
		golldLabel = new TextLabel(String.format("ORO: %d", enemy.getGold()), "textHolder");
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		paintBackground(g2d);
	}

	public void paintBackground(Graphics2D g2d) {

		g2d.drawImage(background, 0, 0, null);
	}
}
