import java.util.Random;

public class NPC extends Actor {
    private String allience;
    private String name;
    private String weakness;

    public NPC() {

    }

    public NPC(String allience, String name, String weakness) {
        setAllience(allience);
        setName(name);
        setWeakness(weakness);
    }

    public void setAllience(String allience) {
        this.allience = allience;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeakness(String weakness) {
        this.weakness = weakness;
    }
}
