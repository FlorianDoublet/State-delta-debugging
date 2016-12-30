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

    /**
     * Check if the state of var is comparable to other one
     * @param stateVariableOfSecondExecution
     * @return
     */
	public boolean isStateComparableTo(StateOfVar stateVariableOfSecondExecution) {
		
		return this.line == stateVariableOfSecondExecution.line 
				&& this.iteration.equals(stateVariableOfSecondExecution.iteration);
	}

}
