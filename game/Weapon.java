package game;

public class Weapon extends Interactable {
    public Weapon(int size, int weight, boolean canBePickedUp, String name, String description, int pierce,
            int damage) {
        super(name, description, size, weight, canBePickedUp);
        this.damage = damage;
    }

    protected int attackSpeed;
    protected String type;
    protected int damage;
    protected int range;
    protected String[] moddifiers;
    protected String name;
    protected boolean sharp;
    protected int pierce;
    // Knife type:CC, damage:3-5, name:knife, pierce:3
    // Short Sword type:CC, damage:5-8, name:Short Sword, pierce:5
    // Sword type:CC, damage:6-9, name:Sword, Pierce:6
    // Great Sword type:CC, damage:10-15, name:Great Sword, Pierce, 10
    // Rapier type:CC, damage:6-9, name:Rapier, Pierce: 13

}
