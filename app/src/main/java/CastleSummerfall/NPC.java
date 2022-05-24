package CastleSummerfall;

import java.util.Random;

public class NPC extends Actor {
    protected NPCAlliance npcAlliance;
    protected String description;
    private Random rand = new Random();
    final int floorsize = 5;
    protected boolean playerDetected;
    protected boolean playerSeen;
    protected boolean playerHeard;
    protected Direction playerDirection;
    private boolean boss;

    public NPC() {

    }

    public NPC(int xCoord, int yCoord, int AC, int strength, int dexterity, int constitution, int intelligence,
            int wisdom, int charisma, int noise, int shield, String name, NPCAlliance npcAlliance, String description) {
        super(xCoord, yCoord, AC, strength, dexterity, constitution, intelligence, wisdom, charisma, noise, shield,
                name);
        setAlliance(npcAlliance);
        setDescription(description);
        setDexterity(dexterity);
        setWeaponSkill();
        boss = false;

    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This will set the Alliance of a NPC so it will attack/not attack the player
     *
     * @param alliance
     */
    public void setAlliance(NPCAlliance alliance) {
        npcAlliance = alliance;
    }

    /**
     * This will make the NPC attack the player
     *
     * @param player
     * @param floorSize
     */
    public void npcTurnAlliance(Actor player, int floorSize) {
        if (maxShield > 0) {
            shield += shieldRegen;
            if (shield > maxShield) {
                shield = maxShield;
            }
        }
        while (energy >= 0) {
            if (npcAlliance == NPCAlliance.ENEMY) {
                if (player.getXCoord() == xCoord && player.getYCoord() == yCoord) {
                    enemyTurnCombat(player);
                    energy -= 10;
                } else {
                    enemyTurnNoneCombat(floorSize, player);
                    energy -= 10;
                }
            } else if (npcAlliance == NPCAlliance.FRIENDLY) {

            } else if (npcAlliance == NPCAlliance.NEUTRAL) {

            }
        }
    }

    /**
     * this will move the NPC instead
     * 
     * @param floorSize
     * @param player
     */
    public void enemyTurnNoneCombat(int floorSize, Actor player) {
        if (player.getXCoord() - 3 < xCoord || player.getXCoord() + 3 > xCoord && player.getYCoord() + 3 > yCoord
                || player.getYCoord() - 3 < yCoord) {
            if (player.getXCoord() - 3 < xCoord) {
                xCoord--;
            } else if (player.getXCoord() + 3 > xCoord) {
                xCoord++;
            } else if (player.getYCoord() - 3 < yCoord) {
                yCoord--;
            } else {
                xCoord++;
            }

        } else {
            int direction = rand.nextInt(3);
            switch (direction) {
            case 0:
                if (yCoord < floorSize - 1 && yCoord >= 0) {
                    yCoord++;
                    // System.out.printf("%s moved North to coordinates x%d, y%d%n", name, xCoord,
                    // yCoord);
                    break;
                }
            case 1:
                if (yCoord < floorSize - 1 && yCoord > 0) {
                    yCoord--;
                    // System.out.printf("%s moved South to coordinates x%d, y%d%n", name, xCoord,
                    // yCoord);
                    break;
                }
            case 2:
                if (xCoord < floorSize - 1 && xCoord >= 0) {
                    xCoord++;
                    // System.out.printf("%s moved East to coordinates x%d, y%d%n", name, xCoord,
                    // yCoord);
                    break;
                }
            case 3:
                if (xCoord < floorSize - 1 && xCoord > 0) {
                    xCoord--;
                    // System.out.printf("%s moved West to coordinates x%d, y%d%n", name, xCoord,
                    // yCoord);
                    break;
                }
            default:

            }
        }

    }

    public void friendlyTurnNoneCombat() {

    }

    public void neutralTurnNoneCombat() {

    }

    /**
     * This will make the player take damage
     * 
     * @param player
     */
    public void enemyTurnCombat(Actor player) {
        Weapon weapon = (Weapon) inventory.get(0);
        System.out.println(UI.colorString("Damage: " + weapon.damage, UI.Colors.RED));
        closeCombat(weapon, player);
        System.out.printf("%s wacked you%n", name);
    }

    public void friendlyTurnCombat() {

    }

    public void neutralTurnCombat() {

    }

    /**
     * @return String
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return boolean
     */
    public boolean isBoss() {
        return boss;
    }

    public void makeBoss() {
        boss = true;
    }

}
