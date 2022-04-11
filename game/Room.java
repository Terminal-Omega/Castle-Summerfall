package game;

import java.util.ArrayList;
import java.util.Hashtable;

public class Room {
    private ArrayList<Interactable> interactables;
    private String description;
    private Door southDoor;
    private Door eastDoor;
    private boolean visited;
    private String[] bookmark = {"_", ""};

    public Room(ArrayList<Interactable> interactables, String description, Door southDoor, Door eastDoor) {
        this.interactables = interactables;
        this.description = description;
        this.southDoor = southDoor;
        this.eastDoor = eastDoor;
        visited = false;
    }

    public Room(int seed){
        
    }
    
    /**
     * very simple, just gets the description of the room, saying which objects are in it.
     * DO NOT USE, you should use the getDescription in the Floor class.
     * 
     * @return the description of the room
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
     * 
     * @param name the name of the item
     * @param index which occurence of the item, starting at 0 for the first occurence.
     * Defaults to 0 if not set.
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
            throw new ThingNotFoundException("Item not found in room");
        }
    }

    
    /** 
     * @param name
     * @return Interactable
     * @throws ThingNotFoundException
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
            throw new ThingNotFoundException("Item not Found");
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
     * @param item
     */
    public void addItem(Interactable item){
        interactables.add(item);
    }

    
    /** 
     * @return boolean
     */
    public boolean isVisited(){
        return visited;
    }

    public void visit(){
        visited = true;
    }

    
    /** 
     * @param bookmark
     * @param description
     */
    public void addBookmark(String bookmarkChar, String description){
        bookmark[0] = bookmarkChar;
        bookmark[1] = description;
    }

    
    /** 
     * @return Hashtable<Character, String> returns all of the bookmarks
     */
    public String[] getBookmarks(){
        return bookmark;
    }

    
    /** 
     * @param toRemove
     */
    public void removeBookmark(){
        bookmark[0] = "";
        bookmark[1] = "";
    }
}
