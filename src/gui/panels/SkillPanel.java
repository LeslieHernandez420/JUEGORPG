package gui.panels;

import gui.game.GameWindow;
import player.Player;
import player.skills.Skill;
import util.managers.ImageManager;

import javax.swing.*;
import java.awt.*;

public class SkillPanel extends JScrollPane {

	private final Image img;
	private final Player player;
	private final enemies.enemy enemy;
	private JPanel mainPanel;
	private JPanel skillList;
	private final GameWindow window;

	public SkillPanel(Player player, enemies.enemy enemy, GameWindow window) {

		this.window = window;
		this.player = player;
		this.enemy = enemy;
		this.img = ImageManager.getInstance().getImage("skillPanel");
		setViewportView(mainPanel);
		setViewportBorder(null);
		setBorder(null);
		setOpaque(false);
		setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		skillList.setLayout(new GridLayout(0, 1, 0, 10));
		player.getSkillMap().forEach((k, v) -> addSkill(v));
	}

	private void addSkill(Skill skill) {

		skillList.add(new SkillDetailPanel(skill, player, enemy, window));
	}

	private void createUIComponents() {

		mainPanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {

				super.paintComponent(g);
				g.drawImage(img, 0, 0, null);
			}
		};
		mainPanel.setOpaque(false);
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	}
}
