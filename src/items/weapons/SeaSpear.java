package items.weapons;

import items.weapons.Weapon;
import player.Stats;
import java.io.Serializable;

public class SeaSpear extends Weapon implements Serializable {
    public SeaSpear() {
        super("Sea Spear", "A sturdy spear made from coral and bone, perfect for fending off undersea threats.", 15);
        initStats();
    }

    @Override
    public String effect() {
        return "The spear glows faintly underwater, illuminating your path.";
    }

    @Override
    protected void initStats() {
        stats.put(Stats.ATTACK, 3);
        stats.put(Stats.DEXTERITY, 2);
    }

    @Override
    public void callEffect() {

    }
}
