package game;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Random;

public class App {

    /**
     * @param args
     */
    public static void main(String[] args) {
        UI.displayOpening();
        UI.helpCommand("all");
        Scanner input = new Scanner(System.in);
        String inputCommand;
        final int FLOORSIZE = 5;
        Pattern helpPat = Pattern.compile("[Hh]elp ([a-z].*)");
        Pattern movePat = Pattern.compile("[Mm]ove ([N|n|S|s|W|w|E|e])");
        Pattern inspectPat = Pattern.compile("[Ii]nspect ([A-Za-z].*)");
        Pattern takePat = Pattern.compile("[tT]ake ([A-Za-z].*)");
        Pattern bookPat = Pattern.compile("[Bb]ookmark ([A-Za-z].*?) : ([A-Za-z].*)");
        Pattern dropPat = Pattern.compile("[Dd]rop ([A-Za-z].*)");
        Pattern attackPat = Pattern.compile("[Aa]ttack ([A-Za-z].*?) [Ww].* ([A-Za-z].*)");
        Floor floor = Generator.generateFloor(FLOORSIZE, FLOORSIZE);
        Player player = new Player(0, 0, 7, 20, 2);
        int energy = player.getEnergy();
        Random rand = new Random();
        boolean endGame = true;
        System.out.println(floor.getDescription(0, 0));
        final String OSNAME = System.getProperty("os.name");

        do {
            System.out.print("\n> ");
            inputCommand = input.nextLine();
            Matcher helpMatch = helpPat.matcher(inputCommand);
            Matcher moveMatch = movePat.matcher(inputCommand);
            Matcher inspectMatch = inspectPat.matcher(inputCommand);
            Matcher takeMatch = takePat.matcher(inputCommand);
            Matcher dropMatch = dropPat.matcher(inputCommand);
            Matcher attackMatch = attackPat.matcher(inputCommand);
            Matcher bookMatch = bookPat.matcher(inputCommand);
            // Matcher takeChestMatch = takeChestPat.matcher(inputCommand);

            boolean commandKnown = true;

            if (inputCommand.equals("help")) {
                UI.helpCommand("all");
                commandKnown = false;
            }

            // help command
            if (helpMatch.find()) {
                UI.helpCommand(helpMatch.group(1));
                commandKnown = false;
            }
            // Move command
            if (moveMatch.find()) {
                commandKnown = false;
                int energyCost = UI.Commands.MOVE.getSpeedCommand();
                if (energy - energyCost < 0) {
                    System.out.println(UI.colorString("You don't have enough energy to do this", UI.Colors.RED));
                    UI.displayEnergy(energy);
                } else {
                    energy -= energyCost;
                    UI.move(moveMatch.group(1), player, floor, FLOORSIZE);
                }
            }

            // clear command
            if (inputCommand.equals(UI.Commands.CLEAR.getStrCommand())) {
                int height = 12;
                if (OSNAME.equals("Windows 10")){
                    System.out.print("\033[H\033[2J\033[5B");
                    System.out.flush();
                }else{
                    for (int i = 0; i < height; i++){
                        System.out.println();
                    }
                }
                commandKnown = false;
            }

            // look around command
            if (inputCommand.equals(UI.Commands.LOOK_AROUND.getStrCommand())) {
                commandKnown = false;
                int energyCost = UI.Commands.LOOK_AROUND.getSpeedCommand();
                if (energy - energyCost < 0) {
                    System.out.println(UI.colorString("You don't have enough energy to do this", UI.Colors.RED));
                    UI.displayEnergy(energy);
                } else {
                    energy -= energyCost;
                    System.out.println(floor.getDescription(player.getXCoord(), player.getYCoord()));
                }
            }

            // bookmark command
            if (bookMatch.find()) {
                floor.getRoom(player.getXCoord(), player.getYCoord() + 1).addBookmark(bookMatch.group(1),
                        bookMatch.group(2));
                System.out.println("This room is bookmarked with the character: " + bookMatch.group(1).charAt(0));
                commandKnown = false;
            }

            if (inputCommand.equals("where")) {
                System.out.printf("x:%d  y:%d", player.getXCoord(), player.getYCoord());
                commandKnown = false;
            }

            // inspect command
            if (inspectMatch.find()) {
                int energyCost = UI.Commands.INSPECT.getSpeedCommand();
                String command = inspectMatch.group(1);
                if (energy - energyCost < 0) {
                    System.out.println(UI.colorString("You don't have enough energy to do this", UI.Colors.RED));
                    UI.displayEnergy(energy);
                } else {
                    energy -= energyCost;
                    String output;
                    try {
                        Interactable thing = floor.getRoom(player.getXCoord(), player.getYCoord()).getItem(inspectMatch.group(1), 0);
                        output = thing.getDescription();
                        output += "\nWeight: " + thing.weight + "\n";
                        if (thing instanceof Weapon){
                            Weapon weapon = (Weapon) thing;
                            output += "Pierce: " + weapon.getPierce() + "\n";
                            output += "Damage: " + weapon.getDamage() + "\n";
                        }

                        System.out.println(output);
                    } catch (ThingNotFoundException e) {
                        try {
                            Interactable description = floor.getRoom(player.getXCoord(), player.getYCoord()).getDescriptionInteractable(command);
                            System.out.println(description.getDescription());
                        }catch (ThingNotFoundException g){
                            System.out.println(g.getMessage());
                        }
                    }
                }

                commandKnown = false;
            }

            // inventory command.
            if (inputCommand.equals(UI.Commands.INVENTORY.getStrCommand())) {
                int energyCost = UI.Commands.INVENTORY.getSpeedCommand();
                if (energy - energyCost < 0) {
                    System.out.println(UI.colorString("You don't have enough energy to do this", UI.Colors.RED));
                    UI.displayEnergy(energy);
                } else {
                    energy -= energyCost;
                    UI.displayInventory(player.getInventory(), player.getHealth(), player.getMaxHealth(),
                            player.getMaxWeight());
                }

                commandKnown = false;
            }

            // take command
            if (takeMatch.find()) {
                int energyCost = UI.Commands.TAKE.getSpeedCommand();
                if (energy - energyCost < 0) {
                    System.out.println(UI.colorString("You don't have enough energy to do this", UI.Colors.RED));
                    UI.displayEnergy(energy);
                } else {
                    String itemString = takeMatch.group(1);
                    itemString = " " + itemString;
                    Pattern takePattern = Pattern.compile("(?<=\\s)(\\w*)");
                    Matcher matcher = takePattern.matcher(itemString);
                    Interactable firstItem;
                    try {
                        matcher.find();
                        firstItem = floor.getRoom(player.getXCoord(), player.getYCoord()).getItem(matcher.group(1));
                        itemString = itemString.replace(matcher.group(1), "");
                    } catch (ThingNotFoundException e) {
                        System.out.print(e.getMessage());
                    }

                }

                commandKnown = false;
            }

            // drop command
            if (dropMatch.find()) {
                int energyCost = UI.Commands.DROP.getSpeedCommand();
                if (energy - energyCost < 0) {
                    System.out.println(UI.colorString("You don't have enough energy to do this", UI.Colors.RED));
                    UI.displayEnergy(energy);
                } else {
                    energy -= energyCost;
                    try {
                        Interactable item = player.dropItem(dropMatch.group(1), 0);
                        floor.getRoom(player.getXCoord(), player.getYCoord()).addItem(item);
                        System.out.println("dropped");
                    } catch (ThingNotFoundException e) {
                        System.out.println(e.getMessage());
                    }

                }

                commandKnown = false;
            }

            // health command
            if (inputCommand.equals(UI.Commands.HEALTH.getStrCommand())) {
                UI.displayHeath(player.getHealth(), player.getMaxHealth());
                commandKnown = false;
            }

            // TODO: remove unlimited command for final draft @yomas000
            if (inputCommand.equals("map")) {
                UI.displayMap(floor.getXSize(), floor.getYSize(), player, floor);
                commandKnown = false;
            }

            // attack command
            if (attackMatch.find()) {
                String actorString = attackMatch.group(1);
                String weaponString = attackMatch.group(2);
                int energyCost = UI.Commands.ATTACK.getSpeedCommand();
                if (energy - energyCost < 0) {
                    System.out.println(UI.colorString("You don't have enough energy to do this", UI.Colors.RED));
                    UI.displayEnergy(energy);
                } else {
                    if (player.isInInventory(weaponString)) {
                        try {
                            Weapon weapon = (Weapon) player.getItem(weaponString, 0);
                            NPC badGuy = floor.getNPC(actorString, player.getXCoord(), player.getYCoord(), 0);
                            if (player.closeCombat(weapon, badGuy)) {
                            } else {
                                UI.displayHeath(badGuy.getHealth(), badGuy.getMaxHealth());
                            }

                        } catch (ThingNotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                        energy -= energyCost;
                    } else {
                        System.out.println("You don't have that in your inventory, so you attack with your hands");
                    }
                }
                commandKnown = false;
            }

            // energy command
            if (inputCommand.equals(UI.Commands.ENERGY.getStrCommand())) {
                System.out.println("\tEnergy: " + energy);
                UI.displayEnergy(energy);
                commandKnown = false;
            }

            if (inputCommand.equals(UI.Commands.REST.getStrCommand())) {
                commandKnown = false;
                energy -= energy;
            }

            // reset turn
            if (energy <= 0) {
                int randNum = rand.nextInt(5);
                switch (randNum) {
                    case 0:
                        System.out.println(
                                "Your eyes feel tired you can't go on. And so you take a short nap. But it must be quick you think, Your family is waiting");
                        break;
                    case 1:
                        System.out.println(
                                "The floor doesn't seem so bad you think, as you sink to your ground. I have to be quick though.");
                        break;
                    case 2:
                        System.out.println(
                                "Your eye lids droop and you can't take another step. This isn't the time to be falling asleep you think. My family can't wait");
                        break;
                    case 3:
                        System.out.println(
                                "Time has flown by and you are too tired tired to think right now. You fall to the ground and start to sleep.");
                        break;
                    case 4:
                        System.out.println(
                                "No more falling asleep you think. You have got to find one of those energy potions. There might be one somewhere you think as you fall asleep.");
                        break;
                    case 5:
                        System.out.println(
                                "I want a bed you think. Sleeping on the ground has got your back in knots. But you are just too tired to find a bed.");
                }

                Updates.update(player, floor);
                energy = player.getEnergy();
            } else {
                Updates.actionUpdate(floor);
            }

            // Easter eggs
            if (inputCommand.equals("Xyzzyz")) {
                player.setConstitution(15);
                player.setHealth();
                System.out.println("You have found the cheat code. Your health is now 30");
                commandKnown = false;
                ;
            }
            if (inputCommand.equals("eat knife")) {
                if (player.isInInventory("Knife")) {
                    player.setConstitution(0);
                    player.setHealth();
                    System.out.println("You found the secret ending. PS this was Adam's idea");
                } else {
                    System.out.println("You don't have a knife to eat");
                }
                commandKnown = false;
            }

            if (inputCommand.equals(UI.Commands.EXIT.getStrCommand())) {
                endGame = false;
                ;
            }

            if (player.getHealth() <= 0) {
                endGame = false;
                UI.displayEnding();
            }

            // if command is not known
            if (commandKnown && inputCommand.equals("exit") == false) {
                System.out.println("Sorry I don't know what you wanted.");
            }

            if (!OSNAME.equals("Windows 10")){
                UI.printHeader(player.getHealth(), player.getMaxHealth(), energy, player.getInventory().size());
            }
            floor.getRoom(player.getXCoord(), player.getYCoord() + 1).visit();
            //System.out.println();

        } while (endGame);

    }
}