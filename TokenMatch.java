/**
 * This class defines the behaviour of the TokenMatch class, which is used for tokenization, and contains properties pertaining to the tokens.
 * 
 * @author Dennis Kaydalov
 * 
 * @version November 8, 2022
 */
public class TokenMatch {
    public TokenType tokenType;
    public String value;
    public boolean isMatch;

    /**
     * Parameterized constructor to initialize the TokenMatch properties.
     * 
     * @param tokenType The TokenType of this TokenMatch, used for better identification
     * @param value The String value of this TokenMatch
     * @param isMatch The boolean which determines whether this specific match was a successful match
     * @see TokenType 
     * @see Lexer
     */
    public TokenMatch(TokenType tokenType, String value, boolean isMatch) {
        this.tokenType = tokenType;
        this.value = value;
        this.isMatch = isMatch;
    }

    //TODO: TODO:TODO:TODO:TODO:TODO:TODO: REMOVE
    public void printFull() {
        System.out.println("tokenType: " + tokenType + " value: " + value + " isMatch: " + isMatch);
    }
}
