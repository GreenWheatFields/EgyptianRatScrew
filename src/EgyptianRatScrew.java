import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;


public class EgyptianRatScrew implements CardStats{
    private static Deck drawingDeck, player1Deck, player2Deck;
    public static Scanner userInput = new Scanner(System.in);
    public static boolean listeningForInput = true;


    public void gameSetup(){
        
        drawingDeck = new Deck();
        drawingDeck.createShuffledDeck();
        player1Deck = Deck.drawDeck(drawingDeck, 0, 26);
        int playerCount = 2;
        int split = 52 / playerCount;
        
    }

    public void gameLoop() throws InterruptedException, IOException {
        gameSetup();
        int count = 0;
        boolean listeningForInput = false;

        while (true){
            acceptUserInput input = new acceptUserInput();
            input.setName("InputThread");
            input.start();

            acceptUserInput.hasResponded = false;
            Result result = isSlapable(drawingDeck, count);

            System.out.println(drawingDeck.getCard(count));
            //TimeUnit.SECONDS.sleep(1); // should be wait, and, should sleep on another thread instead of the main thread input thread notifys sleeping thread, notifies main thread
            synchronized (this) {
                wait(1000);
            }
            input.close();



            if(acceptUserInput.hasResponded && result.isSlappable()){
                System.out.println("CORRECT + " + result.reason());
            }
            count++;
            if (count >= 52){break;}
        }
    }
    public Result isSlapable(Deck deck, int count){
        Result result;
        if(count < 1){
            result = new Result(false, "Insufficient Material");
            return result;
        }
        Card[] toAnalyze = (count == 1) ? deck.getLastTwo(count): deck.getLastThree(count);
        if(toAnalyze.length == 2 && isPairFirstTwo(toAnalyze)){
            result = new Result(true, "Pair");
            return result;
        }else if (toAnalyze.length == 2 && !isPairFirstTwo(toAnalyze)) {
            result = new Result(false, "Invalid");
            return result;
        }

        HashSet<Integer> hashedValues = new HashSet<>(Arrays.asList(alphaNumeric.get(toAnalyze[2].getValue()), alphaNumeric.get(toAnalyze[1].getValue()), alphaNumeric.get(toAnalyze[0].getValue())));

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
        return toAnalyze[2].getValue() == toAnalyze[1].getValue() || toAnalyze[2].getSuit() == toAnalyze[1].getSuit();
    }

    public boolean isSandwich(Card[] toAnalyze){
        return toAnalyze[0].getSuit() == toAnalyze[2].getSuit() || toAnalyze[0].getValue() == toAnalyze[2].getValue();
    }

    public boolean isAscending(Card[] toAnalyze){
        if (alphaNumeric.get(toAnalyze[2].getValue()) - 1 == alphaNumeric.get(toAnalyze[1].getValue())){
            return alphaNumeric.get(toAnalyze[1].getValue()) - 1 == alphaNumeric.get(toAnalyze[0].getValue());
        }
        return false;
    }

    public boolean isDescending(Card[] toAnalyze){
        if(alphaNumeric.get(toAnalyze[2].getValue()) + 1  == alphaNumeric.get(toAnalyze[1].getValue())){
            return alphaNumeric.get(toAnalyze[1].getValue()) + 1 == alphaNumeric.get(toAnalyze[0].getValue());
        }
    return false;
    }

    public boolean isMarriage(Card[] toAnalyze, HashSet<Integer> hashedValues){
        return marriage.contains(alphaNumeric.get(toAnalyze[2].getValue())) && marriage.contains(alphaNumeric.get(toAnalyze[1].getValue())) && hashedValues.size() == toAnalyze.length;
    }

    public boolean isRoyalFamily(Card[] toAnalyze, HashSet<Integer> hashedValues){
        // hashset has no duplicates, so if for example you have three kings, it will read as a royal family until it analyzes size.
        return royalFamily.contains(alphaNumeric.get(toAnalyze[0].getValue())) && royalFamily.contains(alphaNumeric.get(toAnalyze[1].getValue())) && royalFamily.contains(alphaNumeric.get(toAnalyze[2].getValue())) && hashedValues.size() == toAnalyze.length;
    }
    public boolean isPairFirstTwo(Card[] toAnalyze){
        return toAnalyze[1].getValue() == toAnalyze[0].getValue() || toAnalyze[1].getSuit() == toAnalyze[0].getSuit();
    }
    
    
        
   
    public static void main(String[] args) throws InterruptedException, IOException {
        //gameLoop();
        EgyptianRatScrew start = new EgyptianRatScrew();
        start.gameLoop();
        System.out.println("done");
    }
}
