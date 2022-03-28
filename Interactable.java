import java.util.Vector;
public class Interactable {
    protected int size;
    protected int weight;
    protected boolean canBePickedUp;
    protected Vector<Interactable> inventory;
    protected int inventoryCapacity;
    private String name;
    private String description;

    public String getName(){
        return name;
    }
    
}
