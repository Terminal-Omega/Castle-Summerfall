package game;

import java.util.regex.Pattern;
import java.util.Random;
import java.util.regex.Matcher;
import java.lang.Integer;

public class Effects {
    String type;
    String charecteristic;
    int amount;
    int duration;

    public Effects(String type, int amount, int duration) {
        this(type, "none", amount, duration);
    }

    public Effects(String type, String charecteristic, int amount, int duration) {
        this.type = type;
        this.charecteristic = charecteristic;
        this.amount = amount;
        this.duration = duration;
    }

    public void createEffect(String effect, Actor target) {
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
}
