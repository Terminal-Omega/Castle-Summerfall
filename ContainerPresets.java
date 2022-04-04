import java.util.ArrayList;
import java.util.Random;

public class ContainerPresets {
    public static Container Random(int valueFactor){
        Random rand = new Random();
        switch (rand.nextInt(1)) {
            case 0:
                return chest(valueFactor);
            default:
                return null;
        }
    }
    
    public static Container chest(int valueFactor){
        Random rand = new Random();
        ArrayList<Interactable> chestInventory = new ArrayList<Interactable>();
        int loopCount = rand.nextInt(3);
        for (int i = 0; i < loopCount; i++) {
            chestInventory.add(Generator.generateInteractable());
        }
        String description = chestDescriptions[rand.nextInt(chestDescriptions.length)];
        Container result = new Container("Chest", description, 5, 20, false, chestInventory, 7);
        return result;
    }
    private static String[] chestDescriptions = {"The chest is old, nearly to the point of rotting.","The chest is oddly clean, out of place in this dark and dingy dungeon.", "It's a chest."};
}
