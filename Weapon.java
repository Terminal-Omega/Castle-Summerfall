
public class Weapon extends Interactable {
    public Weapon(int size, int weight, boolean canBePickedUp, String name, String description) {
        super(size, weight, canBePickedUp, name, description);
    }
    protected int attackSpeed;
    protected String type;
    protected int damage;
    protected int range;
    protected String[] moddifiers;
    protected String name;
    protected boolean sharp;
    protected int pierce;
}
