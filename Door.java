
public class Door {
    private enum KeyState{
        INSIDE,
        OUTSIDE,
        NONE
    }
    private boolean open;
    private boolean barred;
    private boolean locked;
    private boolean direction; // True is North/South, False is East/West
    private int xCoord;
    private int yCoord; // Coordinates are stored based on the room farthest Northwest that the room is
                        // connected to
    private KeyState keyState;
    public Door(boolean open, boolean barred, boolean locked, boolean direction, int xCoord, int yCoord) {
        setOpen(open);
        setBarred(barred);
        setLocked(locked);
        setDirection(direction);
        setXCoord(xCoord);
        setYCoord(yCoord);
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public void setBarred(boolean barred) {
        this.barred = barred;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public void setDirection(boolean direction) {
        this.direction = direction;
    }

    public void setXCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public void setYCoord(int yCoord) {
        this.yCoord = yCoord;
    }
}
