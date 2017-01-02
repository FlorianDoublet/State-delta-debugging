package debug;

import fr.univ_lille1.m2iagl.dd.ChainElement;

/**
 * Created by FlorianDoublet on 13/12/16.
 * Our Implementation of ChainElement
 */
public class DebugChainElement implements ChainElement {

    public Integer line;
    public String iteration;
    public String varName;
    public String description;
    public Object value;
    //only used for var with a previous assignation like +=
    public Object completeState;

    public DebugChainElement(int line, String varName, Object value, String description, String iteration) {
        this.line = line;
        this.varName = varName;
        this.value = value;
        this.description = description;
        //iteration are considered as a String
        this.iteration = iteration;
    }

    @Override
    public String getLine() {
        return String.valueOf(this.line) + " " + iteration;
    }

    /**
     * added method to only get the line and not the iterations with it
     * @return
     */
    public int getOnlyLine() { return this.line; }

    @Override
    public String getVariable() {
        return this.varName;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public boolean equals(Object o) {
		if(o instanceof DebugChainElement) {
			DebugChainElement elem = (DebugChainElement)o;
			//We only look for "value" and not "completeState" because only element affected by a previous assignation
            //have this completeState, and it's not a criteria for our diffs
            Boolean valueIsNull = false;
            if(this.value == null || elem.value == null){
                valueIsNull = true;
            }
			if(this.varName.equals(elem.varName) && this.getLine().equals(elem.getLine())){
                if (!valueIsNull){
                    if (this.value.equals(elem.value)) return true;
                } else {
                    if(this.value == null && elem.value == null) return true;
                }

			}
		}
    	return false;
    }

	public String getIteration() {
		return iteration;
	}

}
