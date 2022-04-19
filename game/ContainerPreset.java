package game;

import java.util.ArrayList;

public class ContainerPreset extends InteractablePreset{

    public ContainerPreset(String name, String[] descriptions, ArrayList<AbilityOption> abilityOptions, int size,
            int weight, boolean canBePickedUp,  int rarity) {
        super(name, descriptions, abilityOptions, size, weight, canBePickedUp, rarity);
    }

    public ContainerPreset(InteractablePreset preset){
        this(preset.name,preset.descriptions, preset.abilityOptions, preset.size, preset.weight, preset.canBePickedUp, preset.rarity);
    }

    public void setInventorySize(int size){
        inventorySize = size;
    }
    int inventorySize;
    int valueFactor;
    int minItems;
    int maxItems;

    
}
