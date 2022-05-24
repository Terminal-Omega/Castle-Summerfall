package CastleSummerfall;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * @Author @Corbanator
 * 
 *         This class is designed to take strings and turn them into actual
 *         objects that are presets, which will then be used to generate actual
 *         objects in the generator class. It is called almost exclusively by
 *         the Generator class.
 */
public class PresetLoader {

    /**
     * This method takes a json file with an object that has one attribute, "rooms",
     * that is an array of rooms, and uses other methods in the class to load in an
     * array of RoomPresets.
     * 
     * @param toLoad
     * @return ArrayList<RoomPreset>
     */
    public static ArrayList<RoomPreset> loadRoomPresets(String toLoad) {
        // uses parseArray to find the array with the key "rooms" and load in each room
        // in that array
        String[] presets = Parser.parseArray("rooms", toLoad);
        ArrayList<RoomPreset> result = new ArrayList<>();

        for (String string : presets) {
            result.add(loadRoomPreset(string));
        }

        return result;
    }

    /**
     * This method takes in a string that is a roomPreset object in json format and
     * parses it into an actual RoomPreset object
     * 
     * @param toLoad
     * @return RoomPreset
     */
    private static RoomPreset loadRoomPreset(String toLoad) {

        // TrimQuotes gets rid of the quotes that will be in the string array that is
        // returned so that those are not printed
        String[] descriptions = Parser.trimQuotes(Parser.parseArray("descriptions", toLoad));

        // Uses loadInventoryPresets to load in an inventory of interactablePresets, but
        // only if that array is not empty
        ArrayList<InteractablePreset> descriptionInteractables = new ArrayList<>();
        InteractablePreset[] descriptionInteractablesArray = loadInventoryPresets(
                Parser.parseArray("description-interactables", toLoad));
        if (!Objects.isNull(descriptionInteractablesArray)) {
            for (InteractablePreset preset : descriptionInteractablesArray) {
                descriptionInteractables.add(preset);
            }
        }

        // This section does essentially the same thing for normal interactables instead
        // of special description interactables,
        // but it is slightly more complicated because this field is optional, so it
        // must check if it exists.

        ArrayList<InteractablePreset> interactables = new ArrayList<>();
        InteractablePreset[] interactablesArray;
        if (!Objects.isNull(loadInventoryPresets(Parser.parseArray("interactables", toLoad)))) {
            interactablesArray = loadInventoryPresets(Parser.parseArray("interactables", toLoad));
        } else {
            interactablesArray = null;
        }

        if (!Objects.isNull(interactablesArray)) {
            for (InteractablePreset preset : interactablesArray) {
                interactables.add(preset);

            }
        }

        // Loads in the boss to the room, if there is one.
        NPCPreset boss = loadNpcPreset(Parser.parseObject("boss", toLoad));

        RoomPreset result = new RoomPreset(descriptions, interactables, descriptionInteractables);
        result.addBoss(boss);
        return result;

    }

    /**
     * Similar to loadRoomPresets, this loads in an entire array, or "inventory", of
     * interactablePresets
     *
     * @param toLoad
     * @return InteractablePreset[]
     */
    private static InteractablePreset[] loadInventoryPresets(String[] toLoad) {
        if (!Objects.isNull(toLoad) && !(toLoad[0].equals("") && toLoad.length == 1)) {
            InteractablePreset[] result = new InteractablePreset[toLoad.length];
            for (int i = 0; i < toLoad.length; i++) {
                result[i] = loadInteractablePreset(toLoad[i]);
            }
            return result;
        } else {
            return null;
        }
    }

    /**
     * This method parses for all of the attributes of an InteractablePreset and
     * makes one based on a string that is a jsonObject.
     *
     * @param toLoad
     * @return InteractablePreset
     */
    public static InteractablePreset loadInteractablePreset(String toLoad) {

        // find the name of the preset
        String name = Parser.parseString("name", toLoad);

        // find all of the description options

        String[] descriptions = Parser.trimQuotes(Parser.parseArray("descriptions", toLoad));

        // Parse other various attributes, such as size, weight, and whether it can be
        // picked up.

        int size = Parser.parseInt("size", toLoad)[0];

        int weight = Parser.parseInt("weight", toLoad)[0];

        boolean canBePickedUp = Parser.parseBoolean("canbepickedup", toLoad);

        // loads in abilities. These don't actually do anything yet, but it leaves the
        // door open for more complex interactables going forward.

        ArrayList<InteractablePreset.AbilityOption> abilityOptions = new ArrayList<>();

        int rarity = Parser.parseInt("rarity", toLoad)[0];

        // creates an InteractablePreset that will be returned or added to, depending on
        // its type.
        InteractablePreset result = new InteractablePreset(name, descriptions, abilityOptions, size, weight,
                canBePickedUp, rarity);

        String type = Parser.parseString("type", toLoad);

        // if the interactablePreset is a container or weapon, it must parse additional
        // attributes.
        if (type.toLowerCase().equals("container")) {
            return loadContainerPreset(result, toLoad);
        } else if (type.toLowerCase().equals("weapon")) {
            return loadWeaponPreset(result, toLoad);
        } else {
            return result;
        }
    }

