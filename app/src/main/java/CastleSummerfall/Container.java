package CastleSummerfall;

import java.util.List;

/**
 * @author @Corbanator This is a special form of interactable that can have
 *         other interactables inside of it, such as a chest
 */
public class Container extends Interactable {

    protected List<Interactable> inventory;
    protected int inventoryCapacity;

    public Container(String name, String description, int size, int weight, boolean canBePickedUp,
            List<Interactable> inventory, int inventoryCapacity) {
        super(name, description, size, weight, canBePickedUp);
        this.inventory = inventory;
        this.inventoryCapacity = inventoryCapacity;

    }

    /**
     * This method specially obtains what is inside the container and adds that to
     * the description of the object.
     *
     * @Override
     * @return String
     */
    // methods
    public String getDescription() {
        StringBuilder builder = new StringBuilder();
        builder.append(description);
        if (inventory != null) {
            builder.append("\nIn the " + name + " is");
            for (int i = 0; i < inventory.size(); i++) {
                if (inventory.size() <= 0) {
                    return "";
                }
                if (inventory.size() == 1) {
                    builder.append(" a " + inventory.get(i).getName() + ".");
                } else if (i != inventory.size() - 1) {
                    builder.append(" a " + inventory.get(i).getName() + ",");
                } else {
                    builder.append(" and a " + inventory.get(i).getName() + ".");
                }
            }
        }
        return builder.toString();
    }

    /**
     * This method and those like it match exactly the functionality of those in the
     * Room class. This one gets an item based on its name
     *
     * @param name
     * @param index which instance of items with the same name you want. This is
     *              optional due to overloading.
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
     * This method and those like it match exactly the functionality of those in the
     * Room class. This one gets an item based on its name
     *
     * @param name
     * @return Interactable
     */
    public Interactable getItem(String name) {
        return getItem(name, 0);
    }

    /**
     * This method and those like it match exactly the functionality of those in the
     * Room class. This one removes and returns an item based on its name.
     *
     * @param name
     * @param index which instance of items with the same name you want. This is
     *              optional due to overloading.
     * @return Interactable
     * @throws ThingNotFoundException
     */
    public Interactable takeItem(String name, int index) throws ThingNotFoundException {
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
            throw new ThingNotFoundException("A " + name + " was not found in the container");
        }
    }

    /**
     * This method and those like it match exactly the functionality of those in the
     * Room class. This one removes and returns an item based on its name.
     *
     * @param name
     * @return Interactable
     * @throws ThingNotFoundException
     */
    public Interactable takeItem(String name) throws ThingNotFoundException {
        return takeItem(name, 0);
    }

    /**
     * Adds an item to the room, for when the player drops it.
     *
     * @param item
     */
    public void addItem(Interactable item) {
        inventory.add(item);
    }
}
