package CastleSummerfall;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.io.File;
import java.io.FileReader;

/**
 * This is the Class where the more expensive big functions in the app go. It
 * also handles some drawing to the screen
 */
public class UI {
    enum Commands {
        // TODO: add support for aliasing somehow
        LOOK_AROUND("look around", 2, "\tThis will show the room description again\n\tUse: look around"),
        INSPECT("inspect", 0, "\tThis will display an object in the rooms description\n\tUse: inspect [object]"),
        TAKE("take", 4,
                "\tThis will make your character pick up an object in the room or chest and it will go to inventory\n\tUse: take [object]"),
        DROP("drop", 4, "\tThis will drop an item from your inventory to the ground\n\tUse: drop (item name)"),
        MOVE("move", 5,
                "\tThis will move your character in a direction if possible\n\tDirections (North, north, N, n) / (South, south, S, s) etc..\n\tUse: move [direction]"),
        ATTACK("attack", 3,
                "\tThis will attack an Actor in the room with a specified weapon in your inventory\n\tUse: attack [Actor] with [weapon]"),
        // DRINK("drink"),
        // CAST("cast"),
        HELP("help", 0, "haha help go brrrr"), EXIT("exit", 0, "\tThis will exit the game\n\tUse: exit"),
        INVENTORY("inventory", 2, "\tThis will show you the inventory of your player\n\tUse: inventory"),
        CLEAR("clear", 0, "\tThis will clear the output of the console and bring your cursor to the top\n\tUse: clear"),
        USE("use", 3, "\tThis will use a special item in your inventory Ex: a map (>use map)\n\tUse: use [item]"),
        BOOKMARK("bookmark", 0,
                "\tThis will bookmark a room on your map (where you are currently) and save a description of the bookmark\n\tUse: bookmark [visual character] : [description] / Use: bookmark remove [visual char]\nEx. bookmark a : where the stairs are"),
        ENERGY("energy", 0, "\tThis will display how much energy you have left for your turn\n\tUse: energy"),
        REST("rest", 0,
                "This will make your character rest bringing there energy back to full. But it will make it so enemies can attack you."),
        HEALTH("health", 0, "\tThis will display your health in a status bar\n\tUse: health");

        private String strCommand;
        private int speed;
        private String help;

        private Commands(String command, int speed, String help) {
            this.strCommand = command;
            this.speed = speed;
            this.help = help;
        }

        public String getStrCommand() {
            return this.strCommand;
        }

        public int getSpeedCommand() {
            return this.speed;
        }

        public String getHelpMessage() {
            return this.help;
        }
    }

    enum Colors {
        WHITE("\u001B[37m"), BLUE("\u001B[36m"), YELLOW("\u001B[33m"), GREEN("\u001B[32m"), RED("\u001B[31m");

        private String color;

        public String getAnsii() {
            return this.color;
        }

        private Colors(String string) {
            this.color = string;
        }
    }

    /**
     * This will display how to use a command, given the command name. Or display
     * all the commands
     *
     * @param command
     */
    public static void helpCommand(String command) {
        if (command.equals("all")) {
            // System.out.println("\nThis is a text based adventure game where you
            // fight monsters. How many floors can you descend?\n");
            System.out.println("\nCommands:");
            for (Commands name : Commands.values()) {
                System.out.printf("\t%-15s %s %d\n", name.getStrCommand() + ",", "Energy Cost:",
                        name.getSpeedCommand());
            }
            System.out.println(colorString(
                    "Use help to get back to this screen\nUse help [command name] to learn about that command",
                    Colors.RED) + "\nYou can also use [help how to play] for a description how to play\n");
        } else if (Arrays.asList(Commands.values()).stream().map(n -> n.getStrCommand()).collect(Collectors.toList())
                .contains(command)) {
            System.out.println(Arrays.asList(Commands.values()).stream().filter(n -> command.equals(n.getStrCommand()))
                    .collect(Collectors.toList()).get(0).getHelpMessage());
        } else {
            System.out.println("\tSorry I don't know what command you wanted");
        }
    }

