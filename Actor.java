import java.util.Random;
import java.util.ArrayList;

public class Actor {
    protected int xCoord;
    protected int yCoord;
    protected int health;
    protected int AC;
    protected int speed;
    protected int mana;
    protected int strength;
    protected int dexterity;
    protected int constitution;
    protected int intelligence;
    protected int wisdom;
    protected int charisma;
    protected int noise;
    protected int carryWeight;
    protected ArrayList<String> effects;
    protected String name;
    protected ArrayList<Interactable> inventory = new ArrayList<Interactable>();
    protected int shield;
    protected Random rand = new Random();

    public Actor() {
    }

    public Actor(int xCoord, int yCoord, int AC, int strength, int dexterity,
            int constitution, int intelligence, int wisdom, int charisma,
            int noise, int sheild, String name) {

        setStrength(strength);
        setDexterity(dexterity);
        setConstitution(constitution);
        setIntelligence(intelligence);
        setWisdom(wisdom);
        setCharisma(charisma);
        setXCoord(xCoord);
        setYCoord(yCoord);
        setAC(AC);
        setSpeed(speed);
        setMana();
        setNoise(noise);
        setCarryWeight();
        setInventory();
        setShield(sheild);
        setHealth();
        setName(name);

    }

    public void setXCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    public void setHealth() {
        health = constitution * 2;
    }

    public void setAC(int AC) {
        this.AC = AC;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setMana() {
        mana = intelligence * 2;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public void setConstitution(int constituion) {
        this.constitution = constituion;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    public void setNoise(int noise) {
        this.noise = noise;
    }

    public void setCarryWeight() {
        carryWeight = strength * 2;
    }

    public void setInventory() {
        inventory = new ArrayList<Interactable>();

    }

    public void setShield(int sheild) {
        this.shield = sheild;
    }

    public int getXCoord() {
        return xCoord;
    }

    public int getYCoord() {
        return yCoord;
    }

    public int getHealth() {
        return health;
    }

    public int getAC() {
        return AC;
    }

    public int getSpeed() {
        return speed;
    }

    public int getMana() {
        return mana;
    }

    public int getStrength() {
        return strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getConstitution() {
        return constitution;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getWisdom() {
        return wisdom;
    }

    public int getCharisma() {
        return charisma;
    }

    public int getNoise() {
        return noise;
    }

    public int getCarryWeight() {
        return carryWeight;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Interactable> getInventory() {
        return inventory;
    }

    public int getShield() {
        return shield;
    }

    public void putItem(Interactable interactable){
        inventory.add(interactable);
    }

    public void takeDamage(int damage, String name, int AC) {
        int finalDamage = damage - AC;
        if (finalDamage < 0) {
            finalDamage = 0;
        }
        health -= finalDamage;
        System.out.printf("%d damage%n", damage);
        if (health <= 0) {
            if (name == "player") {
                System.out.println("You have been killed");
            } else {
                System.out.printf("%s is defeated");
            }
        }
    }

    public void heal(int heal, String name) {
        health += heal;
        if (name == "player") {
            System.out.printf("you just healed %d amount of health new health is", heal, health);
        }
    }

    public void moveNorth(boolean doorLocked, boolean doorBarred) {
        if (doorLocked == false && doorBarred == false) {
            yCoord++;
        } else if (doorLocked == true) {
            System.out.print("Door is locked");
        } else {
            System.out.print("The Door is barred");
        }

    }

    public void moveSouth() {
        yCoord--;
    }

    public void moveWest() {
        xCoord--;
    }

    public void moveEast() {
        xCoord++;
    }

    public ArrayList<Interactable> search() {
        return inventory; // To be replaced when we have the variable for the items in the room
    }

    /*
     * this works a little werid basicly you WANT negativeModdifier because inorder
     * to hit you need to roll below you BS so negativeModdifiers make it easier to
     * hit possitve modifiers
     * make it harder to hit. If anyone reading this wants a better explination
     * speak to Xander in person because it is easier to describe that way.
     */
    public void magic(int minDamage, int maxDamage, int targetHealth, String targetName, int BS,
            int negativeModdifier,
            int possitveModdifier, int targetAC) {
        int finalDamage = rand.nextInt(minDamage, maxDamage);

        if (rand.nextInt(100) + 1 - negativeModdifier + possitveModdifier <= BS) {
            takeDamage(finalDamage, targetName, targetAC);
        } else {
            System.out.println("Miss");
        }

    }

    public void ranged(int arrowCount, int damageMin, int damageMax, int targetHealth, String targetName, int BS,
            int negativeModdifier,
            int possitiveModdifier, int targetAC) {
        int finalDamage = rand.nextInt(damageMin, damageMax);
        if (arrowCount > 0) {
            if (rand.nextInt(100) + 1 - negativeModdifier + possitiveModdifier <= BS) {
                takeDamage(finalDamage, targetName, targetAC);
            } else {
                System.out.println("Miss");
            }
        }
    }

    public void closeCombat(int damageMin, int damageMax, int targetHealth, String targetName, int WS,
            int negativeModdifier, int possitiveModdifier, int targetAC) {
        int finalDamage = rand.nextInt(damageMin, damageMax);
        if (rand.nextInt(100) + 1 - negativeModdifier + possitiveModdifier <= WS) {
            takeDamage(finalDamage, targetName, targetAC);
        } else {
            System.out.println("Miss");
        }
    }

    public void cast(int castorMana, String type, int spellMana, String spellName) {
        if (spellMana <= castorMana) {
            castorMana -= spellMana;
            if (type == "friendly") {

            } else if (type == "attack") {

            }
        }
    }

    public void openInventory() {

    }

    public void grabFromInventory() {

    }

}
