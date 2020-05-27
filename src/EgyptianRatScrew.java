import java.util.concurrent.TimeUnit;

public class EgyptianRatScrew{

    
    public static void gameSetup(){
        
        Deck drawingDeck = new Deck();
        drawingDeck.createShuffledDeck();
        Deck player1Deck = new Deck();
        Deck player2Deck = new Deck();
        int playerCount = 2;
        int split = 52 / playerCount;
        player1Deck.drawDeck(drawingDeck, split - 1);
        player2Deck.drawDeck(drawingDeck, split += split - 1);
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
