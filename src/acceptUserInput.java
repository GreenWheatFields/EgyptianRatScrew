import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class acceptUserInput extends Thread {
    public volatile static boolean hasResponded;
    public BufferedReader br;
    public acceptUserInput(){
    }

    @Override
    public void run() {
        super.run();
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println(br.ready() + "STATUS");
            getInput();
        } catch (IOException e) {
            System.out.println("exception caught");
            //e.printStackTrace();
        }
    }
    public void getInput() throws IOException {
        String lookForInput = null;
        while(lookForInput == null){
            lookForInput = br.readLine();
        }
        System.out.println("detected");
        synchronized (this){
            System.out.println("notfying");
            notifyAll();
            System.out.println("notified");
        }
    }
    public  void close() throws IOException {
        interrupt();
    }

    }
