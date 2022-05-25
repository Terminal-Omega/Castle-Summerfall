package CastleSummerfall;

import java.util.List;

/**
 * @author @Corbanator This represents a preset for an interactable, kind of
 *         like a pattern for how a particular type of object will be generated.
 */
public class InteractablePreset {
    // all fields are public because presets are only a temporary storage class.
    public String name;
    public String[] descriptions;
    public int size;
    public int weight;
    public List<AbilityOption> abilityOptions;
    public boolean canBePickedUp;
    public int rarity;

    /**
     * AbilityOptions are basically an list of abilities and a number of abilities
     * to choose from that list.
     */
    public class AbilityOption implements Cloneable {
        public int number;
        public List<Ability> options;
    }

    public InteractablePreset(String name, String[] descriptions, List<AbilityOption> abilityOptions, int size,
            int weight, boolean canBePickedUp, int rarity) {
        this.name = name;
        this.descriptions = descriptions;
        this.abilityOptions = abilityOptions;
        this.size = size;
        this.weight = weight;
        this.canBePickedUp = canBePickedUp;
        this.rarity = rarity;
    }

}
