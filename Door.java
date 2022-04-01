
public class Door {
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
        setOpen(open);
        setBarred(barred);
        setLocked(locked);
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

    public boolean passable(){
        return true;
    }
}
