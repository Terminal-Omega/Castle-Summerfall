package game;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * This is the Class where the more expensive big functions in the app go. It also handles some drawing to the screen
 */
public class UI {
    enum Commands {
        LOOK_AROUND("look around", 2),
        INSPECT("inspect", 0),
        TAKE("take", 4),
        DROP("drop", 4),
        MOVE("move", 5),
        ATTACK("attack", 3),
        //DRINK("drink"),
        //CAST("cast"),
        HELP("help", 0),
        EXIT("exit", 0),
        INVENTORY("inventory", 2),
        CLEAR("clear", 0),
        USE("use", 3),
        BOOKMARK("bookmark", 0),
        ENERGY("energy", 0),
        REST("rest", 0),
        HEALTH("health", 0);

        private String strCommand;
        private int speed;

        private Commands(String command, int speed) {
            this.strCommand = command;
            this.speed = speed;
        }

        public String getStrCommand() {
            return this.strCommand;
        }
        public int getSpeedCommand(){
            return this.speed;
        }
    }
    enum Colors {
        WHITE("\u001B[37m"),
        BLUE("\u001B[36m"),
        YELLOW("\u001B[33m"),
        GREEN("\u001B[32m"),
        RED("\u001B[31m");

        private String color;

        public String getAnsii(){
            return this.color;
        }

