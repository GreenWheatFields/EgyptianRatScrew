import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class acceptUserInput extends Thread {
    public volatile static boolean hasResponded;
    BufferedReader br;
    String lookForInput;
    public final static Object inputLock = new Object();
    public boolean test;
    public acceptUserInput(){
    }
    // read input, when input is read start another thread
    @Override
    public void run() {
        super.run();
        try {
            hasResponded = false;
            br = new BufferedReader(new InputStreamReader(System.in));
            sleep();
        } catch (IOException | InterruptedException e) {
            System.out.println("exception caught");
            //e.printStackTrace();
        }
    }
    public void getInput() throws IOException, InterruptedException {
        lookForInput = null;
        while (lookForInput == null && EgyptianRatScrew.listeningForInput) {
            lookForInput = this.br.readLine();
        }
        System.out.println("detected");
        synchronized (EgyptianRatScrew.gameLock) {
            hasResponded = true;
            EgyptianRatScrew.gameLock.notifyAll();
        }
        sleep();


    }
    public void sleep() throws InterruptedException, IOException {
        synchronized (inputLock){
            inputLock.wait(0);
            System.out.println("notifyed");
        }
        getInput();
    }
}
