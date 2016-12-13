package utils;

/**
 * Created by FlorianDoublet on 13/12/16.
 */
public class StateOfVar {

    public int line;
    public Object oldVal = null;
    public Object newVal = null;
    public String binaryOperator;

    public StateOfVar(int line, Object oldVal){
        this.line = line;
        this.oldVal = oldVal;
    }

    public StateOfVar(int line, Object oldVal, Object newVal){
        this.line = line;
        this.oldVal = oldVal;
        this.newVal = newVal;
    }

    public StateOfVar(int line, Object oldVal, Object newVal, String binaryOperator){
        this.line = line;
        this.oldVal = oldVal;
        this.newVal = newVal;
        this.binaryOperator = binaryOperator;
    }

}
