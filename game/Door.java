package game;
// Doors, like the type you go through.
public class Door {
    // stores if there's a key in the door and which side it's on. Useless for now.
    private enum KeyState{
        INSIDE,
        OUTSIDE,
        NONE
    }
    private boolean open;
    private boolean barred;
    private boolean locked;
    private KeyState keyState;
    public Door(boolean open, boolean barred, boolean locked) {
        this.open = open;
        setBarred(barred);
        setLocked(locked);
    }

    
    /** 
     * @return String
     */
    public String open() {
        open = true;
        return "You open the Door";
    }

    public void close() {
        open = false;
    }

    
    /** 
     * @param barred
     */
    public void setBarred(boolean barred) {
        this.barred = barred;
    }

    
    /** 
     * @param locked
     */
    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    
    /** 
     * @return boolean
     */
    public boolean isOpen(){
        return open;
    }
}
