import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * This class defines the behaviour of the ChatBot class, which inherits from VirtualAgent.
 * 
 * @author Dennis Kaydalov
 * 
 * @version November 8, 2022
 */
public class ChatBot extends VirtualAgent { 
    // The userValues map defines all the values that the user has set for themselves, such as age, or name.
    private Map<String, String> userValues = new HashMap<String, String>();
    // The neutralValues map defines all the values that the user has set which have no possesive quality, such as the weather.
    private Map<String, String> neutralValues = new HashMap<String, String>();
    // The hostValues map defines all the values that the user has set for the ChatBot to possess, such as chatBotName, or the chatBotAge.
    private Map<String, String> hostValues = new HashMap<String, String>();

    /**
     * Parameterized constructor to initialize the chatBotName into hostValues, and userName into userValues of a chatBot
     * 
     * @param chatBotName The initial String value for the "name" key in the hostValues map
     * @param userName The initial String value for the "name" key in the userValues map
     */
    public ChatBot(String chatBotName, String userName) {
        super(chatBotName);
        setChatBotName(chatBotName);
        setUserName(userName);
    }

    /**
     * No-argument constructor which calls the parameterized constructor to intialize the ChatBot with "chatbot" as the chatBotName, and "user" as the userName
     */
    public ChatBot() {
        this("chatbot", "user");
    }

    /**
     * Accessor to return the value of the "name" key in hostValues.
     *
     * @return String - the chatBotName String
     */
    public String getChatBotName() {
        // Sanity checking to make sure that hostValues contains the "name" key.
        if(hostValues.containsKey("name")) {
            return hostValues.get("name");
        }
        else {
            return "chatbot";
        }
    }

    /**
     * Accessor to return the value of the "name" key in userValues.
     *
     * @return String - the userName String
     */
    public String getUserName() {
        // Sanity checking to make sure that userValues contains the "name" key.
        if(userValues.containsKey("name")) {
            return userValues.get("name");
        }
        else {
            return "user";
        }
    }

    /**
     * Mutator to set a new value for the "name" key in hostValues
     *
     * @param chatBotName The value to set the chatBotName too
     */
    public void setChatBotName(String chatBotName) {
        hostValues.put("name", chatBotName);
    }

    /**
     * Mutator to set a new value for the "name" key in userValues
     *
     * @param userName The value to set the userName too
     */
    public void setUserName(String userName) {
        userValues.put("name", userName);
    }

    /**
     * This methods accepts a String query, and returns an appropriate String response
     * 
     * @param query The text that the user has entered for the ChatBot to respond to
     * 
     * @return String - The response from the ChatBot
     */
    public String respondToUserQuery(String query) {
        // Trim extra spaces from the user query.
        query = query.trim();
        // Initialize the Lexer with the query for tokenization.
        Lexer lexer = new Lexer(query);
        // Tokenize the query, and put the resulting TokenMatches in a list.
        ArrayList<TokenMatch> matchesList = lexer.tokenize();
        // for(TokenMatch match : matchesList) { match.printFull(); } // TODO: TODO: TODO: TODO: TODO: REMOVE BEFORE SUBMITTING.
        // Initialize the Parser with the list of TokenMatches for parsing.
        Parser parser = new Parser(matchesList);
        // Parse the tokens, and put the response in a ResponseData type variable.
        ResponseData responseData = parser.parseTokens();
        // Trim the response literal and identifier for easier access in the maps.
        responseData.identifier = responseData.identifier.trim();
        responseData.literal = responseData.literal.trim();
        //TO// TODO: TODO: TODO: TODO: TODO: REMOVE BEFORE SUBMITTING.
        //System.out.println("literal: " + responseData.literal + " responseType: " + responseData.responseType + " response: " + responseData.response + " identifier: " + responseData.identifier);
        // Enter modified values in the maps, if any values were modified.
        if(responseData.responseType == ResponseType.USER_VALUE_MODIFIER) {
            userValues.put(responseData.identifier, responseData.literal);
        }
        else if(responseData.responseType == ResponseType.HOST_VALUE_MODIFIER) { 
            hostValues.put(responseData.identifier, responseData.literal);
        }
        else if(responseData.responseType == ResponseType.NEUTRAL_VALUE_MODIFIER) {
            neutralValues.put(responseData.identifier, responseData.literal);
        }
        // Fetch the values from the maps using the identifier as the key.
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
        //TO// TODO: TODO: TODO: TODO: TODO: REMOVE BEFORE SUBMITTING.
        //userValues.forEach((key, value) -> System.out.println("userValue: " + key + " : " + value));
        //neutralValues.forEach((key, value) -> System.out.println("neutralValue: " + key + " : " + value));
        //hostValues.forEach((key, value) -> System.out.println("hostValue: " + key + " : " + value));
        return responseData.response;
    }

    /**
     * This is a standard toString() method.  
     * 
     * @return String - The String representation of the current object.
     */
    public String toString() {
        return super.toString();
    }
}
