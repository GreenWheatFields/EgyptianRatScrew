import java.util.ArrayList;
import java.util.Collections;

public class Deck implements CardStats{

    private ArrayList<Card> cards;

    // Arraylist could probably be public

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
    public Card[] getLastTwo(int index){
        Card[] toAnalyze = new Card[2];
        for (int i = index, j = 1; i > index -2 || j >= 0; i--, j--) {
            toAnalyze[j] = this.cards.get(i);
        }
        
        return toAnalyze;
    }

    public static Deck drawDeck(Deck drawingFrom, int beginIndex, int endIndex){
        Deck deck = new Deck();
        deck.cards = new ArrayList<Card>(drawingFrom.cards.subList(beginIndex, endIndex));
        return deck;
    }

    public Card getCard(int i){
        return this.cards.get(i);
    }
    
    public int getSize(){
        return this.cards.size();
    }
    public void customFill(Card ...cards){
        this.cards = new ArrayList<Card>();
        for (Card card : cards) {
            this.cards.add(card);
        }
    }
    public void add(Deck drawingFrom, int index){
        this.cards.add(drawingFrom.getCard(index));
        drawingFrom.cards.remove(index);
    }
    public void addToDeck(Deck addingFrom){
        this.cards.addAll(addingFrom.cards);
    }
    public void clear(){
        this.cards.clear();
    }
    

}
   