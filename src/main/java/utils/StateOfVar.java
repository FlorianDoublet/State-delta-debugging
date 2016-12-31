package utils;

/**
 * Created by FlorianDoublet on 13/12/16.
 * This class modelise the Status of a captured variable
 */
public class StateOfVar {

    public int line;
    public Object oldVal = null;
    public Object newVal = null;
    public String binaryOperator;
    public String iteration = "";
    //special param only for new value to insert
    public boolean reached = false;
    //store the complete value of an object after an assignation
    // the value of the x in .... += x is stored as newValue
    public Object completeState;

    public StateOfVar(int line, Object newVal, String iteration){
        this.line = line;
        this.newVal = newVal;
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
