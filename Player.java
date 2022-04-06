import javax.lang.model.type.NullType;

public class Player extends Actor {

    public Player(int x, int y, int constituion){
        this.setYCoord(y);
        this.setXCoord(x);
        this.setConstitution(constituion);
        this.setHealth();
        this.setInventory();
    }

    public void putItem(Interactable interactable) {
        if (inventory.size() < 5){
            inventory.add(interactable);
        }else{
            System.out.println("Your bag feels to heavy. You can't add any more.");
        }
    }

    public Interactable dropItem(String interactable){
        Interactable returnInter = new Interactable(true);
        if (inventory.size() > 0){
            for (Interactable inter : inventory){
                if (inter.getName().toLowerCase().equals(interactable)){
                    returnInter = inter;
                }
            }
        }
        inventory.remove(returnInter);
        return returnInter;
    }
}
