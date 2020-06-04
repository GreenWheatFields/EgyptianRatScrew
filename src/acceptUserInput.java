import java.util.Date;
import java.util.Scanner;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class acceptUserInput extends Thread {
    Scanner userInput = new java.util.Scanner(System.in);
    public String userResponse = "";
    public boolean hasResponded;
    public void run() {
        userResponse = userInput.nextLine();
        hasResponded = true;
        System.out.println("got input");
    }

    public void game() throws InterruptedException {
        Timer time = new Timer();
        acceptUserInput input = new  acceptUserInput();
        System.out.println("you have 5 seconds to enter a number");
        input.start();
        


    }
    

public static void main(String[] args) throws InterruptedException {
    acceptUserInput a = new acceptUserInput();
    a.game();
}
}