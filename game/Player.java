package game;
import javax.lang.model.type.NullType;

public class Player extends Actor {

    private int maxWeight = 20;

    public Player(int x, int y, int constituion, int speed) {
        this.setYCoord(y);
        this.setXCoord(x);
        this.setConstitution(constituion);
        this.setHealth();
        this.setInventory();
        this.setSpeed(speed);
        this.setDexterity(30);
        this.setWeaponSkill();
        this.setAC(2);
        this.setBallisticSkill();
        this.setCarryWeight();
        this.setIntelligence(5);
        this.setMana();
        this.setName("name");
        this.setShield(3);
        this.setStrength(5);
        this.setWisdom(5);
    }

    /**
     * @param interactable
     */
    public void putItem(Interactable interactable) {
        int weight = 0;
        for (Interactable inter : inventory){
            weight += inter.weight;
        }
        weight += interactable.weight;

        if (weight < maxWeight){
            if (inventory.size() < 5){
                inventory.add(interactable);
                System.out.println("You put the " + interactable.getName() + " in your bag");
            }
        } else {
            System.out.println("Your bag feels to heavy. You can't add any more.");
        }
    }

    /**
     * @param interactable
     * @param index
     * @return Interactable
     * @throws ThingNotFoundException
     */
    public Interactable dropItem(String interactable, int index) throws ThingNotFoundException {
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

    /**
     * @param name
     * @param index
     * @return Interactable
     */
    public Interactable getItem(String name, int index) {
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

    /**
     * @param interactable
     * @return boolean
     */
    public boolean isInInventory(String interactable) {
        boolean isFound = false;
        if (inventory.size() > 0) {
            for (Interactable inter : inventory) {
                if (inter.getName().toLowerCase().equals(interactable.toLowerCase())) {
                    isFound = true;
                }
            }
        }
        return isFound;
    }

    
    /** 
     * @return int
     */
    public int getMaxWeight(){
        return this.maxWeight;
    }
}
