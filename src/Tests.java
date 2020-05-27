import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class Tests {
    private Deck player1Deck, player2Deck, drawingDeck;

    @Before
    public void setUp() throws Exception {
        drawingDeck = new Deck();
        drawingDeck.createFullDeck();
        player1Deck = Deck.drawDeck(drawingDeck, 0, 10);
        
    }
    
    @Test
    public void test_deckSize(){
        assertEquals(drawingDeck.getSize(), 52);
        assertEquals(player1Deck.getSize(), 10);
    }
}
