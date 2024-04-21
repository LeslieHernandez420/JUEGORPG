package items.armors.head;

import items.armors.Armor;
import items.armors.ArmorType;
import player.Player;
import player.Stats;
import util.interfaces.Interactive;

import java.io.Serializable;

public class IronHelmet extends Armor implements Serializable {
	private int counter;

	public IronHelmet(int str) {

		super("Casco de Hierro",
				"Un casco de hierro que protege más que el de madera.", 10);
		this.counter = 0;
		this.type = ArmorType.HEAD;
	}

	@Override
	public String effect() {

		return "Aumenta la defensa en 2 puntos por 3 turnos.";
	}

	@Override
	protected void initStats() {

		switch (rarity) {
			case COMMON:
				stats.put(Stats.DEFENSE, 2);
				break;
			case RARE:
				stats.put(Stats.DEFENSE, 4);
				stats.put(Stats.RESISTANCE, 1);
				break;
			case EPIC:
				stats.put(Stats.DEFENSE, 6);
				stats.put(Stats.RESISTANCE, 2);
				stats.put(Stats.INTELLIGENCE, 2);
				break;
			case LEGENDARY:
				stats.put(Stats.DEFENSE, 8);
				stats.put(Stats.RESISTANCE, 4);
				stats.put(Stats.INTELLIGENCE, 6);
				stats.put(Stats.DEXTERITY, 3);
				break;

		}
	}

	@Override
	public void callEffect(Player player) {

		counter = 3;
		player.setDefense(player.getDefense() + 2);
	}

	public void update(Player player) {

		if (counter > 0) {
			counter--;
			if (counter == 0) {
				player.setDefense(player.getDefense() - 2);
			}
		}
	}

	public int getCounter() {

		return counter;
	}

	public void setCounter(int counter) {

		this.counter = counter;
	}
}
