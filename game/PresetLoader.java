package game;

import java.util.ArrayList;
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
        Pattern presetPattern = Pattern.compile("{(.*)}");
        Matcher presetMatcher = presetPattern.matcher(toLoad);
        ArrayList<RoomPreset> result = new ArrayList<>();
        while (presetMatcher.find()) {
            result.add(LoadRoomPreset(presetMatcher.group(1)));
        }

        return result;
    }

    private static RoomPreset LoadRoomPreset(String toLoad) {

        String description = Parser.parseString("description", toLoad);

        ArrayList<InteractablePreset> descriptionInteractables = new ArrayList<>();
        InteractablePreset[] descriptionInteractablesArray = loadInventoryPresets(
                Parser.parseArray("description-interactables", toLoad));
        for (InteractablePreset preset : descriptionInteractablesArray) {
            descriptionInteractables.add(preset);
        }

        ArrayList<InteractablePreset> interactables = new ArrayList<>();
        InteractablePreset[] interactablesArray = loadInventoryPresets(Parser.parseArray("interactables", toLoad));
        for (InteractablePreset preset : interactablesArray) {
            interactables.add(preset);
        }

        RoomPreset result = new RoomPreset(description, interactables, descriptionInteractables);
        return result;

    }

    private static InteractablePreset[] loadInventoryPresets(String[] toLoad) {
        InteractablePreset[] result = new InteractablePreset[toLoad.length];
        for (int i = 0; i < toLoad.length; i++) {
            result[i] = loadInteractablePreset(toLoad[i]);
        }
        return result;
    }

    private static InteractablePreset loadInteractablePreset(String toLoad) {

        // find the name of the preset
        String name = Parser.parseString("name", toLoad);

        // find all of the description options

        String[] descriptions = Parser.trimQuotes(Parser.parseArray("descriptions", toLoad));

        int size = Parser.parseInt("size", toLoad)[0];

        int weight = Parser.parseInt("weight",toLoad)[0];

        boolean canBePickedUp = Parser.parseBoolean("canbepickedup", toLoad);

        ArrayList<InteractablePreset.AbilityOption> abilityOptions = new ArrayList<>();

        InteractablePreset result = new InteractablePreset(name, descriptions, abilityOptions, size, weight,
                canBePickedUp);
        
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

        Pattern inventorySizePattern = Pattern.compile("\\\"inventorysize\\\" *: *([0-9]*)");
        Matcher inventorySizeMatcher = inventorySizePattern.matcher(toLoad);
        inventorySizeMatcher.find();
        int inventorySize = Integer.parseInt(inventorySizeMatcher.group(1));

        result.setInventorySize(inventorySize);
        return result;
    }

    private static WeaponPreset loadWeaponPreset(InteractablePreset preset, String toLoad) {

        WeaponPreset result = new WeaponPreset(preset);

        Pattern attackSpeedPattern = Pattern.compile("\\\"attackspeed\\\" *: *\\\"([0-9]*)(-([0-9]*))?\\\"");
        Matcher attackSpeedMatcher = attackSpeedPattern.matcher(toLoad);
        attackSpeedMatcher.find();
        int attackSpeed = Integer.parseInt(attackSpeedMatcher.group(1));
        int attackSpeedRange = Integer.parseInt(attackSpeedMatcher.group(3)) - attackSpeed;

        Pattern damagePattern = Pattern.compile("\\\"damage\\\" *: *\\\"([0-9]*)(-([0-9]*))?\\\"");
        Matcher damageMatcher = damagePattern.matcher(toLoad);
        damageMatcher.find();
        int damage = Integer.parseInt(damageMatcher.group(1));
        int damageRange = Integer.parseInt(damageMatcher.group(3)) - damage;

        Pattern rangePattern = Pattern.compile("\\\"range\\\" *: *\\\"([0-9]*)(-([0-9]*))?\\\"");
        Matcher rangeMatcher = rangePattern.matcher(toLoad);
        rangeMatcher.find();
        int range = Integer.parseInt(rangeMatcher.group(1));
        int rangeRange = Integer.parseInt(rangeMatcher.group(3)) - range;

        Pattern piercePattern = Pattern.compile("\\\"pierce\\\" *: *\\\"([0-9]*)(-([0-9]*))?\\\"");
        Matcher pierceMatcher = piercePattern.matcher(toLoad);
        pierceMatcher.find();
        int pierce = Integer.parseInt(pierceMatcher.group(1));
        int pierceRange = Integer.parseInt(pierceMatcher.group(3)) - pierce;

        Pattern namePattern = Pattern.compile("\\\"name\\\" *: *\\\"(.*)\\\"");
        Matcher nameMatcher = namePattern.matcher(toLoad);
        nameMatcher.find();
        String name = nameMatcher.group(1);

        Pattern typePattern = Pattern.compile("\\\"weapontype\\\" *: *\\\"(.*)\\\"");
        Matcher typeMatcher = typePattern.matcher(toLoad);
        typeMatcher.find();
        String type = typeMatcher.group(1);

        Pattern sharpPattern = Pattern.compile("\\\"sharp\\\" *: *(.*)");
        Matcher sharpMatcher = sharpPattern.matcher(toLoad);
        sharpMatcher.find();
        boolean sharp = sharpMatcher.group(1).toLowerCase().equals("true");

        result.setValues(attackSpeed, attackSpeedRange, type, damage, damageRange, range, rangeRange, name, sharp,
                pierce, pierceRange);
        return result;
    }

}
