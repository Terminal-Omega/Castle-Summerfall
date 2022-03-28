
import java.util.ArrayList;
public class Floor {
    private ArrayList<ArrayList<Room>> rooms; // Stores Coordinates literally based on the location within the vector.

    public Room getRoom(int xCoord, int yCoord){
        return rooms.get(xCoord).get(yCoord);
    }
}
