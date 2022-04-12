package game;

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
    protected int maxShield;
    protected int shieldRegen;
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
        setWeaponSkill();
        setBallisticSkill();
        inventory = new ArrayList<Interactable>();
    }

    /**
     * This will set the x coordinate of the Actor
     * 
     * @param xCoord
     */
    public void setXCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    /**
     * This will set the name of the Actor
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This will set the y coordinate of the player
     * 
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
     * This will set the Armor Class of the Actor
     * 
     * @param AC
     */
    public void setAC(int AC) {
        this.AC = AC;
    }

    /**
     * This will set the speed of the Actor
     * 
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
     * This sets the strength of the Actor
     * 
     * @param strength
     */
    public void setStrength(int strength) {
        this.strength = strength;
    }

    /**
     * This sets the dexterity of the Actor
     * 
     * @param dexterity
     */
    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    /**
     * This will set the constituion of the Actor
     * 
     * @param constituion
     */
    public void setConstitution(int constituion) {
        this.constitution = constituion;
    }

    /**
     * This sets the intelligence of the Actor
     * 
     * @param intelligence
     */
    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    /**
     * This sets the wisdom of the Actor
     * 
     * @param wisdom
     */
    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }

    /**
     * This sets the charisma of the Actor
     * 
     * @param charisma
     */
    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    /**
     * This sets the Noise level the Actor will make
     * 
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
     * This will add an Interactable to an Actors inventory
     * 
     * @param item
     */
    public void addInventory(Interactable item) {
        inventory.add(item);
    }

    /**
     * This sets the sheild value of the Actor
     * 
     * @param sheild
     */
    public void setShield(int sheild) {
        this.shield = sheild;
        maxShield = shield;
    }

    public void setBallisticSkill() {
        ballisticSkill = intelligence * 2;
    }

    public void setWeaponSkill() {
        weaponSkill = dexterity * 2;
    }

    /**
     * This gets the x coordinate of the Actor
     * 
     * @return int
     */
    public int getXCoord() {
        return xCoord;
    }

    /**
     * This gets the y coordinate of the Actor
     * 
     * @return int
     */
    public int getYCoord() {
        return yCoord;
    }

    /**
     * This gets the current health of the Actor
     * 
     * @return int
     */
    public int getHealth() {
        return health;
    }

    /**
     * This returns the Armor Class of the Actor
     * 
     * @return int
     */
    public int getAC() {
        return AC;
    }

    /**
     * This will get the speed of the Actor
     * 
     * @return int
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * This will get the amount of mana the Actor has
     * 
     * @return int
     */
    public int getMana() {
        return mana;
    }

    /**
     * This returns the strength of the Actor
     * 
     * @return int
     */
    public int getStrength() {
        return strength;
    }

    /**
     * This return the dexterity of the Actor
     * 
     * @return int
     */
    public int getDexterity() {
        return dexterity;
    }

    /**
     * This will get the constitution of the Actor
     * 
     * @return int
     */
    public int getConstitution() {
        return constitution;
    }

    /**
     * This will get the intelligece of the Actor
     * 
     * @return int
     */
    public int getIntelligence() {
        return intelligence;
    }

    /**
     * This will get the wisdom of the Actor
     * 
     * @return int
     */
    public int getWisdom() {
        return wisdom;
    }

    /**
     * This will get the Charisma of the Actor
     * 
     * @return int
     */
    public int getCharisma() {
        return charisma;
    }

    /**
     * This will get the amount of noise the Actor makes
     * 
     * @return int
     */
    public int getNoise() {
        return noise;
    }

    /**
     * This will return how much the Actor can carry
     * 
     * @return int
     */
    public int getCarryWeight() {
        return carryWeight;
    }

    /**
     * This will return the name of the Actor
     * 
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * This will return the inventory of the Actor
     * 
     * @return ArrayList<Interactable>
     */
    public ArrayList<Interactable> getInventory() {
        return inventory;
    }

    /**
     * This will return the sheild strength of the Actor
     * 
     * @return int
     */
    public int getShield() {
        return shield;
    }

    /**
     * This will return the ballistic skill of the Actor
     * 
     * @return int
     */
    public int getBallisticSkill() {
        return ballisticSkill;
    }

    /**
     * This will return the weapon skill of the Actor
     * 
     * @return int
     */
    public int getWeaponSkill() {
        return weaponSkill;
    }

    /**
     * This will return the max health of the Actor
     * 
     * @return int
     */
    public int getMaxHealth() {
        return maxHealth;

    }

    /**
     * This will return the max speed of the Actor
     * 
     * @return int
     */
    public int getMaxSpeed() {
        return maxSpeed;
    }

    /**
     * This will return the max mana of the Actor
     * 
     * @return int
     */
    public int getMaxMana() {
        return maxMana;
    }

    /**
     * This will put an Iteractable in the Actors Inventory
     * 
     * @param interactable
     */
    public void putItem(Interactable interactable) {
        inventory.add(interactable);
    }

    /**
     * This will make the Actor take damage decreaseing their health
     * 
     * @param damage
     */
    public boolean takeDamage(int damage, int pierce) {
        int finalDamage = damage;
        int shieldStart = shield;
        int finalAC = AC - pierce;
        shield -= finalDamage;
        if (shield < 0) {
            shield = 0;
        }
        finalDamage -= finalAC + shieldStart;
        if (finalDamage < 0) {
            finalDamage = 0;

        }
        health -= finalDamage;
        if (health <= 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 
     * @param damageMin
     * @param damageMax
     * @param pierce
     * @return
     */
    public boolean takeDamage(int damageMin, int damageMax, int pierce) {
        int finalDamage = rand.nextInt(damageMin, damageMax) + 1;
        int shieldStart = shield;
        int finalAC = AC - pierce;
        shield -= finalDamage;
        if (shield < 0) {
            shield = 0;
        }
        finalDamage -= finalAC + shieldStart;
        if (finalDamage < 0) {
            finalDamage = 0;

        }
        health -= finalDamage;
        if (health <= 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This will heal the Actor, increasing their health
     * 
     * @param heal
     */
    public void heal(int heal) {
        health += heal;

    }

    /**
     * This will do no calculations for the damage to the Actor
     * 
     * @param damage
     */
    public void damageNoAC(int damage) {
        health -= damage;

    }

    /**
     * This is what you should use when doing damage. It will do damage to another
     * thing in the room
     * 
     * @param weapon
     * @param target
     * @return boolean
     */
    public boolean closeCombat(Weapon weapon, Actor target) {
        if (rand.nextInt(100) + 1 <= weaponSkill) {
            System.out.printf("hit %s%n", target.name);
            return target.takeDamage(weapon.damage, weapon.pierce);
        } else {
            System.out.printf("Miss %s%n", target.name);
            return false;
        }

    }

    /**
     * This will cast a spell on the Actor (Not currently implementaed)
     * 
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
