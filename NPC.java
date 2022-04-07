import java.util.Random;
import java.util.ArrayList;

public class NPC extends Actor {
    protected String npcAllience;
    protected String description;
    private Random rand = new Random();
    final int floorsize = 5;
    protected boolean playerDetected;
    protected boolean playerSeen;
    protected boolean playerHeard;
    protected Direction playerDirection;

    public NPC() {

    }

    public NPC(int xCoord, int yCoord, int AC, int strength, int dexterity,
            int constitution, int intelligence, int wisdom, int charisma,
            int noise, int shield, String name, String npcAllience, String description, int health) {
        super(xCoord, yCoord, AC, strength, dexterity, constitution, intelligence, wisdom,
                charisma, noise,
                shield, name, health);
    }

    public NPC(String allience) {
        setAllience(allience);

    }

    public void setAllience(String allience) {
        npcAllience = allience;
    }

    public void npcTurnAllience(Player player, int floorSize) {
        if (npcAllience == "Enemy") {
            if (player.getXCoord() == xCoord && player.getYCoord() == yCoord) {
                enemyTurnCombat();
            } else {
                enemyTurnNoneCombat(floorSize);
            }
        } else if (npcAllience == "Friendly") {

        } else {

        }
    }

    public void enemyTurnNoneCombat(int floorSize) {
        int direction = rand.nextInt();
        switch (direction) {
            case 0:
                if (yCoord < floorSize - 1 && yCoord >= 0) {
                    yCoord++;
                }
            case 1:
                if (yCoord < floorSize - 1 && yCoord > 0) {
                    yCoord--;
                }
            case 2:
                if (xCoord < floorSize - 1 && xCoord >= 0) {
                    xCoord++;
                }
            case 3:
                if (yCoord < floorSize - 1 && yCoord > 0) {
                    yCoord--;
                }
            default:

        }

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
