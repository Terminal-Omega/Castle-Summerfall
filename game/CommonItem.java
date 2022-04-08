package game;
import java.util.Random;
public class CommonItem {
    
    
    /** 
     * @return Interactable
     */
    public static Interactable Random(){
        Random rand = new Random();
        Interactable result = new Interactable();
        switch (rand.nextInt(6)) {
        case 0:
            result = knife();
            break;
        case 1:
            result = shortSword();
            break;
        case 2:
            result = sword();
            break;
        case 3:
            result = greatSword();
            break;
        case 4:
            result = rapier();
            break;
        case 5:
            result = key();
            break;
        default:
            break;
        }
        return result;
    }

    
    /** 
     * @return Weapon
     */
    public static Weapon knife() {
        int size = 1;
        int weight = 5;
        boolean canBePickedUp = true;
        String name = "Knife";
        String description = "It's kinda sharp.";
        int pierce = 3;
        int damage = 4;
        Weapon result = new Weapon(size, weight, canBePickedUp, name, description, pierce, damage);
        return result;
    }

    
    /** 
     * @return Weapon
     */
    public static Weapon shortSword() {
        int size = 2;
        int weight = 8;
        boolean canBePickedUp = true;
        String name = "Shortsword";
        String description = "Like a Knife, but long.";
        int pierce = 5;
        int damage = 6;
        Weapon result = new Weapon(size, weight, canBePickedUp, name, description, pierce, damage);
        return result;
    }

    
    /** 
     * @return Weapon
     */
    public static Weapon sword() {
        int size = 2;
        int weight = 9;
        boolean canBePickedUp = true;
        String name = "Sword";
        String description = "It might hurt someone";
        int pierce = 6;
        int damage = 8;
        Weapon result = new Weapon(2, 9, true, "Sword", "It might hurt someone", 6, 8);
        return result;
    }

    
    /** 
     * @return Weapon
     */
    public static Weapon greatSword() {
        int size = 3;
        int weight = 11;
        boolean canBePickedUp = true;
        String name = "Greatsword";
        String description = "It's a pretty big sword";
        int pierce = 10;
        int damage = 13;
        Weapon result = new Weapon(size, weight, canBePickedUp, name, description, pierce, damage);
        return result;
    }

    
    /** 
     * @return Weapon
     */
    public static Weapon rapier() {
        int size = 1;
        int weight = 6;
        boolean canBePickedUp = true;
        String name = "Rapier";
        String description = "Thin and deadly.";
        int pierce = 13;
        int damage = 8;
        Weapon result = new Weapon(size, weight, canBePickedUp, name, description, pierce, damage);
        return result;
    }


    private static String[] keyDescriptions = {
        "A giant skeleton key rusted over with age. You wonder if it will even fit in a lock",
        "A long, slender key, almost bladelike.",
        "It's literally a bobby pin. You suppose it will do the job, however."
    };

    
    /** 
     * @return Interactable
     */
    public static Interactable key(){
        Random rand = new Random();
        String name = "Key";
        String description = keyDescriptions[rand.nextInt(keyDescriptions.length)];
        int size = 1;
        int weight = 1;
        boolean canBePickedUp = true;
        Interactable result = new Interactable(name, description, size, weight, canBePickedUp);
        return result;
    }
}
