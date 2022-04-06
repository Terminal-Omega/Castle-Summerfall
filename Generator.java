import java.util.ArrayList;
import java.util.Random;

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
                column.add(generateRoom(1, 3, southDoor, eastDoor));
            }
            rooms.add(column);
        }
        Floor result = new Floor(rooms);
        int enemyFactor = (xSize * ySize) / 4;
        int enemyCount = rand.nextInt(enemyFactor) + enemyFactor;
        for (int i = 0; i < enemyCount; i++) {
            int x = rand.nextInt(xSize);
            int y = rand.nextInt(ySize);
            result.addNPC(EnemyPresets.generateEnemy(x, y));
        }
        return result;
    }

    // TODO: @yomas000 write some descriptions of rooms and stuff.
    private static String[] roomDescriptions = {
            "You look around and see nothing the room is too dark to see much. But you can see that the walls are gray brick that has moss and water dripping from the old stones.",
            "The room is a massive room with wooden beams sweaping up into high arched ceilings. It has bright chandeliers glowing with hundreds of candles.\nThe wood looks dark and varneshed. It reminds you of viking archetecture.",
            "The room you walked into is dark and dank. It smells like mildew and has slime covering the floor and walls.",
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
            "You look around and find yourself in a childrens nursery. Toys litter the floor and bright murals cover the walls. There is even two small beds in the room.\nThis is getting werid you think to yourself.",
            "The room you walked into is brick. Just brick. Floor, celing, walls: all brick.\nWhat is going on in this place, you wonder.",
            "The room you walked into feels sterile. It has whitewashed walls with steel tables in the center.\nSmaller tables hold all sorts of deadly looking tools.",
            "The room is a hotel you realise. Torch sconces line the very expensive mahogonay hallway.\nThe doors have brass name plates numbering 1 through 25" };

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
        Room result = new Room(roomInventory, roomDescriptions[rand.nextInt(roomDescriptions.length)], door1, door2);
        return result;
    }

    // below this is all the interactable generation.
    public static Interactable generateInteractable(double containerWeight, double commonWeight, double uncommonWeight,
            double rareWeight, double veryRareWeight, double legendaryWeight, int containerValue) {
        Random rand = new Random();
        Interactable result;
        double commonWeightModified = containerWeight + commonWeight;
        double uncommonWeightModified = commonWeightModified + uncommonWeight;
        double rareWeightModified = uncommonWeightModified + rareWeight;
        double veryRareWeightModified = rareWeightModified + veryRareWeight;
        double legendaryWeightModified = veryRareWeightModified + legendaryWeight;
        double randNum = rand.nextDouble(legendaryWeightModified);

        if (randNum < containerWeight) {
            result = ContainerPresets.Random(containerValue);
        } else if (randNum < commonWeightModified) {
            result = CommonItem.Random();
        } else {
            result = null;
        }

        return result;
    }

    public static Interactable generateInteractable() {
        return generateInteractable(.2, .8, 0, 0, 0, 0, 5);
    }

    // Enemies have been moved to EnemyPresets.java

}
