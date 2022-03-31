//this is a grouping of methods to randomly generate any given game object.
//A room, an interactable, a floor, an enemy, etc.
public class Generator {
    //TODO: how do I generate a random interactable?
    public static Interactable generateInteractable(){
        Interactable result = new Interactable();


        return result;
    }

    //interactables
    private Interactable chest(){
        Interactable result = new Interactable();
        return result;
    }


    //Weapons
    private Weapon knife(){
        Weapon result = new Weapon(1, 5, true,"Knife","It's kinda sharp.");
        return result;
    }

    private Weapon shortSword(){
        Weapon result = new Weapon(2, 8, true,"Short Sword","Like a knife, but long.");
        return result;
    }

    private Weapon sword(){
        Weapon result = new Weapon(2, 9, true,"Sword","It might hurt someone");
        return result;
    }

    private Weapon greatSword(){
        Weapon result = new Weapon(3,11,true,"Greatsword","This is a big sword.");
        return result;
    }

    private Weapon rapier(){
        Weapon result = new Weapon(1, 6, true,"Rapier","Thin and deadly.");
        return result;
    }

}
