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

    public static Deck drawDeck(Deck drawingFrom, int beginIndex, int endIndex){
        Deck deck = new Deck();
        deck.cards = new ArrayList<Card>(drawingFrom.cards.subList(0, 10));
        return deck;
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
   