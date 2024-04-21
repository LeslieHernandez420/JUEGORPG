package gui.panels;

import enemies.enemy;
import gui.events.SkillButtonAction;
import gui.game.GameWindow;
import player.Player;
import player.skills.Skill;
import util.managers.FontManager;
import util.managers.ImageManager;

import javax.swing.*;
import java.awt.*;

public class SkillDetailPanel extends JPanel {

	private final Image skillBanner;
	private final Skill skill;
	private JPanel backgroundPanel;
	private JLabel skillName;
	private JLabel skillDescription;
	private JButton skillCallButton;

	public SkillDetailPanel(Skill skill, Player player, enemy enemy, GameWindow window) {

		this.skill = skill;
		skillBanner = ImageManager.getInstance().getImage("skillDetailPanel");
		Font font = FontManager.getInstance().getFont("Player");
		skillDescription.setFont(font);
		skillName.setFont(font);
		skillName.setText(String.format("%s - %d MP", skill.getName(), skill.getManaCost()));
		skillDescription.setText(skill.getDescription());
		add(backgroundPanel);
		Dimension size = new Dimension(skillBanner.getWidth(null), skillBanner.getHeight(null));
		setPreferredSize(size);
		setMaximumSize(size);
		setMinimumSize(size);
		setOpaque(false);
		skillCallButton.addActionListener(new SkillButtonAction(skill, player, enemy, window));
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		paintBackground(g2d);
	}

	public void paintBackground(Graphics2D g2d) {

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2d.drawImage(skillBanner, 0, 0, null);
	}

	private void createUIComponents() {

		skillCallButton = new JButton();
		skillCallButton.setIcon(new ImageIcon(ImageManager.getInstance().getImage("skillButtonIdle")));
		skillCallButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		skillCallButton.setBackground(null);
		skillCallButton.setBorder(BorderFactory.createEmptyBorder());
		skillCallButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {

				skillCallButton.setIcon(new ImageIcon(ImageManager.getInstance().getImage("skillButtonHover")));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {

				skillCallButton.setIcon(new ImageIcon(ImageManager.getInstance().getImage("skillButtonIdle")));
			}
		});
	}
}
