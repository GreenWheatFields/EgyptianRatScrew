import java.util.Arrays;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;

public class EgyptianRatScrew implements CardStats{
    private static Deck drawingDeck, player1Deck, player2Deck;
    
    public void gameSetup(){
        
        drawingDeck = new Deck();
        drawingDeck.createShuffledDeck();
        player1Deck = Deck.drawDeck(drawingDeck, 0, 26);
        
        int playerCount = 2;
        int split = 52 / playerCount;
        
    }

    public void gameLoop() throws InterruptedException {
        gameSetup();
        int count = 0;
        while (true){
            System.out.println(drawingDeck.getCard(count));
            count++;
            if(count > 3){
                Result result = isSlapable(drawingDeck, count, false);
                System.out.println(result.reason());
            }
            TimeUnit.SECONDS.sleep(1);
            count++;
            if (count >= 52){break;}
        }
    }
    public Result isSlapable(Deck deck, int count, boolean isFirstTwo){
        Result result;
        if(isFirstTwo == true){
            // this will need to be rewritten
            result = new Result(false, "Insufficient Material");
            
            return result;
            
        }

        Card[] toAnalyze = deck.getLastThree(count);
        HashSet<Integer> hashedValues = new HashSet<Integer>(Arrays.asList(alphaNumeric.get(toAnalyze[2].getValue()), alphaNumeric.get(toAnalyze[1].getValue()), alphaNumeric.get(toAnalyze[0].getValue())));

        if(isPair(toAnalyze)){
            result = new Result(true, "Pair");

            if(isSandwich(toAnalyze)){
                            // test within this test for a sandwich;
                result = new Result(true, "Pair/Sandwich");
                
            }
            return result;
        }else if (isSandwich(toAnalyze)) {
                result = new Result(true, "Sandwich");
                return result;
            
        }else if (isDescending(toAnalyze)){
                result = new Result(true, "Descending");
                return result;
            }
        else if(isAscending(toAnalyze)){
                result = new Result(true, "Ascending");
                return result;
        }
        else if(isMarriage(toAnalyze, hashedValues)){
                result = new Result(true, "Marriage");
                return result;
        }else if(isRoyalFamily(toAnalyze, hashedValues)){
                result = new Result(true, "Royal Family");
                return result;
        }
        result = new Result(false, "Invalid");
        return result;
        
    }
    public boolean isPair(Card[] toAnalyze){
        if(toAnalyze[2].getValue() == toAnalyze[1].getValue() || toAnalyze[2].getSuit() == toAnalyze[1].getSuit()){
            return true;
        }
        return false;
    }

    public boolean isSandwich(Card[] toAnalyze){
        if (toAnalyze[0].getSuit() == toAnalyze[2].getSuit() || toAnalyze[0].getValue() == toAnalyze[2].getValue()) {
            return true;          
        }
        return false;
}

    public boolean isDescending(Card[] toAnalyze){
        if (alphaNumeric.get(toAnalyze[2].getValue()) - 1 == alphaNumeric.get(toAnalyze[1].getValue())){
            if (alphaNumeric.get(toAnalyze[1].getValue()) - 1 == alphaNumeric.get(toAnalyze[0].getValue())) {
                return true;
            }
        }
        return false;
    }

    public boolean isAscending(Card[] toAnalyze){
        if(alphaNumeric.get(toAnalyze[0].getValue()) + 1  == alphaNumeric.get(toAnalyze[1].getValue())){
            if (alphaNumeric.get(toAnalyze[1].getValue()) + 1 == alphaNumeric.get(toAnalyze[2].getValue())){
                return true;
            }
            return false;
    }
    return false;
    }

    public boolean isMarriage(Card[] toAnalyze, HashSet hashedValues){
        if (marriage.contains(alphaNumeric.get(toAnalyze[2].getValue())) && marriage.contains(alphaNumeric.get(toAnalyze[1].getValue())) && hashedValues.size() == toAnalyze.length) {
           return true; 
        }
        return false;
    }

    public boolean isRoyalFamily(Card[] toAnalyze, HashSet hashedValues){
        // hashset has no duplicates, so if for example you have three kings, it will read as a royal family until it analyzes size.
        if(royalFamily.contains(alphaNumeric.get(toAnalyze[0].getValue())) && royalFamily.contains(alphaNumeric.get(toAnalyze[1].getValue())) && royalFamily.contains(alphaNumeric.get(toAnalyze[2].getValue())) && hashedValues.size() == toAnalyze.length){
            return true;
        }
        return false;
    }
        
   
    public static void main(String[] args) throws InterruptedException {
        //gameLoop();
        EgyptianRatScrew start = new EgyptianRatScrew();
        start.gameLoop();
        System.out.println("done");
    }
}
