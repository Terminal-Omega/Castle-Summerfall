import java.util.ArrayList;
import java.util.Random;

//this is a grouping of methods to randomly generate any given game object.
//A room, an interactable, a floor, an enemy, etc.
public class Generator {

    public static Floor generateFloor(int xSize, int ySize) {
        ArrayList<ArrayList<Room>> rooms = new ArrayList<>();
        for (int i = 0; i < xSize; i++) {
            ArrayList<Room> column = new ArrayList<>();
            for (int j = 0; j < ySize; j++) {
                column.add(generateRoom(1, 3));
            }
            rooms.add(column);
        }
        Floor result = new Floor(rooms);
        return result;
    }

    public static Room generateRoom(int interactableMin, int interactableMax) {
        int range = interactableMax - interactableMin;
        Random rand = new Random();
        int loopCount = rand.nextInt(range) + interactableMin;
        ArrayList<Interactable> roomInventory = new ArrayList<Interactable>();
        for (int i = 0; i < loopCount; i++) {
            roomInventory.add(generateInteractable());
        }
        Room result = new Room(roomInventory, "This is a room");
        return result;
    }

    // below this is all the interactable generation.
    public static Interactable generateInteractable() {
        Random rand = new Random();
        Interactable result;
        switch (rand.nextInt(6)) {
            case 0:
                result = chest();
                break;
            case 1:
                result = knife();
                break;
            case 2:
                result = shortSword();
                break;
            case 3:
                result = sword();
                break;
            case 4:
                result = greatSword();
                break;
            case 5:
                result = rapier();
                break;
            default:
                result = null;
                break;
        }

        return result;
    }

    // interactables
    private static Interactable chest() {
        Random rand = new Random();
        ArrayList<Interactable> chestInventory = new ArrayList<Interactable>();
        int loopCount = rand.nextInt(3);
        for (int i = 0; i < loopCount; i++) {
            chestInventory.add(generateInteractable());
        }

        Interactable result = new Interactable(5, 20, false, "Chest", "It's a box.", chestInventory);
        return result;
    }

    // Weapons
    private static Weapon knife() {
        Weapon result = new Weapon(1, 5, true, "Knife", "It's kinda sharp.", 3, 4);
        return result;
    }

    private static Weapon shortSword() {
        Weapon result = new Weapon(2, 8, true, "Short Sword", "Like a knife, but long.", 5, 6);
        return result;
    }

    private static Weapon sword() {
        Weapon result = new Weapon(2, 9, true, "Sword", "It might hurt someone", 6, 8);
        return result;
    }

    private static Weapon greatSword() {
        Weapon result = new Weapon(3, 11, true, "Greatsword", "This is a big sword.", 10, 13);
        return result;
    }

    private static Weapon rapier() {
        Weapon result = new Weapon(1, 6, true, "Rapier", "Thin and deadly.", 13, 8);
        return result;
    }

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
    private static Enemy zombie(int xCoord, int yCoord) {
        int health = 10;
        int AC = 10;
        int speed = 1;
        int mana = 0;
        int strength = 5;
        int dexterity = 5;
        int constitution = 5;
        int intelligence = 5;
        int wisdom = 5;
        int charismma = 5;
        int noise = 5;
        int carryWeight = 5;
        String name = "Zombie";
        int shield = 10;
        String npcAlliance = "Enemy";
        String description = "This is a zombie.";
        Enemy result = new Enemy(xCoord, yCoord, health, AC, speed, mana, strength, dexterity, constitution,
                intelligence, wisdom, charismma, noise, carryWeight, name, shield, npcAlliance, description);
        return result;
    }

    private static Enemy skeleton(int xCoord, int yCoord) {
        int health = 10;
        int AC = 10;
        int speed = 1;
        int mana = 0;
        int strength = 5;
        int dexterity = 5;
        int constitution = 5;
        int intelligence = 5;
        int wisdom = 5;
        int charismma = 5;
        int noise = 5;
        int carryWeight = 5;
        String name = "Skeleton";
        int shield = 10;
        String npcAlliance = "Enemy";
        String description = "This is a Skeleton.";
        Enemy result = new Enemy(xCoord, yCoord, health, AC, speed, mana, strength, dexterity, constitution,
                intelligence, wisdom, charismma, noise, carryWeight, name, shield, npcAlliance, description);
        return result;

    }

    private static Enemy goblin(int xCoord, int yCoord) {
        int health = 10;
        int AC = 10;
        int speed = 1;
        int mana = 0;
        int strength = 5;
        int dexterity = 5;
        int constitution = 5;
        int intelligence = 5;
        int wisdom = 5;
        int charismma = 5;
        int noise = 5;
        int carryWeight = 5;
        String name = "Goblin";
        int shield = 10;
        String npcAlliance = "Enemy";
        String description = "This is a Goblin.";
        Enemy result = new Enemy(xCoord, yCoord, health, AC, speed, mana, strength, dexterity, constitution,
                intelligence, wisdom, charismma, noise, carryWeight, name, shield, npcAlliance, description);
        return result;

    }

}
