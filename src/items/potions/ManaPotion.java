package items.potions;

import player.Player;
import util.interfaces.Usable;

public record ManaPotion(PotionSize size) implements Usable {

	@Override
	public void use(Player player) {

		System.out.println("You have used a " + size + " mana potion.");
		player.recoverMp(size.getAmount());
	}
}