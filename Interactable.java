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

    public String getDescription(){
        StringBuilder builder = new StringBuilder();
        builder.append(description);
        if(inventory != null){
            builder.append("In the " + name + "are ");
            for(int i = 0; i<inventory.size();i++){
                if(i != inventory.size()-1){ 
                    builder.append("a "+inventory.get(i).getName() + ", ");
                } else{
                    builder.append("and a " + inventory.get(i).getName() + ".");
                }
            }
        }
        return builder.toString();
    }

    public Interactable getItem(String name, int index){
        int timesFound = -1;
        int latestIndex = 0;
        for(int i = 0;i<inventory.size();i++){
            if(inventory.get(i).getName().toLowerCase().equals(name)){
                latestIndex = i;
                timesFound++;
                if(timesFound == index){
                    return inventory.get(i);
                }
            }
        }
        if(latestIndex !=0){
            return inventory.get(latestIndex);
        } else{
            return null;
        }
    }
    
}
