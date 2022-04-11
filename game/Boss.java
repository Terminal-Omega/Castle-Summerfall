package game;

/// 
public class Boss extends NPC {

    public Boss(int xCoord, int yCoord, int AC, int strength, int dexterity,
            int constitution, int intelligence, int wisdom, int charisma,
            int noise, int shield, String name, String npcAllience, String description) {
        super(xCoord, yCoord, AC, strength, dexterity, constitution, intelligence, wisdom,
                charisma, noise,
                shield, name, npcAllience, description);
        ;
    }

    public void bossCombat(Actor player) {

    }
}
