import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {

    public static void main(String[] args) {
        UI.helpCommand("all");
        Scanner input = new Scanner(System.in);
        String inputCommand;
        final int FLOORSIZE = 5;
        Pattern helpPat = Pattern.compile("help ([a-z].*)");
        Pattern movePat = Pattern.compile("move ([N|n|S|s|W|w|E|e])");
        Floor floor1 = Generator.generateFloor(FLOORSIZE, FLOORSIZE);
        Player player = new Player();
        player.setYCoord(0);
        player.setXCoord(0);

        do{
            System.out.print("> ");
            inputCommand = input.nextLine();
            Matcher helpMatch = helpPat.matcher(inputCommand);
            Matcher moveMatch = movePat.matcher(inputCommand);

            if (inputCommand.equals("help")){
                UI.helpCommand("all");
            }

            if (helpMatch.find()){
                UI.helpCommand(helpMatch.group(1));
            }
            if (moveMatch.find()){
                if (moveMatch.group(1).equals("N") || moveMatch.group(1).equals("n")){
                    if (player.getYCoord() < 5 && player.getYCoord() >= 0){
                        player.setYCoord(player.getYCoord() + 1);
                    }
                }
                if (moveMatch.group(1).equals("S") || moveMatch.group(1).equals("s")) {
                    if (player.getYCoord() < 5 && player.getYCoord() >= 0) {
                        player.setYCoord(player.getYCoord() - 1);
                    }
                }
                if (moveMatch.group(1).equals("E") || moveMatch.group(1).equals("E")) {
                    if (player.getXCoord() < 5 && player.getXCoord() >= 0) {
                        player.setXCoord(player.getXCoord() - 1);
                    }
                }
                if (moveMatch.group(1).equals("W") || moveMatch.group(1).equals("w")) {
                    if (player.getXCoord() < 5 && player.getXCoord() >= 0) {
                        player.setXCoord(player.getXCoord() + 1);
                    }
                }
            }

            if (inputCommand.equals("clear")){
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }

            if (inputCommand.equals(UI.Commands.LOOK_AROUND.getStrCommand())){
               System.out.println(floor1.getRoom(player.getXCoord(), player.getYCoord()).getDescription());
            }

            if (inputCommand.equals("where")) {
                System.out.print("x: " + player.getXCoord() + " y: " + player.getYCoord());
            }

        }while(inputCommand.equals(UI.Commands.EXIT.getStrCommand()) == false);
    }
}
