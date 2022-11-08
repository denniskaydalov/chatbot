import java.util.ArrayList;
import java.util.List;

enum TokenType {
    TERMINATE,
    GREETING,
    POSSESSIVE,
    DECLERATION_IDENTIFIER,
    DECLERATION_MODIFIER,
    REQUEST_INITIALIZER,
    LITERAL;
}

public class Lexer {
    
    private String query;
    private List<TokenDefinition> tokenDefinitions = new ArrayList<TokenDefinition>();
    
    public Lexer(String query) {
        this.query = query;

        tokenDefinitions.add(new TokenDefinition(TokenType.TERMINATE, "^(goodbye|bye).*"));
        tokenDefinitions.add(new TokenDefinition(TokenType.POSSESSIVE, "^(my|the|a|your) .*"));
        tokenDefinitions.add(new TokenDefinition(TokenType.REQUEST_INITIALIZER, "^(what\'s|whats|what is|get|tell me|tell us|tell) .*"));
        tokenDefinitions.add(new TokenDefinition(TokenType.GREETING, "^(hi|hello|ayo|yo|good morning|good evening|good afternoon|hey|morning|afternoon|howdy|evening|ola).*"));
        tokenDefinitions.add(new TokenDefinition(TokenType.DECLERATION_IDENTIFIER, "^(\\d+|\\w+)(.)* is .+"));
        tokenDefinitions.add(new TokenDefinition(TokenType.DECLERATION_MODIFIER, "^(now is|is now|is) .+"));
        tokenDefinitions.add(new TokenDefinition(TokenType.LITERAL, "^(\\d+|\\w+)(.)*"));
    }
    
    public ArrayList<TokenMatch> tokenize() {
        ArrayList<TokenMatch> matchesList = new ArrayList<TokenMatch>();
        //System.out.println("Lexing... query: " + query + " query.length: " + query.length());
        for(int i = 0; i < query.length(); ++i) {
            //System.out.println("In lexing loop, query.substring(i): " + query.substring(i));
            if(!Character.isLetter(query.charAt(i)) && !Character.isDigit(query.charAt(i))) continue;
            for(TokenDefinition tokenDefinition : tokenDefinitions) {
                TokenMatch tokenMatch = tokenDefinition.matchToken(query.substring(i));
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
