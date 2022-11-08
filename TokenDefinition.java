import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TokenDefinition {
    private TokenType tokenType;
    private Pattern pattern;

    public TokenDefinition(TokenType tokenType, String regex) {
        this.tokenType = tokenType;
        pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    }

    public TokenMatch matchToken(String token) {
        Matcher matcher = pattern.matcher(token);
        boolean matchFound = matcher.find();
        if(matchFound) {
            return new TokenMatch(tokenType, matcher.group(1), true);
        }
        return new TokenMatch(tokenType, "", false);
    }
}
