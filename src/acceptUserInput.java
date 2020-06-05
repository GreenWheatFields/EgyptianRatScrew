import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.Scanner;

public class acceptUserInput extends Thread {
    Scanner userInput = new Scanner(System.in);
    public volatile static String userResponse = "@";
    public volatile boolean hasResponded;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public void run() {
        while (EgyptianRatScrew.threadsTurn) {
            System.out.println("value of userresponse " + userResponse);
            System.out.println("here");
            userResponse = userInput.nextLine();            
            System.out.println("input detected");
            hasResponded = true;
        }
    }
    
    public String getUserResponse(){return userResponse;}
    public boolean getHasResponded(){return hasResponded;} //probably redundant, can just check if string is null

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