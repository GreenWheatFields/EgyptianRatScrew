import java.util.Scanner;

public class acceptUserInput extends Thread {
    Scanner userInput = new Scanner(System.in);
    public volatile static String userResponse = "@";
    public volatile static boolean hasResponded;

    public void run() {
        while (EgyptianRatScrew.inputThreadsTurn ) {
            // will need a way to listen for input and stop someone from spamming 
            userResponse = userInput.nextLine();
            System.out.println("input detected");
            hasResponded = true;
            
        }
    }
    
    

    // testing how threads interact
    // public void game() throws InterruptedException {
    //     acceptUserInput input = new  acceptUserInput();
    //     System.out.println("Enter a number on thread 2 while main thread sleeps 5 seconds");
    //     input.start();
    //     //long startTime = System.currentTimeMillis() / 1000;
    //     System.out.println("sleeping");
    //     TimeUnit.SECONDS.sleep(5);
    //     input.interrupt();
    //     //long endTime = ((System.currentTimeMillis() / 1000) - (startTimeSeconds));    
    //     System.out.println(userResponse);
    //     System.out.println(hasResponded);
    // }

}