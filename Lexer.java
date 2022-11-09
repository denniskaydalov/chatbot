import java.util.ArrayList;
import java.util.List;

/**
 * This enum defines the behaviour of the TokenType enum, which is used to identify tokens in lexical analysis.
 * 
 * @author Dennis Kaydalov
 * 
 * @version November 8, 2022
 */
enum TokenType {
    TERMINATE,
    GREETING,
    POSSESSIVE,
    DECLERATION_IDENTIFIER,
    DECLERATION_MODIFIER,
    REQUEST_INITIALIZER,
    LITERAL;
}

/**
 * This class defines the behaviour of the Lexer class, which is used to tokenize a string into tokens for further processing.
 * @author Dennis Kaydalov
 * 
 * @version November 8, 2022
 */
public class Lexer {
    
    private String query;
    // List of all TokenDefinitions that will are supported.
    private List<TokenDefinition> tokenDefinitions = new ArrayList<TokenDefinition>();
    
    /**
     * Parameterized constructor to initialize the query and tokenDefinitions values
     * 
     * @param query The query that the user entered, excpeting a response from the ChatBot
     * @see ChatBot
     * @see AgentTest
     */
    public Lexer(String query) {
        this.query = query;

        tokenDefinitions.add(new TokenDefinition(TokenType.TERMINATE, "^(goodbye|bye)$"));
        tokenDefinitions.add(new TokenDefinition(TokenType.POSSESSIVE, "^(my|the|a|your) .*"));
        tokenDefinitions.add(new TokenDefinition(TokenType.REQUEST_INITIALIZER, "^(what\'s|whats|what is|get|tell me|tell us|tell|what are) .*"));
        tokenDefinitions.add(new TokenDefinition(TokenType.GREETING, "^(hi|hello|ayo|yo|good morning|good evening|good afternoon|hey|morning|afternoon|howdy|evening|ola).*"));
        tokenDefinitions.add(new TokenDefinition(TokenType.DECLERATION_IDENTIFIER, "^(\\d+|\\w+)(.)* (now is|is now|is|now are|are now|are) .+"));
        tokenDefinitions.add(new TokenDefinition(TokenType.DECLERATION_MODIFIER, "^(now is|is now|is|now are|are now|are) .+"));
        tokenDefinitions.add(new TokenDefinition(TokenType.LITERAL, "^(\\d+|\\w+)(.)*"));
    }
    
    /**
     * This method accepts nothing because it uses the private query variable assigned in the constructor, and breaks the query into tokens, which are returned in an ArrayList to the caller for further processing
     * 
     * @return ArrayList&gt;TokenMatch&lt; - The list of all tokens in the query
     */
    public ArrayList<TokenMatch> tokenize() {
        // ArrayList to hold all matches.
        ArrayList<TokenMatch> matchesList = new ArrayList<TokenMatch>();
        // Loop through every character of the query.
        for(int i = 0; i < query.length(); ++i) {
            // Check if the character is a letter or digit, if false, then skip to the next character.
            if(!Character.isLetter(query.charAt(i)) && !Character.isDigit(query.charAt(i))) continue;
            // Loop through each tokenDefinition, and match the tokenDefinition against the current query substring.
            for(TokenDefinition tokenDefinition : tokenDefinitions) {
                TokenMatch tokenMatch = tokenDefinition.matchToken(query.substring(i));
                // Check if the match was successful, if true, then add the match, and add the length of the match to i.
                if(tokenMatch.isMatch) {
                    matchesList.add(tokenMatch);
                    i += tokenMatch.value.length() - 1;
                    break;
                }
            }
        }
        return matchesList;
    }
    
}
