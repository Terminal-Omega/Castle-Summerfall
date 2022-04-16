package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PresetLoader {

    /**
     * TODO: @yomas000 transfer presets to json.
     * [] Room Presets
     * [] Interactable Presets
     * [] Enemy Presets (Enemy Loader needs to be done first)
     */

    public static ArrayList<RoomPreset> loadRoomPresets(String toLoad) {
        String[] presets = Parser.parseArray("rooms", toLoad);
        ArrayList<RoomPreset> result = new ArrayList<>();
        for(String string : presets){
            result.add(loadRoomPreset(string));
        }

        return result;
    }

    private static RoomPreset loadRoomPreset(String toLoad) {

        String[] descriptions = Parser.trimQuotes(Parser.parseArray("descriptions", toLoad));

        ArrayList<InteractablePreset> descriptionInteractables = new ArrayList<>();
        InteractablePreset[] descriptionInteractablesArray = loadInventoryPresets(Parser.parseArray("description-interactables", toLoad));
        for (InteractablePreset preset : descriptionInteractablesArray) {
            descriptionInteractables.add(preset);
        }

        ArrayList<InteractablePreset> interactables = new ArrayList<>();
        InteractablePreset[] interactablesArray;
        
        if(!Objects.isNull(loadInventoryPresets(Parser.parseArray("interactables", toLoad)))){
            interactablesArray = loadInventoryPresets(Parser.parseArray("interactables", toLoad));
        } else{
            interactablesArray = null;
        }
        if(!Objects.isNull(interactablesArray)){
            for (InteractablePreset preset : interactablesArray) {
                interactables.add(preset);

            }
        }

        RoomPreset result = new RoomPreset(descriptions, interactables, descriptionInteractables);
        return result;

    }

    private static InteractablePreset[] loadInventoryPresets(String[] toLoad) {
        if(!Objects.isNull(toLoad) && !(toLoad[0].equals("") && toLoad.length == 1)){
            InteractablePreset[] result = new InteractablePreset[toLoad.length];
            for (int i = 0; i < toLoad.length; i++) {
                result[i] = loadInteractablePreset(toLoad[i]);
            }
            return result;
        } else {
            return null;
        }
    }

    public static InteractablePreset loadInteractablePreset(String toLoad) {

        // find the name of the preset
        String name = Parser.parseString("name", toLoad);

        // find all of the description options

        String[] descriptions = Parser.trimQuotes(Parser.parseArray("descriptions", toLoad));

        int size = Parser.parseInt("size", toLoad)[0];

        int weight = Parser.parseInt("weight",toLoad)[0];

        boolean canBePickedUp = Parser.parseBoolean("canbepickedup", toLoad);

        ArrayList<InteractablePreset.AbilityOption> abilityOptions = new ArrayList<>();
        
        int rarity = Parser.parseInt("rarity", toLoad)[0];


        InteractablePreset result = new InteractablePreset(name, descriptions, abilityOptions, size, weight,
                canBePickedUp, rarity);
        
        String type = Parser.parseString("type", toLoad);

        if (type.toLowerCase().equals("container")) {
            return loadContainerPreset(result, toLoad);
        } else if (type.toLowerCase().equals("weapon")) {
            return loadWeaponPreset(result, toLoad);
        } else {
            return result;
        }
    }

    private static ContainerPreset loadContainerPreset(InteractablePreset preset, String toLoad) {
        ContainerPreset result = new ContainerPreset(preset);
        int inventorySize = Parser.parseInt("inventory-size", toLoad)[0];
        int[] startingItems = Parser.parseInt("starting-items",toLoad);

        result.minItems = startingItems[0];
        result.maxItems = startingItems[1];
        result.setInventorySize(inventorySize);
        return result;
    }

    private static WeaponPreset loadWeaponPreset(InteractablePreset preset, String toLoad) {

        WeaponPreset result = new WeaponPreset(preset);

        int[] nums = new int[2];

        nums = Parser.parseInt("attack-speed", toLoad);
        int attackSpeed = nums[0];
        int attackSpeedRange = nums[1];

        nums = Parser.parseInt("damage",toLoad);
        int damage = nums[0];
        int damageRange = nums[1];

        nums = Parser.parseInt("range",toLoad);
        int range = nums[0];
        int rangeRange = nums[1];

        nums = Parser.parseInt("Pierce",toLoad);
        int pierce = nums[0];
        int pierceRange = nums[1];

        String name = Parser.parseString("name", toLoad);

        String type = Parser.parseString("weapon-type",toLoad);

        boolean sharp = Parser.parseBoolean("sharp", toLoad);

        result.setValues(attackSpeed, attackSpeedRange, type, damage, damageRange, range, rangeRange, name, sharp,
                pierce, pierceRange);
        return result;
    }

    public static NPCPreset loadNpcPreset(String toLoad){
        String allianceString = Parser.parseString("alliance", toLoad);
        NpcAllience alliance;
        if(allianceString.equals("enemy")){
            alliance = NpcAllience.ENEMY;
        } else if(allianceString.equals("friendly")){
            alliance = NpcAllience.FRIENDLY;
        } else{
            alliance = NpcAllience.NEUTRAL;
        }

        String descriptions[] = Parser.trimQuotes(Parser.parseArray("descriptions", toLoad));

        int[] ACRange = Parser.parseInt("AC", toLoad);
        int[] strRange = Parser.parseInt("str", toLoad);
        int[] dexRange = Parser.parseInt("dex", toLoad);
        int[] conRange = Parser.parseInt("con", toLoad);
        int[] intRange = Parser.parseInt("int", toLoad);
        int[] wisRange = Parser.parseInt("wis", toLoad);
        int[] chaRange = Parser.parseInt("cha", toLoad);
        int[] noiseRange = Parser.parseInt("noise", toLoad);
        int[] shieldRange = Parser.parseInt("shield", toLoad);

        String[] name = Parser.trimQuotes(Parser.parseArray("names", toLoad));

        InteractablePreset[] inventoryArray = loadInventoryPresets(Parser.parseArray("inventory", toLoad));
        ArrayList<InteractablePreset> inventory = new ArrayList<>();
        inventory.addAll(Arrays.asList(inventoryArray));

        return new NPCPreset(alliance, descriptions, ACRange, strRange, dexRange, conRange, intRange, wisRange, chaRange, noiseRange, shieldRange, name, inventory);
        
    }

}
