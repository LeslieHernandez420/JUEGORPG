package items.weapons;

import items.Item;

public class Cutclass extends Item {
    private int damage;
    private int durability;

    public Cutclass() {
        super("Cutlass", "A sharp, curved sword favored by pirates for close combat.", 100);
        this.damage = 15;  // Damage puede ser ajustado según el balance del juego
        this.durability = 100;  // La durabilidad también puede ajustarse
    }

    public int getDamage() {
        return damage;
    }

    public int getDurability() {
        return durability;
    }

    // Método para usar la espada en combate
    public void use() {
        System.out.println("Swinging the cutlass fiercely!");
        durability -= 1;
    }
}
