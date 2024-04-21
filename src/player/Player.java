package player;

import characters.BasicCharacter;
import enemies.enemy;
import game.exceptions.PlayerDeathException;
import items.armors.Armor;
import items.weapons.Weapon;
import player.jobs.Job;
import player.skills.BasicHeal;
import player.skills.FuryAttack;
import player.skills.Skill;
import util.interfaces.Randomized;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Player extends BasicCharacter implements Serializable {
    private int strength;
    private int defense;
    private int intelligence;
    private int dexterity;
    private int luck;
    private int resistance;
    private int speed;
    private int experience;
    private int level;
    private int gold;
    private Job job;
    private Weapon weapon;
    private Armor headArmor;
    private Armor chestArmor;
    private Armor legArmor;
    private Armor footArmor;
    private Armor handArmor;
    private final Inventory inventory;  // Asumiendo que esta es la clase correcta para el inventario
    private Map<String, Skill> skillMap;

    public Player(String name) {
        super(name, 100, 1); // Suponiendo salud inicial de 100 y maná inicial de 1
        this.experience = 0;
        this.level = 1;
        this.gold = 0;
        this.job = new Job("Pirate"); // Asumiendo que hay una implementación de Job
        this.weapon = null;
        this.headArmor = null;
        this.chestArmor = null;
        this.legArmor = null;
        this.footArmor = null;
        this.handArmor = null;
        this.strength = 10;
        this.defense = 10;
        this.intelligence = 5;
        this.dexterity = 5;
        this.luck = 5;
        this.resistance = 5;
        this.speed = 5;
        this.inventory = new Inventory();
        this.skillMap = new HashMap<>();
        initSkills();
        randomizeStats(20);
    }

    private void initSkills() {
        this.skillMap.put("Basic Heal", new BasicHeal());
        this.skillMap.put("Fury Attack", new FuryAttack());
    }

    public void randomizeStats(int maxPoints) {
        while (maxPoints > 0) {
            int stat = Randomized.randomizeNumber(1, 7);
            switch (stat) {
                case 1:
                    if (strength < level * 5) strength++;
                    break;
                case 2:
                    if (defense < level * 5) defense++;
                    break;
                case 3:
                    intelligence++;
                    break;
                case 4:
                    dexterity++;
                    break;
                case 5:
                    luck++;
                    break;
                case 6:
                    resistance++;
                    break;
                case 7:
                    speed++;
                    break;
            }
            maxPoints--;
        }
    }

    public void equipWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void equipArmor(Armor armor) {
        switch (armor.getType()) {
            case HEAD:
                headArmor = armor;
                break;
            case CHEST:
                chestArmor = armor;
                break;
            case LEGS:
                legArmor = armor;
                break;
            case FEET:
                footArmor = armor;
                break;
            case HANDS:
                handArmor = armor;
                break;
        }
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void attack(enemy enemy) throws PlayerDeathException {
        if (!isDead()) {
            int damageDealt = enemy.takeDamage(getAttack());
            System.out.println(getName() + " attacks " + enemy.getName() + " for " + damageDealt + " damage!");
            if (enemy.isDead()) {
                getRewards(enemy);
            }
        } else {
            throw new PlayerDeathException(getName() + " is dead and cannot attack.");
        }
    }

    private void getRewards(enemy enemy) {
        this.experience += enemy.getExperience();
        this.gold += enemy.getGold();
        System.out.println(getName() + " has gained " + enemy.getExperience() + " experience and " + enemy.getGold() + " gold.");
        if (this.experience >= this.level * 100) { // Ejemplo de cálculo para subir de nivel
            levelUp();
        }
    }

    private void levelUp() {
        this.level++;
        this.strength += 2;
        this.defense += 2;
        System.out.println(getName() + " has leveled up to level " + this.level + "!");
    }

    public boolean isDead() {
        return this.hp <= 0;
    }

    public int getGold() {
    }

    public void setGold(int minus) {
    }

    public Integer getLevel() {
    }

    public Integer getDexterity() {
    }

    public int getDefense() {
    }

    public Integer getLuck() {
    }

    public int getDamage() {
    }

    public void printStats() {
    }

    public void printInventory() {
    }

    public void equipArmor() {
    }

    public void equipWeapon() {
    }
}
