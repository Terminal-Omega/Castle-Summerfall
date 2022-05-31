package CastleSummerfall;

import javax.lang.model.type.NullType;
import java.util.stream.Collectors;

public class Player extends Actor {

    private int maxWeight = 20;

    public Player(int x, int y, int constitution, int energy, int AC) {
        this.setYCoord(y);
        this.setXCoord(x);
        this.setConstitution(constitution);
        this.setHealth();
        this.setInventory();
        this.setDexterity(energy);
        this.setEnergy();
        this.setWeaponSkill();
        this.setName("Player");
        this.setAC(AC);
    }

    /**
     * @param interactable
     */
    public void putItem(Interactable interactable) {
        int weight = inventory.stream().map(n -> n.weight).reduce(0, Integer::sum);
        weight += interactable.weight;

        if (weight <= maxWeight) {
            if (inventory.size() < 5) {
                inventory.add(interactable);
                System.out.println("You put the " + interactable.getName() + " in your bag");
            } else {
                System.out.println("Your bag is too full to add any more");
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
            if (inventory.get(i).getName().toLowerCase().equals(interactable.toLowerCase())) {
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
            throw new ThingNotFoundException(interactable + " is not found in your inventory");
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
        return !inventory.stream().filter(n -> n.getName().toLowerCase().equals(interactable.toLowerCase()))
                .collect(Collectors.toList()).isEmpty();
    }

    /**
     * @return int
     */
    public int getMaxWeight() {
        return this.maxWeight;
    }
}
