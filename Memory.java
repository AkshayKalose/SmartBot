import java.util.HashMap;

public class Memory {
    
    // Permanent memory to implement in database or DaaS like Orchestrate.
    String[] temp = new String[100];
    
    // First dimension = Subject, 2nd = Action, 3rd = Object
    HashMap<String, HashMap<String, String>> relations = new HashMap<String, HashMap<String, String>>();
    
    public Memory() {
        
    }
}