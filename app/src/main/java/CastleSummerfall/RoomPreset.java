package CastleSummerfall;

import java.util.List;

/**
 * @author @Corbanator A preset for a room, complete with a set of possible
 *         descriptions and interactables to allow the player to inspect what is
 *         described in the description
 */
public class RoomPreset {
    public String[] descriptions;
    public List<InteractablePreset> interactables;
    public List<InteractablePreset> descriptionInteractables;
    // If the room has a boss, it's a boss room.
    public NPC boss;

    public RoomPreset(String[] descriptions, List<InteractablePreset> interactables,
            List<InteractablePreset> descriptionInteractables) {
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
