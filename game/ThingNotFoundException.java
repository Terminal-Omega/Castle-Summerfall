package game;
/**
 * @author @Corbanator
 * A small custom exception to avoid throwing null all the way back to the app class.
 */
public class ThingNotFoundException extends Exception {
    public ThingNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
