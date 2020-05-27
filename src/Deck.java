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
    public Card[] getLastThree(int index){
        Card[] toAnalyze = new Card[3];
        for (int i = index, j = 2; i > index -3 || j >= 0; i--, j--) {
            toAnalyze[j] = this.cards.get(i);
        }
        
        return toAnalyze;
    }
    public void drawDeck(Deck drawingFrom, int split){
        for (int i = 0; i < split; i++) {
            this.cards.add(drawingFrom.cards.get(i));
        }
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
   