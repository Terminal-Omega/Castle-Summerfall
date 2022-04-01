import java.util.Random;
import java.util.ArrayList;

public class NPC extends Actor {
    protected String npcAllience;

    public NPC() {

    }

    public NPC(int xCoord, int yCoord, int health, int AC, int speed, int mana, int strength, int dexterity,
            int constitution, int intelligence, int wisdom, int charisma,
            int noise, int carryWeight, int shield, String name, String npcAllience) {
        super(xCoord, yCoord, health, AC, speed, mana, strength, dexterity, constitution, intelligence, wisdom,
                charisma, noise,
                carryWeight, shield, name);
    }

    public NPC(String allience) {
        setAllience(allience);

    }

    public void setAllience(String allience) {
        npcAllience = allience;
    }

    public void npcTurnNoneCombat(String doorLocation, boolean doorLocked, boolean doorBarred) {

    }

}
