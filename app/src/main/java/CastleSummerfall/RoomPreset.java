package CastleSummerfall;

import java.util.ArrayList;

/**
 * @author @Corbanator A preset for a room, complete with a set of possible
 *         descriptions and interactables to allow the player to inspect what is
 *         described in the description
 */
public class RoomPreset {
    public String[] descriptions;
    public ArrayList<InteractablePreset> interactables;
    public ArrayList<InteractablePreset> descriptionInteractables;
    // If the room has a boss, it's a boss room.
    public NPC boss;

    public RoomPreset(String[] descriptions, ArrayList<InteractablePreset> interactables,
            ArrayList<InteractablePreset> descriptionInteractables) {
        this.descriptions = descriptions;
        this.interactables = interactables;
        this.descriptionInteractables = descriptionInteractables;
    }

    // easier than putting it in the constructor, for the way the generator works.
    /**
     * @param boss
     */
    public void addBoss(NPCPreset boss) {
        this.boss = Generator.spinNPC(0, 0, boss, 0);
    }

}
