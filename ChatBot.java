import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class ChatBot extends VirtualAgent { 
    //private String chatBotName;
    //private int chatBotAge;
    private Map<String, String> userValues = new HashMap<String, String>();
    private Map<String, String> neutralValues = new HashMap<String, String>();
    private Map<String, String> hostValues = new HashMap<String, String>();

    //public ChatBot(String chatBotName, int age) {
     //   setChatBotName(chatBotName);
      //  setChatBotAge(chatBotAge);
    //}

    //public ChatBot() {
     //   this("chatbot", -1);
    //}

    //public String getChatBotName() {
     //   return chatBotName;
    //}
    public String getChatBotName() {
        if(hostValues.containsKey("name")) {
            return hostValues.get("name");
        }
        else {
            return "chatbot";
        }
    }

    public String getUserName() {
        if(userValues.containsKey("name")) {
            return userValues.get("name");
        }
        else {
            return "user";
        }
    }

    //public void setChatBotName(String chatBotName) {
     //   this.chatBotName = chatBotName;
    //}

    //public int getChatBotAge() {
     //   return chatBotAge;
    //}

    //public void setChatBotAge(int chatBotAge) {
     //   this.chatBotAge = chatBotAge;
    //}

    public String respondToUserQuery(String query) {
        //TODO: make tokenize and parse static methods
        //query = toTitleCase(query.trim());
        query = query.trim();
        Lexer lexer = new Lexer(query);
        ArrayList<TokenMatch> matchesList = lexer.tokenize();
        //for(TokenMatch match : matchesList) { match.printFull(); }
        Parser parser = new Parser(matchesList);
        ResponseData responseData = parser.parseTokens();
        responseData.identifier = responseData.identifier.trim();
        responseData.literal = responseData.literal.trim();
        //System.out.println("literal: " + responseData.literal + " responseType: " + responseData.responseType + " response: " + responseData.response + " identifier: " + responseData.identifier);
        if(responseData.responseType == ResponseType.USER_VALUE_MODIFIER) {
            userValues.put(responseData.identifier, responseData.literal);
        }
        else if(responseData.responseType == ResponseType.HOST_VALUE_MODIFIER) { 
            hostValues.put(responseData.identifier, responseData.literal);
        }
        else if(responseData.responseType == ResponseType.NEUTRAL_VALUE_MODIFIER) {
            neutralValues.put(responseData.identifier, responseData.literal);
        }
        if(responseData.responseType == ResponseType.USER_VALUE_REQUEST) {
            if(userValues.containsKey(responseData.identifier)) {
                return userValues.get(responseData.identifier);
            }
            else {
                return "I don\'t know what your " + responseData.identifier + " is.";
            }
        }
        else if(responseData.responseType == ResponseType.HOST_VALUE_REQUEST) { 
            if(hostValues.containsKey(responseData.identifier)) {
                return hostValues.get(responseData.identifier);
            }
            else {
                return "I don\'t know what my " + responseData.identifier + " is.";
            }
        }
        else if(responseData.responseType == ResponseType.NEUTRAL_VALUE_REQUEST) {
            if(neutralValues.containsKey(responseData.identifier)) {
                return neutralValues.get(responseData.identifier);
            }
            else {
                return "I don\'t know what the " + responseData.identifier + " is.";
            }
        }
        //userValues.forEach((key, value) -> System.out.println("userValue: " + key + " : " + value));
        //neutralValues.forEach((key, value) -> System.out.println("neutralValue: " + key + " : " + value));
        //hostValues.forEach((key, value) -> System.out.println("hostValue: " + key + " : " + value));
        return responseData.response;
    }
}
