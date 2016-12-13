package utils;

/**
 * Created by FlorianDoublet on 13/12/16.
 */
public class StateOfVar {

    public int line;
    public Object oldVal = null;
    public Object newVal = null;
    public String binaryOperator;
    public String iteration = "";

    public StateOfVar(int line, Object oldVal, String iteration){
        this.line = line;
        this.oldVal = oldVal;
        this.iteration = iteration;
    }

    public StateOfVar(int line, Object oldVal, Object newVal, String iteration){
        this.line = line;
        this.oldVal = oldVal;
        this.newVal = newVal;
        this.iteration = iteration;
    }

    public StateOfVar(int line, Object oldVal, Object newVal, String binaryOperator, String iteration){
        this.line = line;
        this.oldVal = oldVal;
        this.newVal = newVal;
        this.binaryOperator = binaryOperator;
        this.iteration = iteration;
    }

}
