import java.util.Scanner;


public class App {

    public static void main(String[] args) {
        UI.helpCommand("all");
        Scanner input = new Scanner(System.in);
        String inputCommand;
        do{
            System.out.print("> ");
            inputCommand = input.nextLine();
        }while(inputCommand.equals(UI.Commands.EXIT.getStrCommand()) == false);
    }
}
