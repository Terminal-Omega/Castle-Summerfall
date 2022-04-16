package game;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

import javax.annotation.processing.Filer;

//this is a grouping of methods to randomly generate any given game object.
//A room, an interactable, a floor, an enemy, etc.
public class Generator {

    public enum Rarity {
        COMMON,
        UNCOMMON,
        RARE,
        VERYRARE,
        LEGENDARY
    }

    
    /** 
     * This will generate a floor
     * @param xSize
     * @param ySize
     * @return Floor
     */
    public static Floor generateFloor(int xSize, int ySize) {
        Random rand = new Random();
        ArrayList<ArrayList<Room>> rooms = new ArrayList<>();
        for (int i = 0; i < xSize; i++) {
            ArrayList<Room> column = new ArrayList<>();
            for (int j = 0; j < ySize; j++) {
                boolean southDoor = false;
                boolean eastDoor = false;
                if (j != 0) {
                    southDoor = true;
                }
                if (i != xSize - 1) {
                    eastDoor = true;
                }
                column.add(generateRoom(southDoor, eastDoor));
            }
            rooms.add(column);
        }
        Floor result = new Floor(rooms);
        int enemyFactor = (xSize * ySize) / 4;
        int enemyCount = rand.nextInt(enemyFactor) + enemyFactor;
        for (int i = 0; i < enemyCount; i++) {
            int x = rand.nextInt(xSize);
            int y = rand.nextInt(ySize);
            result.addNPC(generateEnemy(x, y, 0));
        }
        return result;
    }

