public class TokenMatch {
    public TokenType tokenType;
    public String value;
    public boolean isMatch;

    public TokenMatch(TokenType tokenType, String value, boolean isMatch) {
        this.tokenType = tokenType;
        this.value = value;
        this.isMatch = isMatch;
    }

    public void printFull() {
        System.out.println("tokenType: " + tokenType + " value: " + value + " isMatch: " + isMatch);
    }
}
