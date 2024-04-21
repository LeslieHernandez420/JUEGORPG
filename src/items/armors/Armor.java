package items.armors;

import items.Item;
import player.Player;
import player.Stats;

import java.io.Serializable;
import java.util.HashMap;

public abstract class Armor extends Item implements Serializable {

	protected HashMap<Stats, Integer> stats = new HashMap<>();
	protected ArmorType type;

	public Armor(String name, String description, int price) {

		super(name, description, price);
		initStats();
	}

	public abstract String effect();

	protected abstract void initStats();

	public abstract void callEffect(Player player);

	public HashMap<Stats, Integer> getStats() {

		return stats;
	}

	public void setStats(HashMap<Stats, Integer> stats) {

		this.stats = stats;
	}

	public ArmorType getType() {

		return type;
	}

	public void setType(ArmorType type) {

		this.type = type;
	}
}
