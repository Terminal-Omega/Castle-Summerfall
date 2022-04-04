import java.util.ArrayList;
import java.util.Random;

public class Enemy extends NPC {

    public Enemy() {

    }

    public Enemy(int xCoord, int yCoord, int AC, int strength, int dexterity,
            int constitution, int intelligence, int wisdom, int charisma, int noise, String name,
            int shield, String npcAllience, String description) {
        super(xCoord, yCoord, AC, strength, dexterity, constitution, intelligence, wisdom,
                charisma, noise, shield, name, npcAllience, description);
    }

    public void noneCombatAI() {

    }
}
