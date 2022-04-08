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
            int noise, int shield, String name, String npcAllience, String description) {
        super(xCoord, yCoord, AC, strength, dexterity, constitution, intelligence, wisdom,
                charisma, noise,
                shield, name);
        setAllience(npcAllience);
    }

    public NPC(String allience) {
        setAllience(allience);

    }

    /**
     * @param allience
     */
    public void setAllience(String allience) {
        npcAllience = allience;
    }

    /**
     * @param player
     * @param floorSize
     */
    public void npcTurnAllience(Player player, int floorSize) {
        if (npcAllience.equals("Enemy")) {
            if (player.getXCoord() == xCoord && player.getYCoord() == yCoord) {
                enemyTurnCombat();
            } else {
                enemyTurnNoneCombat(floorSize);
            }
        } else if (npcAllience == "Friendly") {

        } else {

        }
    }

    /**
     * @param floorSize
     */
    public void enemyTurnNoneCombat(int floorSize) {
        int direction = rand.nextInt(3);
        switch (direction) {
            case 0:
                if (yCoord < floorSize - 1 && yCoord >= 0) {
                    yCoord++;
                    System.out.printf("%s moved North to cordanites x%d, y%d", name, xCoord, yCoord);
                    break;
                }
            case 1:
                if (yCoord < floorSize - 1 && yCoord > 0) {
                    yCoord--;
                    System.out.printf("%s moved South to cordanites x%d, y%d", name, xCoord, yCoord);
                    break;
                }
            case 2:
                if (xCoord < floorSize - 1 && xCoord >= 0) {
                    xCoord++;
                    System.out.printf("%s moved East to cordanites x%d, y%d", name, xCoord, yCoord);
                    break;
                }
            case 3:
                if (xCoord < floorSize - 1 && xCoord > 0) {
                    xCoord--;
                    System.out.printf("%s moved West to cordanites x%d, y%d", name, xCoord, yCoord);
                    break;
                }
            default:

        }

    }

    public void friendlyTurnNoneCombat() {

    }

    public void neutralTurnNoneCombat() {

    }

    public void enemyTurnCombat() {
        System.out.printf("%s wacked the player%n", name);
    }

    public void friendlyTurnCombat() {

    }

    public void neutralTurnCombat() {

    }

}