        private Colors(String string){
            this.color = string;
        }
    }
    
    
    /** 
     * This will display how to use a command, given the command name. Or display all the commands
     * @param command
     */
    public static void helpCommand(String command){
        if (command.equals("all")){
           // System.out.println("\nThis is a text based adventure game where you fight monsters. How many floors can you decesend?\n");
            System.out.println("\nCommands:");
            for (Commands name : Commands.values()){
                System.out.printf("\t%-15s %s %d\n", name.getStrCommand() + ",", "Energy Cost:", name.getSpeedCommand());
            }
            System.out.println(colorString("Use help to get back to this screen\nUse help [command name] to learn about that command", Colors.RED) +"\nYou can alse use [help how to play] for a description how to play\n");
        } else if (command.equals(Commands.DROP.getStrCommand())){
            System.out.println("\tThis will drop an item from your inventory to the ground\n\tUse: drop (item name)");
        } else if (command.equals(Commands.LOOK_AROUND.getStrCommand())) {
            System.out.println("\tThis will show the room description again\n\tUse: look around");
        } else if (command.equals(Commands.INVENTORY.getStrCommand())) {
            System.out.println("\tThis will show you the inventory of your player\n\tUse: inventory");
        } else if (command.equals(Commands.CLEAR.getStrCommand())) {
            System.out.println("\tThis will clear the output of the console and bring your cursor to the top\n\tUse: clear");
        } else if (command.equals(Commands.TAKE.getStrCommand())) {
            System.out.println("\tThis will make your character pick up an object in the room or chest and it will go to inventory\n\tUse: take [object]");
        } else if (command.equals(Commands.INSPECT.getStrCommand())) {
            System.out.println("\tThis will display an object in the rooms description\n\tUse: inspect [object]");
        } else if (command.equals(Commands.EXIT.getStrCommand())) {
            System.out.println("\tThis will exit the game\n\tUse: exit");
        } else if (command.equals(Commands.MOVE.getStrCommand())) {
            System.out.println("\tThis will move your character in a direction if possible\n\tDirections (North, north, N, n) / (South, south, S, s) etc..\n\tUse: move [direction]");
        } else if (command.equals(Commands.ATTACK.getStrCommand())) {
            System.out.println("\tThis will attack an Actor in the room with a specified weapon in your inventory\n\tUse: attack [Actor] with [weapon]");
        } else if (command.equals(Commands.CLEAR.getStrCommand())) {
            System.out.println("\tThis will clear the output of the console and bring your cursor to the top\n\tUse: clear");
        } else if (command.equals(Commands.HEALTH.getStrCommand())) {
            System.out.println("\tThis will display your health in a status bar\n\tUse: health");
        } else if (command.equals(Commands.USE.getStrCommand())) {
            System.out.println("\tThis will use a special item in your inventory Ex: a map (>use map)\n\tUse: use [item]");
        } else if (command.equals(Commands.BOOKMARK.getStrCommand())) {
            System.out.println("\tThis will bookmark a room on your map (where you are currently) and save a description of the bookmark\n\tUse: bookmark [visual character] : [description] / Use: bookmark remove [visual char]\nEx. bookmark a : where the stairs are");
            //System.out.println("\tSorry this isn't currently implemented");
        } else if (command.equals(Commands.ENERGY.getStrCommand())){
            System.out.println("\tThis will display how much energy you have left for your turn\n\tUse: energy");
        } else if (command.equals("how to play")){
            System.out.println("The goal of the game is to save your family at the lowest level of the castle. In order to to that you need to find the stairs on each level in order to decend.\nDefeating whatever bosses you meet along the way. There are several things you can do inorder to help yourself beat the bosses and decend the floor. \nYou can look around the floor moving through rooms to find weapons that do more damage. You can also find spells and magic items to boost your stats for a certain amount of time to help beat the boss. \nThe floors are randomly generated, so you won't the get the same experience twice.");
        } else if (command.equals(Commands.REST.getStrCommand())){
            System.out.println("This will make your character rest bringing there energy back to full. But it will make it so enemies can attack you.");
        } else {
            System.out.println("\tSorry I don't know what command you wanted");
        }
    }

    
    /** 
     * This will move the player, first checking for doors and walls
     * @param command
     * @param player
     * @param floor1
     * @param floorSize
     */
    public static void move(String command, Player player, Floor floor1, int floorSize){
        int x = player.getXCoord();
        int y = player.getYCoord();

        // move north
        if (command.equals("N") || command.equals("n")) {
            if (y < (floorSize - 1) && y >= 0) {
                try {
                    if (floor1.getDoor(x, y, Direction.NORTH).isOpen()){ //Sorry Thomas, had to fix the doors
                        player.setYCoord(y + 1);
                        System.out.println(floor1.getDescription(player.getXCoord(), player.getYCoord()));
                    }
                } catch (ThingNotFoundException e) {
                    System.out.println("You don't see a door in that wall. You can't move that way.");
                }
            }else{
                System.out.println("You don't see a door in that wall. You can't move that way.");
            }
        }
        //move south
        if (command.equals("S") || command.equals("s")) {
            if (y <= (floorSize - 1) && y > 0) {
                try {
                    if (floor1.getDoor(x, y, Direction.SOUTH).isOpen()){
                        player.setYCoord(y - 1);
                    }
                } catch (ThingNotFoundException e) {
                    System.out.println("You don't see a door in that wall. You can't move that way.");
                }
                System.out.println(floor1.getDescription(player.getXCoord(), player.getYCoord()));
            } else {
                System.out.println("You don't see a door in that wall. You can't move that way.");
            }
        }
        //move east
        if (command.equals("E") || command.equals("e")) {
            if (x < (floorSize - 1) && x >= 0) {
                try {
                    if (floor1.getDoor(x, y, Direction.EAST).isOpen()){
                        player.setXCoord(x + 1);
                        System.out.println(floor1.getDescription(player.getXCoord(), player.getYCoord()));
                    }
                } catch (ThingNotFoundException e) {
                    System.out.println("You don't see a door in that wall. You can't move that way.");
                }
            } else {
                System.out.println("You don't see a door in that wall. You can't move that way.");
            }
        }
        //move west
        if (command.equals("W") || command.equals("w")) {
            if (x <= floorSize - 1 && x > 0) {
                try {
                    if (floor1.getDoor(x, y, Direction.WEST).isOpen()){
                        player.setXCoord(x - 1);
                        System.out.println(floor1.getDescription(player.getXCoord(), player.getYCoord()));
                    }
                } catch (ThingNotFoundException e) {
                    System.out.println("You don't see a door in that wall. You can't move that way.");
                }
            } else {
                System.out.println("You don't see a door in that wall. You can't move that way.");
            }
            
        }
    }

    
    /** 
     * This will display the health of an Actor
     * @param health
     */
    public static void displayHeath(int health, int maxHealth){
        String output = "\tHealth: [";
        for (int i = 0; i < maxHealth; i++){
            if (i <= health){
                output += "-";
            }else{
                output += " ";
            }
        }
        output += "] " + health;

        /*This doesn't work on windows so comment it out
        double percent = (double) health / maxHealth;

        if (percent <= 0.3){
            output = colorString(output, Colors.RED);
        }else if (percent <= 0.6 && percent > 0.3) {
            output = colorString(output, Colors.YELLOW);
        } else {
            output = colorString(output, Colors.GREEN);
        }
        */
        System.out.println(output);
        
    }

    
    /** 
     * This will display the inventory of an Actor, and some stats
     * @param inventory
     * @param health
     */
    public static void displayInventory(ArrayList<Interactable> inventory, int health, int maxHealth, int maxWeight){
        String inventoryOutput = "";
        int weight = 0;
        System.out.println();
        for (Interactable inter : inventory) {
            if (inter instanceof Weapon){
                Weapon weapon = (Weapon) inter;
                inventoryOutput += String.format("\t%-10s: Weight: %-2d / Pierce: %-2d / Damage: %-2d\n", weapon.getName(), weapon.weight, weapon.getPierce(), weapon.getDamage());
            }else{
                inventoryOutput += String.format("\t%-10s: Weight: %-2d\n", inter.getName(), inter.weight);
            }
            weight += inter.weight;
        }
        if (inventoryOutput.equals("")) {
            System.out.println("You don't have anything on you");
        } else {
            inventoryOutput += "\n\n\tTotal Weight: " + weight + "\n\tMax Weight: " + maxWeight;
            System.out.println(inventoryOutput);
        }
        System.out.println();
        displayHeath(health, maxHealth);
        System.out.println("\n");
    }
    /**
     * This is displays the opening sequence of the game
     */
    public static void displayOpening(){
        System.out.println("\n\nYour breath comes heavy as you break your way out of the haunted forest\nYour goal lies on ahead. A forboding dark castle made of black stone rises like a mountain in front of you.");
        System.out.println("You start running along the rough path to the castle. You have to reach the castle, your goal lies inside.\nNobody knows how man floors the castle actually has, each of them guarded impossible guards going progressively deeper");
        System.out.println("But what ever it takes you will reach your goal. The dark wizard of the castle has captured your family because you refused to bow to him.\nAnd now he was going to pay.\n\n");
        System.out.printf("%50s", "WELCOME TO\n\n\n");

        System.out.println("  _____          _   _         _____                                      __      _ _ ");
        System.out.println(" / ____|        | | | |       / ____|                                    / _|    | | |");
        System.out.println("| |     __ _ ___| |_| | ___  | (___  _   _ _ __ ___  _ __ ___   ___ _ __| |_ __ _| | |");
        System.out.println("| |    / _` / __| __| |/ _ \\  \\___ \\| | | | '_ ` _ \\| '_ ` _ \\ / _ \\ '__|  _/ _` | | |");
        System.out.println("| |___| (_| \\__ \\ |_| |  __/  ____) | |_| | | | | | | | | | | |  __/ |  | || (_| | | |");
        System.out.println(" \\_____\\__,_|___/\\__|_|\\___| |_____/ \\__,_|_| |_| |_|_| |_| |_|\\___|_|  |_| \\__,_|_|_|");

    }
    /**
     * This displays the ending died sequence of the game
     */
    public static void displayEnding(){
        System.out.println(" __     __           _____  _          _ _   _______                             _      ___  ");
        System.out.println(" \\ \\   / /          |  __ \\(_)        | | | |__   __|                           (_)    |__ \\ ");
        System.out.println("  \\ \\_/ /__  _   _  | |  | |_  ___  __| | |    | |_ __ _   _    __ _  __ _  __ _ _ _ __   ) |");
        System.out.println("   \\   / _ \\| | | | | |  | | |/ _ \\/ _` | |    | | '__| | | |  / _` |/ _` |/ _` | | '_ \\ / / ");
        System.out.println("    | | (_) | |_| | | |__| | |  __/ (_| |_|    | | |  | |_| | | (_| | (_| | (_| | | | | |_|  ");
        System.out.println("    |_|\\___/ \\__,_| |_____/|_|\\___|\\__,_(_)    |_|_|   \\__, |  \\__,_|\\__, |\\__,_|_|_| |_(_)  ");
        System.out.println("                                                        __/ |         __/ |                  ");
        System.out.println("                                                       |___/         |___/                   ");
        
    }

    
    /** 
     * This will display a map of the floor
     * @param xSize
     * @param ySize
     * @param player
     */
    public static void displayMap(int xSize, int ySize, Player player, Floor floor){
        int xP = player.getXCoord();
        int yP = player.getYCoord();
        String bookmarkDisplay = "\n\n\n";
       // String bookmarkString = "";

        for (int i = ySize - 1; i >= 0; i--){
            for (int j = 0; j < xSize; j++){
                String bookmarkString = "";
                String[] bookmark = floor.getRoom(j, i).getBookmark();
                if (!bookmark[0].equals("")){
                    bookmarkString += "_" + bookmark[0].charAt(0) + "_|";
                    bookmarkDisplay += bookmark[0] + " : " + bookmark[1] + "\n";
                }



                if (i == yP && j == xP){
                    System.out.print("_*_|");
                }else if (!bookmarkString.equals("")){
                    System.out.print(bookmarkString);
                   // bookmarkString = "";
                } else if (floor.getRoom(j, i).isVisited()) {
                    System.out.print("___|");
                }else{
                    System.out.print("    ");
                }

            }
            System.out.println();
        }
        System.out.println(bookmarkDisplay);

        
    }

