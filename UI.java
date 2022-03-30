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
        CLEAR("clear");

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
                System.out.println("    " + name + ",");
            }
            System.out.println("Use help (command name) to learn about that command\n");
        } else if (command.equals(Commands.DROP.getStrCommand())){
            System.out.println("    This will drop an item from your inventory to the ground\n    Use: drop (item name)");
        } else if (command.equals(Commands.LOOK_AROUND.getStrCommand())) {
            System.out.println("    This will show the room description again\n    Use: look around");
        } else if (command.equals(Commands.INVENTORY.getStrCommand())) {
            System.out.println("    This will show you the inventory of you rplayer\n    Use: inventory");
        } else if (command.equals(Commands.CLEAR.getStrCommand())) {
            System.out.println("    This will clear the output of the console and bring your cursor to the top\n    Use: clear");
        } else if (command.equals(Commands.TAKE.getStrCommand())) {
            System.out.println("    This will make your character pick up an object in the room and it will go to inventory\n    Use: take (object)");
        } else if (command.equals(Commands.INSPECT.getStrCommand())) {
            System.out.println("    This will display an object in the rooms description\n    Use: inspect (object)");
        } else if (command.equals(Commands.EXIT.getStrCommand())) {
            System.out.println("    This will exit the game\n    Use: inspect");
        } else if (command.equals(Commands.MOVE.getStrCommand())) {
            System.out.println("    This will move your character in a direction if possible\n    Directions (North, north, N) / (South, south, S) etc..\n    Use: move (direction)");
        } else if (command.equals(Commands.DROP.getStrCommand())) {
            System.out.println("    This will drop and item from your inventory\n    Use: drop (item)");
        } else if (command.equals(Commands.ATTACK.getStrCommand())) {
            System.out.println("    This will attack an Actor in the room with a spesificed weapon in your inventory\n    Use: attack (Actor) with (weapon)");
        }
    }
}
