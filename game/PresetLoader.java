package game;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PresetLoader {
    public static ArrayList<RoomPreset> loadRoomPresets(String toLoad){
        Pattern presetPattern = Pattern.compile("{(.*)}");
        Matcher presetMatcher = presetPattern.matcher(toLoad);
        ArrayList<RoomPreset> result = new ArrayList<>();
        while(presetMatcher.find()){
            result.add(LoadRoomPreset(presetMatcher.group(1)));
        }

        return result;
    }

    private static RoomPreset LoadRoomPreset(String toLoad){
        Pattern RegExDescription = Pattern.compile("\\\"description\\\" *: *\\\"(.*)\\\",");
        Pattern RegExDescriptionInteractable = Pattern.compile("\\\"description[ \\-_]?interactables\\\" *: *\\[(.*)\\],");
        Pattern RegExInteractable = Pattern.compile("\\\"interactables\\\" *: *\\[(.*)\\],");
        Pattern RegExBoss = Pattern.compile("\\\"boss\\\" *:.*,");

        Matcher descriptionMatcher = RegExDescription.matcher(toLoad);
        Matcher descriptionInteractableMatcher = RegExDescriptionInteractable.matcher(toLoad);
        Matcher interactableMatcher = RegExInteractable.matcher(toLoad);
        Matcher bossMatcher = RegExBoss.matcher(toLoad);

        descriptionMatcher.find();
        String description = descriptionMatcher.group(1);

        descriptionInteractableMatcher.find();
        String descriptionInteractableString = descriptionInteractableMatcher.group(0);
        ArrayList<InteractablePreset> descriptionInteractables = loadInventoryPresets(descriptionInteractableString);

        ArrayList<InteractablePreset> interactables;
        if(interactableMatcher.find()){
        String interactableString = interactableMatcher.group(1);
        interactables = loadInventoryPresets(interactableString);
        } else {
            interactables = new ArrayList<>();
        }

        //bossMatcher.find();
        //String bossString = bossMatcher.group(1);
        // NPC boss = loadNPC(bossString);

        RoomPreset result = new RoomPreset(description, interactables, descriptionInteractables);
        return result;

    }

    private static ArrayList<Interactable> loadInventory(String toLoad){
        Pattern InteractablePattern = Pattern.compile("{(.*)}");
        Matcher matcher = InteractablePattern.matcher(toLoad);
        ArrayList<Interactable> result = new ArrayList<>();

        while(matcher.find()){
            result.add(loadInteractable(matcher.group(1)));
        }
        return result;
    }

    private static Interactable loadInteractable(String toLoad){
        Pattern typePattern = Pattern.compile("\\\"type\\\" *: *(.*),");
        Matcher typeMatcher = typePattern.matcher(toLoad);
        typeMatcher.find();
        boolean container = typeMatcher.group(1).toLowerCase().equals("container");

        Pattern namePattern = Pattern.compile("\\\"name\\\" *: *\\\"(.*)\\\"");
        Matcher nameMatcher = namePattern.matcher(toLoad);
        nameMatcher.find();
        String name = nameMatcher.group(1);

        Pattern descriptionPattern = Pattern.compile("\\\"description\\\" *: *\\\"(.*)\\\"");
        Matcher descriptionMatcher = descriptionPattern.matcher(toLoad);
        descriptionMatcher.find();
        String description = descriptionMatcher.group(1);

        Pattern sizePattern = Pattern.compile("\\\"size\\\" *: *([0-9]*),");
        Matcher sizeMatcher = sizePattern.matcher(toLoad);
        int size;
        if(sizeMatcher.find()){
            size = Integer.parseInt(sizeMatcher.group(1));
        } else{
            size = 0;
        }

        Pattern weightPattern = Pattern.compile("\\\"weight\\\" *: *([0-9]*),");
        Matcher weightMatcher = weightPattern.matcher(toLoad);
        int weight;
        if(weightMatcher.find()){
            weight = Integer.parseInt(weightMatcher.group(1));
        } else{
            weight = 0;
        }

        Pattern canBePickedUpPattern = Pattern.compile("\\\"canbebickedup\\\" *: *\\\"(.*)\\\"");
        Matcher canBePickedUpMatcher = canBePickedUpPattern.matcher(toLoad);
        canBePickedUpMatcher.find();
        String canBePickedUpString = canBePickedUpMatcher.group(1);
        boolean canBePickedUp = canBePickedUpString.toLowerCase().equals("true".toLowerCase());

        Pattern inventoryCapacityPattern = Pattern.compile("\\\"inventorycapacity\\\" *: *([0-9]*),");
        Matcher inventoryCapacityMatcher = inventoryCapacityPattern.matcher(toLoad);
        int inventoryCapacity;
        if(inventoryCapacityMatcher.find()){
            inventoryCapacity = Integer.parseInt(inventoryCapacityMatcher.group(1));
        } else{
            inventoryCapacity = 0;
        }

        if(container){
            Pattern inventoryPattern = Pattern.compile("\\\"inventory\\\" *: *[().*)]");
            Matcher inventoryMatcher = inventoryPattern.matcher(toLoad);
            inventoryMatcher.find();
            ArrayList<Interactable> inventory = loadInventory(inventoryMatcher.group(1));
            Container result = new Container(name, description, size, weight, canBePickedUp, inventory, inventoryCapacity);
            return result;
        } else{
            Interactable result = new Interactable(name, description, size, weight, canBePickedUp);
            return result;
        }



    }

    private static ArrayList<InteractablePreset> loadInventoryPresets(String toLoad){
        Pattern InteractablePattern = Pattern.compile("{(.*)}");
        Matcher matcher = InteractablePattern.matcher(toLoad);
        ArrayList<InteractablePreset> result = new ArrayList<>();

        while(matcher.find()){
            result.add(loadInteractablePreset(matcher.group(1)));
        }
        return result;
    }

    private static InteractablePreset loadInteractablePreset(String toLoad){
        Pattern typePattern = Pattern.compile("\\\"type\\\" *: * \\\"(.*)\\\"");
        Matcher typeMatcher = typePattern.matcher(toLoad);
        typeMatcher.find();

            //find the name of the preset
            Pattern namePattern = Pattern.compile("\\\"name\\\" *: *\\\"(.*)\\\"");
            Matcher nameMatcher = namePattern.matcher(toLoad);
            nameMatcher.find();
            String name = nameMatcher.group(1);

            //find all of the description options
            Pattern descriptionPattern = Pattern.compile("\\\"descriptions\\\" *: *[(.*)]");
            Matcher descriptionMatcher = descriptionPattern.matcher(toLoad);
            descriptionMatcher.find();
            String descriptionString = descriptionMatcher.group(1);

            Pattern descriptionPattern2 = Pattern.compile("\\\"(.*)\\\"");
            Matcher descriptionMatcher2 = descriptionPattern2.matcher(descriptionString);
            ArrayList<String> descriptionsArrayList = new ArrayList<>();
            while(descriptionMatcher2.find()){
                descriptionsArrayList.add(descriptionMatcher2.group(1));
            }
            int arraySize = descriptionsArrayList.size();
            String[] descriptions = new String[arraySize];
            for(int i = 0; i<descriptions.length;i++){
                descriptions[i] = descriptionsArrayList.get(i);
            }

            Pattern sizePattern = Pattern.compile("\\\"size\\\" *: *([0-9]*),");
            Matcher sizeMatcher = sizePattern.matcher(toLoad);
            int size;
            if(sizeMatcher.find()){
                size = Integer.parseInt(sizeMatcher.group(1));
            } else{
                size = 0;
            }

            Pattern weightPattern = Pattern.compile("\\\"weight\\\" *: *([0-9]*),");
            Matcher weightMatcher = weightPattern.matcher(toLoad);
            int weight;
            if(weightMatcher.find()){
                weight = Integer.parseInt(weightMatcher.group(1));
            } else{
                weight = 0;
            }

            Pattern canBePickedUpPattern = Pattern.compile("\\\"canbebickedup\\\" *: *(.*)");
            Matcher canBePickedUpMatcher = canBePickedUpPattern.matcher(toLoad);
            canBePickedUpMatcher.find();
            String canBePickedUpString = canBePickedUpMatcher.group(1);
            boolean canBePickedUp = canBePickedUpString.toLowerCase().equals("true".toLowerCase());

            ArrayList<InteractablePreset.AbilityOption> abilityOptions = new ArrayList<>();

            InteractablePreset result = new InteractablePreset(name, descriptions, abilityOptions, size, weight, canBePickedUp);

        if(typeMatcher.group(1).toLowerCase().equals("container")){
            return loadContainerPreset(result, toLoad);
        } else if(typeMatcher.group(1).toLowerCase().equals("weapon")){
            return loadWeaponPreset(result, toLoad);
        } else{
            return result;
        }
    }

    private static ContainerPreset loadContainerPreset(InteractablePreset preset, String toLoad){
        ContainerPreset result = new ContainerPreset(preset);

        Pattern inventorySizePattern = Pattern.compile("\\\"inventorysize\\\" *: *([0-9]*)");
        Matcher inventorySizeMatcher = inventorySizePattern.matcher(toLoad);
        inventorySizeMatcher.find();
        int inventorySize = Integer.parseInt(inventorySizeMatcher.group(1));
        
        result.setInventorySize(inventorySize);
        return result;
    }

    private static WeaponPreset loadWeaponPreset(InteractablePreset preset, String toLoad){

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

        result.setValues(attackSpeed, attackSpeedRange, type, damage, damageRange, range, rangeRange, name, sharp, pierce, pierceRange);
        return result;
    }

}
