import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

//probably better practice to split tests into class, like a class for testing deck, card, etc. but i'm almost done. next time.
public class Tests implements CardStats{
    Deck player1Deck, player2Deck, drawingDeck, prizeDeck;
    int playerCount = 2;
    int split = 52 / playerCount;


    Card card;
    EgyptianRatScrew ers = new EgyptianRatScrew();
    Result result;



    @Before
    public void setUp() {
        drawingDeck = new Deck();
        drawingDeck.createFullDeck();

    }
    
    @Test 
    public void test_deckSize(){
        player1Deck = Deck.drawDeck(drawingDeck, 0, 10);
        assertEquals(drawingDeck.getSize(), 52);
        assertEquals(player1Deck.getSize(), 10);

        player1Deck = Deck.drawDeck(drawingDeck, 0, 26);
        player2Deck = Deck.drawDeck(drawingDeck,26, drawingDeck.getSize());
        System.out.println(drawingDeck.getSize() - 26);
        assertEquals(26, (player2Deck.getSize() + player1Deck.getSize()) / 2 );
        assertFalse(player1Deck.getCard(25).toString().equals(player2Deck.getCard(0).toString()));
        //assertTrue(player2Deck.getCard(25) != null);

    }
    @Test @Ignore
    public void testSplit(){
        try {
            player1Deck = Deck.drawDeck(drawingDeck, split - split , split);
            player2Deck = Deck.drawDeck(drawingDeck, split, split+= split);
            System.out.println(player2Deck.getCard(52));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("out of bounds");
            fail();
        }
        
    }
    @Test @Ignore
    public void testAlphanumericValues(){
        card = new Card(Suit.CLUB, Value.ACE);
        assertSame(1, alphaNumeric.get(card.getValue()));
        card = new Card(Suit.HEART, Value.JACK);
        assertSame(11, alphaNumeric.get(card.getValue()));
        card = new Card(Suit.SPADE, Value.KING);
        assertSame(13, alphaNumeric.get(card.getValue()));
    }

    @Test @Ignore
    public void testCustomDecks(){
        Card jack = new Card(Suit.HEART, Value.JACK);
        Card king = new Card(Suit.HEART, Value.KING);
        Card queen = new Card(Suit.HEART, Value.QUEEN);
        drawingDeck.customFill(jack, king, queen);
        assertEquals(3, drawingDeck.getSize());
    }
    @Test @Ignore
    public void testRoyalFamily(){
        
        Result target = new Result(true, "Royal Family");

        Card jack = new Card(Suit.HEART, Value.JACK);
        Card king = new Card(Suit.HEART, Value.KING);
        Card queen = new Card(Suit.HEART, Value.QUEEN);

        drawingDeck.customFill(king, jack, queen);

        result = ers.isSlapable(drawingDeck, 2);

        if (result == null) {
            System.out.println("Null Object");
            fail();
        }
        
        assertEquals(target.reason(), result.reason());

        drawingDeck.customFill(king, queen, jack);
        result = ers.isSlapable(drawingDeck, 2);

        assertEquals(target.reason(), result.reason());

        drawingDeck.customFill(jack, queen, king);
        result = ers.isSlapable(drawingDeck, 2);

        assertEquals(target.reason(), result.reason());

    }
    @Test @Ignore
    public void testAscending(){
        Result target = new Result(true, "Ascending");

        Card two = new Card(Suit.CLUB, Value.TWO);
        Card three = new Card(Suit.HEART, Value.THREE);
        Card four = new Card(Suit.HEART, Value.FOUR);

        drawingDeck.customFill(two, three, four); //top down
        result = ers.isSlapable(drawingDeck, 2);

        assertEquals(target.reason(), result.reason());

        drawingDeck.customFill(three, two, four);
        result = ers.isSlapable(drawingDeck, 2);

        assertFalse(target.reason() == result.reason());

        Card jack = new Card(Suit.HEART, Value.JACK);
        Card king = new Card(Suit.HEART, Value.KING);
        Card queen = new Card(Suit.HEART, Value.QUEEN);

        drawingDeck.customFill(jack, queen, king);
        result = ers.isSlapable(drawingDeck, 2);
        //royal family should be first not ascend/descend

        assertFalse(target.reason().equals(result.reason()));
        
    }
    @Test @Ignore
    public void testDescending(){
        Result target = new Result(true, "Descending");

        Card two = new Card(Suit.CLUB, Value.TWO);
        Card three = new Card(Suit.HEART, Value.THREE);
        Card four = new Card(Suit.HEART, Value.FOUR);
        Card ace = new Card(Suit.SPADE, Value.ACE);
        Card nine = new Card(Suit.CLUB, Value.NINE);
        Card ten = new Card(Suit.DIAMOND, Value.TEN);
        Card jack = new Card(Suit.HEART, Value.JACK);


        drawingDeck.customFill(jack, ten, nine);
        result = ers.isSlapable(drawingDeck, 2);
        assertEquals(target.reason(), result.reason());


        drawingDeck.customFill(three, two, ace);
        result = ers.isSlapable(drawingDeck, 2);
        assertEquals(target.reason(), result.reason());
        
    }
    @Test
    public void testAdd(){
        player1Deck = Deck.drawDeck(drawingDeck, 0, 26);
        player2Deck = Deck.drawDeck(drawingDeck,26, drawingDeck.getSize());
        prizeDeck = new Deck();
        prizeDeck.add(player1Deck.getCard(0));
        System.out.println(prizeDeck.getCard(0).toString());
    }

    
}
