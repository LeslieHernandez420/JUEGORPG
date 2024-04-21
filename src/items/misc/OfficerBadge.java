package items.misc;

import items.Item;
import java.io.Serializable;

public class OfficerBadge extends Item implements Serializable {
    public OfficerBadge() {
        super("Officer Badge", "A badge of rank from a navy officer, often collected by pirates as a trophy.", 50);
    }


    public String use() {
        return "You polish the badge, remembering your victories at sea.";
    }
}
