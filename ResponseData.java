public class ResponseData {
    //TODO: Add defaults for all properties of classes;
    public ResponseType responseType;
    public String response;
    public String literal;
    public String identifier;

    public ResponseData(ResponseType responseType, String response, String identifier, String literal) {
        this.responseType = responseType;
        this.response = response;
        this.identifier = identifier;
        this.literal = literal;
    }

    public ResponseData(ResponseType responseType, String response) {
        this(responseType, response, "", "");
    }

    public ResponseData(ResponseType responseType) {
        this(responseType, "");
    }
}
