package game;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        Pattern dropPat = Pattern.compile("[Dd]rop ([A-Za-z].*)");
        Pattern attackPat = Pattern.compile("[Aa]ttack ([A-Za-z].*?) [Ww].* ([A-Za-z].*)");
        Floor floor1 = Generator.generateFloor(FLOORSIZE, FLOORSIZE);
        Player player = new Player(0, 0, 5, 15);
        int speed = player.getSpeed();
        boolean endTurn = false;

        do {
            System.out.print("> ");
            inputCommand = input.nextLine();
            Matcher helpMatch = helpPat.matcher(inputCommand);
            Matcher moveMatch = movePat.matcher(inputCommand);
            Matcher inspectMatch = inspectPat.matcher(inputCommand);
            Matcher takeMatch = takePat.matcher(inputCommand);
            Matcher dropMatch = dropPat.matcher(inputCommand);
            Matcher attackMatch = attackPat.matcher(inputCommand);

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
                int speedCost = UI.Commands.MOVE.getSpeedCommand();
                if (speed - speedCost <= 0) {
                    System.out.println("You don't have enought time to do this");
                } else {
                    speed -= speedCost;
                    UI.move(moveMatch.group(1), player, floor1, FLOORSIZE);
                }
            }

            // clear command
            if (inputCommand.equals(UI.Commands.CLEAR.getStrCommand())) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                commandKnown = false;
            }

            // look around command
            if (inputCommand.equals(UI.Commands.LOOK_AROUND.getStrCommand())) {
                commandKnown = false;
                int speedCost = UI.Commands.LOOK_AROUND.getSpeedCommand();
                if (speed - speedCost <= 0) {
                    System.out.println("You don't have enought time to do this");
                } else {
                    speed -= speedCost;
                    System.out.println(floor1.getDescription(player.getXCoord(), player.getYCoord()));
                }
            }

            // inspect command
            if (inspectMatch.find()) {
                int speedCost = UI.Commands.INSPECT.getSpeedCommand();
                if (speed - speedCost <= 0) {
                    System.out.println("You don't have enought time to do this");
                } else {
                    speed -= speedCost;
                    String name;
                    try {
                        name = floor1.getRoom(player.getXCoord(), player.getYCoord()).getItem(inspectMatch.group(1), 0)
                                .getDescription();
                        System.out.println(name);
                    } catch (ThingNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                }

                commandKnown = false;
            }

            // inventory command.
            if (inputCommand.equals(UI.Commands.INVENTORY.getStrCommand())) {
                int speedCost = UI.Commands.INVENTORY.getSpeedCommand();
                if (speed - speedCost <= 0) {
                    System.out.println("You don't have enought time to do this");
                } else {
                    speed -= speedCost;
                    UI.displayInventory(player.getInventory(), player.getHealth(), player.getMaxHealth());
                }

                commandKnown = false;
            }

            // take command
            if (takeMatch.find()) {
                int speedCost = UI.Commands.TAKE.getSpeedCommand();
                if (speed - speedCost < 0) {
                    System.out.println("You don't have enought time to do this");
                } else {
                    speed -= speedCost;
                    try {
                        Interactable interactable = floor1.getRoom(player.getXCoord(), player.getYCoord())
                                .takeItem(takeMatch.group(1));
                        player.putItem(interactable);
                    } catch (ThingNotFoundException e) {
                        System.out.println(e.getMessage());
                        ;
                    }
                }

                commandKnown = false;
            }

            // drop command
            if (dropMatch.find()) {
                int speedCost = UI.Commands.DROP.getSpeedCommand();
                if (speed - speedCost <= 0) {
                    System.out.println("You don't have enought time to do this");
                } else {
                    speed -= speedCost;
                    try {
                        Interactable item = player.dropItem(dropMatch.group(1), 0);
                        floor1.getRoom(player.getXCoord(), player.getYCoord()).addItem(item);
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
                UI.displayMap(floor1.getXSize(), floor1.getYSize(), player);
                commandKnown = false;
            }

            // attack command
            if (attackMatch.find()) {
                String actorString = attackMatch.group(1);
                String weaponString = attackMatch.group(2);
                int speedCost = UI.Commands.ATTACK.getSpeedCommand();
                if (speed - speedCost <= 0) {
                    System.out.println("You don't have enought time to do this");
                } else {
                    if (player.isInInventory(weaponString)) {
                        try {
                            Weapon weapon = (Weapon) player.getItem(weaponString, 0);
                            NPC badGuy = floor1.getNPC(actorString, player.getXCoord(), player.getYCoord(), 0);
                            if (player.closeCombat(weapon, badGuy)) {
                                System.out.println("Dead");
                            } else {
                                UI.displayHeath(badGuy.getHealth(), badGuy.getMaxHealth());
                            }

                        } catch (ThingNotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    } else {
                        System.out.println("You don't have that in your inventory, so you attack with your hands");
                    }
                }
                commandKnown = false;
            }

            if (inputCommand.equals("speed")) {
                System.out.println(speed);
                commandKnown = false;
            }

            if (inputCommand.equals(UI.Commands.WAIT.getStrCommand())) {
                commandKnown = false;
                speed -= speed;
            }

            // if command is not known
            if (commandKnown && inputCommand.equals("exit") == false) {
                System.out.println("Sorry I don't know what you wanted.");
            }

            // reset turn
            if (speed <= 0) {
                // System.out.println("Enemies do things now");
                Updates.update(player, floor1);
                speed = player.getSpeed();
                endTurn = false;
            } else {
                Updates.actionUpdate(floor1);
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

        } while (inputCommand.equals(UI.Commands.EXIT.getStrCommand()) == false || player.getHealth() >= 0);
    }
}