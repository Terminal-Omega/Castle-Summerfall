package CastleSummerfall;

import java.util.ArrayList;
import java.util.List;

/**
 * @author @Corbanator This class represents any object in the game that does
 *         not take actions based on AI or User Input.
 */
public class Interactable {

    protected int size;
    protected int weight;
    protected boolean canBePickedUp;
    protected String name;
    protected String description;

    // Abilities are included to allow for extensibility; They are set up to allow
    // for a robust system of various items, but aren't actually implemented.
    List<Ability> abilities;

    // Constructors
    public Interactable(String name, String description, int size, int weight, boolean canBePickedUp,
            List<Ability> abilities) {
        this.size = size;
        this.weight = weight;
        this.canBePickedUp = canBePickedUp;
        this.name = name;
        this.description = description;
        this.abilities = abilities;
    }

    public Interactable(String name, String description, int size, int weight, boolean canBePickedUp) {
        this(name, description, size, weight, canBePickedUp, new ArrayList<>());
    }

    public Interactable(String name, String description) {
        this(name, description, 0, 0, false);
    }

    /**
     * @return String the name of the interactable
     */
    public String getName() {
        return name;
    }

    /**
     * @return String the description of the interactable
     */
    public String getDescription() {
        return description;
    }

}
