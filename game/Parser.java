package game;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
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

            if(!Objects.isNull(intMatcher.group(3))){
                result[1] = Integer.parseInt(intMatcher.group(3));
            } else{
                result[1] = 0;
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
        int squareCount = 0;
        int curlyCount = 0;
        boolean quoteCount = false;
        int startIndex = 1;
        int index = 0;
        char nextChar;
        ArrayList<String> strings = new ArrayList<>();
        do{

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
            }else if(nextChar ==',' && curlyCount == 0 && squareCount == 1 && !quoteCount){
                strings.add(arrayString.substring(startIndex, index));
                startIndex = index + 1;
            }
            index++;

        }while(squareCount !=0);

        strings.add(arrayString.substring(startIndex, index-1).trim());

        String[] arrayStrings = new String[strings.size()];
        for(int i = 0;i<strings.size();i++){
            arrayStrings[i] = strings.get(i);
        }
        return arrayStrings;
    }


    public static String[] trimQuotes (String[] toTrim){
        if(Objects.isNull(toTrim)){
            return null;
        }
        String[] result = new String[toTrim.length];
        for(int i = 0; i< toTrim.length;i++){
            String stringToAdd = toTrim[i].replaceAll("\"", "");
            result[i] = stringToAdd.trim();
        }
        return result;
    }

    public static String parseObject(String attribute, String toLoad){
        Pattern objectPattern = Pattern.compile("\"" + attribute + "\" *: *{(.*)}");
        Matcher objectMatcher = objectPattern.matcher(toLoad);
        objectMatcher.find();
        String string = objectMatcher.group(1);

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
            } else if(nextChar ==',' && curlyCount == 0 && squareCount == 1){
                return string.substring(startIndex, index);
            }
            index++;

        }while(squareCount !=0);
        return null;
    }
}
