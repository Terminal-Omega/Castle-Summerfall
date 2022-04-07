import java.util.ArrayList;

public class Container extends Interactable {
    protected ArrayList<Interactable> inventory;
    protected int inventoryCapacity;

    public Container(String name, String description, int size, int weight, boolean canBePickedUp, ArrayList<Interactable> inventory,int inventoryCapacity){
        super(name, description, size, weight, canBePickedUp);
        this.inventory = inventory;
        this.inventoryCapacity = inventoryCapacity;
        
    }

    //methods
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
    
    public Interactable takeItem(String name, int index) throws ThingNotFoundException{
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
            throw new ThingNotFoundException("Item not found.");
        }
    }

    public Interactable takeItem(String name) throws ThingNotFoundException{
        return takeItem(name, 0);
    }

    public void addItem(Interactable item){
        inventory.add(item);
    }
}
