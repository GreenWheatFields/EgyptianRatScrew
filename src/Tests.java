import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class Tests implements CardStats{
    private Deck player1Deck, player2Deck, drawingDeck;
    public int playerCount = 2;
    public int split = 52 / playerCount;

    private Card card;



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
