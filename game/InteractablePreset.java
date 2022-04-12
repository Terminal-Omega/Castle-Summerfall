package game;

import java.util.ArrayList;

public class InteractablePreset {
    public class AbilityOption implements Cloneable{
        public int number;
        public ArrayList<Ability> options;
    }
    public String name;
    public String[] descriptions;
    public int size;
    public int weight;
    ArrayList<AbilityOption> abilityOptions;
    public boolean canBePickedUp;

    public InteractablePreset(String name, String[] descriptions, ArrayList<AbilityOption> abilityOptions,
            int size, int weight, boolean canBePickedUp) {
        this.name = name;
        this.descriptions = descriptions;
        this.abilityOptions = abilityOptions;
        this.size = size;
        this.weight = weight;
        this.canBePickedUp = canBePickedUp;
    }
    

}
