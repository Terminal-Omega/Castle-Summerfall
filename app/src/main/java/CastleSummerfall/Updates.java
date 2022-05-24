package CastleSummerfall;

import java.util.regex.Pattern;
import java.util.Random;
import java.util.regex.Matcher;
import java.lang.Integer;

public class Updates {

    /**
     * this method is not currently used, but is a work-in-progress for
     * extensibility.
     *
     * @author @Corbanator @The-Watcher-213
     * @param effect
     * @param target
     */
    public static void applyEffect(String effect, Actor target) {
        Random rand = new Random();

        Pattern damage = Pattern.compile("damage\\(([0-9]*(-([0-9]*))?)\\)");
        Pattern heal = Pattern.compile("heal\\(([0-9]*(-([0-9]*))?)\\)");
        Pattern damageNoAC = Pattern.compile("damageNoAC\\(([0-9]*(-([0-9]*))?)\\)");
        Pattern statChanger = Pattern.compile("stat");

        Matcher damageMatcher = damage.matcher(effect);
        if (damageMatcher.find()) {
            int damageAmount;
            if (damageMatcher.group(3) != "") {
                damageAmount = rand.nextInt(Integer.parseInt(damageMatcher.group(3)))
                        + Integer.parseInt(damageMatcher.group(1));
                // TODO: @Corbanator make this work once Xander pushes the thing
            } else {
                damageAmount = Integer.parseInt(damageMatcher.group(1));
            }
            target.takeDamage(damageAmount, 0);
        }

        Matcher healMatcher = heal.matcher(effect);
        if (damageMatcher.find()) {
            int healAmount;
            if (healMatcher.group(3) != "") {
                healAmount = rand.nextInt(Integer.parseInt(damageMatcher.group(3)))
                        + Integer.parseInt(damageMatcher.group(1));
                // TODO: @Corbanator make this work once Xander pushes the thing
            } else {
                healAmount = Integer.parseInt(damageMatcher.group(1));
            }
            target.heal(healAmount);
        }

        Matcher damageNoACMathcer = damageNoAC.matcher(effect);
        if (damageNoACMathcer.find()) {
            int damageAmount;
            if (damageNoACMathcer.group(3) != "") {
                damageAmount = rand.nextInt(Integer.parseInt(damageMatcher.group(3)))
                        + Integer.parseInt(damageMatcher.group(1));

            } else {
                damageAmount = Integer.parseInt(damageNoACMathcer.group(1));
            }
            target.damageNoAC(damageAmount);
        }

    }

    /**
     * @param actor
     */
    public static void doPassives(Actor actor) {

    }

    /**
     * updates the game in between playter turns.
     *
     * @param player
     * @param floor
     */
    public static void update(Player player, Floor floor) {
        doPassives(player);
        // TODO: @Corbanator implement noise and LOS
        int size = floor.getNPCs().size();
        // for each npc, check if they're dead, then activate their AI if they're still
        // alive.
        for (int i = 0; i < size; i++) {
            NPC npc = floor.getNPCs().get(i);
            doPassives(npc);
            if (npc.getHealth() <= 0) {
                floor.removeNPC(i);
                System.out.println(npc.getName() + " was killed.");
            }
            npc.npcTurnAlliance(player, floor.getXSize());
            npc.setEnergy();
            // TODO: @Corbanator notify player if NPC enters the room
        }
    }

    /**
     * update in-between player actions during the same turn
     *
     * @param floor
     */
    public static void actionUpdate(Floor floor) {
        // check if enemies are dead
        for (int i = 0; i < floor.getNPCs().size(); i++) {
            NPC npc = floor.getNPCs().get(i);
            if (npc.getHealth() <= 0) {
                floor.getNPCs().remove(i);
                System.out.println(npc.getName() + " was killed.");
            }
        }
    }

}
