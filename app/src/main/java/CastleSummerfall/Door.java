package CastleSummerfall;

/**
 * @author @Corbanator
 * 
 *         This class is a door designed to be opened and closed by the player.
 */
public class Door {

    // These store the state of the door. barred is not used yet but included for
    // future extensibility.
    private boolean open;
    private boolean barred;
    private boolean locked;

    public Door(boolean open, boolean barred, boolean locked) {
        this.open = open;
        setBarred(barred);
        setLocked(locked);
    }

    /**
     * This method attempts to open the door. It is designed to be printed to give
     * the user feedback on whether the opening of the door was successful or what
     * went wrong.
     *
     * @return String
     */
    public String open() {
        if (!locked) {
            open = true;
            return "You open the door";
        } else {
            return "The door appears to be locked.";
        }
    }

    /**
     * This method closes the door.
     */
    public void close() {
        open = false;
    }

    /**
     * This is included for testing purposes and allows for manually setting whether
     * the door is barred.
     *
     * @param barred
     */
    public void setBarred(boolean barred) {
        this.barred = barred;
    }

    /**
     * This is included for testing purposes and allows for manually setting whether
     * the door is locked.
     *
     * @param locked
     */
    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    /**
     * This method just unlockes the door.
     */
    public void unlock() {
        locked = false;
    }

    /**
     * returns whether the door is open and, therefore, passable.
     *
     * @return boolean
     */
    public boolean isOpen() {
        return open;
    }
}
