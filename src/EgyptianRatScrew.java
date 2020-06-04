import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class EgyptianRatScrew implements CardStats{
    private static Deck drawingDeck, player1Deck, player2Deck;
    Scanner userInput = new Scanner(System.in);
    
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
            Result result = isSlapable(drawingDeck, count);
                        



            System.out.println(result.reason());
            
            count++;
            TimeUnit.SECONDS.sleep(1);
            if (count >= 52){break;}
        }
    }
    public Result isSlapable(Deck deck, int count){
        Result result;
        if(count < 1){
            result = new Result(false, "Insufficient Material");
            return result;
        }
        // for this to work it will probably need an optional temp paramere , until then.
        Card[] toAnalyze = (count == 1) ? deck.getLastTwo(count): deck.getLastThree(count);
        if(toAnalyze.length == 2 && isPairFirstTwo(toAnalyze)){
            result = new Result(true, "Pair");
            return result;
        }else if (toAnalyze.length == 2 && !isPairFirstTwo(toAnalyze)) {
            result = new Result(false, "Invalid");
            return result;
        }

        HashSet<Integer> hashedValues = new HashSet<Integer>(Arrays.asList(alphaNumeric.get(toAnalyze[2].getValue()), alphaNumeric.get(toAnalyze[1].getValue()), alphaNumeric.get(toAnalyze[0].getValue())));

        if(isRoyalFamily(toAnalyze, hashedValues)){
            return new Result(true, "Royal Family");
            
        }else if(isMarriage(toAnalyze, hashedValues)){
            return new Result(true, "Marriage");

        }else if (isAscending(toAnalyze)){
                return new Result(true, "Ascending");

        }else if(isDescending(toAnalyze)){
                return new Result(true, "Descending");

        }else if (isSandwich(toAnalyze)) {
            return new Result(true, "Sandwich");

        }else if(isPair(toAnalyze)){

            if(isSandwich(toAnalyze)){
                return new Result(true, "Pair/Sandwich");
            }
            return new Result(true, "Pair");

        }else{
            return new Result(false, "Invalid");
        }
        
        
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

    public boolean isAscending(Card[] toAnalyze){
        if (alphaNumeric.get(toAnalyze[2].getValue()) - 1 == alphaNumeric.get(toAnalyze[1].getValue())){
            if (alphaNumeric.get(toAnalyze[1].getValue()) - 1 == alphaNumeric.get(toAnalyze[0].getValue())) {
                return true;
            }
        }
        return false;
    }

    public boolean isDescending(Card[] toAnalyze){
        if(alphaNumeric.get(toAnalyze[2].getValue()) + 1  == alphaNumeric.get(toAnalyze[1].getValue())){
            if (alphaNumeric.get(toAnalyze[1].getValue()) + 1 == alphaNumeric.get(toAnalyze[0].getValue())){
                return true;
            }
            return false;
    }
    return false;
    }

    public boolean isMarriage(Card[] toAnalyze, HashSet<Integer> hashedValues){
        if (marriage.contains(alphaNumeric.get(toAnalyze[2].getValue())) && marriage.contains(alphaNumeric.get(toAnalyze[1].getValue())) && hashedValues.size() == toAnalyze.length) {
           return true; 
        }
        return false;
    }

    public boolean isRoyalFamily(Card[] toAnalyze, HashSet<Integer> hashedValues){
        // hashset has no duplicates, so if for example you have three kings, it will read as a royal family until it analyzes size.
        if(royalFamily.contains(alphaNumeric.get(toAnalyze[0].getValue())) && royalFamily.contains(alphaNumeric.get(toAnalyze[1].getValue())) && royalFamily.contains(alphaNumeric.get(toAnalyze[2].getValue())) && hashedValues.size() == toAnalyze.length){
            return true;
        }
        return false;
    }
    public boolean isPairFirstTwo(Card[] toAnalyze){
        if(toAnalyze[1].getValue() == toAnalyze[0].getValue() || toAnalyze[1].getSuit() == toAnalyze[0].getSuit()){
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
