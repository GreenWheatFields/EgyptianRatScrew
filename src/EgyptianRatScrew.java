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
    public static Result isSlapable(Deck deck, int count, boolean isFirstTwo){
        Result result;
        if(isFirstTwo == true){
            // this will need to be rewritten
            result = new Result(false, "Insufficent Material");
            
            return result;
            
        }

        Card[] toAnalyze = deck.getLastThree(count);

        // test for pair
        if(toAnalyze[2].getValue() == toAnalyze[1].getValue() || toAnalyze[2].getSuit() == toAnalyze[1].getSuit()){
            result = new Result(true, "Pair");
            // test within this test for a sandwich;
            if(toAnalyze[0].getSuit() == toAnalyze[2].getSuit() || toAnalyze[0].getValue() == toAnalyze[2].getValue()){
                result = new Result(true, "Pair/Sandwitch");
            }
            return result;
        }else if (toAnalyze[0].getSuit() == toAnalyze[2].getSuit() || toAnalyze[0].getValue() == toAnalyze[2].getValue()) {
            // test for sandwitch outright
            result = new Result(true, "Sandwitch");
        }else if ( 1 == 2/*toAnalyze[2].getValue()*/) {
            return null;
        }
        return null;
    }
    public static void main(String[] args) throws InterruptedException {
        //gameLoop();
        gameSetup();
        System.out.println("done");
    }
    }
