
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
}
