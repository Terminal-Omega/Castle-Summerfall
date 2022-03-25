import java.util.Random;

public class NPC extends Actor {
    private String allience;
    private String weakness;

    public NPC() {

    }

    public NPC(String allience, String weakness) {
        setAllience(allience);

        setWeakness(weakness);
    }

    public void setAllience(String allience) {
        this.allience = allience;
    }

    public void setWeakness(String weakness) {
        this.weakness = weakness;
    }

}
