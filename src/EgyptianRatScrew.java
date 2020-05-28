import java.util.Arrays;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;

public class EgyptianRatScrew implements CardStats{
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
            result = new Result(false, "Insufficient Material");
            
            return result;
            
        }

        Card[] toAnalyze = deck.getLastThree(count);
        //HashSet<Integer> temp = new HashSet<Integer>(Arrays.asList(alphaNumeric.get(toAnalyze[2].getValue())))

        // test for pair
        if(toAnalyze[2].getValue() == toAnalyze[1].getValue() || toAnalyze[2].getSuit() == toAnalyze[1].getSuit()){
            result = new Result(true, "Pair");

            if(toAnalyze[0].getSuit() == toAnalyze[2].getSuit() || toAnalyze[0].getValue() == toAnalyze[2].getValue()){
                            // test within this test for a sandwich;
                result = new Result(true, "Pair/Sandwich");
                
            }
            return result;
        }else if (toAnalyze[0].getSuit() == toAnalyze[2].getSuit() || toAnalyze[0].getValue() == toAnalyze[2].getValue()) {
            // test for sandwich outright
            result = new Result(true, "Sandwich");
            
        }else if (alphaNumeric.get(toAnalyze[2].getValue()) - 1 == alphaNumeric.get(toAnalyze[1].getValue())){
                if (alphaNumeric.get(toAnalyze[1].getValue()) - 1 == alphaNumeric.get(toAnalyze[0].getValue())) {
                    //check for descending
                    result = new Result(true, "Descending");
                    return result;
                }
        else if(alphaNumeric.get(toAnalyze[0].getValue()) + 1  == alphaNumeric.get(toAnalyze[1].getValue())){
                if (alphaNumeric.get(toAnalyze[1].getValue()) + 1 == alphaNumeric.get(toAnalyze[2].getValue())) {
                    result = new Result(true, "Ascending");
                    return result;
                }
        else if(marriage.contains(alphaNumeric.get(toAnalyze[2].getValue())) && marriage.contains(alphaNumeric.get(toAnalyze[1].getValue())) && toAnalyze[2].getValue() != toAnalyze[1].getValue()){
                result = new Result(true, "Marriage");
                return result;
        }else if(1 == 2){
            
        }
            
        
        }

            
        }
        return null;
    }
    public boolean isRoyalFamily(Card[] toAnalyze){
        if(1 == 2/*royalFamily.contains(alphaNumeric.get(toAnalyze[0].getValue())) && royalFamily.contains(alphaNumeric.get(toAnalyze[1].getValue()))) && royalFamily.contains(alphaNumeric.get(toAnalyze[2].getValue() && toAnalyze[1])*/){}
            
        return true;
    }
        
   
    public static void main(String[] args) throws InterruptedException {
        //gameLoop();
        gameSetup();
        System.out.println("done");
    }
}
