
import java.util.ArrayList;
import java.util.Random;
public class Floor {
    private ArrayList<ArrayList<Room>> rooms; // Stores Coordinates literally based on the location within the vector.

    public Floor(ArrayList<ArrayList<Room>> rooms) {
        this.rooms = rooms;
    }

    // public Floor(){
    //     ArrayList<ArrayList<Room>> rooms;
    //     ArrayList<Room> roomList;
    //     for (int i = 0; i < 5; i++){
    //         for (int j = 0; j < 5; j++){
    //             Room room = new Room();
    //             roomList.add(room);
    //         }
    //         rooms.add(roomList);
    //     }
    //     Floor(rooms);
    // }

    public Room getRoom(int xCoord, int yCoord){
        return rooms.get(xCoord).get(yCoord);
    }

    //use this to get a door going in a specific direction
    public Door getDoor(int xCoord, int yCoord, Direction direction){
        switch (direction) {
            case NORTH:
                return rooms.get(xCoord).get(yCoord+1).getDoor(Direction.SOUTH);
            case SOUTH:
                return rooms.get(xCoord).get(yCoord).getDoor(Direction.SOUTH);
            case EAST:
                return rooms.get(xCoord).get(yCoord).getDoor(Direction.EAST);
            case WEST:
            return rooms.get(xCoord-1).get(yCoord).getDoor(Direction.EAST);
            default:
                return null;
        }
    }
}
