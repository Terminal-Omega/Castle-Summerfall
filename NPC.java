import java.util.Random;

public class NPC extends Actor {
    String allience;

    public NPC() {

    }

    public NPC(String allience) {
        setAllience(allience);
    }

    public void setAllience(String allience) {
        this.allience = allience;
    }

}
