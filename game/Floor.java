package game;

import java.util.ArrayList;
import java.util.Objects;
/**
 * @author @Corbanator
 * This Class stores a grid of rooms as a 2-dimensional ArrayList, as well as all of the NPCs in the room.
 * It does special functions only allowed by access to all rooms/NPCS
 */
public class Floor {
    private ArrayList<ArrayList<Room>> rooms; // Stores Coordinates literally based on the location within the ArrayList.
    private ArrayList<NPC> NPCS; //NPCs store their own Coordinates, as 2 NPCs can be in the same room.

    public Floor(ArrayList<ArrayList<Room>> rooms) {
        this.rooms = rooms;
        this.NPCS = new ArrayList<>();
    }

    
    /** 
     * Adds an NPC to the Floor.
     * @param NPC
     */
    public void addNPC(NPC NPC) {
        NPCS.add(NPC);
    }

    
    /** 
     * Gets a room based on coordinates
     * @return Room
     */
    public Room getRoom(int xCoord, int yCoord) {
        return rooms.get(xCoord).get(yCoord);
    }

    
    /** 
     * gets a door based on coordinates and a direction, or throws an exception if that door doesn't exist.
     * multiple sets of inputs can obtain the same door, as each door is technically connected to two rooms.
     * @param xCoord
     * @param yCoord
     * @param direction
     * @return Door
     * @throws ThingNotFoundException
     */
    public Door getDoor(int xCoord, int yCoord, Direction direction) throws ThingNotFoundException {

        switch (direction) {
            case NORTH:
                /**
                 * Both North and West rooms are a bit tricky, as they are stored in a room different from the one with the coordinates given.
                 * As a result, it is required that I check if this room exists before attempting to get a door from it.
                 */
                if (!(yCoord + 1 >= rooms.get(xCoord).size())) {
                    return rooms.get(xCoord).get(yCoord + 1).getDoor(Direction.SOUTH);
                } else {
                    throw new ThingNotFoundException("Door does not exist");
                }
            case SOUTH:
                return rooms.get(xCoord).get(yCoord).getDoor(Direction.SOUTH);
            case EAST:
                return rooms.get(xCoord).get(yCoord).getDoor(Direction.EAST);
            case WEST:
                if (xCoord != 0) {
                    return rooms.get(xCoord - 1).get(yCoord).getDoor(Direction.EAST);
                } else {
                    throw new ThingNotFoundException("You walk into a wall, but there is not door there");
                }
            default:
                throw new ThingNotFoundException("There isn't a door there");
        }
    }

    
    /** 
     * This method returns the description of a certain room based on location.
     * It is required to be in the floor class because it must include doors and enemies, both of which are inaccessible from the actual Room itself.
     * @param xCoord
     * @param yCoord
     * @return String
     */
    public String getDescription(int xCoord, int yCoord) {
       

        StringBuilder builder = new StringBuilder(rooms.get(xCoord).get(yCoord).getDescription());

        /**If there are stairs in the room, then add a special portion to the description explaining that 
         * there is either a boss or that the boss is defeated.
         */
        if(rooms.get(xCoord).get(yCoord).isStairs()){
            if((!Objects.isNull(getBoss()))){
                builder.append("\nThis is the exit. Fight the boss to escape.");
            } else{
                builder.append("\nThe boss has been defeated! Use \"descend\" to move to the next floor.");
            }
        }

        //gets all the NPCs at those coordinates and adds them to an ArrayList
        ArrayList<NPC> localNPC = new ArrayList<>();
        for (NPC NPC : NPCS) {
            if (NPC.getXCoord() == xCoord && NPC.getYCoord() == yCoord) {
                localNPC.add(NPC);
            }
        }
        //Add all the NPCs into a specially formatted string based on the plurality of enemies to ensure grammatical correctness
        switch (localNPC.size()) {
            case 0:
                break;
            case 1:
                builder.append("\n");
                builder.append("There is a " + localNPC.get(0).getName() + " in the room.");
                break;
            case 2:
                builder.append("\n");
                builder.append(String.format("In the room is a %s and a %s", localNPC.get(0).getName(),
                        localNPC.get(0).getName()));
                break;
            default:
                builder.append("\n");
                for (int i = 0; i < localNPC.size(); i++) {
                    if (i != localNPC.size() - 1) {
                        builder.append(" a " + localNPC.get(i).getName() + ",");
                    } else {
                        builder.append(" and a" + localNPC.get(i).getName() + ".");
                    }
                }

        }

        //check each direction for a door, then add it to a string. Has some slight grammatical issues for two doors in particular.
        int j = 0;
        for (Direction direction : Direction.values()) {
            try {
                getDoor(xCoord, yCoord, direction);
                j++;
                String openStatus = "";
                String directionString = direction.toString().substring(0, 1).toUpperCase()
                        + direction.toString().substring(1);
                if (getDoor(xCoord, yCoord, direction).isOpen()) {
                    openStatus = "an open";
                } else {
                    openStatus = "a closed";
                }
                if (j == 1) {
                    builder.append("\nThere is " + openStatus + " door to the " + directionString);
                } else if (j < Direction.values().length - 1) {
                    builder.append(", " + openStatus + " door to the " + directionString);
                } else {
                    builder.append(", and " + openStatus + " door to the " + directionString);
                }
            } catch(Exception e){
                
            }
        }
        builder.append(".");

        //returns the resulting string to be printed.
        return builder.toString();
    }

    
    /** 
     * @return int x size of floor
     */
    public int getXSize(){
        return rooms.size();
    }

    
    /** 
     * @return int y size of floor, assuming the floor is rectangular
     */
    public int getYSize(){
        return rooms.get(0).size();
    }

    
    /** 
     * @return ArrayList<NPC> all of the NPCs
     */
    public ArrayList<NPC> getNPCs(){
        return NPCS;
    }

    
    /** 
     * Returns an NPC based on name and location. The format for the parameters is designed to make it easy to use on the frontend.
     * This is because the NPC name passed in by the player can then be passed directly to this method.
     * @param name
     * @param xCoord
     * @param yCoord
     * @param index
     * @return NPC
     * @throws ThingNotFoundException
     */
    public NPC getNPC(String name, int xCoord, int yCoord ,int index) throws ThingNotFoundException{
        for(NPC npc : NPCS){
            if(npc.getName().toLowerCase().equals(name.toLowerCase()) && npc.getXCoord() == xCoord && npc.getYCoord() == yCoord){
                return npc;
            }
        }
        throw new ThingNotFoundException("Enemy not found.");
    }

    
    /** 
     * Removes an NPC based on the index of that NPC.
     * @param index
     */
    public void removeNPC(int index){
        NPCS.remove(index);
    }

    
    /** 
     * Returns the boss, if there is one, or null, if there isn't.
     * @return NPC
     */
    public NPC getBoss(){
        for(NPC npc : NPCS){
            if(npc.isBoss()){
                return npc;
            }
        }
        return null;
    }
}
