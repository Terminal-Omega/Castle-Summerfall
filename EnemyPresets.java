import java.util.Random;

public class EnemyPresets {

     /*
     * example and description of what everything does for creation of enemies
     * / private static Enemy INSERTNAMEHERE(int xCoord, int yCoord){
     * // int health = AMOUNT //how much health the enemy has
     * // int AC = AMOUNT //how much damge reduction they have
     * // int speed = AMOUNT //for latter impliamtation can effect how many actions
     * // have you
     * /// get per turn
     * // int mana = AMOUNT // how much magic you have
     * // int strenght = AMOUNT // this desides things like carryWeight and also
     * // will be implimented latterlatter
     * /// on damage
     * // int dexterity = AMOUNT // this helps decided speed
     * // int constitution = AMOUNT // resilience to certain things
     * // int intelligence = AMOUNT // for latter implitation manily dealing with AI
     * // int wisdom = AMOUNT // same as intelligence
     * // int charisma = AMOUNT // how this charecter interacts with other NPC
     * // int noise = AMOUNT // this changes around saying how far they can be
     * // detected
     * /// everyone has a natural noise level
     * // String name = NAMW // the name of the enemy
     * // int shield = AMOUNT // a special second health bar
     * // String npcAlliance = ALLIANCE // is the NPC friendly, neutral, or enemy to
     * // the player
     * // String description = DESCRIPTION // the description of the NPC that the
     * /// player can see if they check
     * //Enemy result = new Enemy(xCoord, yCoord, health, AC, speed, mana, strength,
     * dexterity, constitution,
     * intelligence, wisdom, charismma, noise, carryWeight, name, shield,
     * npcAlliance, description);
     * return result;
     * }
     */

    public static NPC generateEnemy(int xCoord, int yCoord) {
        Random rand = new Random();
        switch (rand.nextInt(3)) {
            case 0:
                return zombie(xCoord, yCoord);
            case 1:
                return skeleton(xCoord, yCoord);
            case 2:
                return goblin(xCoord, yCoord);
            default:
                return null;
        }
    }

    private static NPC zombie(int xCoord, int yCoord) {
        int AC = 10;
        int strength = 5;
        int dexterity = 5;
        int constitution = 5;
        int intelligence = 5;
        int wisdom = 5;
        int charismma = 5;
        int noise = 5;
        String name = "Zombie";
        int shield = 10;
        String npcAlliance = "Enemy";
        String description = "This is a zombie.";
        NPC result = new NPC(xCoord, yCoord, AC, strength, dexterity, constitution,
                intelligence, wisdom, charismma, noise, shield, name, npcAlliance, description);
        return result;
    }

    private static NPC skeleton(int xCoord, int yCoord) {
        int AC = 10;
        int strength = 5;
        int dexterity = 5;
        int constitution = 5;
        int intelligence = 5;
        int wisdom = 5;
        int charismma = 5;
        int noise = 5;
        String name = "Skeleton";
        int shield = 10;
        String npcAlliance = "Enemy";
        String description = "This is a Skeleton.";
        NPC result = new NPC(xCoord, yCoord, AC, strength, dexterity, constitution,
                intelligence, wisdom, charismma, noise, shield, name, npcAlliance, description);
        return result;

    }

    private static NPC goblin(int xCoord, int yCoord) {
        int AC = 10;
        int strength = 5;
        int dexterity = 5;
        int constitution = 5;
        int intelligence = 5;
        int wisdom = 5;
        int charismma = 5;
        int noise = 5;
        String name = "Goblin";
        int shield = 10;
        String npcAlliance = "Enemy";
        String description = "This is a Goblin.";
        NPC result = new NPC(xCoord, yCoord, AC, strength, dexterity, constitution,
                intelligence, wisdom, charismma, noise, shield, name, npcAlliance, description);
        return result;

    }
}
