import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class Tests implements CardStats{
    Deck player1Deck, player2Deck, drawingDeck;
    int playerCount = 2;
    int split = 52 / playerCount;


    Card card;
    EgyptianRatScrew ers = new EgyptianRatScrew();
    Result result;



    @Before
    public void setUp() throws Exception {
        drawingDeck = new Deck();
        drawingDeck.createFullDeck();

    }
    
    @Test @Ignore
    public void test_deckSize(){
        player1Deck = Deck.drawDeck(drawingDeck, 0, 10);
        assertEquals(drawingDeck.getSize(), 52);
        assertEquals(player1Deck.getSize(), 10);
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
    @Test
    public void testRoyalFamily(){
        //should test for pair / sandwitch last.
        Card jack = new Card(Suit.HEART, Value.JACK);
        Card king = new Card(Suit.CLUB, Value.KING);
        Card queen = new Card(Suit.SPADE, Value.QUEEN);
        drawingDeck.customFill(king, jack, queen);
        
        result = ers.isSlapable(drawingDeck, 2);
        if (result == null) {
            System.out.println("Null Result");
            fail();
        }
        Result target = new Result(true, "Royal Family");
        assertEquals(target.reason(), result.reason());

    }

    @Test @Ignore
    
    public void testSimpleIf(){
        // did this in python, just making sure
        boolean test = true;
        int x = 0;
        if(test){
            x = 1;
        }
        System.out.println(x);
        assertEquals(1, x);
    }
}
