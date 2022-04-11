package game;

import java.util.Random;

public class Boss extends NPC {
    Random rand = new Random();

    public Boss(int xCoord, int yCoord, int AC, int strength, int dexterity,
            int constitution, int intelligence, int wisdom, int charisma,
            int noise, int shield, String name, NpcAllience npcAllience, String description) {
        super(xCoord, yCoord, AC, strength, dexterity, constitution, intelligence, wisdom,
                charisma, noise,
                shield, name, npcAllience, description);

    }

    public void bossFight(Actor player) {
        int attackChoice = rand.nextInt(3);
        switch (attackChoice) {
            case 0:
                Weapon weapon1 = (Weapon) inventory.get(0);
                System.out.println(weapon1.damage + "Slam");
                closeCombat(weapon1, player);
            case 1:
                Weapon weapon2 = (Weapon) inventory.get(1);
                System.out.println(weapon2.damage + "Swish");
                closeCombat(weapon2, player);
            case 2:
                Weapon weapon3 = (Weapon) inventory.get(2);
                System.out.println(weapon3.damage + "Smack");
                closeCombat(weapon3, player);
            default:
                System.out.println("uh Something broke... well that is annoying");

        }
    }
}
