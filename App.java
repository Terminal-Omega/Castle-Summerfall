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
        Pattern inspectPat = Pattern.compile("inspect ([A-Z][a-z].*)");
        Floor floor1 = Generator.generateFloor(FLOORSIZE, FLOORSIZE);
        Player player = new Player();
        player.setYCoord(0);
        player.setXCoord(0);

        do{
            System.out.print("> ");
            inputCommand = input.nextLine();
            Matcher helpMatch = helpPat.matcher(inputCommand);
            Matcher moveMatch = movePat.matcher(inputCommand);
            Matcher inspectMatch = inspectPat.matcher(inputCommand);

            if (inputCommand.equals("help")){
                UI.helpCommand("all");
            }

            if (helpMatch.find()){
                UI.helpCommand(helpMatch.group(1));
            }
            //Move command
            if (moveMatch.find()){
                UI.move(moveMatch.group(1), player, floor1, FLOORSIZE);
            }

            //clear command
            if (inputCommand.equals(UI.Commands.CLEAR.getStrCommand())){
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }

            //look around command
            if (inputCommand.equals(UI.Commands.LOOK_AROUND.getStrCommand())){
               System.out.println(floor1.getRoom(player.getXCoord(), player.getYCoord()).getDescription());
            }

            //where TODO: remove this for final draft @yomas000
            if (inputCommand.equals("where")) {
                System.out.print("x: " + player.getXCoord() + " y: " + player.getYCoord());
            }

            //inspect command
            if (inspectMatch.find()){
                String name = floor1.getRoom(player.getXCoord(), player.getYCoord()).getItem(inspectMatch.group(1), 0).getDescription();
                System.out.println(name);
            }

            if (inputCommand.equals(UI.Commands.INVENTORY.getStrCommand())){
                String inventory = "";
                for (String name : player.getInventory()){
                    inventory += name + " ";
                }
                if (inventory.equals("")){
                    System.out.println("You don't have anything on you");
                }else{
                    System.out.println(inventory);
                }
            }
        }while(inputCommand.equals(UI.Commands.EXIT.getStrCommand()) == false || player.health > 0);
    }
}