    /**
     * This will move the player, first checking for doors and walls
     *
     * @param command
     * @param player
     * @param floor1
     * @param floorSize
     */
    public static void move(String command, Player player, Floor floor1, int floorSize) {
        // TODO: make this code less garbage
        int x = player.getXCoord();
        int y = player.getYCoord();

        // move north
        command = command.toLowerCase();
        try {
            if (command.equals("n")) {
                if (y < (floorSize - 1) && y >= 0) {
                    if (floor1.getDoor(x, y, Direction.NORTH).isOpen()) { // Sorry Thomas, had to fix the doors
                        player.setYCoord(y + 1);
                        System.out.println(floor1.getDescription(player.getXCoord(), player.getYCoord()));
                    }
                    return;
                }
            }

            // move south
            else if (command.equals("s")) {
                if (y <= (floorSize - 1) && y > 0) {
                    if (floor1.getDoor(x, y, Direction.SOUTH).isOpen()) {
                        player.setYCoord(y - 1);
                    }
                    System.out.println(floor1.getDescription(player.getXCoord(), player.getYCoord()));
                    return;
                }
            }

            // move east
            else if (command.equals("e")) {
                if (x < (floorSize - 1) && x >= 0) {
                    if (floor1.getDoor(x, y, Direction.EAST).isOpen()) {
                        player.setXCoord(x + 1);
                        System.out.println(floor1.getDescription(player.getXCoord(), player.getYCoord()));
                    }
                    return;
                }
            }

            // move west
            else if (command.equals("w")) {
                if (x <= floorSize - 1 && x > 0) {
                    if (floor1.getDoor(x, y, Direction.WEST).isOpen()) {
                        player.setXCoord(x - 1);
                        System.out.println(floor1.getDescription(player.getXCoord(), player.getYCoord()));
                    }
                    return;
                }
            } else {
                System.out.println("That's not a valid direction");
                return;
            }
        } catch (ThingNotFoundException e) {
        } finally {
            System.out.println("You don't see a door in that wall. You can't move that way");
        }
    }

    /**
     * This will display the health of an Actor
     *
     * @param health
     */
    public static void displayHeath(int health, int maxHealth) {
        String output = "\tHealth: [";
        output += "-".repeat(health) + " ".repeat(maxHealth - health);
        output += "] " + health;

        // This doesn't work on windows so comment it out
        double percent = (double) health / maxHealth;

        if (percent <= 0.3) {
            output = colorString(output, Colors.RED);
        } else if (percent <= 0.6 && percent > 0.3) {
            output = colorString(output, Colors.YELLOW);
        } else {
            output = colorString(output, Colors.GREEN);
        }

        System.out.println(output);
    }

    /**
     * This will display the inventory of an Actor, and some stats
     *
     * @param inventory
     * @param health
     */
    public static void displayInventory(List<Interactable> inventory, int health, int maxHealth, int carryWeight) {
        String inventoryOutput = "";
        int weight = 0;
        System.out.println();
        for (Interactable inter : inventory) {
            if (inter instanceof Weapon) {
                Weapon weapon = (Weapon) inter;
                inventoryOutput += String.format("\t%-10s: Weight: %-2d / Pierce: %-2d / Damage: %-2d\n",
                        weapon.getName(), weapon.weight, weapon.getPierce(), weapon.getDamage());
            } else {
                inventoryOutput += String.format("\t%-10s: Weight: %-2d\n", inter.getName(), inter.weight);
            }
            weight += inter.weight;
        }
        if (inventoryOutput.equals("")) {
            System.out.println("You don't have anything on you");
        } else {
            inventoryOutput += "\n\n\tTotal Weight: " + weight + "\n\tMax Weight: " + carryWeight;
            System.out.println(inventoryOutput);
        }
        System.out.println();
        displayHeath(health, maxHealth);
        System.out.println();
    }

    /**
     * This is displays the opening sequence of the game
     */
    public static void displayOpening() {
        // TODO: make opening sequence a data file, and simply print the file
        System.out.println(
                "\n\nYour breath comes heavy as you break your way out of the haunted forest\nYour goal lies on ahead. A forboding dark castle made of black stone rises like a mountain in front of you.");
        System.out.println(
                "You start running along the rough path to the castle. You have to reach the castle, your goal lies inside.\nNobody knows how man floors the castle actually has, each of them guarded impossible guards going progressively deeper");
        System.out.println(
                "But what ever it takes you will reach your goal. The dark wizard of the castle has captured your family because you refused to bow to him.\nAnd now he was going to pay.\n\n");
        System.out.printf("%50s", "WELCOME TO\n\n\n");

        System.out.println("  _____          _   _         _____                                      __      _ _ ");
        System.out.println(" / ____|        | | | |       / ____|                                    / _|    | | |");
        System.out.println("| |     __ _ ___| |_| | ___  | (___  _   _ _ __ ___  _ __ ___   ___ _ __| |_ __ _| | |");
        System.out.println(
                "| |    / _` / __| __| |/ _ \\  \\___ \\| | | | '_ ` _ \\| '_ ` _ \\ / _ \\ '__|  _/ _` | | |");
        System.out.println("| |___| (_| \\__ \\ |_| |  __/  ____) | |_| | | | | | | | | | | |  __/ |  | || (_| | | |");
        System.out.println(
                " \\_____\\__,_|___/\\__|_|\\___| |_____/ \\__,_|_| |_| |_|_| |_| |_|\\___|_|  |_| \\__,_|_|_|");
    }

