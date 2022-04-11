package game;
import java.util.ArrayList;
import java.util.Hashtable;

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
            System.out.println("Use help to get back to this screen\nUse help (command name) to learn about that command\nYou can alse use (help how to play) for a description how to play\n");
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
            System.out.println("\tThis will attack an Actor in the room with a spesificed weapon in your inventory\n\tUse: attack [Actor] with [weapon]");
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
            System.out.println("In this game you have a set amount of time to preform your action. This is called energy.\nDifferent actions take a different amount of engergy. If you run out of energy the enemy can attack you.\nYou can use the speed command to see how much speed you have left to spend.");
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
            if (y < (floorSize - 2) && y >= 0) {
                if (floor1.getDoor(x, y, Direction.NORTH).isOpen()){
                    player.setYCoord(y + 1);
                    System.out.println(floor1.getDescription(player.getXCoord(), player.getYCoord()));
                }
            }else{
                System.out.println("You don't see a door in that wall. You can't move that way.");
            }
        }
        //move south
        if (command.equals("S") || command.equals("s")) {
            if (y < floorSize - 1 && y > 0) {
                if (floor1.getDoor(x, y, Direction.SOUTH).isOpen()){
                    player.setYCoord(y - 1);
                }
                System.out.println(floor1.getDescription(player.getXCoord(), player.getYCoord()));
            } else {
                System.out.println("You don't see a door in that wall. You can't move that way.");
            }
        }
        //move east
        if (command.equals("E") || command.equals("e")) {
            if (x < floorSize - 1 && x >= 0) {
                if (floor1.getDoor(x, y, Direction.EAST).isOpen()){
                    player.setXCoord(x + 1);
                    System.out.println(floor1.getDescription(player.getXCoord(), player.getYCoord()));
                }
            } else {
                System.out.println("You don't see a door in that wall. You can't move that way.");
            }
        }
        //move west
        if (command.equals("W") || command.equals("w")) {
            if (x <= floorSize - 1 && x > 0) {
                if (floor1.getDoor(x, y, Direction.WEST).isOpen()){
                    player.setXCoord(x - 1);
                    System.out.println(floor1.getDescription(player.getXCoord(), player.getYCoord()));
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

        double percent = (double) health / maxHealth;

        if (percent <= 0.3){
            output = colorString(output, Colors.RED);
        }else if (percent <= 0.6 && percent > 0.3) {
            output = colorString(output, Colors.YELLOW);
        } else {
            output = colorString(output, Colors.GREEN);
        }
        System.out.println(output);
    }

    
    /** 
     * This will display the inventory of an Actor, and some stats
     * @param inventory
     * @param health
     */
    public static void displayInventory(ArrayList<Interactable> inventory, int health, int maxHealth){
        String inventoryOutput = "";
        int weight = 0;
        for (Interactable name : inventory) {
            inventoryOutput += "\n\t" + name.getName() + ": Weight: " + name.weight + ", Size: " + name.size + ",";
            weight += name.weight;
        }
        if (inventoryOutput.equals("")) {
            System.out.println("You don't have anything on you");
        } else {
            inventoryOutput += "\n\n\tTotal Weight: " + weight;
            System.out.println(inventoryOutput);
        }
        System.out.println();
        displayHeath(health, maxHealth);
    }

    public static void displayOpening(){
        System.out.println("\n\nYour breath comes heavy as you break your way out of the haunted forest\nYour goal lies on ahead. A forboding dark castle made of black stone rises like a mountain in front of you.");
        System.out.println("You start running along the rough path to the castle. You have to reach the castle, your goal lies inside.\nNobody knows how man flooors the castle actullay has, each of them gaurded impossible guards going progressivly deeper");
        System.out.println("But what ever it takes you will reach your goal. The dark wizard of the castle has captured your family because you refused to bow to him.\nAnd now he was going to pay.\n\n");
        System.out.printf("%50s", "WELCOME TO\n\n\n");

        System.out.println("  _____          _   _         _____                                      __      _ _ ");
        System.out.println(" / ____|        | | | |       / ____|                                    / _|    | | |");
        System.out.println("| |     __ _ ___| |_| | ___  | (___  _   _ _ __ ___  _ __ ___   ___ _ __| |_ __ _| | |");
        System.out.println("| |    / _` / __| __| |/ _ \\  \\___ \\| | | | '_ ` _ \\| '_ ` _ \\ / _ \\ '__|  _/ _` | | |");
        System.out.println("| |___| (_| \\__ \\ |_| |  __/  ____) | |_| | | | | | | | | | | |  __/ |  | || (_| | | |");
        System.out.println(" \\_____\\__,_|___/\\__|_|\\___| |_____/ \\__,_|_| |_| |_|_| |_| |_|\\___|_|  |_| \\__,_|_|_|");

    }

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
        int xP = ySize - player.getYCoord() - 1;
        int yP = player.getXCoord();
        String bookmarkMap = "";

        for (int i = 0; i<xSize; i++){
            for (int k = 0; k<ySize * 2; k++){
                if (k % 2 == 0){
                    String[] bookmark = floor.getRoom(i, k/2).getBookmarks();
                    if (!bookmark[0].equals("")){
                        bookmarkMap = "_" + bookmark[0] + "_";
                    }
                    
                    
                    if (xP == i && yP == k/2){
                        System.out.print("_*_");
                    }else if (!bookmarkMap.equals("")){
                        System.out.print(bookmarkMap);
                    }else{
                        System.out.print("___");
                    }
                }else{
                    System.out.print("|");
                }
            }
            System.out.println();
        }
    }

    /**
     * This will display the energy of the player and display what they could do with their left over energy
     * @param energy
     */

    public static void displayEnergy(int energy){
        System.out.println("\tYou could do these instead");
        for (Commands command : Commands.values()){
            if (command.getSpeedCommand() <= energy){
                System.out.println("\t" + command.getStrCommand() + ",");
            }
        }
        System.out.println(colorString("You could also rest to reset your energy", Colors.YELLOW));
    }

    public static String colorString(String string, Colors color){
        return color.getAnsii() + string + "\u001B[0m";
    }
}
