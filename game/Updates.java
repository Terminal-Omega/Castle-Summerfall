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
        Random rand = new Random();

        Pattern damage = Pattern.compile("damage\\(([0-9])*(-([0-9]*))?\\)");
        Pattern heal = Pattern.compile("heal\\(([0-9])*(-([0-9]*))?\\)");
        Pattern damageNoAC = Pattern.compile("damageNoAC\\(([0-9]*(-([0-9]*))?)\\)");
        Pattern statChanger = Pattern.compile("(?<=stat:)(.*):\\s*([0-9]*)(?:-)?([0-9]+)?;");

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

        Matcher damageNoACMatcher = damageNoAC.matcher(effect);
        if (damageNoACMatcher.find()) {
            int damageAmount;
            if (damageNoACMatcher.group(3) != "") {
                damageAmount = rand.nextInt(Integer.parseInt(damageNoACMatcher.group(3)))
                        + Integer.parseInt(damageNoACMatcher.group(1));

            } else {
                damageAmount = Integer.parseInt(damageNoACMatcher.group(1));
            }
            target.damageNoAC(damageAmount);
        }

        Matcher statEffect = statChanger.matcher(effect);
        if (statEffect.find()) {
            int effectAmount;
            int rangeMin;
            int rangeMax;
            if (statEffect.group(1).equals("Strength")) {
                if (statEffect.group(3) != "") {
                    rangeMin = Integer.parseInt(statEffect.group(2));
                    rangeMax = Integer.parseInt(statEffect.group(3));
                    effectAmount = rand.nextInt(rangeMin, rangeMax) + 1;

                } else {
                    effectAmount = Integer.parseInt(statEffect.group(2));
                }
                target.modifiyStrength(effectAmount);
            } else if (statEffect.group(2).equals("Dexterity")) {
                if (statEffect.group(3) != "") {
                    rangeMin = Integer.parseInt(statEffect.group(2));
                    rangeMax = Integer.parseInt(statEffect.group(3));
                    effectAmount = rand.nextInt(rangeMin, rangeMax) + 1;

                } else {
                    effectAmount = Integer.parseInt(statEffect.group(2));
                }
                target.modifyDexterity(effectAmount);
            } else if (statEffect.group(1).equals("Constitution")) {
                if (statEffect.group(3) != "") {
                    rangeMin = Integer.parseInt(statEffect.group(2));
                    rangeMax = Integer.parseInt(statEffect.group(3));
                    effectAmount = rand.nextInt(rangeMin, rangeMax) + 1;

                } else {
                    effectAmount = Integer.parseInt(statEffect.group(2));
                }
                target.modifyConstitution(effectAmount);
            } else if (statEffect.group(1).equals("Wisdom")) {
                if (statEffect.group(3) != "") {
                    rangeMin = Integer.parseInt(statEffect.group(2));
                    rangeMax = Integer.parseInt(statEffect.group(3));
                    effectAmount = rand.nextInt(rangeMin, rangeMax) + 1;

                } else {
                    effectAmount = Integer.parseInt(statEffect.group(2));
                }
                target.modifyWisdom(effectAmount);
            } else if (statEffect.group(1).equals("Charisma")) {
                if (statEffect.group(3) != "") {
                    rangeMin = Integer.parseInt(statEffect.group(2));
                    rangeMax = Integer.parseInt(statEffect.group(3));
                    effectAmount = rand.nextInt(rangeMin, rangeMax) + 1;

                } else {
                    effectAmount = Integer.parseInt(statEffect.group(2));
                }
                target.modifyCharisma(effectAmount);
            } else if (statEffect.group(1).equals("Intelligence")) {
                if (statEffect.group(3) != "") {
                    rangeMin = Integer.parseInt(statEffect.group(2));
                    rangeMax = Integer.parseInt(statEffect.group(3));
                    effectAmount = rand.nextInt(rangeMin, rangeMax) + 1;

                } else {
                    effectAmount = Integer.parseInt(statEffect.group(2));
                }
                target.modifyIntellignce(effectAmount);

            }
        }

    }

    /**
     * @param actor
     */
    public static void doPassives(Actor actor) {

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
