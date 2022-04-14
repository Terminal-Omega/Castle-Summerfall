package game;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public static String parseString(String attribute, String toLoad){
        Pattern stringPattern = Pattern.compile("\"" + attribute + "\" *?: *?\"(.*?)\"");
        Matcher stringMatcher = stringPattern.matcher(toLoad);
        stringMatcher.find();
        return stringMatcher.group(1);
    }

    public static int[] parseInt(String attribute, String toLoad){
        Pattern intPattern = Pattern.compile("\"" + attribute + "\" *?: *?\"([0-9]+)(-([0-9]+))?\"");
        Matcher intMatcher = intPattern.matcher(toLoad);
        intMatcher.find();
        int[] result = new int[2];
        result[0] = Integer.parseInt(intMatcher.group(1));
        if(!intMatcher.group(3).toLowerCase().equals("")){
            result[1] = Integer.parseInt(intMatcher.group(3));
        } else{
            result[1] = -1;
        }
        return result;
    }

    public static boolean parseBoolean(String attribute, String toLoad){
        Pattern boolPattern = Pattern.compile("\"" + attribute + "\" *: *\"(.*?)\"");
        Matcher boolMatcher = boolPattern.matcher(toLoad);
        boolMatcher.find();
        return Boolean.parseBoolean(boolMatcher.group(1));
    }

    public static String[] parseArray(String attribute, String toLoad){
        Pattern arrayPattern = Pattern.compile("\"" + attribute + "\"\\s*:\\s*\\[(.*)\\]",Pattern.DOTALL);
        Matcher arrayMatcher = arrayPattern.matcher(toLoad);
        arrayMatcher.find();
        String arrayString = arrayMatcher.group(1);

        String[] arrayStrings = arrayString.split(",");
        return arrayStrings;
    }

    public static String[] trimQuotes (String[] toTrim){
        String[] result = new String[toTrim.length];
        for(int i = 0; i< toTrim.length;i++){
            result[i] = toTrim[i].replace("\"", "");
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