    /**
     * This will display the energy of the player and display what they could do with their left over energy
     * @param energy
     */

    public static void displayEnergy(int energy){
        System.out.println("\tYou could do these instead");
        for (Commands command : Commands.values()){
            if (command.getSpeedCommand() <= energy && command.getSpeedCommand() != 0){
                System.out.println("\t" + command.getStrCommand() + ",");
            }
        }
        System.out.println("\trest,");
        System.out.println(colorString("You could also rest to reset your energy", Colors.YELLOW));
    }

    
    /** 
     * This, in non windows systems, will display return the string with a set of colors given to display that color
     * @param string
     * @param color
     * @return String
     */
    public static String colorString(String string, Colors color){
        return color.getAnsii() + string + "\u001B[0m";
    }

    
    /** 
     * This will print a header bar showing stats of the player, this is displayed on non windows systems
     * @param health
     * @param maxHealth
     * @param energy
     * @param inventorySize
     */
    public static void printHeader(int health, int maxHealth, int energy, int inventorySize){
        int width = 100;
        System.out.printf("\033[s\033[0;%dH", width);
        for (int i = 0; i < 6; i++){
            System.out.printf("\033[%d;%dH", i, width);
            System.out.print("\033[0K\033[1B");
        }
        System.out.printf("\033[1;%dH", width);
        displayHeath(health, maxHealth);
        System.out.printf("\033[2;%dH", width);
        System.out.println("\tEnergy left: " + energy);
        System.out.printf("\033[3;%dH", width);
        System.out.println("\tItems in Inventory: " + inventorySize);
        System.out.print("\033[u");
    }
}
