import java.util.Vector;
import java.util.function.*;

public class Interactable {
    protected int size;
    protected int weight;
    protected boolean canBePickedUp;
    protected Vector<Interactable> inventory;
    protected int inventoryCapacity;
}
