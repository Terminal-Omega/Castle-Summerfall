package game;

import java.util.ArrayList;

public class RoomPreset {
    public String description;
    public ArrayList<Interactable> interactables;
    public ArrayList<Interactable> descriptionInteractables;
    public NPC boss;

    public RoomPreset(String description, ArrayList<Interactable> interactables,
            ArrayList<Interactable> descriptionInteractables) {
        this.description = description;
        this.interactables = interactables;
        this.descriptionInteractables = descriptionInteractables;
    }
    
}
