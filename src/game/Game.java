package game;

import enemies.enemy;
import enemies.navy.NavyOfficer;
import enemies.rival.RivalPirate;
import enemies.sea_creature.SeaBat;
import game.exceptions.PlayerDeathException;
import org.jetbrains.annotations.NotNull;
import player.Player;
import util.managers.FileManager;
import util.interfaces.Randomized;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * La clase Game es la clase principal del juego. Es la clase que controla el flujo del juego y las interacciones entre
 * el jugador y los enemigos.
 */
public class Game {
        private Player player;
        private final int slot;
        private final List<enemy> enemies;

        public Game(int slot) {
            this.slot = slot;
            player = null;
            enemies = new ArrayList<>();
            // Población inicial de enemigos con temas de piratas
            enemies.add(new RivalPirate(player));
            enemies.add(new NavyOfficer(player));
            enemies.add(new SeaBat(player));
            printMainMenu();
        }

        public void printMainMenu() {
            String menu = "1. Jugar\n2. Salir";
            try {
                int option = Integer.parseInt(JOptionPane.showInputDialog(menu));
                switch (option) {
                    case 1:
                        try {
                            player = FileManager.loadGame(new File("files\\game" + slot + ".dat"));
                            JOptionPane.showMessageDialog(null, "¡Bienvenido de nuevo!");
                        } catch (Exception e) {
                            player = new Player(JOptionPane.showInputDialog("Ingresa el nombre del jugador:"));
                        }
                        printPlayerMenu();
                        break;
                    case 2:
                        JOptionPane.showMessageDialog(null, "Gracias por jugar");
                        break;
                    default:
                        throw new IllegalArgumentException("Opción inválida");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "La opción ingresada no es válida");
                printMainMenu();
            }
        }

        private void printPlayerMenu() {
            String menu = "1. Ver estadísticas\n2. Ver inventario";
            if (!enemies.isEmpty()) {
                menu += String.format("3. Atacar [%d enemigos restantes]\n", enemies.size());
            }
            menu += "4. Equipar arma\n5. Equipar armadura\n6. Salir";
            try {
                int option = Integer.parseInt(JOptionPane.showInputDialog(menu));
                switch (option) {
                    case 1:
                        player.printStats();
                        break;
                    case 2:
                        player.printInventory();
                        break;
                    case 3:
                        attackCycle();
                        break;
                    case 4:
                        player.equipWeapon();
                        break;
                    case 5:
                        player.equipArmor();
                        break;
                    case 6:
                        endGame();
                        break;
                    default:
                        throw new IllegalArgumentException("Opción inválida");
                }
                if (option != 6) {
                    printPlayerMenu();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "La opción ingresada no es válida");
                printPlayerMenu();
            }
        }

        private void endGame() {
            JOptionPane.showMessageDialog(null, "Gracias por jugar");
            FileManager.saveGame(player, new File("files\\game" + slot + ".dat"));
            enemies.clear();
        }

        private void attackCycle() throws PlayerDeathException {
            if (!enemies.isEmpty()) {
                enemy currentEnemy = getEnemy();
                while (!currentEnemy.isDead() && !player.isDead()) {
                    battleMenu(currentEnemy);
                }
                enemies.remove(currentEnemy);
            }
        }

        private void battleMenu(enemy enemy) throws PlayerDeathException {
            String menu = "1. Atacar\n2. Huir";
            int battleOption = Integer.parseInt(JOptionPane.showInputDialog(menu));
            switch (battleOption) {
                case 1:
                    player.attack(enemy);
                    if (!enemy.isDead()) {
                        enemy.attack(player);
                    }
                    break;
                case 2:
                    if (Randomized.randomizeBoolean()) {
                        JOptionPane.showMessageDialog(null, "Has logrado huir exitosamente.");
                    } else {
                        JOptionPane.showMessageDialog(null, "No has podido huir!");
                        enemy.attack(player);
                    }
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "La opción ingresada no es válida");
                    break;
            }
            if (!player.isDead() && !enemy.isDead()) {
                battleMenu(enemy);
            }
        }

        private enemy getEnemy() {
            enemy enemy = enemies.get(Randomized.randomizeNumber(0, enemies.size() - 1));
            JOptionPane.showMessageDialog(null, String.format("Un %s aparece!", enemy.getName()));
            return enemy;
        }
    }