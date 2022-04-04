import java.util.ArrayList;
public class Interactable {
    protected int size;
    protected int weight;
    protected boolean canBePickedUp;
    protected ArrayList<Interactable> inventory;
    protected int inventoryCapacity;
    private String name;
    private String description;

    public Interactable(String name, String description, int size, int weight, boolean canBePickedUp, ArrayList<Interactable> inventory) {
        this.size = size;
        this.weight = weight;
        this.canBePickedUp = canBePickedUp;
        this.name = name;
        this.description = description;
        this.inventory = inventory;
    }

    public Interactable(String name, String description, int size, int weight, boolean canBePickedUp){
        this(name,description,size, weight, canBePickedUp,null);
    }

    public Interactable(){};

    public String getName(){
        return name;
    }

    public String getDescription(){
        StringBuilder builder = new StringBuilder();
        builder.append(description);
        if(inventory != null){
            builder.append("\nIn the " + name + " is");
            for(int i = 0; i<inventory.size();i++){
                if(inventory.size() == 1){
                    builder.append(" a " + inventory.get(i).getName() + ".");
                }else if(i != inventory.size()-1){ 
                    builder.append(" a " +inventory.get(i).getName() + ",");
                } else{
                    builder.append(" and a " + inventory.get(i).getName() + ".");
                }
            }
        }
        return builder.toString();
    }

    public Interactable getItem(String name, int index){
        int timesFound = -1;
        int latestIndex = 0;
        for(int i = 0;i<inventory.size();i++){
            if(inventory.get(i).getName().toLowerCase().equals(name.toLowerCase())){
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

    public Interactable getItem(String name){
        return getItem(name, 0);
    }
    
    public Interactable takeItem(String name, int index){
        int timesFound = -1;
        int latestIndex = 0;
        for(int i = 0;i<inventory.size();i++){
            if(inventory.get(i).getName().toLowerCase().equals(name.toLowerCase())){
                latestIndex = i;
                timesFound++;
                if(timesFound == index){
                    Interactable result = inventory.get(i);
                    inventory.remove(i);
                    return result;
                }
            }
        }
        if(latestIndex !=0){
            Interactable result = inventory.get(latestIndex);
            inventory.remove(latestIndex);
            return result;
        } else{
            return null;
        }
    }

    public Interactable takeitem(String name){
        return takeItem(name, 0);
    }
}
