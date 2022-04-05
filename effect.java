import java.util.regex.Pattern;
import java.util.Random;
import java.util.regex.Matcher;
import java.lang.Integer;
public class effect {
    public void applyEffect(String effect, Actor target){
        Random rand = new Random();

        Pattern damage = Pattern.compile("damage ([0-9]*(-([0-9]*))?)");

        
        Matcher damageMatcher = damage.matcher(effect);
        if(damageMatcher.find()){
            if(damageMatcher.group(3)!=""){
                int damageAmount = rand.nextInt(Integer.parseInt(damageMatcher.group(1)));
            }
        }


    }
}
