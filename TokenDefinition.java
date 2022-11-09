/**
 * This class defines the behaviour of the TokenDefinition class, which is used for string matching when the user query undergoes lexical analysis.
 * 
 * @author Dennis Kaydalov
 * 
 * @version November 8, 2022
 */
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TokenDefinition {
    private TokenType tokenType;
    private Pattern pattern;

    /**
     * Parameterized constructor to initialize the TokenDefinition properties.
     * 
     * @param tokenType The TokenType of this TokenDefinition
     * @param regex The regex pattern that will be used for matching.
     * @see TokenType 
     * @see Lexer
     */
    public TokenDefinition(TokenType tokenType, String regex) {
        this.tokenType = tokenType;
        pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    }

    /**
     * This methods accepts a String token, and returns an appropriate TokenMatch.
     * 
     * @param token The String token that the matchToken method will match against the Pattern pattern.
     * 
     * @return TokenMatch - the approriate TokenMatch.
     */
    public TokenMatch matchToken(String token) {
        Matcher matcher = pattern.matcher(token);
        boolean matchFound = matcher.find();
        if(matchFound) {
            // Return a TokenMatch with the type and the text from the match.
            return new TokenMatch(tokenType, matcher.group(1), true);
        }
        return new TokenMatch(tokenType, "", false);
    }
}
