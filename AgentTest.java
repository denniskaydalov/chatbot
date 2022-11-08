import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class AgentTest {
    public static void main(String[] args) {
        ChatBot chatBot = new ChatBot();
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.print(chatBot.getUserName() + ": ");
            String input = scanner.nextLine();
            String response = chatBot.respondToUserQuery(input);
            if(response.equals("")) {
                continue;
            }
            else {
                System.out.print(chatBot.getChatBotName() + ": ");
                System.out.println(response);
            }
        }
    }
}
