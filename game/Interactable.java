package game;
import java.util.ArrayList;
public class Interactable {
    protected int size;
    protected int weight;
    protected boolean canBePickedUp;
    protected String name;
    protected String description;
    ArrayList<Ability> abilities;

    //Constructors
    public Interactable(String name, String description, int size, int weight, boolean canBePickedUp, ArrayList<Ability> abilities){
        this.size = size;
        this.weight = weight;
        this.canBePickedUp = canBePickedUp;
        this.name = name;
        this.description = description;
        this.abilities = abilities;
    }

    public Interactable(String name, String description, int size, int weight, boolean canBePickedUp){
        this(name, description, size, weight, canBePickedUp, new ArrayList<>());
    }

    public Interactable(String name, String description){
        this(name, description, 0, 0, false);
    }


    /** 
     * @return String
     */
    //Methods
    public String getName(){
        return name;
    }

    
    /** 
     * @return String
     */
    public String getDescription(){
        StringBuilder builder = new StringBuilder();
        builder.append(description);
        return builder.toString();
    }

}
