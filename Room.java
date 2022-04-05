
import java.util.ArrayList;

public class Room {
    private ArrayList<Interactable> interactables;
    private String description;
    private Door southDoor;
    private Door eastDoor;
    private boolean visited;
    private ArrayList<String> bookmarks;

    public Room(ArrayList<Interactable> interactables, String description, Door southDoor, Door eastDoor) {
        this.interactables = interactables;
        this.description = description;
        this.southDoor = southDoor;
        this.eastDoor = eastDoor;
        bookmarks = new ArrayList<>();
        visited = false;
    }

    public Room(int seed){
        
    }
    
    //Grab the description of the room
    public String getDescription(){
        StringBuilder describe = new StringBuilder();
        describe.append(description);
        switch (interactables.size()) {
            case 0:
                break;
            case 1:
            describe.append("\n");
            describe.append("There is a " + interactables.get(0).getName() + " in the room.");
            break;
            case 2:
            describe.append("\n");
            describe.append(String.format("In the room is a %s and a %s", interactables.get(0).getName(),interactables.get(1).getName()));
            break;
            default:
            describe.append("\n");
            for(int i = 0;i<interactables.size();i++){
                if(i != interactables.size() - 1){
                    describe.append(" a "+interactables.get(i).getName() + ",");
                } else{
                    describe.append(" and a" + interactables.get(i).getName() + ".");
                }
            }
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
        int latestIndex = -1;
        for(int i = 0;i<interactables.size();i++){
            if(interactables.get(i).getName().toLowerCase().equals(name.toLowerCase())){
                latestIndex = i;
                timesFound++;
                if(timesFound == index){
                    return interactables.get(i);
                }
            }
        }
        if(latestIndex !=-1){
            return interactables.get(latestIndex);
        } else{
            return null;
        }
    }

    public Interactable getItem(String name){
        return getItem(name,0);
    }

    public Interactable takeItem(String name, int index){
        int timesFound = -1;
        int latestIndex = -1;
        for(int i = 0;i<interactables.size();i++){
            if(interactables.get(i).getName().toLowerCase().equals(name.toLowerCase())){
                latestIndex = i;
                timesFound++;
                if(timesFound == index){
                    Interactable result = interactables.get(i);
                    interactables.remove(i);
                    return result;
                }
            }
        }
        if(latestIndex !=-1){
            Interactable result = interactables.get(latestIndex);
            interactables.remove(latestIndex);
            return result;
        } else{
            return null;
        }
    }

    public Interactable takeItem(String name){
        return takeItem(name, 0);
    }

    public void addItem(Interactable item){
        interactables.add(item);
    }

    public boolean isVisited(){
        return visited;
    }

    public void visit(){
        visited = true;
    }

    public void addBookmark(String bookmark){
        bookmarks.add(bookmark);
    }

    public String[] getBookmarks(){
        String[] result = new String[bookmarks.size()];
        for(int i = 0;i<bookmarks.size();i++){
            result[i] = bookmarks.get(i);
        }
        return result;
    }
}
