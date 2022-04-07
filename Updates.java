import java.util.regex.Pattern;
import java.util.Random;
import java.util.regex.Matcher;
import java.lang.Integer;
public class Updates {
    public static void applyEffect(String effect, Actor target){
        Random rand = new Random();

        Pattern damage = Pattern.compile("damage\\(([0-9]*(-([0-9]*))?)\\)");
        Pattern heal = Pattern.compile("heal\\(([0-9]*(-([0-9]*))?)\\)");

        Matcher damageMatcher = damage.matcher(effect);
        if(damageMatcher.find()){
            int damageAmount;
            if(damageMatcher.group(3)!=""){
                damageAmount = rand.nextInt(Integer.parseInt(damageMatcher.group(3))) + Integer.parseInt(damageMatcher.group(1));
                // TODO: @Corbanator make this work once Xander pushes  the thing
            } else{
                damageAmount = Integer.parseInt(damageMatcher.group(1));
            }
        }

        Matcher healMatcher = heal.matcher(effect);
        if(damageMatcher.find()){
            int healAmount;
            if(damageMatcher.group(3)!=""){
                healAmount = rand.nextInt(Integer.parseInt(damageMatcher.group(3))) + Integer.parseInt(damageMatcher.group(1));
                // TODO: @Corbanator make this work once Xander pushes  the thing
            } else{
                healAmount = Integer.parseInt(damageMatcher.group(1));
            }
            target.heal(healAmount);
        }


    }

    public static void doPassives(Actor actor){

    }

    public static void update(Player player, Floor floor){
        doPassives(player);
        // TODO: @Corbanator implement noise and LOS
        for(int i = 0; i<floor.getNPCs().size();i++){
            NPC npc = floor.getNPCs().get(i);
            doPassives(npc);
            if(npc.getHealth() <= 0){
                floor.getNPCs().remove(i);
                System.out.println(npc.getName() +" was killed.");
            }
            npc.npcTurnAllience(player, floor.getXSize());
            // TODO: @Corbanator call NPC AI once it exists
            // TODO: @Corbanator notify player if NPC enters the room
        }
    }
}
