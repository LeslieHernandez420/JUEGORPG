package characters;

import java.io.Serializable;

/**
 * La clase BasicCharacter es una clase abstracta que proporciona una estructura base
 * para personajes en un juego con temática de piratas, incluyendo tanto héroes como enemigos.
 * Cada personaje tiene HP (puntos de vida), MP (puntos de magia), y capacidades para
 * tomar daño, curarse y usar MP.
 */
public abstract class BasicCharacter implements Serializable {

    protected String name;
    protected int hp;
    protected int mp;
    protected int maxHp;
    protected int maxMp;

    /**
     * Constructor para crear un personaje básico con nombre, puntos de vida y puntos de magia.
     *
     * @param name El nombre del personaje.
     * @param hp   Los puntos de vida iniciales y máximos del personaje.
     * @param mp   Los puntos de magia iniciales y máximos del personaje.
     */
    public BasicCharacter(String name, int hp, int mp) {
        this.name = name;
        this.hp = hp;
        this.mp = mp;
        this.maxHp = hp;
        this.maxMp = mp;
    }

    /**
     * Método para que el personaje reciba daño. Reduce los HP y devuelve un mensaje.
     *
     * @param damage La cantidad de daño recibido.
     * @return Un mensaje indicando el daño recibido y si el personaje ha sido derrotado.
     */
    public String takeDamage(int damage) {
        hp -= damage;
        String message = String.format("%s takes %d damage!\n", name, damage);
        if (isDead()) message += String.format("%s has been defeated!\n", name);
        return message;
    }

    /**
     * Método para curar al personaje. Incrementa los HP sin superar el máximo.
     *
     * @param amount La cantidad de HP a curar.
     */
    public void heal(int amount) {
        hp += amount;
        if (hp > maxHp) hp = maxHp;
    }

    /**
     * Método para recuperar puntos de magia. Incrementa los MP sin superar el máximo.
     *
     * @param amount La cantidad de MP a recuperar.
     */
    public void recoverMp(int amount) {
        mp += amount;
        if (mp > maxMp) mp = maxMp;
    }

    /**
     * Método para usar puntos de magia.
     *
     * @param amount La cantidad de MP a usar.
     */
    public void useMp(int amount) {
        mp -= amount;
    }

    /**
     * Método para determinar si el personaje está muerto.
     *
     * @return true si los HP del personaje son 0 o menos, false de lo contrario.
     */
    public boolean isDead() {
        return hp <= 0;
    }

    // Getters y Setters

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMp() {
        return mp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getMaxMp() {
        return maxMp;
    }
}
