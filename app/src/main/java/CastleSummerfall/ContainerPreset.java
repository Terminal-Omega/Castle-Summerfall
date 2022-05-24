package CastleSummerfall;

import java.util.ArrayList;

/**
 * @author @Corbanator A special form of InteractablePreset with extra fields
 *         due to it being a container, such as inventory capacity and how many
 *         items spawn inside of it.
 */
public class ContainerPreset extends InteractablePreset {
    int inventorySize;
    int valueFactor; // How valuable are the items inside? Currently unused but kept for
                     // extensibility.
    int minItems;
    int maxItems;

    public ContainerPreset(String name, String[] descriptions, ArrayList<AbilityOption> abilityOptions, int size,
            int weight, boolean canBePickedUp, int rarity) {
        super(name, descriptions, abilityOptions, size, weight, canBePickedUp, rarity);
    }

    public ContainerPreset(InteractablePreset preset) {
        this(preset.name, preset.descriptions, preset.abilityOptions, preset.size, preset.weight, preset.canBePickedUp,
                preset.rarity);
    }

}
