import java.util.ArrayList;
public class Interactable {
    protected int size;
    protected int weight;
    protected boolean canBePickedUp;
    protected ArrayList<Interactable> inventory;
    protected int inventoryCapacity;
    private String name;
    private String description;

    public Interactable(int size, int weight, boolean canBePickedUp, String name, String description, ArrayList<Interactable> inventory) {
        this.size = size;
        this.weight = weight;
        this.canBePickedUp = canBePickedUp;
        this.name = name;
        this.description = description;
        this.inventory = inventory;
    }

    public Interactable(int size, int weight, boolean canBePickedUp, String name, String description){
        this(size, weight, canBePickedUp,name,description,null);
    }
    public Interactable(){};

    public String getName(){
        return name;
    }
    
}
