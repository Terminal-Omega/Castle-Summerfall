import java.util.Random;
public class CommonItem {
    
    public static Interactable Random(){
        Random rand = new Random();
        Interactable result = new Interactable();
        switch (rand.nextInt(5)) {
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
        default:
            break;
        }
        return result;
    }

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
}
