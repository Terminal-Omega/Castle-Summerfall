import java.util.ArrayList;
import java.util.Random;

public class Enemy extends NPC {

    public Enemy() {

    }

    public Enemy(int xCoord, int yCoord, int health, int AC, int speed, int mana, int strength, int dexterity,
            int constitution, int intelligence, int wisdom, int charisma, int noise, int carryWeight, String name,
            int shield, String npcAllience, String description) {
        super(xCoord, yCoord, health, AC, speed, mana, strength, dexterity, constitution, intelligence, wisdom,
                charisma, noise, carryWeight, shield, name, npcAllience, description);
    }

    public void noneCombatAI() {

    }
}
