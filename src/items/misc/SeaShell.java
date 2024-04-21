package items.misc;

import items.Item;
import java.io.Serializable;

public class SeaShell extends Item implements Serializable {
    public SeaShell() {
        super("Sea Shell", "A beautiful shell from the ocean, often used by pirates as currency or decoration.", 10);
    }


    public String use() {
        return "You listen to the sea echo in the shell, feeling a sense of peace.";
    }
}
