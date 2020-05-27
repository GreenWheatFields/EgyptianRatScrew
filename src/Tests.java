import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class Tests {
    private Deck player1Deck, player2Deck, drawingDeck;
    public int playerCount = 2;
    public int split = 52 / playerCount;

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
    @Test
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
}
