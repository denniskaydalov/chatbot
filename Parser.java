import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;
import java.lang.Math;

enum ResponseType {
    USER_VALUE_MODIFIER,
    NEUTRAL_VALUE_MODIFIER,
    HOST_VALUE_MODIFIER,
    USER_VALUE_REQUEST,
    NEUTRAL_VALUE_REQUEST,
    HOST_VALUE_REQUEST,
    TERMINATE,
    EMPTY,
    PLAIN_TEXT;
}

public class Parser {
    private ArrayList<TokenMatch> matchesList;

    public Parser(ArrayList<TokenMatch> matchesList) {
        this.matchesList = matchesList;
    }

    public ResponseData parseTokens() {
        ArrayList<ResponseData> responses = new ArrayList<ResponseData>();
        for(int i = 0; i < matchesList.size(); ++i) {
            switch(matchesList.get(i).tokenType) {
                case GREETING:
                    return new ResponseData(ResponseType.PLAIN_TEXT, greeting());
                case TERMINATE:
                    //TODO: GET ACTUALLT CHAT BOT NAME, OR SOME WORKAROUND
                    //TODO: not actually allowed to print stuff from here???
                    System.out.println("chatbot: " + farewell());
                    System.exit(0);
                case REQUEST_INITIALIZER:
                    if(matchesList.get(i+1).value.matches(".*my.*")) {
                        //TODO: Add try catching to make sure that there is a 'is' and a value
                        return new ResponseData(ResponseType.USER_VALUE_REQUEST, "", matchesList.get(i+2).value, ""); 
                    }
                    else if(matchesList.get(i+1).value.matches(".*the.*")) {
                        return new ResponseData(ResponseType.NEUTRAL_VALUE_REQUEST, "", matchesList.get(i+2).value, "");
                    }
                    else {
                        return new ResponseData(ResponseType.HOST_VALUE_REQUEST, "", matchesList.get(i+2).value, ""); 
                    }
                case POSSESSIVE:
                    if(matchesList.get(i).value.matches(".*my.*")) {
                        //TODO: Add try catching to make sure that there is a 'is' and a value
                        return new ResponseData(ResponseType.USER_VALUE_MODIFIER, "", matchesList.get(i+1).value, matchesList.get(i+3).value);
                    }
                    else if(matchesList.get(i).value.matches(".*the.*")) {
                        return new ResponseData(ResponseType.NEUTRAL_VALUE_MODIFIER, "", matchesList.get(i+1).value, matchesList.get(i+3).value);
                    }
                    else {
                        return new ResponseData(ResponseType.HOST_VALUE_MODIFIER, "", matchesList.get(i+1).value, matchesList.get(i+3).value);
                    }
                default:
                    return new ResponseData(ResponseType.EMPTY);
            }
        }
        return new ResponseData(ResponseType.EMPTY);
    }
    
    private String greeting() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH");  
        LocalDateTime now = LocalDateTime.now();  
        int hour = Integer.parseInt(dtf.format(now));
        String option = randomOption(new String[] {"Hi!", "Hello!", "Long time no see!", "Yo!", "Good", "Howdy!"});
        if(option.equals("Good")) {
            if(hour < 12 && hour >= 5) { 
                return "Good morning!";
            }
            if(hour >= 12 && hour < 18) { 
                return "Good afternoon!";
            }
            if (hour >= 18 && hour < 24){
                return "Good evening!";
            }
            else {
                return "Good morning? What are you doing up at this hour?!";
            }
        }
        else {
            return option;
        }
    }

    private String farewell() {
        return randomOption(new String[] {"Farewell!", "Goodbye then!", "See you later!", "Buh-bye now!", ":(", "Later!", "Cya!", "Adios!", "I guess I'll cry by myself now...", "\'bout time.", "Cheerio!", "Ciao!", "Au revoir!", "Bye-bye!", "Goodbye then.", "Later!", "Bye... for now.", "Peace!", "Take care!", "Fare thee well!", "Don\'t get run over!", "K"});
    }

    private String randomOption(String[] options) {
        return options[(int)(Math.random()*options.length)];
    }
}
