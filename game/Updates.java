package game;

import java.util.regex.Pattern;
import java.util.Random;
import java.util.regex.Matcher;
import java.lang.Integer;

public class Updates {

    /**
     * @param effect
     * @param target
     */
    public static void applyEffect(String effect, Actor target) {

    }

    /**
     * @param actor
     */
    public static void doPassives(Actor actor) {
        String theEffectString = "hi";
        // TODO: @Corbanator I need the effect Strings
        applyEffect(theEffectString, actor);
    }

    /**
     * @param player
     * @param floor
     */
    public static void update(Player player, Floor floor) {
        doPassives(player);
        // TODO: @Corbanator implement noise and LOS
        int size = floor.getNPCs().size();
        for (int i = 0; i < size; i++) {
            NPC npc = floor.getNPCs().get(i);
            doPassives(npc);
            if (npc.getHealth() <= 0) {
                floor.removeNPC(i);
                System.out.println(npc.getName() + " was killed.");
            }
            npc.npcTurnAllience(player, floor.getXSize());
            npc.setEnergy();
            // TODO: @Corbanator notify player if NPC enters the room
        }
    }

    /**
     * @param floor
     */
    public static void actionUpdate(Floor floor) {
        for (int i = 0; i < floor.getNPCs().size(); i++) {
            NPC npc = floor.getNPCs().get(i);
            if (npc.getHealth() <= 0) {
                floor.getNPCs().remove(i);
                System.out.println(npc.getName() + " was killed.");
            }
        }
    }

}
