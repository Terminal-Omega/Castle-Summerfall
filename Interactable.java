import java.util.ArrayList;
public class Interactable {
    protected int size;
    protected int weight;
    protected boolean canBePickedUp;
    protected ArrayList<Interactable> inventory;
    protected int inventoryCapacity;
    private String name;
    private String description;

    public Interactable(int size, int weight, boolean canBePickedUp, String name, String description) {
        this.size = size;
        this.weight = weight;
        this.canBePickedUp = canBePickedUp;
        this.name = name;
        this.description = description;
    }
    public Interactable(){};

    public String getName(){
        return name;
    }
    
}
