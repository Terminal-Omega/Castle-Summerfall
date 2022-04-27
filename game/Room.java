package game;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

/**
 * @Author @Corbanator
 * 
 * Represents a room in the virtual dungeon the player will explore.
 * Stores the objects in the room, whether it's been visited, whether it's bookmarked,
 * whether there are stairs, and the doors south and east of it. 
 * The system of storing doors allows to easily access doors from the floor class because every door that exists will be stored in a room.
 * It also stores what the room looks like and allows the player to inspect the room further.
 */
public class Room {

    // Randomly generate objects, placed seperately in the description
    private ArrayList<Interactable> interactables;

    // Objects already included in the room description; Not designed to be picked up.
    private ArrayList<Interactable> descriptionInteractables;
    private String description;

    //The doors
    private Door southDoor;
    private Door eastDoor;

    // Stores some booleans about the state of the room, such as if it's been visited
    private boolean visited;
    private String[] bookmark = {"", ""};
    private boolean stairs;

    //Constructors
    public Room(ArrayList<Interactable> interactables, ArrayList<Interactable> descriptionInteractables, String description, Door southDoor, Door eastDoor) {
        this.interactables = interactables;
        this.descriptionInteractables = descriptionInteractables;
        this.description = description;
        this.southDoor = southDoor;
        this.eastDoor = eastDoor;
        visited = false;
        stairs = false;
    }


    
    /**
     * very simple, just gets the description of the room, saying which objects are in it.
     * DO NOT USE, you should use the getDescription in the Floor class.
     * 
     * @return the description of the room as a string
     */
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

    /**
     * DO NOT USE THIS METHOD. USE THE ONE IN THE FLOOR CLASS.
     * This method only exists to allow the Floor class to access its doors.
     * 
     * @param direction the direction you want the door in. Accepts only South and East
     * @return A door corresponding to that direction
     */
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

    /**
     * This method gets an item from the room based on a name.
     * 
     * @param name the name of the item
     * @param index which occurence of the item, starting at 0 for the first occurence. Defaults to 0 if not set.
     * 
     * @return an Interactable corresponding to the name
     * @throws ThingNotFoundException Throws this exception if it's not found.
     */
    public Interactable getItem(String name, int index) throws ThingNotFoundException{
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
            throw new ThingNotFoundException("A" + name + " was not found in the room");
        }
    }

    
    /** 
     * 
     * @param name
     * @return Interactable
     * @throws ThingNotFoundException
     * @see getItem
     */
    public Interactable getItem(String name) throws ThingNotFoundException{
        return getItem(name, 0);
    }
    /**
     * Exactly like getItem, but it removes the item when it returns it.
     * @param name the name of the item
     * @param index which occurence of the item, starting at 0 for the first occurence.
     * Defaults to 0 if left out.
     * @return an Interactable corresponding to the name
     * @throws ThingNotFoundException Throws this exception if it's not found.
     */
    public Interactable takeItem(String name, int index) throws ThingNotFoundException{
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
            throw new ThingNotFoundException("a " + name + " was not found in the room");
        }
    }

    
    /** 
     * @param name
     * @return Interactable
     * @throws ThingNotFoundException
     */
    public Interactable takeItem(String name) throws ThingNotFoundException{
        return takeItem(name, 0);
    }

    
    /** 
     * Adds an item to the room. Designed for when the player drops an item.
     * @param item
     */
    public void addItem(Interactable item){
        interactables.add(item);
    }

    
    /** 
     * @return boolean whether the room has been visited
     */
    public boolean isVisited(){
        return visited;
    }

    /**
     * mark the room as visited
     */
    public void visit(){
        visited = true;
    }

    
    /** 
     * make a bookmark for the room to mark it for later. These are visible in the map.
     * @param bookmark
     * @param description
     */
    public void addBookmark(String bookmarkChar, String description){
        bookmark[0] = bookmarkChar;
        bookmark[1] = description;
    }

    
    /** 
     * 
     * @return  String[] the bookmark in the room
     */
    public String[] getBookmark(){
        return bookmark;
    }

    
    /** 
     *  Removes the bookmark placed on the room
     */
    public void removeBookmark(){
        bookmark[0] = "";
        bookmark[1] = "";
    }

    
    /** 
     *  This method gets an interactable just like getItem, but it gets it from the description interactables instead.
     * @param name
     * @param index  Index is optional
     * @return Interactable
     * @throws ThingNotFoundException
     */
    public Interactable getDescriptionInteractable(String name, int index) throws ThingNotFoundException{
        int timesFound = -1;
        int latestIndex = -1;
        for(int i = 0;i<descriptionInteractables.size();i++){
            if(descriptionInteractables.get(i).getName().toLowerCase().equals(name.toLowerCase())){
                latestIndex = i;
                timesFound++;
                if(timesFound == index){
                    return descriptionInteractables.get(i);
                }
            }
        }
        if(latestIndex !=-1){
            return descriptionInteractables.get(latestIndex);
        } else{
            throw new ThingNotFoundException("A " + name + " was not found in the room");
        }
    }

    
    /** 
     * @param name
     * @return Interactable
     * @throws ThingNotFoundException
     */
    public Interactable getDescriptionInteractable(String name) throws ThingNotFoundException{
        return getDescriptionInteractable(name, 0);
    }

    
    /** 
     * @return boolean whether there are stairs in the room or not
     */
    public boolean isStairs(){
        return stairs;
    }

    /**
     *  marks the room as having stars
     */
    public void makeStairs(){
        stairs = true;
    }
}