    /**
     * This displays the ending died sequence of the game
     */
    public static void displayEnding() {
        System.out.println(
                " __     __           _____  _          _ _   _______                             _      ___  ");
        System.out.println(
                " \\ \\   / /          |  __ \\(_)        | | | |__   __|                           (_)    |__ \\ ");
        System.out.println(
                "  \\ \\_/ /__  _   _  | |  | |_  ___  __| | |    | |_ __ _   _    __ _  __ _  __ _ _ _ __   ) |");
        System.out.println(
                "   \\   / _ \\| | | | | |  | | |/ _ \\/ _` | |    | | '__| | | |  / _` |/ _` |/ _` | | '_ \\ / / ");
        System.out.println(
                "    | | (_) | |_| | | |__| | |  __/ (_| |_|    | | |  | |_| | | (_| | (_| | (_| | | | | |_|  ");
        System.out.println(
                "    |_|\\___/ \\__,_| |_____/|_|\\___|\\__,_(_)    |_|_|   \\__, |  \\__,_|\\__, |\\__,_|_|_| |_(_)  ");
        System.out.println(
                "                                                        __/ |         __/ |                  ");
        System.out.println(
                "                                                       |___/         |___/                   ");
    }

    /**
     * This will display a map of the floor
     *
     * @param xSize
     * @param ySize
     * @param player
     */
    public static void displayMap(int xSize, int ySize, Player player, Floor floor) {
        int xP = player.getXCoord();
        int yP = player.getYCoord();
        String bookmarkDisplay = "\n\n\n";
        // String bookmarkString = "";

        for (int i = ySize - 1; i >= 0; i--) {
            for (int j = 0; j < xSize; j++) {
                String bookmarkString = "";
                String[] bookmark = floor.getRoom(j, i).getBookmark();
                if (!bookmark[0].equals("")) {
                    bookmarkString += "_" + bookmark[0].charAt(0) + "_|";
                    bookmarkDisplay += bookmark[0] + " : " + bookmark[1] + "\n";
                }

                if (i == yP && j == xP) {
                    System.out.print("_*_|");
                } else if (!bookmarkString.equals("")) {
                    System.out.print(bookmarkString);
                    // bookmarkString = "";
                } else if (floor.getRoom(j, i).isVisited()) {
                    System.out.print("___|");
                } else {
                    System.out.print("    ");
                }
            }
            System.out.println();
        }
        System.out.println(bookmarkDisplay);
    }

    /**
     * This will display the energy of the player and display what they could do
     * with their left over energy
     *
     * @param energy
     */

    public static void displayEnergy(int energy) {
        System.out.println("\tYou could do these instead");
        for (Commands command : Commands.values()) {
            if (command.getSpeedCommand() <= energy && command.getSpeedCommand() != 0) {
                System.out.println("\t" + command.getStrCommand() + ",");
            }
        }
        System.out.println("\trest,");
        System.out.println(colorString("You could also rest to reset your energy", Colors.YELLOW));
    }

    /**
     * This, in non windows systems, will display return the string with a set of
     * colors given to display that color
     *
     * @param string
     * @param color
     * @return String
     */
    public static String colorString(String string, Colors color) {
        return color.getAnsii() + string + "\u001B[0m";
    }

    /**
     * This will print a header bar showing stats of the player, this is displayed
     * on non windows systems
     *
     * @param health
     * @param maxHealth
     * @param energy
     * @param inventorySize
     */
    public static void printHeader(int health, int maxHealth, int energy, int inventorySize) {
        int width = 100;
        // This clears the screen before writing it
        System.out.printf("\033[s\033[0;%dH", width);
        for (int i = 0; i < 6; i++) {
            System.out.printf("\033[%d;%dH", i, width);
            System.out.print("\033[0K\033[1B");
        }
        // this writes to the header
        System.out.printf("\033[3;%dH", width);
        displayHeath(health, maxHealth);
        System.out.printf("\033[4;%dH", width);
        System.out.println("\tEnergy left: " + energy);
        System.out.printf("\033[5;%dH", width);
        System.out.println("\tItems in Inventory: " + inventorySize);
        System.out.print("\033[u");
    }
}
