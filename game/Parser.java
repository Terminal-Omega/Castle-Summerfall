package game;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Objects;

public class Parser {
    public static String parseString(String attribute, String toLoad){
        Pattern stringPattern = Pattern.compile("\"" + attribute + "\" *?: *?\"(.*?)\"");
        Matcher stringMatcher = stringPattern.matcher(toLoad);
        if(stringMatcher.find()){
            return stringMatcher.group(1);
        } else{
            return "";
        }
    }

    public static int[] parseInt(String attribute, String toLoad){
        Pattern intPattern = Pattern.compile("\"" + attribute + "\"\\s*?:\\s*?\"([0-9]+)(-([0-9]+))?\"");
        Matcher intMatcher = intPattern.matcher(toLoad);
        int[] result = new int[2];
        if(intMatcher.find()){
            result[0] = Integer.parseInt(intMatcher.group(1));

            if(!Objects.isNull(intMatcher.group(4).toLowerCase())){
                result[1] = Integer.parseInt(intMatcher.group(3));
            } else{
                result[1] = -1;
            }
        }
        return result;
    }

    public static boolean parseBoolean(String attribute, String toLoad){
        Pattern boolPattern = Pattern.compile("\"" + attribute + "\"\\s*:\\s*\"(.*?)\"");
        Matcher boolMatcher = boolPattern.matcher(toLoad);
        if(boolMatcher.find()){
            return Boolean.parseBoolean(boolMatcher.group(1));
        } else{
            return false;
        }
    }

    public static String[] parseArray(String attribute, String toLoad){
        Pattern arrayPattern = Pattern.compile("\"" + attribute + "\"\\s*?:\\s*?\\[\\s*(.*?)\\s*\\]", Pattern.DOTALL);
        Matcher arrayMatcher = arrayPattern.matcher(toLoad);
        String arrayString = "";
        if(arrayMatcher.find()){
            arrayString = arrayMatcher.group(1);
        } else {
            return null;
        }
        String[] arrayStrings = arrayString.split(",");
        return arrayStrings;
    }

    public static String[] trimQuotes (String[] toTrim){
        String[] result = new String[toTrim.length];
        for(int i = 0; i< toTrim.length;i++){
            String stringToAdd = toTrim[i].replaceAll("\"", "");
            result[i] = stringToAdd;
        }
        return result;
    }

    public static String parseObject(String attribute, String toLoad){
        Pattern objectPattern = Pattern.compile("\"" + attribute + "\" *: *{(.*)}");
        Matcher objectMatcher = objectPattern.matcher(toLoad);
        objectMatcher.find();
        return objectMatcher.group(1);
    }
}
