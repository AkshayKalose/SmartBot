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
            
        } else if (isExclamation(s)) {
            // something dramatic to affect emotion?
        } else {
            
        }
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