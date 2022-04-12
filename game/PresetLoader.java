package game;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PresetLoader {
    private RoomPreset LoadPreset(String toLoad){
        Pattern RegExDescription = Pattern.compile("description: *\\\"(.*)\\\",");
        Pattern RegExDescriptionInteractable = Pattern.compile("description-interactables:\\[(.*)\\],");
        Pattern RegExInteractable = Pattern.compile("interactables: *\\[(.*)\\],");
        Pattern RegExBoss = Pattern.compile("boss:.*,");

        Matcher descriptionMatcher = RegExDescription.matcher(toLoad);
        Matcher descriptionInteractableMatcher = RegExDescriptionInteractable.matcher(toLoad);
        Matcher interactableMatcher = RegExInteractable.matcher(toLoad);
        Matcher bossMatcher = RegExBoss.matcher(toLoad);

        descriptionMatcher.find();
        String description = descriptionMatcher.group(1);

        descriptionInteractableMatcher.find();
        String descriptionInteractableString = descriptionInteractableMatcher.group(0);
        ArrayList<Interactable> descriptionInteractables = loadInventory(descriptionInteractableString);

        ArrayList<Interactable> interactables;
        if(interactableMatcher.find()){
        String interactableString = interactableMatcher.group(1);
        interactables = loadInventory(interactableString);
        } else {
            interactables = new ArrayList<>();
        }

        //bossMatcher.find();
        //String bossString = bossMatcher.group(1);
        // NPC boss = loadNPC(bossString);

        RoomPreset result = new RoomPreset(description, interactables, descriptionInteractables);
        return result;

    }

    private ArrayList<Interactable> loadInventory(String toLoad){
        Pattern InteractablePattern = Pattern.compile("{(.*)}");
        Matcher matcher = InteractablePattern.matcher(toLoad);
        ArrayList<Interactable> result = new ArrayList<>();

        while(matcher.find()){
            result.add(loadInteractable(matcher.group(1)));
        }
        return result;
    }

    private Interactable loadInteractable(String toLoad){
        Pattern typePattern = Pattern.compile("type: *(.*),");
        Matcher typeMatcher = typePattern.matcher(toLoad);
        typeMatcher.find();
        boolean container = typeMatcher.group(1).toLowerCase().equals("container");

        Pattern namePattern = Pattern.compile("name: *\\\"(.*)\\\"");
        Matcher nameMatcher = namePattern.matcher(toLoad);
        nameMatcher.find();
        String name = nameMatcher.group(1);

        Pattern descriptionPattern = Pattern.compile("description: *\\\"(.*)\\\"");
        Matcher descriptionMatcher = descriptionPattern.matcher(toLoad);
        descriptionMatcher.find();
        String description = descriptionMatcher.group(1);

        Pattern sizePattern = Pattern.compile("size: *([0-9]*),");
        Matcher sizeMatcher = sizePattern.matcher(toLoad);
        int size;
        if(sizeMatcher.find()){
            size = Integer.parseInt(sizeMatcher.group(1));
        } else{
            size = 0;
        }

        Pattern weightPattern = Pattern.compile("weight: *([0-9]*),");
        Matcher weightMatcher = weightPattern.matcher(toLoad);
        int weight;
        if(weightMatcher.find()){
            weight = Integer.parseInt(weightMatcher.group(1));
        } else{
            weight = 0;
        }

        Pattern canBePickedUpPattern = Pattern.compile("canbebickedup: *\\\"(.*)\\\"");
        Matcher canBePickedUpMatcher = canBePickedUpPattern.matcher(toLoad);
        canBePickedUpMatcher.find();
        String canBePickedUpString = canBePickedUpMatcher.group(1);
        boolean canBePickedUp = canBePickedUpString.toLowerCase().equals("true".toLowerCase());

        Pattern inventoryCapacityPattern = Pattern.compile("inventoryCapacity: *([0-9]*),");
        Matcher inventoryCapacityMatcher = inventoryCapacityPattern.matcher(toLoad);
        int inventoryCapacity;
        if(inventoryCapacityMatcher.find()){
            inventoryCapacity = Integer.parseInt(inventoryCapacityMatcher.group(1));
        } else{
            inventoryCapacity = 0;
        }

        if(container){
            Pattern inventoryPattern = Pattern.compile("inventory: *[().*)]");
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


}
