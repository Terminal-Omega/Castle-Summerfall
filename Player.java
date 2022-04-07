import javax.lang.model.type.NullType;

public class Player extends Actor {

    public Player(int x, int y, int constituion, int speed){
        this.setYCoord(y);
        this.setXCoord(x);
        this.setConstitution(constituion);
        this.setHealth();
        this.setInventory();
        this.setSpeed(speed);
        this.setWweaponSkill();
    }

    public void putItem(Interactable interactable) {
        if (inventory.size() < 5){
            inventory.add(interactable);
            System.out.println("taken");
        }else{
            System.out.println("Your bag feels to heavy. You can't add any more.");
        }
    }

    public Interactable dropItem(String interactable, int index) throws ThingNotFoundException{
        int timesFound = -1;
        int latestIndex = 0;
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getName().toLowerCase().equals(name.toLowerCase())) {
                latestIndex = i;
                timesFound++;
                if (timesFound == index) {
                    Interactable result = inventory.get(i);
                    inventory.remove(i);
                    return result;
                }
            }
        }
        if (latestIndex != 0) {
            Interactable result = inventory.get(latestIndex);
            inventory.remove(latestIndex);
            return result;
        } else {
            throw new ThingNotFoundException("Item not found.");
        }
    }

    public Interactable getItem(String name, int index){
        int timesFound = -1;
        int latestIndex = 0;
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getName().toLowerCase().equals(name.toLowerCase())) {
                latestIndex = i;
                timesFound++;
                if (timesFound == index) {
                    return inventory.get(i);
                }
            }
        }
        if (latestIndex != 0) {
            return inventory.get(latestIndex);
        } else {
            return null;
        }
    }

    public boolean isInInventory(String interactable){
        boolean isFound = false;
        if (inventory.size() > 0) {
            for (Interactable inter : inventory) {
                if (inter.getName().toLowerCase().equals(interactable)) {
                    isFound = true;
                }
            }
        }
        return isFound;
    }
}
