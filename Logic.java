import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Logic {

    SmartBot b;
    Communication c;
    Memory m;

    /**
     * Trust starts at 1.0 because as a baby, our minds are impressionable,
     * similarly, this bot trusts everything you say at first... but once you say
     * something false, the trust value will decrease by the set constant decrement.
     * value.
     */
    double trust = 1.0;
    final double DECREMENT = .01;
    public Logic (SmartBot bot, Communication com, Memory mem) {
        b = bot;
        c = com;
        m = mem;
    }
    
    public void interpret(String s) {
        
        if (isDebugCommand(s)) {
            interpretAsDebugCommand(s);
            return;
        }
        
        m.appendTempLog(s);
        
        if (isQuestion(s)) {
            Pattern pattern;
            Matcher matcher;
            for(String delimiter : m.getDelimeters()) {
                pattern = Pattern.compile(delimiter + " (.*)\\?");
                matcher = pattern.matcher(s);
                if (matcher.find()) {
                    ArrayList<String[]> relations = m.getRelations(matcher.group(1), delimiter);
                    if (relations != null) {
                        c.say((getHighestConfidenceRelation(relations))[0]);
                    } else {
                        c.say("I don't know.");
                    }
                    return;
                }
            }
        } else if (isExclamation(s)) {
            Pattern pattern;
            Matcher matcher;
            for(String delimiter : m.getDelimeters()) {
                pattern = Pattern.compile("(.*) " + delimiter + " ([a-zA-Z1-9]*)\\.?");
                matcher = pattern.matcher(s);
                if (matcher.find()) {
                    m.addNewRelation(matcher.group(1), delimiter, matcher.group(2), trust, 0.7, true);
                    //c.say((getHighestConfidenceRelation(m.getRelations(matcher.group(1), delimiter)))[0]);
                    c.say(s);
                    return;
                }
            }
        } else {
            // Treat as a Statement.
            Pattern pattern;
            Matcher matcher;
            for(String delimiter : m.getDelimeters()) {
                pattern = Pattern.compile("(.*) " + delimiter + " ([a-zA-Z1-9]*)\\.?");
                matcher = pattern.matcher(s);
                if (matcher.find()) {
                    m.addNewRelation(matcher.group(1), delimiter, matcher.group(2), trust, 0.5, true);
                    //c.say((getHighestConfidenceRelation(m.getRelations(matcher.group(1), delimiter)))[0]);
                    c.say(s);
                    return;
                }
            }
        }
        // Imitate/Mimick user input if it does not understand.
        c.say(s);
    }
    
    public static boolean isQuestion(String s) {
        return s.endsWith("?");
    }
    
    public static boolean isExclamation(String s) {
        return s.endsWith("!");
    }
    
    public static boolean isDebugCommand(String s) {
        return s.startsWith("$");
    }
    
    
    public void interpretAsDebugCommand(String s) {
        if (s.contains("sleep")) {
            b.sleep();
        } else if (s.contains("log")) {
            c.say(m.tempLogToString());
        } else if (s.contains("temporary relations")) {
            c.say(m.relationsToString(true));
        } else if (s.contains("relations")) {
            c.say(m.relationsToString(false));
        }
    }
    
    public String[] getHighestConfidenceRelation(ArrayList<String[]> list) {
        String[] result = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (Double.parseDouble((list.get(i))[1]) > Double.parseDouble(result[1])) {
                result = list.get(i);
            }
        }
        return result;
    }
    
    public static int levenshtein_distance(String a, String b) {
        a = a.toLowerCase();
        b = b.toLowerCase();
        // i == 0
        int [] costs = new int [b.length() + 1];
        for (int j = 0; j < costs.length; j++)
            costs[j] = j;
        for (int i = 1; i <= a.length(); i++) {
            costs[0] = i;
            int nw = i - 1;
            for (int j = 1; j <= b.length(); j++) {
                int cj = Math.min(1 + Math.min(costs[j], costs[j - 1]), a.charAt(i - 1) == b.charAt(j - 1) ? nw : nw + 1);
                nw = costs[j];
                costs[j] = cj;
            }
        }
        return costs[b.length()];
    }
}