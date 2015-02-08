import java.util.HashMap;
import java.util.ArrayList;

public class Memory {
    
    SmartBot b;
    Communication c;
    Logic l;
    
    // Permanent memory to implement in database or DaaS like Orchestrate.
    String[] tempLog = new String[100];
    
    // First dimension = Subject, 2nd = Action, 3rd[0] = Object, 3rd[1] = Cponfidence, 3rd[2] = Importance
    private HashMap<String, HashMap<String, ArrayList<String[]>>> tempRelations = new HashMap<String, HashMap<String, ArrayList<String[]>>>();
    
    private HashMap<String, HashMap<String, ArrayList<String[]>>> relations     = new HashMap<String, HashMap<String, ArrayList<String[]>>>();
    
    /**
     * Babies learn how relationships work.... but forget specific instances of those relationships.
     * Thus, no need for temporary delimeters.
     */
    private ArrayList<String> delimeters = new ArrayList<String>();
    
    public Memory(SmartBot bot, Communication com, Logic log) {
        b = bot;
        c = com;
        l = log;
        
        /**
         * Start bot off with a few sentense verbs/seperators as we are starting the bot "grown" up.
         * Babies do not speak, but rather listen and adapt to speak from their environment.
         * It would be boring to just type to the bot without it responsing, so we will give it these values.
         */
        delimeters.add("is");
        delimeters.add("are");
        
        /**
         * SAMPLE DATA
         * FOR TESTING PURPOSES.
         */
        tempRelations.put("Akshay", new HashMap<String, ArrayList<String[]>>());
        tempRelations.get("Akshay").put("is", new ArrayList<String[]>());
        String[] temp = {"a guy", ".75", ".40"};
        /**
         *      Value       ^^
         *              Confidence   ^^
         *                      Importance ^^
         */
        tempRelations.get("Akshay").get("is").add(temp);
         
        tempRelations.put("Chaitya", new HashMap<String, ArrayList<String[]>>());
        tempRelations.get("Chaitya").put("is", new ArrayList<String[]>());
        String[] temp1 = {"cool", ".7", ".75"};
        tempRelations.get("Chaitya").get("is").add(temp1);
        //GET VALUE
        //tempRelations.get("Akshay").get("is")[0];
        for(int i = 0; i < 100; i++)
            tempLog[i] = "";
    }
    
    public ArrayList<String> getDelimeters() {
        return delimeters;
    }
    
    /**
     * Loops through all of the relations that it has formed during the day and stores them permanently if
     * the importance is greater than .5
     */
    public void transferFromTempToPerm() {
        for(String key1 : tempRelations.keySet()) {
            for(String key2 : tempRelations.get(key1).keySet()) {
                for(String[] key3 : tempRelations.get(key1).get(key2)) {
                    if(Double.parseDouble(key3[2]) > .5) {
                        relations.put(key1, new HashMap<String, ArrayList<String[]>>());
                        relations.get(key1).put(key2, new ArrayList<String[]>());
                        String[] key3Copy = {key3[0], key3[1], key3[2]};
                        relations.get(key1).get(key2).add(key3Copy);
                    }
                }
            }
        }
    }
    
    public double getImportance(String key1, String key2, String key3) {
        ArrayList<String[]> list = tempRelations.get(key1).get(key2);
        int index = -1;
        for(int i = 0; i < list.size(); i++)
            if(list.get(i)[0].equals(key3))
                index = i;
        return Double.parseDouble(list.get(index)[2]);
                
    }
    
    
    /**
     * Add the latest message input to log.
     */
    public void appendTempLog(String s) {
        for(int i = 99; i > 0; i--) {
            tempLog[i] = tempLog[i - 1];
        }
        tempLog[0] = s;
    }
    
    /*public TYPE findRelation() {
        // return relation value;
    }*/
    
    public String tempLogToString() {
        String result = "";
        for (int i = tempLog.length - 1; i > -1; i--) {
            if (!tempLog[i].equals("")) result += tempLog[i] + "\n";
        }
        return result;
    }
    
    public String relationsToString(boolean temp) {
        String result = "";
        HashMap<String, HashMap<String, ArrayList<String[]>>> data = temp ? tempRelations : relations;
        for(String key1 : data.keySet()) {
            for(String key2 : data.get(key1).keySet()) {
                for(String[] key3 : data.get(key1).get(key2)) {
                   result += key1 + "\t" + key2 + "\t" + key3[0] + "\t" + "Confidence: " + key3[1] + "\t" + "Importance: " + key3[2] + "\n";
               }
            }
        }
        return result;
    }
}