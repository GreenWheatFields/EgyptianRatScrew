import java.util.concurrent.TimeUnit;

public class Slapjack{

    
    public static void gameLoop() throws InterruptedException {
        Deck drawingDeck = new Deck();
        drawingDeck.createShuffledDeck();
        int count = 0;
        while (1 < 2){
            System.out.println(drawingDeck.getCard(count));
            count++;
            TimeUnit.SECONDS.sleep(1);
            count++;
            if (count >= 52){break;}
        }
    }
    public static boolean isSlapable(Deck deck, int count, boolean isFirstTwo){
        if(isFirstTwo == true){
            return false;
        }
        Card[] toAnalyze = deck.getLastThree(count);
        if(toAnalyze[0].)
        return false;
    }
    public static void main(String[] args) throws InterruptedException {
        //gameLoop();
         Deck drawingDeck = new Deck();
        drawingDeck.createShuffledDeck();
        isSlapable(drawingDeck, 5, false);
    }
    }
