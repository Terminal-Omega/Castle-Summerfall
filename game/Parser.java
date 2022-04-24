package game;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Objects;
/**
 * This class is an incomplete, yet adequate, JSON parser. I built it from scratch because I was under the impression we weren't allowed to use external libraries
 * and Java doesn't have built-in JSON parsing.
 */
public class Parser {
    
    /** 
     * takes in a string that is the key of the attribute and a string to a load from and returns the string that correlates with that key
     * @param attribute
     * @param toLoad
     * @return String
     */
    public static String parseString(String attribute, String toLoad){
        //searches for a string enclosed in quotes and preceded by the attribute name, then returns the contents of the string, or an empty string if not found.
        Pattern stringPattern = Pattern.compile("\"" + attribute + "\" *?: *?\"(.*?)\"");
        Matcher stringMatcher = stringPattern.matcher(toLoad);
        if(stringMatcher.find()){
            return stringMatcher.group(1);
        } else{
            return "";
        }
    }

    
    /** 
     * loads an integer or integer range based on the name of the attribute associated with it.
     * integers must be enclosed in quotes for this to work.
     * @param attribute
     * @param toLoad
     * @return int[]
     */
    public static int[] parseInt(String attribute, String toLoad){
        //searches for a number (i.e "0") or range of numbers (i.e. "4-6") enclosed in quotes and preceded by the attribute name
        Pattern intPattern = Pattern.compile("\"" + attribute + "\"\\s*?:\\s*?\"([0-9]+)(-([0-9]+))?\"");
        Matcher intMatcher = intPattern.matcher(toLoad);
        int[] result = new int[2];
        if(intMatcher.find()){
            result[0] = Integer.parseInt(intMatcher.group(1));

            if(!Objects.isNull(intMatcher.group(3))){
                result[1] = Integer.parseInt(intMatcher.group(3));
            } else{
                result[1] = 0;
            }
        }
        return result;
    }

    
    /** 
     * parses a boolean, which must also be enclosed in quotes, based on the key associated with it.
     * @param attribute
     * @param toLoad
     * @return boolean
     */
    public static boolean parseBoolean(String attribute, String toLoad){
        //searches for a string enclosed in quotes and preceded by the attribute name
        Pattern boolPattern = Pattern.compile("\"" + attribute + "\"\\s*:\\s*\"(.*?)\"");
        Matcher boolMatcher = boolPattern.matcher(toLoad);

        if(boolMatcher.find()){
            //parses a boolean from the string 
            return Boolean.parseBoolean(boolMatcher.group(1));
        } else{
            return false;
        }
    }

    
    /** 
     * parses a JSON array into an array of strings
     * @param attribute
     * @param toLoad
     * @return String[]
     */
    public static String[] parseArray(String attribute, String toLoad){
        // if it can't find the attribute, it assumes that the whole string is the array and starts with an opening square bracket.
        String arrayString;
        if(Objects.isNull(attribute)){
            arrayString = toLoad;
        } else{
            Pattern arrayPattern = Pattern.compile("\"" + attribute + "\"\\s*:\\s*(\\[.*)", Pattern.DOTALL);
            Matcher arrayMatcher = arrayPattern.matcher(toLoad);
            if(arrayMatcher.find()){
                arrayString = arrayMatcher.group(1);
            } else {
                return null;
            }
        }

        //various variables to do tracking during the parsing.
        int squareCount = 0;
        int curlyCount = 0;
        boolean quoteCount = false;
        int startIndex = 1;
        int index = 0;
        char nextChar;
        ArrayList<String> strings = new ArrayList<>();

        do{

            // for each character, increment or decrement how many layers of brackets you are in according to that character.
            nextChar = arrayString.charAt(index);
            if(nextChar == '['){
                squareCount++;
            } else if(nextChar == ']'){
                squareCount--;
            } else if(nextChar == '{'){
                curlyCount++;
            } else if(nextChar == '}'){
                curlyCount--;
            } else if(nextChar == '\"' && arrayString.charAt(index-1) !='\\'){
                quoteCount = !quoteCount;
                //if the character is a comma between items in the array, return a substring and update the starting index to the new location
            }else if(nextChar ==',' && curlyCount == 0 && squareCount == 1 && !quoteCount){
                strings.add(arrayString.substring(startIndex, index));
                startIndex = index + 1;
            }
            index++;

        }while(squareCount !=0);

        //add the last element to the array
        strings.add(arrayString.substring(startIndex, index-1).trim());

        String[] arrayStrings = new String[strings.size()];

        //make escaped newlines work properly
        for(int i = 0;i<strings.size();i++){
            arrayStrings[i] = strings.get(i).replace("\\n", "\n");
        }
        return arrayStrings;
    }


    
    /** 
     * remove the quotes from each element of an array of strings that were surrounded by quotes
     * @param toTrim
     * @return String[]
     */
    public static String[] trimQuotes (String[] toTrim){
        //avoid null errors
        if(Objects.isNull(toTrim)){
            return null;
        }

        String[] result = new String[toTrim.length];
        for(int i = 0; i< toTrim.length;i++){
            //replace quotes with nothing
            String stringToAdd = toTrim[i].replaceAll("\"", "");
            result[i] = stringToAdd.trim();
        }
        return result;
    }

    
    /** 
     * load an object. Not currently used for anything, but was easy to implement because it was similar to parseArray
     * @param attribute
     * @param toLoad
     * @return String
     */
    public static String parseObject(String attribute, String toLoad){
        //find the object based on its name
        Pattern objectPattern = Pattern.compile("\"" + attribute + "\" *: *(\\{.*)", Pattern.DOTALL);
        Matcher objectMatcher = objectPattern.matcher(toLoad);
        String string;
        if(objectMatcher.find()){
            string = objectMatcher.group(1);
        } else{
            return null;
        }

        //this works almost exactly like parseArray.
        int index = 0;
        char nextChar;
        int squareCount = 0;
        int curlyCount = 0;
        int startIndex = 1;
        do{

            nextChar = string.charAt(index);
            if(nextChar == '['){
                squareCount++;
            } else if(nextChar == ']'){
                squareCount--;
            } else if(nextChar == '{'){
                curlyCount++;
            } else if(nextChar == '}'){
                curlyCount--;
            }
            
            if(curlyCount == 0){
                return string.substring(startIndex, index);
            }
            index++;

        }while(curlyCount !=0);
        return null;
    }
}
