import java.util.HashMap;

public interface CardStats {
    public enum Suit {
        CLUB,DIAMOND,SPADE,HEART
    }
    public enum Value {
        TWO,THREE,FOUR,FIVE,SIX,SEVEN,EIGHT,NINE,TEN,JACK,KING,QUEEN,ACE
    }

    public HashMap<Value, Integer> alphaNumeric = new HashMap<>();

}