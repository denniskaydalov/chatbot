/**
 * This class defines the behaviour of the ResponseData class, which is used for better identification for responses from the Parser.
 * 
 * @author Dennis Kaydalov
 * 
 * @version November 8, 2022
 */
public class ResponseData {
    public ResponseType responseType;
    public String response;
    public String literal;
    public String identifier;

    /**
     * Parameterized constructor to initialize the ResponseData properties.
     * 
     * @param responseType The responseType of this ResponseData instance
     * @param response The String response of this ResponseData instance
     * @param identifier The String identifier of this ResponseData instance, used as the key for the ChatBot value maps
     * @param literal The string literal of this ResponseData instance, used as the value for the ChatBot value maps
     * @see ChatBot
     * @see Parser
     * @see ResponseType
     */
    public ResponseData(ResponseType responseType, String response, String identifier, String literal) {
        this.responseType = responseType;
        this.response = response;
        this.identifier = identifier;
        this.literal = literal;
    }

    /**
     * Parameterized constructor to initialize the ResponseData properties without an identifier nor a literal.
     * 
     * @param responseType The responseType of this ResponseData instance
     * @param response The String response of this ResponseData instance
     * @see ChatBot
     * @see Parser
     * @see ResponseType
     */
    public ResponseData(ResponseType responseType, String response) {
        this(responseType, response, "", "");
    }

    /**
     * Parameterized constructor to initialize the ResponseData properties without a response nor an identifier nor a literal .
     * 
     * @param responseType The responseType of this ResponseData instance
     * @see ChatBot
     * @see Parser
     * @see ResponseType
     */
    public ResponseData(ResponseType responseType) {
        this(responseType, "");
    }
}
