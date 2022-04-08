package game;
import java.util.ArrayList;

public class UI {
    enum Commands {
        INSPECT("inspect", 3),
        TAKE("take", 4),
        LOOK_AROUND("look around", 2),
        DROP("drop", 4),
        MOVE("move", 5),
        ATTACK("attack", 3), //TODO: this should maybe change
        //DRINK("drink"),
        //CAST("cast"),
        HELP("help", 0),
        EXIT("exit", 0),
        INVENTORY("inventory", 2),
        CLEAR("clear", 0),
        USE("use", 3),
        BOOKMARK("bookmark", 0),
        SPEED("speed", 0),
        WAIT("wait", 0),
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

    
    
    /** 
     * @param command
     */
    public static void helpCommand(String command){
        if (command.equals("all")){
            System.out.println("\nThis is a text based adventure game where you fight monsters. How many floors can you decesend?\n");
            System.out.println("Commands:");
            for (Commands name : Commands.values()){
                System.out.printf("\t%-15s %s %d\n", name.getStrCommand() + ",", "Speed Cost:", name.getSpeedCommand());
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
            System.out.println("\tThis will make your character pick up an object in the room or chest and it will go to inventory\n\tUse: take (object)");
        } else if (command.equals(Commands.INSPECT.getStrCommand())) {
            System.out.println("\tThis will display an object in the rooms description\n\tUse: inspect (object)");
        } else if (command.equals(Commands.EXIT.getStrCommand())) {
            System.out.println("\tThis will exit the game\n\tUse: exit");
        } else if (command.equals(Commands.MOVE.getStrCommand())) {
            System.out.println("\tThis will move your character in a direction if possible\n\tDirections (North, north, N, n) / (South, south, S, s) etc..\n\tUse: move (direction)");
        } else if (command.equals(Commands.ATTACK.getStrCommand())) {
            System.out.println("\tThis will attack an Actor in the room with a spesificed weapon in your inventory\n\tUse: attack (Actor) with (weapon)");
        } else if (command.equals(Commands.CLEAR.getStrCommand())) {
            System.out.println("\tThis will clear the output of the console and bring your cursor to the top\n\tUse: (clear)");
        } else if (command.equals(Commands.HEALTH.getStrCommand())) {
            System.out.println("\tThis will display your health in a status bar\n\tUse: health");
        } else if (command.equals(Commands.USE.getStrCommand())) {
            System.out.println("\tThis will use a special item in your inventory Ex: a map (>use map)\n\tUse: use (item)");
        } else if (command.equals(Commands.BOOKMARK.getStrCommand())) {
            //System.out.println("\tThis will bookmark a room on your map (where you are currently) and save a description of the bookmark\n\tUse: bookmark (visual char) (description) / Use: bookmark remove (visual char)");
            System.out.println("\tSorry this isn't currently implemented");
        } else if (command.equals(Commands.SPEED.getStrCommand())){
            System.out.println("\tThis will display how much speed you have left for your turn\n\tUse: speed");
        } else if (command.equals("how to play")){
            System.out.println("In this game you have a set amount of time to preform your action. This is called speed.\nDifferent actions take a different amount of speed. If you run out of speed the enemy can attack you.\nYou can use the speed command to see how much speed you have left to spend.");
        } else if (command.equals(Commands.WAIT.getStrCommand())){
            System.out.println("This will wait out the rest of your turn bringing your speed down to 0");
        } else {
            System.out.println("\tSorry I don't know what command you wanted");
        }
    }

    
    /** 
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
     * @param health
     */
    public static void displayHeath(int health, int maxHealth){
       System.out.print("Health: [");
       for (int i = 0; i<maxHealth; i++){
           if (i <= health) {
               System.out.print("-");
            } else { 
                System.out.print(" ");
            }
       }
       System.out.println("]: " + health);
    }

    
    /** 
     * @param inventory
     * @param health
     */
    public static void displayInventory(ArrayList<Interactable> inventory, int health, int maxHealth){
        String inventoryOutput = "";
        for (Interactable name : inventory) {
            inventoryOutput += name.getName() + ", ";
        }
        if (inventoryOutput.equals("")) {
            System.out.println("You don't have anything on you");
        } else {
            System.out.println(inventoryOutput);
        }
        displayHeath(health, maxHealth);
    }

    public static void displayOpening(){
        System.out.println("  _____          _   _         _____                                      __      _ _ ");
        System.out.println(" / ____|        | | | |       / ____|                                    / _|    | | |");
        System.out.println("| |     __ _ ___| |_| | ___  | (___  _   _ _ __ ___  _ __ ___   ___ _ __| |_ __ _| | |");
        System.out.println("| |    / _` / __| __| |/ _ \\  \\___ \\| | | | '_ ` _ \\| '_ ` _ \\ / _ \\ '__|  _/ _` | | |");
        System.out.println("| |___| (_| \\__ \\ |_| |  __/  ____) | |_| | | | | | | | | | | |  __/ |  | || (_| | | |");
        System.out.println(" \\_____\\__,_|___/\\__|_|\\___| |_____/ \\__,_|_| |_| |_|_| |_| |_|\\___|_|  |_| \\__,_|_|_|");

    }

    
    /** 
     * @param xSize
     * @param ySize
     * @param player
     */
    public static void displayMap(int xSize, int ySize, Player player){
        int xP = ySize - player.getYCoord() -1;
        int yP = player.getXCoord();

        for (int i = 0; i<xSize; i++){
            for (int k = 0; k<ySize * 2; k++){
                if (k % 2 == 0){
                    if (xP == i && yP == k/2){
                        System.out.print("_*_");
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
}