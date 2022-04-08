import java.util.ArrayList;
public class Interactable {
    protected int size;
    protected int weight;
    protected boolean canBePickedUp;
    protected String name;
    protected String description;

    //Constructors
    public Interactable(String name, String description, int size, int weight, boolean canBePickedUp){
        this.size = size;
        this.weight = weight;
        this.canBePickedUp = canBePickedUp;
        this.name = name;
        this.description = description;
    }

    public Interactable(){};


    
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
