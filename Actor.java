import java.util.Random;
import java.util.ArrayList;

public class Actor {
    protected int xCoord;
    protected int yCoord;
    protected int maxHealth;
    protected int health;
    protected int AC;
    protected int speed;
    protected int mana;
    protected int strength;
    protected int dexterity;
    protected int ballisticSkill;
    protected int weaponSkill;
    protected int constitution;
    protected int intelligence;
    protected int wisdom;
    protected int charisma;
    protected int noise;
    protected int carryWeight;
    protected ArrayList<String> effects;
    protected String name;
    protected ArrayList<Interactable> inventory;
    protected int shield;
    protected Random rand = new Random();

    public Actor() {
    }

    public Actor(int xCoord, int yCoord, int AC, int strength, int dexterity,
            int constitution, int intelligence, int wisdom, int charisma,
            int noise, int sheild, String name, int health) {
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
        inventory = new ArrayList<Interactable>();
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

    public void setBallisticSkill() {
        ballisticSkill = intelligence * 2;
    }

    public void setWweaponSkill() {
        weaponSkill = dexterity * 2;
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

    public int getBallisticSkill() {
        return ballisticSkill;
    }

    public int getWeaponSkill() {
        return weaponSkill;
    }

    public void putItem(Interactable interactable) {
        inventory.add(interactable);
    }

    public void takeDamage(int damage) {
        int finalDamage = damage - AC;
        if (finalDamage < 0) {
            finalDamage = 0;
        }
        health -= finalDamage;
        if (health <= 0) {
            System.out.print("Dead");
        }
    }

    public void heal(int heal) {
        health += heal;

    }

    public void damageNoAC(int damage) {
        health -= damage;
        if (health < -0) {
            System.out.print("Dead");
        }
    }

    public boolean closeCombat(Weapon weapon, Actor target) {
        if (rand.nextInt(100) + 1 <= weaponSkill) {
            target.takeDamage(weapon.damage);
            System.out.println("x");
            return true;
        }
        return false;
    }

    public void cast(int castorMana, String type, int spellMana, String spellName) {
        if (spellMana <= castorMana) {
            castorMana -= spellMana;
            if (type == "friendly") {

            } else if (type == "attack") {

            }
        }
    }

}
