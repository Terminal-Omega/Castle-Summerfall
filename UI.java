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
        EXIT("exit");

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
        }
    }
}
