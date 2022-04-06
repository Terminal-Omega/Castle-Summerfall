import java.util.ArrayList;
public class Interactable {
    protected int size;
    protected int weight;
    protected boolean canBePickedUp;
    protected String name;
    protected String description;
    protected boolean isNull;

    //Constructors
    public Interactable(String name, String description, int size, int weight, boolean canBePickedUp){
        this.size = size;
        this.weight = weight;
        this.canBePickedUp = canBePickedUp;
        this.name = name;
        this.description = description;
    }

    public Interactable(){};

    public Interactable(boolean isNull){
        this.isNull = isNull;
    }


    //Methods
    public String getName(){
        return name;
    }

    public boolean isNull(){
        return isNull;
    }

    public String getDescription(){
        StringBuilder builder = new StringBuilder();
        builder.append(description);
        return builder.toString();
    }

}
