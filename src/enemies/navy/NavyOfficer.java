package enemies.navy;

import enemies.enemy;
import game.exceptions.EnemyDeadException;
import gui.panels.CharactersPanel;
import gui.panels.DialogPanel;
import items.misc.OfficerBadge;
import items.weapons.blades.OfficerSword;
import player.Player;
import player.Stats;
import util.annotations.RegularEnemy;
import util.interfaces.Randomized;

import javax.swing.*;

@RegularEnemy
public class NavyOfficer extends enemy {

    public NavyOfficer(Player player) {
        super(player, "Navy Officer", 10, 100, 50, 30);
        image = imageManager.getImage("rookieGoblin",
                new ImageIcon("img\\enemies\\goblins\\rookieGoblin.png").getImage());
        //* Inicialización de estadísticas para el Oficial de la Marina
        stats.put(Stats.ATTACK, 15);
        stats.put(Stats.DEFENSE, 10);
        stats.put(Stats.LUCK, 5);
        stats.put(Stats.SPEED, 7);
        stats.put(Stats.DEXTERITY, 8);
    }

    //* Implementación del método de ataque
    @Override
    public void attack(Player player, CharactersPanel panel) throws EnemyDeadException {
        String message = "";
        if (!isDead()) {
            double simpleAttackProbability = 0.6;
            double callReinforcementsProbability = 0.4;
            double totalProbability = simpleAttackProbability + callReinforcementsProbability;
            double ratio = Randomized.randomizeDouble(totalProbability);
            // simpleAttackProbability = 60%, callReinforcementsProbability = 40%
            // simpleAttackProbability + callReinforcementsProbability = 100%
            // ratio = 0.0 - 0.6 -> simpleAttack, ratio = 0.61 - 1.0 -> callReinforcements
            if (ratio <= simpleAttackProbability) message = simpleAttack(player);
            else message = callReinforcements(player);
        } else {
            throw new EnemyDeadException();
        }
        ((DialogPanel) panel.getDialogPanel()).getText().append(message);
    }

    //* Método para dejar caer un objeto
    @Override
    public void dropItem(Player player, CharactersPanel panel) {
        int ratio = Randomized.randomizeNumber(1, 100);
        player.getInventory().addItem(ratio > 75 ? new OfficerSword() : new OfficerBadge(), (DialogPanel) panel.getDialogPanel());
    }

    //* Implementación de un ataque simple
    public String simpleAttack(Player player) {
        int damage = getDamage(player);
        String message = String.format("%s embiste con su espada causando %d puntos de daño!\n", getName(), damage);
        message += player.takeDamage(damage);
        return message;
    }

    //* Método para llamar refuerzos
    public String callReinforcements(Player player) {
        int damage = getAdjustedAttack() + 5;
        String message = String.format("%s llama a refuerzos que causan %d puntos de daño!\n", getName(), damage);
        message += player.takeDamage(damage);
        return message;
    }
}
