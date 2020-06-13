import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;


public class EgyptianRatScrew extends Thread implements CardStats{
    private static Deck player1Deck;
    private static Deck player2Deck;
    private static Deck prizeDeck;
    public static Scanner userInput = new Scanner(System.in);
    public static boolean listeningForInput = true;
    public static acceptUserInput input;
    Random random = new Random();


    public static final Object gameLock = new Object();


    public void gameSetup(){

        Deck drawingDeck = new Deck();
        drawingDeck.createShuffledDeck();
        player1Deck = Deck.drawDeck(drawingDeck, 0, 26);
        player2Deck = Deck.drawDeck(drawingDeck,26, drawingDeck.getSize());
        prizeDeck = new Deck();

    }
    @SuppressWarnings("InfiniteLoopStatement")
    public void gameLoop() throws InterruptedException {
        gameSetup();
        int count = 0;
        int indices = 0;
        int deck1count = 0, deck2count = 0;
        int waitTime = 1000;

        input = new acceptUserInput();
        input.setName("InputThread");
        input.start();
        while (true){
            //as deck sizes change independently, you cant use one variable to access both
            deck1count = (deck1count >= player1Deck.getSize()) ? 0 : deck1count;
            deck2count = (deck2count >= player2Deck.getSize()) ? 0 : deck2count;
            //check if game is over
            if (player1Deck.getSize() == 0 || player2Deck.getSize() == 0){
                if(Dialogues.endGame(player1Deck.getSize() != 0)){
                    //kill input thread
                    input.interrupt();
                    //kill current thread
                    interrupt();
                    //create new threads in this method
                    beginGame(false);
                }

            }
            //allow player to read the results of the last round before continuing
            if (prizeDeck.getSize() == 0) {
                System.out.println("NEW PRIZE DECK\n------------------");
                synchronized (gameLock){
                    gameLock.wait(3000);
                }
            }
            // player 1 draws on even, player 2 on odd
            if (count % 2 == 0){
                prizeDeck.add(player1Deck, deck1count);
            }else{
                prizeDeck.add(player2Deck, deck2count);
            }
            //analyze deck
            Result result = isSlapable(prizeDeck, indices);
            System.out.println(prizeDeck.getCard(indices));
            //accept user input
            synchronized (acceptUserInput.inputLock){
                acceptUserInput.inputLock.notifyAll();
            }
            //wait to either be woken by user or by timeout
            synchronized (gameLock) {
                gameLock.wait(waitTime);
            }
            //determine whether woken by user, and what to do next
            if(acceptUserInput.hasResponded && result.isSlappable()){
                System.out.println("YOU SLAPPED CORRECT: " + result.reason());
                player1Deck.addToDeck(prizeDeck);
                Dialogues.showCardCounts();
                prizeDeck.clear();
                indices = 0;
                count++;
                continue;
            }else if(acceptUserInput.hasResponded && !result.isSlappable()){
                System.out.println("YOU SLAPPED INCORRECTLY " + result.reason());
                player2Deck.addToDeck(prizeDeck);
                Dialogues.showCardCounts();
                prizeDeck.clear();
                indices = 0;
                count++;
                continue;
            }else if (!acceptUserInput.hasResponded && result.isSlappable()){
                System.out.println("COMPUTER SLAPS CORRECTLY " + result.reason());
                player2Deck.addToDeck(prizeDeck);
                Dialogues.showCardCounts();
                prizeDeck.clear();
                indices = 0;
                count++;
                continue;
            }else if (!acceptUserInput.hasResponded && !result.isSlappable()){
                //one in 10 chance of slapping incorrectly
                int chance = random.nextInt(10) + 1;
                if (chance == 5){
                    System.out.println("COMPUTER SLAPS INCORRECTLY " + result.reason());
                    player1Deck.addToDeck(prizeDeck);
                    Dialogues.showCardCounts();
                    prizeDeck.clear();
                    indices = 0;
                    count++;
                    continue;
                }
            }
            deck1count++;
            deck2count++;
            count++;
            indices++;
        }
    }
    public Result isSlapable(Deck deck, int count){
        //return result object, so gameloop has access to boolean and string
        Result result;
        //can't slap on single card
        if(deck.getSize() == 1){
            result = new Result(false, "Insufficient Material");
            return result;
        }
        //Deck arraylist to array of last two or three card objects
        Card[] toAnalyze = (deck.getSize() == 2) ? deck.getLastTwo(count): deck.getLastThree(count);
        //if there are only two cards, you only need to check for a pair
        if(toAnalyze.length == 2 && isPairFirstTwo(toAnalyze)){
            result = new Result(true, "Pair");
            return result;
        }else if (toAnalyze.length == 2 && !isPairFirstTwo(toAnalyze)) {
            result = new Result(false, "Invalid");
            return result;
        }

        HashSet<Integer> hashedValues = new HashSet<>(Arrays.asList(alphaNumeric.get(toAnalyze[2].getValue()), alphaNumeric.get(toAnalyze[1].getValue()), alphaNumeric.get(toAnalyze[0].getValue())));
        //check for fringe cases first, then common cases like pair and sandwich
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

    @Override
    public void run() {
        super.run();
        try {
            gameLoop();
        } catch (InterruptedException ignored) {
        }
    }

    public static void beginGame(boolean firstGame) throws InterruptedException {
        if (firstGame) Dialogues.introMessage();
        EgyptianRatScrew ers = new EgyptianRatScrew();
        ers.setName("GameThread");
        ers.start();
        ers.join();

    }
    public static void main(String[] args) throws InterruptedException {
        //all setup is done in beginGame() because it takes a boolean argument
        beginGame(true);
    }
    //just wanted to see how inner classes interact with outer classes tbh
    public static class Dialogues {
        public static void showCardCounts(){
            System.out.println("YOUR DECK SIZE: " + player1Deck.getSize());
            System.out.println("COMPUTER DECK SIZE: " + player2Deck.getSize());
        }
        public static boolean endGame(boolean didPlayer1Win) {
            if (didPlayer1Win){
                System.out.println("YOU WIN\n + PLAY AGAIN?\n" + "Y/N");
            }else{
                System.out.println("YOU LOSE\n" + "PLAY AGAIN?\n" + "Y/N");
            }
            Scanner userInput = new Scanner(System.in);
            String userResponse = userInput.nextLine();
            if (userResponse.toUpperCase().equals("Y")){
                return true;
            }else{
                System.exit(0);

            }
            return true;
        }
        public static void introMessage(){
            System.out.println("EGYPTIAN RAT SCREW IS A CARD GAME.\n" +
                    " YOU AND THE COMPUTER WILL BE GIVEN 26 CARDS EACH.\n" +
                    "STARTING WITH YOU, YOU WILL TAKE TURNS DEALING CARDS FROM YOUR DECK TO CREATE A PRIZE DECK\n" +
                    "THE GOAL IS TO IDENTIFY PATTERNS IN THE PRIZE DECK AND 'SLAP' THE PATTERNS TO ADD THE PRIZE DECK TO YOUR OWN DECK\n" +
                    "THE PLAYER THAT RUNS OUT OF CARDS FIRST LOSES\n" +
                    "THE COMPUTER ALWAYS SLAPS ON THE CORRECT PATTERN, AND SLAPS INCORRECTLY 1/10 TIMES\n" +
                    "YOU HAVE 1 SECOND TO SLAP BEFORE THE COMPUTER SLAPS\n" +
                    "YOU CAN CHANGE THE TIME TO SLAP BY CHANGE THE VALUE OF THE 'waitTime' VARIABLE IN THE CODE\n" +
                    "YOU CAN SLAP ON THESE PATTERNS:\n" +
                    "ROYAL FAMILY - ANY COMBINATION OF JACK, QUEEN, KING\n" +
                    "MARRIAGE - ANY ORDER OF KING OR QUEEN\n" +
                    "ASCENDING - RISING NUMERIC VALUES OF THREE EX : (ACE, ONE , TWO) OR (TEN, JACK, QUEEN)\n" +
                    "DESCENDING - FALLING NUMERIC VALUES OF THREE EX : (SEVEN, SIX, FIVE) OR (QUEEN, JACK, TEN)\n" +
                    "PAIR - TWO CARDS OF SAME SUIT OR VALUE EX (SPADE JACK, SPADE TWO) OR (HEART KING, SPADE KING)\n" +
                    "SANDWICH - TWO CARDS OF SAME SUIT OR VALUE WITH ONE CARD IN BETWEEN THEM EX (SPADE JACK, HEART ONE, CLUB JACK)\n");
            System.out.println("THANK YOU FOR PLAYING, PRESS ENTER TO BEGIN");
            //allow user to read message before continuing
            String gameBreak = userInput.nextLine();
        }
    }
}
