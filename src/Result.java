public class Result {
    private final String reason;
    private final boolean isSlappable;

    public Result(boolean isSlappable, String reason){
        this.reason = reason;
        this.isSlappable = isSlappable;
    }
    public boolean isSlappable(){
        return this.isSlappable;
    }
    public String reason(){
        return this.reason;
    }
    
}