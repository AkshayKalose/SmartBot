import java.util.Scanner;

public class Communication {
    
    /**
     * Maximum time in seconds to come up with answer or "I don't know".
     * Like real life when you dont know what someone mreans.
     */
    final int MAXTIME = 3;
    
    /**
     * Minimum time in seconds to respond, as humans to not reply instantaneously.
     */
    final int MINTIME = 1;
    
    long listenTime = 0;
    
    public static Scanner input;
    
    public Communication() {
        input = new Scanner(System.in);
    }
    
    public String listen() {
        System.out.println("\nYour Message:");
        String message = input.nextLine();
        listenTime = System.currentTimeMillis();
        return message;
    }
    public void say(String s) {
        while (System.currentTimeMillis() < listenTime + MINTIME*1000) {
            // Loop infintely until MINTIME second(s) have passed.
        }
        System.out.println("\nResponse:\n" + s);
    }
}