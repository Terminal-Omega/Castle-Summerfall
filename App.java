import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {

    public static void main(String[] args) {
        UI.helpCommand("all");
        Scanner input = new Scanner(System.in);
        String inputCommand;
        Pattern helpPat = Pattern.compile("help ([a-z].*)");
        do{
            System.out.print("> ");
            inputCommand = input.nextLine();
            Matcher helpMatch = helpPat.matcher(inputCommand);

            if (inputCommand.equals("help")){
                UI.helpCommand("all");
            }

            if (helpMatch.find()){
                UI.helpCommand(helpMatch.group(1));
            }

            if (inputCommand.equals("clear")){
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }

        }while(inputCommand.equals(UI.Commands.EXIT.getStrCommand()) == false);
    }
}
