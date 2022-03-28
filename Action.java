public class Action {
    protected String name;

    public interface effect {
        void someMethod(String[] args);
    }
}
