public class SmartBot {
    
    Communication c;
    Memory m;
    Logic l;
    
    public SmartBot() {
        c = new Communication(this, m, l);
        m = new Memory(this, c, l);
        l = new Logic(this, c, m);
    }
    
    public void run() {
        String input = "";
        while(true) {
            input = c.listen();
            l.interpret(input);
        }
    }
    
    public void sleep() {
        c.say("Going to sleep...");
        m.transferFromTempToPermRelations();
        c.say("Good morning!");
    }
    
    public static void main(String[] args) {
        SmartBot bot = new SmartBot();
        bot.run();
    }
}