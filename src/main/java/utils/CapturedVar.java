package utils;

import debug.DebugChainElement;
import fr.univ_lille1.m2iagl.dd.ChainElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FlorianDoublet on 13/12/2016.
 * Class used to Captured our variable before to transform it into a ChainElementList.
 * A Captured var has a List of State
 */
public class CapturedVar {


	public String name;
	public Class varClass;
	public Object lastVal;
	public List<StateOfVar> states = new ArrayList<>();
	public List<DebugChainElement> chainElementList = new ArrayList<DebugChainElement>();


	public CapturedVar(int line, Object val, String name, Class varClass, String iteration) {
		this.name = name;
		this.varClass = varClass;
		this.lastVal = val;
		states.add(new StateOfVar(line, val, iteration));
	}

	public void addState(int line, Object newVal, String iteration){
		states.add(new StateOfVar(line, this.lastVal, newVal, iteration));
	}

	public void addState(int line, Object newVal, String binaryOperator, String iteration){
		states.add(new StateOfVar(line, this.lastVal, newVal, binaryOperator, iteration));
	}

	//Transform or CapturedVar with is States into a ChainElement list
	public List<DebugChainElement> buildChainElementList(){
		for(StateOfVar state : states){
			int line = state.line;
			String description = "";
			if(state.newVal == null){
				description += "was initialized to " + state.oldVal.toString();
			} else {
				description += " became " + state.newVal.toString();
			}
			chainElementList.add(new DebugChainElement(line, name, description, state.iteration));
		}
		return chainElementList;

	}

	/**
	 * change the last value of the captured var into the new one given
	 * @param newVal the new value to replace the older one
	 */
	public void changeLastNewValue(Object newVal){
		states.get(states.size() - 1).newVal = newVal;
	}

	//Used to transform this object in a Hashable one, but only based on the name of the Var
	@Override
	public int hashCode(){
		return name.hashCode();
	}

	public boolean equalsOnSpecificLine(CapturedVar other,int lineOfCompare){
		// We check if the last value is the same on process
		for(StateOfVar state : states){
			for(StateOfVar stateOfSecondValue : other.states) {
				if(state.line == lineOfCompare && stateOfSecondValue.line == lineOfCompare
						&& state.equals(stateOfSecondValue)) {
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * Used to transform this object to handle the equals operation, but only based on the name of the Vars
	 */
	@Override
	public boolean equals(Object other){
		if(other instanceof CapturedVar){
			return this.name.equals(((CapturedVar) other).name);
		} else {
			return false;
		}
	}
}
