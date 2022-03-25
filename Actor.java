
public class Actor {
    private int xCoord;
    private int yCoord;
    private int health;
    private int AC;
    private int speed;
    private int mana;
    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;
    private int noise;
    private int carryWeight;
    private String[] inventory;
    private int shield;

    public Actor() {

    }

    public Actor(int xCoord, int yCoord, int health, int AC, int speed, int mana, int strength, int dexterity,
            int constitution, int intelligence, int wisdom, int charisma,
            int noise, int carryWeight, String[] inventory, int sheild) {
        super();
        setXCoord(xCoord);
        setYCoord(yCoord);
        setHealth(health);
        setAC(AC);
        setSpeed(speed);
        setMana(mana);
        setStrength(strength);
        setDexterity(dexterity);
        setConstitution(constitution);
        setIntelligence(intelligence);
        setWisdom(wisdom);
        setCharisma(charisma);
        setNoise(noise);
        setCarryWeight(carryWeight);
        setInventory(inventory);
        setShield(sheild);

    }

    public void setXCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public void setYCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setAC(int AC) {
        this.AC = AC;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setMana(int mana) {
        this.mana = mana;
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

    public void setCarryWeight(int carryWeight) {
        this.carryWeight = carryWeight;
    }

    public void setInventory(String[] inventory) {
        this.inventory = inventory;
    }

    public void setShield(int sheild) {
        this.shield = sheild;
    }

    public static void moveRoom() {

    }

    public static void search() {

    }

    public static void magic() {

    }

    public static void ranged() {

    }

    public static void closeCombat() {

    }

    public static void openInventory() {

    }

    public static void grabFromInventory() {

    }

    public static void openDoor() {

    }

    public static void closeDoor() {

    }

    public static void barDoor() {

    }

    public static void unBarDoor() {

    }

    public static void lockDoor() {

    }

    public static void unlockDoor() {

    }

    public static void grabFromRoom() {

    }
}
