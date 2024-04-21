package items.potions;

import items.Item;

public class HealPotion extends Item {
	private int healingAmount;

	public HealPotion() {
		super("Healing Potion", "Brewed from the rare corals of the deep sea, this potion can heal your wounds.", 120);
		this.healingAmount = 20; // Cura 20 puntos de vida
	}

	public void use() {
		System.out.println("You drink the Healing Potion and feel your wounds begin to mend.");
	}

	public int getHealingAmount() {
		return healingAmount;
	}
}
