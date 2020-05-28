import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public interface CardStats {
    public enum Suit {
        CLUB,DIAMOND,SPADE,HEART
    }
    public enum Value {
        TWO,THREE,FOUR,FIVE,SIX,SEVEN,EIGHT,NINE,TEN,JACK,KING,QUEEN,ACE
    }

    final HashMap<Value, Integer> alphaNumeric = new HashMap<Value, Integer>()
    {{
        put(Value.ACE, 1);
        put(Value.TWO, 2);
        put(Value.THREE, 3);
        put(Value.FOUR, 4);
        put(Value.FIVE, 5);
        put(Value.SIX, 6);
        put(Value.SEVEN, 7);
        put(Value.EIGHT, 8);
        put(Value.NINE, 9);
        put(Value.TEN, 10);
        put(Value.JACK, 11);
        put(Value.QUEEN, 12);
        put(Value.KING, 13);
    
    
    }};

    
    final Set<Integer> marriage = new HashSet<Integer>(Arrays.asList(12, 13));

    final Set<Integer> royalFamily = new HashSet<Integer>(Arrays.asList(11,12, 13));

}