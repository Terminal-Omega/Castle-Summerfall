import java.util.Random;
import java.util.ArrayList;

public class NPC extends Actor {
    protected String npcAllience;
    protected String description;

    public NPC() {

    }

    public NPC(int xCoord, int yCoord, int AC, int strength, int dexterity,
            int constitution, int intelligence, int wisdom, int charisma,
            int noise, int shield, String name, String npcAllience, String description) {
        super(xCoord, yCoord, AC, strength, dexterity, constitution, intelligence, wisdom,
                charisma, noise,
                shield, name);
    }

    public NPC(String allience) {
        setAllience(allience);

    }

    public void setAllience(String allience) {
        npcAllience = allience;
    }

    public void npcTurnCheck(Player player) {
        if (player.getXCoord() == xCoord && player.getYCoord() == yCoord) {
            npcTurnNoneCombat();
        } else {
            npcTurnCombat();
        }
    }

    public void npcTurnCombat() {
        if (npcAllience == "Enemy") {
            enemyTurnCombat();
        } else if (npcAllience == "Friendly") {
            friendlyTurnCombat();
        } else {
            neutralTurnCombat();
        }
    }

    public void npcTurnNoneCombat() {
        if (npcAllience == "Enemy") {
            enemyTurnNoneCombat();
        } else if (npcAllience == "Friendly") {
            friendlyTurnNoneCombat();
        } else {
            neutralTurnNoneCombat();
        }
    }

    public void enemyTurnNoneCombat() {

    }

    public void friendlyTurnNoneCombat() {

    }

    public void neutralTurnNoneCombat() {

    }

    public void enemyTurnCombat() {

    }

    public void friendlyTurnCombat() {

    }

    public void neutralTurnCombat() {

    }

}
