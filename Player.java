
public class Player extends Actor {

    public Player(int x, int y, int constituion){
        this.setYCoord(y);
        this.setXCoord(x);
        this.setConstitution(constituion);
        this.setHealth();
    }

    public void putItem(Interactable interactable) {
        if (inventory.size() < 5){
            inventory.add(interactable.getName());
        }else{
            System.out.println("Your bag feels to heavy. You can't add any more.");
        }
    }

    public void dropItem(Interactable interactable){
        for (String inter : inventory){
            if (interactable.getName().equals(inter)){
                inventory.remove(inter);
            }
        }
    }
}
