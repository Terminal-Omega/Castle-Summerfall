package game;

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
     * @param npcAllience
     * @param description
     */
    public Boss(int xCoord, int yCoord, int AC, int strength, int dexterity,
            int constitution, int intelligence, int wisdom, int charisma,
            int noise, int shield, String name, NpcAllience npcAllience, String description) {
        super(xCoord, yCoord, AC, strength, dexterity, constitution, intelligence, wisdom,
                charisma, noise,
                shield, name, npcAllience, description);

    }

    /**
     * 
     * @param player
     */
    public void bossFight(Actor player) {
        int attackChoice = 0;
        if (health <= maxHealth / 2) {
            attackChoice = rand.nextInt(4);
        } else {
            attackChoice = rand.nextInt(3);
        }

        switch (attackChoice) {
            case 0:
                Weapon weapon1 = (Weapon) inventory.get(0);
                System.out.println(weapon1.damage + "Slam");
                closeCombat(weapon1, player);
            case 1:
                Weapon weapon2 = (Weapon) inventory.get(1);
                System.out.println(weapon2.damage + "Swish");
                closeCombat(weapon2, player);
            case 2:
                Weapon weapon3 = (Weapon) inventory.get(2);
                System.out.println(weapon3.damage + "Smack");
                closeCombat(weapon3, player);
            case 3:
                int healAmountBaseMax = maxHealth / 2;
                int healAmountBaseMin = maxHealth / 4;
                int healAmount = rand.nextInt(healAmountBaseMin, healAmountBaseMax);
                heal(healAmount);

            default:
                System.out.println("uh Something broke... well that is annoying");

        }
    }

    @Override
    public void npcTurnAllience(Actor Player, int FloorSize){
        bossFight(Player);
    }
}
