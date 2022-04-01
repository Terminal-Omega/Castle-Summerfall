
import java.util.ArrayList;

public class Room {
    private ArrayList<Interactable> interactables;
    private String description;

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
}
