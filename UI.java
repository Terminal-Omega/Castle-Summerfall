public class UI {
    enum Commands {
        INSPECT("inspect"),
        TAKE("take"),
        LOOK_AROUND("look around"),
        DROP("drop"),
        MOVE("move"),
        ATTACK("attack"),
        DRINK("drink"),
        CAST("cast"),
        HELP("help"),
        EXIT("exit"),
        INVENTORY("inventory"),
        CLEAR("clear"),
        HEALTH("health");

        private String strCommand;

        private Commands(String command) {
            this.strCommand = command;
        }

        public String getStrCommand() {
            return this.strCommand;
        }
        }

    
    public static void helpCommand(String command){
        if (command.equals("all")){
            System.out.println("\nThis is a text based adventure game where you fight monsters. How many floors can you decesend?\n");
            System.out.println("Commands:");
            for (Commands name : Commands.values()){
                System.out.println("    " + name.getStrCommand() + ",");
            }
            System.out.println("Use help (command name) to learn about that command\n");
        } else if (command.equals(Commands.DROP.getStrCommand())){
            System.out.println("    This will drop an item from your inventory to the ground\n    Use: drop (item name)");
        } else if (command.equals(Commands.LOOK_AROUND.getStrCommand())) {
            System.out.println("    This will show the room description again\n    Use: look around");
        } else if (command.equals(Commands.INVENTORY.getStrCommand())) {
            System.out.println("    This will show you the inventory of your player\n    Use: inventory");
        } else if (command.equals(Commands.CLEAR.getStrCommand())) {
            System.out.println("    This will clear the output of the console and bring your cursor to the top\n    Use: clear");
        } else if (command.equals(Commands.TAKE.getStrCommand())) {
            System.out.println("    This will make your character pick up an object in the room or chest and it will go to inventory\n    Use: take (object)");
        } else if (command.equals(Commands.INSPECT.getStrCommand())) {
            System.out.println("    This will display an object in the rooms description\n    Use: inspect (object)");
        } else if (command.equals(Commands.EXIT.getStrCommand())) {
            System.out.println("    This will exit the game\n    Use: exit");
        } else if (command.equals(Commands.MOVE.getStrCommand())) {
            System.out.println("    This will move your character in a direction if possible\n    Directions (North, north, N, n) / (South, south, S, s) etc..\n    Use: move (direction)");
        } else if (command.equals(Commands.DROP.getStrCommand())) {
            System.out.println("    This will drop and item from your inventory\n    Use: drop (item)");
        } else if (command.equals(Commands.ATTACK.getStrCommand())) {
            System.out.println("    This will attack an Actor in the room with a spesificed weapon in your inventory\n    Use: attack (Actor) with (weapon)");
        }
    }

    public static void move(String command, Player player, Floor floor1, int floorSize){

        if (command.equals("N") || command.equals("n")) {
            if (player.getYCoord() < floorSize - 1 && player.getYCoord() >= 0) {
                player.setYCoord(player.getYCoord() + 1);
               // System.out.println(floor1.getRoom(player.getXCoord(), player.getYCoord()).getDescription());
               System.out.println("You walked north");
            }else{
                System.out.println("You don't see a door in that wall. You can't move that way.");
            }
        }
        if (command.equals("S") || command.equals("s")) {
            if (player.getYCoord() < floorSize - 1 && player.getYCoord() > 0) {
                player.setYCoord(player.getYCoord() - 1);
               // System.out.println(floor1.getRoom(player.getXCoord(), player.getYCoord()).getDescription());
               System.out.println("You walked south");
            } else {
                System.out.println("You don't see a door in that wall. You can't move that way.");
            }
        }
        if (command.equals("E") || command.equals("e")) {
            if (player.getXCoord() < floorSize - 1 && player.getXCoord() > 0) {
                player.setXCoord(player.getXCoord() - 1);
               // System.out.println(floor1.getRoom(player.getXCoord(), player.getYCoord()).getDescription());
               System.out.println("You walked east");
            } else {
                System.out.println("You don't see a door in that wall. You can't move that way.");
            }
        }
        if (command.equals("W") || command.equals("w")) {
            if (player.getXCoord() < floorSize - 1 && player.getXCoord() >= 0) {
                player.setXCoord(player.getXCoord() + 1);
                //System.out.println(floor1.getRoom(player.getXCoord(), player.getYCoord()).getDescription());
                System.out.println("You walked west");
            } else {
                System.out.println("You don't see a door in that wall. You can't move that way.");
            }
            
        }
    }

    public static void displayHeath(int health){
       System.out.print("[");
       for (int i = 0; i<health; i++){
           System.out.print("-");
       }
       System.out.println("]: " + health);
    }

    public static void displayOpening(){
        System.out.println("  _____          _   _         _____                                      __      _ _ ");
        System.out.println(" / ____|        | | | |       / ____|                                    / _|    | | |");
        System.out.println("| |     __ _ ___| |_| | ___  | (___  _   _ _ __ ___  _ __ ___   ___ _ __| |_ __ _| | |");
        System.out.println("| |    / _` / __| __| |/ _ \\  \\___ \\| | | | '_ ` _ \\| '_ ` _ \\ / _ \\ '__|  _/ _` | | |");
        System.out.println("| |___| (_| \\__ \\ |_| |  __/  ____) | |_| | | | | | | | | | | |  __/ |  | || (_| | | |");
        System.out.println(" \\_____\\__,_|___/\\__|_|\\___| |_____/ \\__,_|_| |_| |_|_| |_| |_|\\___|_|  |_| \\__,_|_|_|");

    }
}
