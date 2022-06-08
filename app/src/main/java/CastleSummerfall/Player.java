package CastleSummerfall;

import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.ArrayList;

public class Player extends Actor {

    public Player(int x, int y, int AC) {
        // create starting stats of all zero
        // TODO: set correct default stat values
        HashMap<Stat, Integer> stats = new HashMap<>();
        stats.put(Stat.CONSTITUTION, 6);
        stats.put(Stat.DEXTERITY, 19);
        stats.put(Stat.STRENGTH, 9);
        stats.put(Stat.CHARISMA, 9);
        stats.put(Stat.INTELLIGENCE, 9);
        stats.put(Stat.WISDOM, 9);
        HashMap<Stat, Range> statGrowth = new HashMap<>();
        for (Stat stat : Stat.values()) {
            statGrowth.put(stat, new Range(1, 3));
        }

        // NOTE: I'm duplicating all of this from the Actor constructor because I'm too
        // lazy to find a work around
        setXCoord(xCoord);
        setYCoord(yCoord);
        setAC(AC);
        setNoise(noise);
        setShield(shield);
        // TODO: calculation to convert level to exp
        this.level = 1;

        this.stats = stats;
        this.statGrowth = statGrowth;
        setStrength(stats.get(Stat.STRENGTH) + statGrowth.get(Stat.STRENGTH).random());
        setDexterity(stats.get(Stat.DEXTERITY) + statGrowth.get(Stat.DEXTERITY).random());
        setConstitution(stats.get(Stat.CONSTITUTION) + statGrowth.get(Stat.CONSTITUTION).random());
        setIntelligence(stats.get(Stat.INTELLIGENCE) + statGrowth.get(Stat.INTELLIGENCE).random());
        setWisdom(stats.get(Stat.WISDOM) + statGrowth.get(Stat.WISDOM).random());
        setCharisma(stats.get(Stat.CHARISMA) + statGrowth.get(Stat.CHARISMA).random());

        inventory = new ArrayList<Interactable>();
        this.setInventory();
        this.setWeaponSkill();
        this.setName("Player");
    }

    /**
     * @param interactable
     */
    public void putItem(Interactable interactable) {
        int weight = inventory.stream().map(n -> n.weight).reduce(0, Integer::sum);

        if (weight + interactable.weight <= carryWeight) {
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

    public void addExp(long exp) {
        System.out.println("You gained " + exp + " exp");
        this.exp += exp;
        // TODO: calculate growth formula
    }
}
