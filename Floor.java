
import java.util.ArrayList;
import java.util.Random;
public class Floor {
    private ArrayList<ArrayList<Room>> rooms; // Stores Coordinates literally based on the location within the vector.

    public Floor(ArrayList<ArrayList<Room>> rooms) {
        this.rooms = rooms;
    }

    public Room getRoom(int xCoord, int yCoord){
        return rooms.get(xCoord).get(yCoord);
    }
}
