import java.util.Vector;
import java.util.function.*;

public class Interactable {
    private int size;
    private int weight;
    private boolean canBePickedUp;
    private Vector<Interactable> inventory;
    private int inventoryCapacity;
}
