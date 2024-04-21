package items.weapons.blades;

import items.weapons.Weapon;
import player.Stats;
import java.io.Serializable;

public class OfficerSword extends Weapon implements Serializable {
    @Override
    public void callEffect() {

    }

    public OfficerSword() {
        super("Officer Sword", "A finely crafted sword once belonging to a navy officer, sharp and well balanced.", 30);
        initStats();
    }

    @Override
    public String effect() {
        return "The sword's blade shines brightly in the sun, intimidating your enemies.";
    }

    @Override
    protected void initStats() {
        stats.put(Stats.ATTACK, 4);
        stats.put(Stats.DEXTERITY, 3);
        stats.put(Stats.LUCK, 2);
    }
}
