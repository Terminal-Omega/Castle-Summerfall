import java.util.ArrayList;
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
        Pattern inspectPat = Pattern.compile("inspect ([A-Z][a-z].*)"); // TODO: @yomas000 this RegEx is broken, I think, and it only finds item names that are capitalized. Maybe remove some square brackets?
        Pattern takePat = Pattern.compile("take ([A-Z][a-z].*)");
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
            Matcher takeMatch = takePat.matcher(inputCommand);
            boolean commandKnown = true;

            if (inputCommand.equals("help")){
                UI.helpCommand("all");
                commandKnown = false;
            }

            if (helpMatch.find()){
                UI.helpCommand(helpMatch.group(1));
                commandKnown = false;
            }
            //Move command
            if (moveMatch.find()){
                UI.move(moveMatch.group(1), player, floor1, FLOORSIZE);
                commandKnown = false;
            }

            //clear command
            if (inputCommand.equals(UI.Commands.CLEAR.getStrCommand())){
                System.out.print("\033[H\033[2J");
                System.out.flush();
                commandKnown = false;
            }

            //look around command
            if (inputCommand.equals(UI.Commands.LOOK_AROUND.getStrCommand())){
               System.out.println(floor1.getDescription(player.getXCoord(), player.getYCoord())); // Hey Thomas, just replaced the getDescription with the new one in floor so that it picks up enemies too. Otherwise, it works the exact same.
               commandKnown = false;
            }

            //where TODO: remove this for final draft @yomas000
            if (inputCommand.equals("where")) {
                System.out.print("x: " + player.getXCoord() + " y: " + player.getYCoord());
                commandKnown = false;
            }

            //inspect command
            if (inspectMatch.find()){
                String name = floor1.getRoom(player.getXCoord(), player.getYCoord()).getItem(inspectMatch.group(1), 0).getDescription();
                System.out.println(name);
                commandKnown = false;
            }

            //inventory command.
            if (inputCommand.equals(UI.Commands.INVENTORY.getStrCommand())){
                String inventory = "";
                for (String name : player.getInventory()){
                    inventory += name + ", ";
                }
                if (inventory.equals("")){
                    System.out.println("You don't have anything on you");
                }else{
                    System.out.println(inventory);
                }
                commandKnown = false;
            }

            //take command
            if (takeMatch.find()){
                player.putItem(floor1.getRoom(player.getXCoord(), player.getYCoord()).getItem(takeMatch.group(1)));
                //TODO: I need a room.removeItem() please @Corbanator
            }

            //if command is not known
            if (commandKnown && inputCommand.equals("exit") == false){
                System.out.println("Sorry I don't know what you wanted.");
            }

        }while(inputCommand.equals(UI.Commands.EXIT.getStrCommand()) == false || player.health > 0);
    }
}
