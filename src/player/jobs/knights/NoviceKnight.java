package player.jobs.knights;

import player.Player;
import player.jobs.Job;

public class NoviceKnight extends Job {
	public NoviceKnight(Player player) {

		super(player, "Caballero novato");
	}

	public NoviceKnight(Player player, String name) {

		super(player, name);
	}
}
