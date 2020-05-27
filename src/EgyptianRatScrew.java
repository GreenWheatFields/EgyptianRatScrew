import java.util.concurrent.TimeUnit;

public class EgyptianRatScrew{
    private static Deck drawingDeck, player1Deck, player2Deck;
    
    public static void gameSetup(){
        
        drawingDeck = new Deck();
        drawingDeck.createShuffledDeck();
        player1Deck = Deck.drawDeck(drawingDeck, 0, 26);
        
        int playerCount = 2;
        int split = 52 / playerCount;
        
    }

    public static void gameLoop(Deck deck) throws InterruptedException {
        int count = 0;
        while (1 < 2){
            System.out.println(deck.getCard(count));
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
        if(toAnalyze[2].getValue() == toAnalyze[1].getValue()){
            
        }
        return false;
    }
    public static void main(String[] args) throws InterruptedException {
        //gameLoop();
        gameSetup();
        System.out.println("done");
    }
    }
