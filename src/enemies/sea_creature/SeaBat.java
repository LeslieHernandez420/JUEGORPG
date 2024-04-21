package enemies.sea_creature;

import enemies.enemy;
import game.exceptions.EnemyDeadException;
import gui.panels.CharactersPanel;
import gui.panels.DialogPanel;
import items.misc.SeaShell;
import items.weapons.SeaSpear;
import org.jetbrains.annotations.NotNull;
import player.Player;
import player.Stats;
import util.annotations.RegularEnemy;
import util.interfaces.Randomized;

import javax.swing.*;

/**
 * La clase SeaBat es una subclase de la clase Enemy. Es un enemigo acuático que el jugador puede encontrar en el juego.
 * Tiene un método de ataque único que le permite realizar una de tres acciones: simpleAttack, sonicBoom, or diveAttack.
 * El método simpleAttack permite al SeaBat atacar al jugador e infligir una cantidad determinada de daño.
 * El método sonicBoom permite al SeaBat desorientar al jugador temporalmente.
 * El método diveAttack es un ataque potente que puede infligir un daño considerable.
 */
@RegularEnemy
public class SeaBat extends enemy {

    /**
     * Constructor de la clase SeaBat.
     */
    public SeaBat(Player player) {
        super(player, "Murciélago Marino", 10, 10, 5, 4);
        image = imageManager.getImage("tinyBat",
                new ImageIcon("img\\enemies\\bats\\tinyBat.png").getImage());
        stats.put(Stats.ATTACK, 8);
        stats.put(Stats.DEFENSE, 3);
        stats.put(Stats.LUCK, 3);
        stats.put(Stats.SPEED, 5);
        stats.put(Stats.DEXTERITY, 4);
    }

    /**
     * Función que permite al SeaBat atacar al jugador.
     *
     * @param player Jugador al que se le ataca.
     *
     * @throws EnemyDeadException Excepción que se lanza si el enemigo está muerto.
     */
    @Override
    public void attack(Player player, CharactersPanel panel) throws EnemyDeadException {
        String message;
        if (!isDead()) {
            double simpleAttackProbability = 0.5;
            double sonicBoomProbability = 0.3;
            double diveAttackProbability = 0.2;
            double totalProbability = simpleAttackProbability + sonicBoomProbability + diveAttackProbability;
            double ratio = Randomized.randomizeDouble(totalProbability);
            // simpleAttackProbability = 50%, sonicBoomProbability = 30%, diveAttackProbability = 20%
            // simpleAttackProbability + sonicBoomProbability + diveAttackProbability = 100%
            // ratio = 0.0 - 0.5 -> simpleAttack, ratio = 0.51 - 0.7 -> diveAttack, ratio = 0.71 - 1.0 -> sonicBoom
            if (ratio <= simpleAttackProbability) message = simpleAttack(player);
            else if (ratio <= simpleAttackProbability + diveAttackProbability) message = diveAttack(player);
            else message = sonicBoom(player);
        } else {
            throw new EnemyDeadException();
        }
        ((DialogPanel) panel.getDialogPanel()).getText().append(message);
    }

    /**
     * Función que permite al SeaBat soltar un objeto al morir.
     *
     * @param player Jugador al que se le suelta el objeto.
     */
    @Override
    public void dropItem(Player player, CharactersPanel panel) {
        int ratio = Randomized.randomizeNumber(1, 100);
        player.getInventory().addItem(ratio > 65 ? new SeaSpear() : new SeaShell(), (DialogPanel) panel.getDialogPanel());
    }

    /**
     * Función que permite al SeaBat atacar.
     *
     * @param player Jugador al que se le ataca.
     */
    public String simpleAttack(@NotNull Player player) {
        int damage = getDamage(player);
        String message = String.format("¡%s ataca con %d punto(s) de daño!\n", getName(), damage);
        message += player.takeDamage(damage);
        return message;
    }

    /**
     * Función que permite al SeaBat emitir un sonido desorientador.
     *
     * @param player Jugador al que se le afecta.
     */
    public String sonicBoom(@NotNull Player player) {
        //TODO: Implementar efecto de desorientación.
        return String.format("¡%s emite un sonido ensordecedor!\n", getName());
    }

    /**
     * Función que permite al SeaBat realizar un ataque sumergido poderoso.
     *
     * @param player Jugador al que se le ataca.
     */
    public String diveAttack(@NotNull Player player) {
        int totalDamage = getAdjustedAttack() + 2;
        String message = String.format("¡%s realiza un ataque sumergido con %d punto(s) de daño!\n", getName(), totalDamage);
        return message + player.takeDamage(totalDamage);
    }
}
