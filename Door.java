
public class Door {
    private boolean open;
    private boolean barred;
    private boolean locked;
    private boolean direction; // True is North/South, False is East/West
    private int xCoord;
    private int yCoord; // Coordinates are stored based on the room farthest Northwest that the room is
                        // connected to
}
