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
    protected int maxHealth;
    protected int maxSpeed;
    protected int maxMana;
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
        inventory = new ArrayList<Interactable>();
    }

    
    /** 
     * @param xCoord
     */
    public void setXCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    
    /** 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    
    /** 
     * @param yCoord
     */
    public void setYCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    public void setHealth() {
        health = constitution * 2;
        maxHealth = health;
    }

    
    /** 
     * @param AC
     */
    public void setAC(int AC) {
        this.AC = AC;
    }

    
    /** 
     * @param speed
     */
    public void setSpeed(int speed) {
        this.speed = speed;
        maxSpeed = speed;
    }

    public void setMana() {
        mana = intelligence * 2;
        maxMana = mana;
    }

    
    /** 
     * @param strength
     */
    public void setStrength(int strength) {
        this.strength = strength;
    }

    
    /** 
     * @param dexterity
     */
    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    
    /** 
     * @param constituion
     */
    public void setConstitution(int constituion) {
        this.constitution = constituion;
    }

    
    /** 
     * @param intelligence
     */
    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    
    /** 
     * @param wisdom
     */
    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }

    
    /** 
     * @param charisma
     */
    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    
    /** 
     * @param noise
     */
    public void setNoise(int noise) {
        this.noise = noise;
    }

    public void setCarryWeight() {
        carryWeight = strength * 2;
    }

    public void setInventory() {
        inventory = new ArrayList<Interactable>();

    }

    
    /** 
     * @param sheild
     */
    public void setShield(int sheild) {
        this.shield = sheild;
    }

    public void setBallisticSkill() {
        ballisticSkill = intelligence * 2;
    }

    public void setWweaponSkill() {
        weaponSkill = dexterity * 2;
    }

    
    /** 
     * @return int
     */
    public int getXCoord() {
        return xCoord;
    }

    
    /** 
     * @return int
     */
    public int getYCoord() {
        return yCoord;
    }

    
    /** 
     * @return int
     */
    public int getHealth() {
        return health;
    }

    
    /** 
     * @return int
     */
    public int getAC() {
        return AC;
    }

    
    /** 
     * @return int
     */
    public int getSpeed() {
        return speed;
    }

    
    /** 
     * @return int
     */
    public int getMana() {
        return mana;
    }

    
    /** 
     * @return int
     */
    public int getStrength() {
        return strength;
    }

    
    /** 
     * @return int
     */
    public int getDexterity() {
        return dexterity;
    }

    
    /** 
     * @return int
     */
    public int getConstitution() {
        return constitution;
    }

    
    /** 
     * @return int
     */
    public int getIntelligence() {
        return intelligence;
    }

    
    /** 
     * @return int
     */
    public int getWisdom() {
        return wisdom;
    }

    
    /** 
     * @return int
     */
    public int getCharisma() {
        return charisma;
    }

    
    /** 
     * @return int
     */
    public int getNoise() {
        return noise;
    }

    
    /** 
     * @return int
     */
    public int getCarryWeight() {
        return carryWeight;
    }

    
    /** 
     * @return String
     */
    public String getName() {
        return name;
    }

    
    /** 
     * @return ArrayList<Interactable>
     */
    public ArrayList<Interactable> getInventory() {
        return inventory;
    }

    
    /** 
     * @return int
     */
    public int getShield() {
        return shield;
    }

    
    /** 
     * @return int
     */
    public int getBallisticSkill() {
        return ballisticSkill;
    }

    
    /** 
     * @return int
     */
    public int getWeaponSkill() {
        return weaponSkill;
    }

    
    /** 
     * @return int
     */
    public int getMaxHealth() {
        return maxHealth;

    }

    
    /** 
     * @return int
     */
    public int getMaxSpeed() {
        return maxSpeed;
    }

    
    /** 
     * @return int
     */
    public int getMaxMana() {
        return maxMana;
    }

    
    /** 
     * @param interactable
     */
    public void putItem(Interactable interactable) {
        inventory.add(interactable);
    }

    
    /** 
     * @param damage
     */
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

    
    /** 
     * @param heal
     */
    public void heal(int heal) {
        health += heal;

    }

    
    /** 
     * @param damage
     */
    public void damageNoAC(int damage) {
        health -= damage;
        if (health < -0) {
            System.out.print("Dead");
        }
    }

    
    /** 
     * @param weapon
     * @param target
     * @return boolean
     */
    public boolean closeCombat(Weapon weapon, Actor target) {
        if (rand.nextInt(100) + 1 <= weaponSkill) {
            target.takeDamage(weapon.damage);
            System.out.println("x");
            return true;
        }
        return false;
    }

    
    /** 
     * @param castorMana
     * @param type
     * @param spellMana
     * @param spellName
     */
    public void cast(int castorMana, String type, int spellMana, String spellName) {
        if (spellMana <= castorMana) {
            castorMana -= spellMana;
            if (type == "friendly") {

            } else if (type == "attack") {

            }
        }
    }

}
