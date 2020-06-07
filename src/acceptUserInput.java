import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class acceptUserInput extends Thread {
    public volatile static boolean hasResponded;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public void run() {
        try {
            getInput();
        } catch (IOException e) {
            System.out.println("lol");
            //e.printStackTrace();
        }
    }
    public void getInput() throws IOException {
        while(true){
            while (br.ready() && EgyptianRatScrew.listeningForInput){
                //System.out.println("ready");
            hasResponded = true;
                //signal response, awake main thread
                //there may be a better practice to resetting buffered reader but i forgot it
                br.reset();
            }
            while (!br.ready() && EgyptianRatScrew.listeningForInput){
                //System.out.println("waiting");

            }
        }
    }
    public  void close() throws IOException {
        br.close();
    }

    }
