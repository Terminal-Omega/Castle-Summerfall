package CastleSummerfall;

import java.util.Random;

public class Boss extends NPC {
    Random rand = new Random();

    /**
     * 
     * @param xCoord
     * @param yCoord
     * @param AC
     * @param strength
     * @param dexterity
     * @param constitution
     * @param intelligence
     * @param wisdom
     * @param charisma
     * @param noise
     * @param shield
     * @param name
     * @param npcAlliance
     * @param description
     */
    public Boss(int xCoord, int yCoord, int AC, int strength, int dexterity, int constitution, int intelligence,
            int wisdom, int charisma, int noise, int shield, String name, NPCAlliance npcAlliance, String description) {
        super(xCoord, yCoord, AC, strength, dexterity, constitution, intelligence, wisdom, charisma, noise, shield,
                name, npcAlliance, description);

    }

    /**
     * 
     * @param player
     */
    public void bossFight(Actor player) {
        int healChance = rand.nextInt(100) + 1;
        if (health < maxHealth / 2 && healChance <= 30) {
            int healAmount = rand.nextInt(maxHealth / 4) + maxHealth / 4;
            heal(healAmount);
        } else {
            int weaponChoiseInt = rand.nextInt(inventory.size() - 1);
            Weapon weaponChoise = (Weapon) inventory.get(weaponChoiseInt);
            closeCombat(weaponChoise, player);
        }
    }

    /**
     * @param Player
     * @param FloorSize
     */
    @Override
    public void npcTurnAlliance(Actor Player, int FloorSize) {
        bossFight(Player);
    }
}
