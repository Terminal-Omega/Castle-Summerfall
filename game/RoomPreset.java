package game;

import java.util.ArrayList;

public class RoomPreset {
    public String description;
    public ArrayList<InteractablePreset> interactables;
    public ArrayList<InteractablePreset> descriptionInteractables;
    public NPC boss;

    public RoomPreset(String description, ArrayList<InteractablePreset> interactables,
            ArrayList<InteractablePreset> descriptionInteractables) {
        this.description = description;
        this.interactables = interactables;
        this.descriptionInteractables = descriptionInteractables;
    }
    
}
