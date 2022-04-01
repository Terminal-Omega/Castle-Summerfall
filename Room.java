
import java.util.ArrayList;

public class Room {
    private ArrayList<Interactable> interactables;
    private String description;
    private Door southDoor;
    private Door eastDoor;

    public Room(ArrayList<Interactable> interactables, String description) {
        this.interactables = interactables;
        this.description = description;
    }

    public Room(int seed){
        
    }

    //Grab the description of the room
    public String getDescription(){
        StringBuilder describe = new StringBuilder();
        describe.append("The room is ");
        describe.append(description);
        describe.append("\nIn the room are ");
        for(int i = 0;i<interactables.size();i++){
            if(i!= 0){
                describe.append(", ");
            }
            describe.append(interactables.get(i).getName());
        }
        return describe.toString();
    }

    // DO NOT USE this method. Use the one in the Floor Class, it's much easier.
    public Door getDoor(Direction direction){
        switch (direction) {
            case SOUTH:
                return southDoor;
            case EAST:
                return eastDoor;
            default:
            return null;
        }
    }

    public Interactable getItem(String name, int index){
        int timesFound = -1;
        int latestIndex = 0;
        for(int i = 0;i<interactables.size();i++){
            if(interactables.get(i).getName().toLowerCase().equals(name)){
                latestIndex = i;
                timesFound++;
                if(timesFound == index){
                    return interactables.get(i);
                }
            }
        }
        if(latestIndex !=0){
            return interactables.get(latestIndex);
        } else{
            return null;
        }
    }
}
