
public class Player extends Actor {

    public Player(int x, int y, int constituion){
        this.setYCoord(y);
        this.setXCoord(x);
        this.setConstitution(constituion);
        this.setHealth();
    }

    public void putItem(Interactable interactable) {
        if (inventory.size() < 5){
            inventory.add(interactable);
        }else{
            System.out.println("Your bag feels to heavy. You can't add any more.");
        }
    }

    public Interactable dropItem(Interactable interactable){
        Interactable returnCrap = new Interactable();
        for (Interactable inter : inventory){
            if (inter.getName().equals(interactable.getName())){
                returnCrap = inter;
                inventory.remove(inter);
            }
        }
        return returnCrap;
    }
}
