import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 * This class defines the behaviour of the AgentTest class to test the ChatBot.
 * 
 * @author Dennis Kaydalov
 * 
 * @version November 8, 2022
 */
public class AgentTest {
    /**
     * Main method to test the ChatBot class
     * 
     * @param args - Command line arguments
     */
    public static void main(String[] args) {
        ChatBot chatBot = new ChatBot();
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.print(chatBot.getUserName() + ": ");
            String input = scanner.nextLine();
            String response = chatBot.respondToUserQuery(input);
            System.out.print(chatBot.getChatBotName() + ": ");
            if(response.contains("BYE;")) { 
                System.out.println(response.substring(4));
                System.exit(0);
            } 
            System.out.println(response);
        }
    }
}
