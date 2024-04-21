package items.misc;

import items.Item;
import java.io.Serializable;

public class PirateHat extends Item implements Serializable {
    public PirateHat() {
        super("Pirate Hat", "A stylish hat worn by pirates, said to grant authority and respect among the crew.", 25);
    }



    public String use() {
        return "You wear the hat and feel like a true pirate captain.";
    }
}