    private static String[] roomDescriptions = {
            "You look around and see nothing the room is too dark to see much. But you can see that the walls are gray brick that has moss and water dripping from the old stones.",
            "The room is a massive room with wooden beams sweaping up into high arched ceilings. It has bright chandeliers glowing with hundreds of candles.\nThe wood looks dark and varneshed. It reminds you of viking archetecture.",
            "The room you walked into is dark and dank. It smells like mildew and has slime covering the floor and walls.",
            "You look around and see that this room looks like a storage room. There is crates and barrels of goods waiting for something to happen to them.",
            "The room is what appears to be a bedchamber. There is a massive four poster bed with red velvet curtains.\nYou don't know who would want to sleep in a dungeon though.",
            "The room you walked into has dark stone tiled floors. Rows of pews extend as far as you can see into the scatily lit room. It looks like an old cathedral.",
            "The room you came to is a black stone room with a huge bonfire at one end. There were thick wooden tables on either end that held instrumetns of some dark profession.",
            "You look around the room you came to and see that it looks like a study. It has a light thin wooden desk with a chair behind it. Bookshelves line the room. You look at one of the book titles, it reads, The Study of the Nature of Artificing.",
            "The room you walked into has rows and rows of barrles. looking into one of the barrels you see that it holds a dark red liquid. It smells like wine.",
            "The room you find yourself in now has a counter along one end with lit stoves and ovens. Prepped food lies on the counter, it looks like someone had to leave their job in a rush. The food smells delecious.",
            "This room is throne room. It has large sweeping walls. With massive domed celing. And to cap it all is a huge golden throne with inlade diamonds sparkling in the bright room. You wonder who would sit on such an ostentasions throne.",
            "This room is obviously a jail, it has brushed steel doors with small holes in the bottom and top. Torches iluminate the hallway but enough to see inside the cells.",
            "You look around and find yourself in a childrens nursery. Toys litter the floor and bright murals cover the walls. There is even two small beds in the room.\nThis is getting weird you think to yourself.",
            "The room you walked into is brick. Just brick. Floor, celing, walls: all brick.\nWhat is going on in this place, you wonder.",
            "The room you walked into feels sterile. It has whitewashed walls with steel tables in the center.\nSmaller tables hold all sorts of deadly looking tools.",
            "The room is a hotel you realise. Torch sconces line the very expensive mahogonay hallway.\nThe doors have brass name plates numbering 1 through 25" };

    
    /** 
     * This will generate a random room
     * @param interactableMin
     * @param interactableMax
     * @param southDoor
     * @param eastDoor
     * @return Room
     */
    public static Room generateRoom(int interactableMin, int interactableMax, boolean southDoor, boolean eastDoor) {
        int range = interactableMax - interactableMin;
        Random rand = new Random();
        int loopCount = rand.nextInt(range) + interactableMin;
        ArrayList<Interactable> roomInventory = new ArrayList<Interactable>();
        Door door1;
        Door door2;
        for (int i = 0; i < loopCount; i++) {
            roomInventory.add(generateInteractable());
        }
        if (southDoor) {
            door1 = new Door(true, false, false);
        } else {
            door1 = null;
        }
        if (eastDoor) {
            door2 = new Door(true, false, false);
        } else {
            door2 = null;
        }
        Room result = new Room(roomInventory, null, roomDescriptions[rand.nextInt(roomDescriptions.length)], door1, door2);
        return result;
    }

    
    /** 
     * This will generate a random Interactable to go in a room
     * @param containerWeight
     * @param commonWeight
     * @param uncommonWeight
     * @param rareWeight
     * @param veryRareWeight
     * @param legendaryWeight
     * @param containerValue
     * @return Interactable
     */
    // below this is all the interactable generation.
    public static Interactable generateInteractable(double containerWeight) {
        Random rand = new Random();
        Interactable result;
        double randNum = rand.nextDouble();

        try{
            File pathFile = new File("data/config/paths.json");
            FileReader pathReader = new FileReader(pathFile);
            int i;
            String pathString = "";
            while((i = pathReader.read()) != -1){
                pathString += (char) i;
            }
            
            if (randNum < containerWeight) {
                String[] containerPaths = Parser.trimQuotes(Parser.parseArray("containers", pathString));
                ArrayList<ContainerPreset> containers = new ArrayList<>();

                for(String path : containerPaths){
                    File file = new File(path);
                    FileReader reader = new FileReader(file);
                    int j;
                    String containerString = "";
                    while((j = reader.read()) !=-1){
                        containerString += (char) j;
                    }
                    String[] containerStrings = Parser.parseArray("containers", containerString);

                    for(String string : containerStrings){
                        containers.add((ContainerPreset)PresetLoader.loadInteractablePreset(string));
                    }
                }
                long totalWeight = 0;
                for(InteractablePreset preset : containers){
                    totalWeight += preset.rarity;
                }
                Long choice = rand.nextLong(totalWeight + 1);

                for(InteractablePreset preset : containers){
                    choice -= preset.rarity;
                    if(choice<=0){
                        result = spinInteractable(preset);
                        return result;
                    }
                }
                return null;
            } else{
                String[] interactablePaths = Parser.trimQuotes(Parser.parseArray("interactables", pathString));
                ArrayList<InteractablePreset> interactables = new ArrayList<>();

                for(String path : interactablePaths){
                    File file = new File(path);
                    FileReader reader = new FileReader(file);
                    int j;
                    String interactableString = "";
                    while((j = reader.read()) !=-1){
                        interactableString += (char) j;
                    }
                    String[] interactableStrings = Parser.parseArray("interactables", interactableString);

                    for(String string : interactableStrings){
                        interactables.add(PresetLoader.loadInteractablePreset(string));
                    }
                }
                long totalWeight = 0;
                for(InteractablePreset preset : interactables){
                    totalWeight += preset.rarity;
                }
                Long choice = rand.nextLong(totalWeight + 1);

                for(InteractablePreset preset : interactables){
                    choice -= preset.rarity;
                    if(choice<=0){
                        result = spinInteractable(preset);
                        return result;
                    }
                }
                return null;
            }

        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static Room generateRoom(RoomPreset preset, int interactableMin, int interactableMax, boolean southDoor, boolean eastDoor){
        int range = interactableMax - interactableMin;
        Random rand = new Random();
        int loopCount = rand.nextInt(range) + interactableMin;
        ArrayList<Interactable> roomInventory = new ArrayList<>();
        for(InteractablePreset interactable : preset.interactables){
            if(!Objects.isNull(spinInteractable(interactable))){
                roomInventory.add(spinInteractable(interactable));
            }
        }
        ArrayList<Interactable> descriptionInteractables = new ArrayList<>();
        for(InteractablePreset interactable : preset.descriptionInteractables){
            descriptionInteractables.add(spinInteractable(interactable));
        }
        Door door1;
        Door door2;
        for (int i = 0; i < loopCount; i++) {
            roomInventory.add(generateInteractable());
        }
        if (southDoor) {
            door1 = new Door(true, false, false);
        } else {
            door1 = null;
        }
        if (eastDoor) {
            door2 = new Door(true, false, false);
        } else {
            door2 = null;
        }
        Room result = new Room(roomInventory,new ArrayList<>(), roomDescriptions[rand.nextInt(roomDescriptions.length)], door1, door2);
        return result;
    }

    public static Room generateRoom(boolean southDoor, boolean eastDoor){
        File filePaths = new File("data/config/paths.json");
        String[] files;
        String pathString = "";

        try {
            FileReader pathIn = new FileReader(filePaths);
            int i = 0;
            
            while((i = pathIn.read()) !=-1){
                pathString += (char) i;
            }
            pathIn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        files = Parser.trimQuotes(Parser.parseArray("room-presets", pathString));
        
        ArrayList<RoomPreset> presets = new ArrayList<>();

        for(String file : files){
            File presetFile = new File(file);
            String presetString = "";
            try {
                FileReader presetIn = new FileReader(presetFile);
                int i = 0;
                
                while((i = presetIn.read()) !=-1){
                    presetString += (char)i;
                }
                presetIn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            presets.addAll(PresetLoader.loadRoomPresets(presetString));

        }

        Random rand = new Random();
        int choice = rand.nextInt(presets.size());
        return generateRoom(presets.get(choice), 1, 3, southDoor, eastDoor);
    }

    public Room spinRoom(RoomPreset preset, boolean southDoor, boolean eastDoor){
        Random rand = new Random();
        ArrayList<Interactable> interactables = new ArrayList<Interactable>();
        for(InteractablePreset interactable : preset.interactables){
            interactables.add(spinInteractable(interactable));
        }
        String description = preset.descriptions[rand.nextInt(preset.descriptions.length)];
        ArrayList<Interactable> descriptionInteractables = new ArrayList<Interactable>();
        for(InteractablePreset interactable : preset.descriptionInteractables){
            descriptionInteractables.add(spinInteractable(interactable));
        }
        Door doorSouth;
        Door doorEast;
        if(southDoor){
            doorSouth = new Door(true, false, false);
        } else{
            doorSouth = null;
        }

        if(eastDoor){
            doorEast = new Door(true, false, false);
        } else{
            doorEast = null;
        }

        return new Room(interactables, descriptionInteractables, description, doorSouth, doorEast);
    }
    
    /** 
     * This generate a default Interactable
     * @return Interactable
     */
    public static Interactable generateInteractable() {
        return generateInteractable(.2);
    }

    public static Interactable spinInteractable(InteractablePreset preset){
        if(Objects.isNull(preset)){
            return null;
        }

        Random rand = new Random();
        String name = preset.name;

        if(Objects.isNull(preset.descriptions)){
            return null;
        }

        String description = preset.descriptions[rand.nextInt(preset.descriptions.length)];
        ArrayList<Ability> abilities = new ArrayList<>();

        ArrayList<InteractablePreset.AbilityOption> options = new ArrayList<>();
        for(InteractablePreset.AbilityOption abilityOption : preset.abilityOptions){
            options.add(abilityOption);
        }

        for(InteractablePreset.AbilityOption abilityOption : options){
            for(int i = 0; i< abilityOption.number;i++){
                int choice = rand.nextInt(abilityOption.options.size());
                abilities.add(abilityOption.options.get(choice));
                abilityOption.options.remove(choice);
            }
        }


        if(preset instanceof ContainerPreset){
            return spinContainer((ContainerPreset)preset);
        }
        if(preset instanceof WeaponPreset){
            return spinWeapon((WeaponPreset)preset);
        }
        return new Interactable(name, description, preset.size, preset.weight, preset.canBePickedUp, abilities);
        
    }

    public static Container spinContainer(ContainerPreset preset){
        Random rand = new Random();
        String name = preset.name;
        String description = preset.descriptions[rand.nextInt(preset.descriptions.length)];
        ArrayList<Interactable> inventory = new ArrayList<>();
        int loopCount = rand.nextInt(preset.maxItems - preset.minItems) + preset.minItems;
        for(int i = 0; i< loopCount; i++){
            inventory.add(generateInteractable());
        }
        return new Container(name, description, preset.size, preset.weight, preset.canBePickedUp, inventory, preset.inventorySize);
    }

    public static Weapon spinWeapon(WeaponPreset preset){
        Random rand = new Random();
        String description = preset.descriptions[rand.nextInt(preset.descriptions.length)];
        int pierce = 0;

        if(preset.pierceRange != 0){
            pierce = rand.nextInt(preset.pierceRange) + preset.pierce;
        } else{
            pierce = preset.pierce;
        }

        int damage = 0;
        if(preset.damageRange != 0){
            damage = rand.nextInt(preset.damageRange) + preset.damage;
        } else{
            damage = preset.damage;
        }
        int range = 0;
        if(!(preset.rangeRange <= 0)){
            range = rand.nextInt(preset.rangeRange) + preset.range;
        } else{
            range = preset.range;
        }

        return new Weapon(preset.size, preset.weight, preset.canBePickedUp, preset.name, description, pierce, damage, range);
    }

    //NPC Time!

    public static NPC spinNPC(int xCoord, int yCoord, NPCPreset preset, int challengeRating){
        Random rand = new Random();
        NpcAllience npcAllience = preset.npcAllience;
        String description = preset.descriptions[rand.nextInt(preset.descriptions.length)];
        int AC = randomFromRange(preset.ACRange);
        int strength = randomFromRange(preset.strRange);
        int dexterity = randomFromRange(preset.dexRange);
        int constitution = randomFromRange(preset.conRange);
        int intelligence = randomFromRange(preset.intRange);
        int wisdom = randomFromRange(preset.wisRange);
        int charisma = randomFromRange(preset.chaRange);
        int noise = randomFromRange(preset.noiseRange);
        int shield = randomFromRange(preset.shieldRange);
        String name = preset.name[rand.nextInt(preset.name.length)];
        
        NPC result = new NPC(xCoord, yCoord, AC, strength, dexterity, constitution, intelligence, wisdom, charisma, noise, shield, name, npcAllience, description);
        for(InteractablePreset itemPreset : preset.inventory){
            result.addInventory(spinInteractable(itemPreset));
        }

        return result;
    }

    private static int randomFromRange(int[] range){
        Random rand = new Random();
        if(range[1] > range[0]){
            return rand.nextInt(range[1]-range[0]) + range[0];
        } else{
            return range[0];
        }
    }

    private static NPC generateEnemy(int xCoord, int yCoord, int challenge){
        Random rand = new Random();
        File filePaths = new File("data/config/paths.json");
        String[] files;
        String pathString = "";

        try {
            FileReader pathIn = new FileReader(filePaths);
            int i = 0;
            
            while((i = pathIn.read()) !=-1){
                pathString += (char) i;
            }
            pathIn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        files = Parser.trimQuotes(Parser.parseArray("enemy-presets", pathString));
        try{
            ArrayList<String> enemyChoices = new ArrayList<>();
            for(String fileString : files){
                File file = new File(fileString);
                FileReader reader = new FileReader(file);
                String string = "";

                int i = 0;
                while((i = reader.read()) != -1){
                    string += (char) i;
                }

                String[] stringsToAdd = Parser.parseArray("enemy-presets", string);
                for(String stringToAdd : stringsToAdd){
                    enemyChoices.add(stringToAdd);
                }

            }

            String choice = enemyChoices.get(rand.nextInt(enemyChoices.size()));
            return spinNPC(xCoord, yCoord, PresetLoader.loadNpcPreset(choice), 0);

        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
