package utils;

import java.util.ArrayList;
import java.util.HashMap;

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
        if(newVal instanceof ArrayList){
            this.newVal = new ArrayList<>((ArrayList)newVal);
        } else if (newVal instanceof HashMap) {
            this.newVal = new HashMap((HashMap)newVal);
        }else{
            this.newVal = newVal;
        }
        this.iteration = iteration;
    }

    public StateOfVar(int line, Object oldVal, Object newVal, String iteration){
        this.line = line;
        this.oldVal = oldVal;
        if(newVal instanceof ArrayList){
            this.newVal = new ArrayList<>((ArrayList)newVal);
        } else if (newVal instanceof HashMap) {
            this.newVal = new HashMap((HashMap)newVal);
        }else{
            this.newVal = newVal;
        }
        this.iteration = iteration;
    }

    public StateOfVar(int line, Object oldVal, Object newVal, String binaryOperator, String iteration){
        this.line = line;
        this.oldVal = oldVal;
        if(newVal instanceof ArrayList){
            this.newVal = new ArrayList<>((ArrayList)newVal);
        } else if (newVal instanceof HashMap) {
            this.newVal = new HashMap((HashMap)newVal);
        }else{
            this.newVal = newVal;
        }
        this.binaryOperator = binaryOperator;
        this.iteration = iteration;
    }

}
