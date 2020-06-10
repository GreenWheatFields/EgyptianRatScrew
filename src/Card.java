
public class Card implements CardStats{
    private final Suit suit;
    private final Value value;

    public Card(Suit suit, Value value){
        this.value = value;
        this.suit = suit;
    }
    public String toString(){
        return this.suit.toString() + " " + this.value.toString();
    }
    public Value getValue(){
        return  this.value;
    }
    public Suit getSuit(){
        return this.suit;
    }
//    public Card toJoker(){
//        return null;
//    }
}