    /**
     * this method is called only by loadInteractablePreset to load the remaining
     * information about a ContainerPreset.
     *
     * @param preset
     * @param toLoad
     * @return ContainerPreset
     */
    private static ContainerPreset loadContainerPreset(InteractablePreset preset, String toLoad) {
        ContainerPreset result = new ContainerPreset(preset);
        int inventorySize = Parser.parseInt("inventory-size", toLoad)[0];
        int[] startingItems = Parser.parseInt("starting-items", toLoad);

        result.minItems = startingItems[0];
        result.maxItems = startingItems[1];
        result.inventorySize = inventorySize;
        return result;
    }

    /**
     * this method is called only by loadInteractablePreset to load the remaining
     * information about a ContainerPreset.
     *
     * @param preset
     * @param toLoad
     * @return WeaponPreset
     */
    private static WeaponPreset loadWeaponPreset(InteractablePreset preset, String toLoad) {

        WeaponPreset result = new WeaponPreset(preset);

        int[] nums = new int[2];

        nums = Parser.parseInt("attack-speed", toLoad);
        int attackSpeed = nums[0];
        int attackSpeedRange = nums[1];

        nums = Parser.parseInt("damage", toLoad);
        int damage = nums[0];
        int damageRange = nums[1];

        nums = Parser.parseInt("range", toLoad);
        int range = nums[0];
        int rangeRange = nums[1];

        nums = Parser.parseInt("pierce", toLoad);
        int pierce = nums[0];
        int pierceRange = nums[1];

        String name = Parser.parseString("name", toLoad);

        String type = Parser.parseString("weapon-type", toLoad);

        boolean sharp = Parser.parseBoolean("sharp", toLoad);

        result.setValues(attackSpeed, attackSpeedRange, type, damage, damageRange, range, rangeRange, name, sharp,
                pierce, pierceRange);
        return result;
    }

    /**
     * This method loads in an NPCPreset from a string that is a JSONObject,
     * including all of its attributes and whatever it has in its inventory.
     *
     * @param toLoad
     * @return NPCPreset
     */
    public static NPCPreset loadNpcPreset(String toLoad) {
        // returns null if it is passed a null string, to avoid nullPointerExceptions
        if (Objects.isNull(toLoad)) {
            return null;
        }

        String allianceString = Parser.parseString("alliance", toLoad);
        NPCAlliance alliance;
        if (allianceString.equals("enemy")) {
            alliance = NPCAlliance.ENEMY;
        } else if (allianceString.equals("friendly")) {
            alliance = NPCAlliance.FRIENDLY;
        } else {
            alliance = NPCAlliance.NEUTRAL;
        }

        // A description will be chosen out of the array at random when the preset is
        // generated.
        String descriptions[] = Parser.trimQuotes(Parser.parseArray("descriptions", toLoad));

        // Parse various ranges of attributes, where a number in the range will be
        // selected when the NPC is actually created from the preset
        int[] ACRange = Parser.parseInt("AC", toLoad);
        int[] strRange = Parser.parseInt("str", toLoad);
        int[] dexRange = Parser.parseInt("dex", toLoad);
        int[] conRange = Parser.parseInt("con", toLoad);
        int[] intRange = Parser.parseInt("int", toLoad);
        int[] wisRange = Parser.parseInt("wis", toLoad);
        int[] chaRange = Parser.parseInt("cha", toLoad);
        int[] noiseRange = Parser.parseInt("noise", toLoad);
        int[] shieldRange = Parser.parseInt("shield", toLoad);

        // A name will be chosen out of the array at random when the preset is generated
        String[] name = Parser.trimQuotes(Parser.parseArray("names", toLoad));

        // Loads in the inventory by obtaining an array of strings to then load in as
        // InteractablePresets
        InteractablePreset[] inventoryArray = loadInventoryPresets(Parser.parseArray("inventory", toLoad));
        ArrayList<InteractablePreset> inventory = new ArrayList<>();
        inventory.addAll(Arrays.asList(inventoryArray));

        return new NPCPreset(alliance, descriptions, ACRange, strRange, dexRange, conRange, intRange, wisRange,
                chaRange, noiseRange, shieldRange, name, inventory);

    }

}
