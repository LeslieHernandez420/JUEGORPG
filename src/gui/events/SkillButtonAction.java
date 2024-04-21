package gui.events;

import gui.game.GameWindow;
import gui.panels.*;
import player.Player;
import player.skills.BasicHeal;
import player.skills.FuryAttack;
import player.skills.Skill;
import util.enemies.EnemyFactory;

import java.awt.event.ActionListener;

public class SkillButtonAction implements ActionListener {

	private Player player;
	private enemies.enemy enemy;
	private Skill skill;
	private GameWindow window;

	public SkillButtonAction(Skill skill, Player player, enemies.enemy enemy, GameWindow window) {

		this.player = player;
		this.enemy = enemy;
		this.skill = skill;
		this.window = window;
	}

	@Override
	public void actionPerformed(java.awt.event.ActionEvent evt) {

		CharactersPanel charactersPanel = CharactersPanel.getInstance(player, enemy, window);
		enemy = GameWindow.getInstance().getEnemy();
		if (player.getMp() > skill.getManaCost()) {

			skill.setCharactersPanel(charactersPanel);
			skill.effect(player, enemy);
			if (enemy.isDead()) {

				enemies.enemy newEnemy = EnemyFactory.generateRegularEnemy(player);
				GameWindow.getInstance().setEnemy(newEnemy);
				CharactersPanel.getInstance(player, newEnemy, window).updateEnemy(newEnemy);
			}
			EnemyPanel.getInstance(enemy).updateEnemy(enemy);
			PlayerPanel.getInstance(player).updatePlayer(player);
		} else {
			DialogPanel.getInstance().getText().append("No tienes suficiente mana para usar esta habilidad\n");
		}
	}
}
