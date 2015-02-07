public class SmartBot {
    
    Communication c;
    Memory m;
    Logic l;
    
    public SmartBot() {
        c = new Communication();
    }
    
    public void run() {
        String input = "";
        while(true) {
            input = c.hear();
            c.say(input);
        }
    }
    
    public static void main(String[] args) {
        SmartBot bot = new SmartBot();
        bot.run();
    }
}