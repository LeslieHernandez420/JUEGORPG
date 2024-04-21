package enemies.rival;

import enemies.enemy;
import game.exceptions.EnemyDeadException;
import game.exceptions.ZeroException;
import gui.panels.CharactersPanel;
import gui.panels.DialogPanel;
import items.misc.PirateHat;
import items.weapons.Cutclass;
import org.jetbrains.annotations.NotNull;
import player.Player;
import player.Stats;
import util.annotations.RegularEnemy;
import util.interfaces.Randomized;

import javax.swing.*;

/**
 * La clase RivalPirate es una subclase de la clase Enemy. Es un enemigo que el jugador puede encontrar en el juego.
 * Tiene un método de ataque único que le permite realizar una de tres acciones: plainAttack, runAway, or stealGold.
 * El método plainAttack permite al RivalPirate atacar al jugador e infligir una cantidad determinada de daño.
 * El método runAway permite al RivalPirate huir de la batalla.
 * El método stealGold permite al RivalPirate robar una cantidad determinada de oro del jugador.
 */
@RegularEnemy
public class RivalPirate extends enemy {

    /**
     * Constructor de la clase RivalPirate.
     */
    public RivalPirate(Player player) {
        super(player, "Pirata Rival", 5, 5, 5, 2);
        image = imageManager.getImage("aloneWolf",
                new ImageIcon("img\\enemies\\wolfs\\aloneWolf.png").getImage());getImage();
        stats.put(Stats.ATTACK, 7);
        stats.put(Stats.DEFENSE, 3);
        stats.put(Stats.LUCK, 3);
        stats.put(Stats.SPEED, 4);
        stats.put(Stats.DEXTERITY, 3);
    }

    /**
     * Función que permite al RivalPirate atacar al jugador.
     *
     * @param player Jugador al que se le ataca.
     *
     * @throws EnemyDeadException Excepción que se lanza si el enemigo está muerto.
     */
    @Override
    public void attack(Player player, CharactersPanel panel) throws EnemyDeadException {
        String message = "";
        if (!isDead()) {
            double plainAttackProbability = 0.5;
            double runAwayProbability = 0.2;
            double stealGoldProbability = 0.3;
            double totalProbability = plainAttackProbability + runAwayProbability + stealGoldProbability;
            double ratio = Randomized.randomizeDouble(totalProbability);
            // plainAttackProbability = 50%, runAwayProbability = 20%, stealGoldProbability = 30%
            // plainAttackProbability + runAwayProbability + stealGoldProbability = 100%
            // ratio = 0.0 - 0.5 -> plainAttack, ratio = 0.51 - 0.8 -> stealGold, ratio = 0.81 - 1.0 -> runAway
            if (ratio <= plainAttackProbability) message = plainAttack(player);
            else if (ratio <= plainAttackProbability + stealGoldProbability) message = stealGold(player);
            else message = runAway();
        } else {
            throw new EnemyDeadException();
        }
        ((DialogPanel) panel.getDialogPanel()).getText().append(message);
    }

    /**
     * Función que permite al RivalPirate soltar un objeto al morir.
     *
     * @param player Jugador al que se le suelta el objeto.
     */
    @Override
    public void dropItem(Player player, CharactersPanel panel) {
        int ratio = Randomized.randomizeNumber(1, 100);
        player.getInventory().addItem(ratio > 50 ? new Cutclass() : new PirateHat(), (DialogPanel) panel.getDialogPanel());
    }

    /**
     * Función que permite al RivalPirate atacar al jugador.
     *
     * @param player Jugador al que se le ataca.
     */
    private String plainAttack(@NotNull Player player) {
        int damage = getDamage(player);
        String message = String.format("¡%s ataca con %d punto(s) de daño!\n", getName(), damage);
        message += player.takeDamage(damage);
        return message;
    }

    /**
     * Función que permite al RivalPirate huir de la batalla.
     */
    public String runAway() {
        this.hp = 0;
        return "¡El Pirata Rival huye de la batalla!\n";
    }

    /**
     * Función que permite al RivalPirate robar oro del jugador.
     *
     * @param player Jugador al que se le roba el oro.
     */
    public String stealGold(@NotNull Player player) {
        String message = "";
        try {
            int minus = player.getGold() - 5;
            if (minus < 0)
                throw new ZeroException();
            player.setGold(minus);
            message += "¡El Pirata Rival roba 5 de oro!\n";
        } catch (ZeroException e) {
            player.setGold(0);
            message += "¡El Pirata Rival roba todo el oro del jugador!\n";
        }
        return message;
    }
}
