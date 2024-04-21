package items;

import player.Stats;
import util.interfaces.Interactive;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Random;

public abstract class Item implements Serializable {

	protected final String name;
	protected final String description;
	protected final int price;
	protected final Rarity rarity;
	protected final HashMap<Stats, Integer> stats;

	public Item(String name, String description, int price) {

		this.name = name;
		this.description = description;
		this.price = price;
		this.rarity = Rarity.getRandomRarity();
		stats = new HashMap<>();
	}

	public String getRarity() {

		switch (rarity) {
			case COMMON:
				return "Común";
			case UNCOMMON:
				return "Poco común";
			case RARE:
				return "Raro";
			case EPIC:
				return "Épico";
			case LEGENDARY:
				return "Legendario";
			default:
				throw new IllegalArgumentException("Invalid rarity: " + rarity);
		}
	}

	public void displayData() {

		Interactive.printDialog(String.format("Nombre: %s\nDescripción: %s\nPrecio: %d", name, description, price));
	}

	public String getName() {

		return name;
	}

	public String getDescription() {

		return description;
	}

	public int getPrice() {

		return price;
	}

	public HashMap<Stats, Integer> getStats() {

		return stats;
	}
}
