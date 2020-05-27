import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private ArrayList<Card> cards;
    
    public Deck(){
        this.cards = new ArrayList<Card>();
        
    }
    public void createFullDeck(){
        for (Suit cardSuit : Suit.values()){
            for (Value cardValue : Value.values()){
                this.cards.add(new Card(cardSuit,cardValue));
            }
        }
    }
    public void createShuffledDeck(){
        for (Suit cardSuit : Suit.values()){
            for (Value cardValue : Value.values()){
                this.cards.add(new Card(cardSuit,cardValue));
            }
        }
        Collections.shuffle(this.cards);
    }
    public void shuffle(){
        Collections.shuffle(this.cards);
    }
    public Card getCard(int i){
        return this.cards.get(i);
    }
    
    public int getSize(){
        return this.cards.size();
    }

}
   