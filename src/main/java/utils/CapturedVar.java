package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import debug.DebugChainElement;
import debug.FancyDDebugger;

/**
 * Created by FlorianDoublet on 13/12/2016.
 * Class used to Captured our variable before to transform it into a ChainElementList.
 * A Captured var has a List of State
 */
public class CapturedVar {


	public String name;
	public Object lastVal;
	public Boolean wantToTransformInChainElement = false;
	public List<StateOfVar> states = new ArrayList<>();



	public CapturedVar(int line, Object val, String name, String iteration) {
		this.name = name;
		this.lastVal = val;
		StateOfVar stateOfVar = new StateOfVar(line, val, iteration);
		addState(stateOfVar);
	}

	//Really ugly technique to avoid unwanted chainElement
	//hope it will be temporary
	public CapturedVar(int line, Object val, String name, Class varClass, String iteration, Boolean wantToTransformInChainElement) {
		this.name = name;
		this.lastVal = val;
		StateOfVar stateOfVar = new StateOfVar(line, val, iteration);
		this.wantToTransformInChainElement = wantToTransformInChainElement;
		addState(stateOfVar);
	}

	public void addState(int line, Object newVal, String iteration){
		StateOfVar stateOfVar = new StateOfVar(line, this.lastVal, newVal, iteration);
		addState(stateOfVar);
	}

	public void addState(int line, Object newVal, String binaryOperator, String iteration){
		StateOfVar stateOfVar = new StateOfVar(line, this.lastVal, newVal, binaryOperator, iteration);
		addState(stateOfVar);
	}

	private void addState(StateOfVar stateOfVar){
		states.add(stateOfVar);
		if(!wantToTransformInChainElement) {
			DebugChainElement debugChainElement = buildChainElement(stateOfVar);
			FancyDDebugger.runtimeCauseEffectChain.add(debugChainElement);
		}
	}

	//Transform or CapturedVar with is States into a ChainElement list
	protected DebugChainElement buildChainElement(StateOfVar state){
		int line = state.line;
		String description = "";
		Object stateNewVal = state.newVal;
		String stateNewValStr = "";
		if(stateNewVal instanceof String[]){
			stateNewValStr = Arrays.toString((String[])stateNewVal);
		} else {
			stateNewValStr = stateNewVal.toString();
		}
		if(state.newVal == null){
			stateNewVal = new String("null");
		}
		if(state.oldVal == null){
			description += "was initialized to " + stateNewValStr;
		} else {
			description += " became " + stateNewValStr;
		}
		return new DebugChainElement(line, name, state.newVal, description, state.iteration);
	}

	/**
	 * change the last completeState of the captured var into the new one given
	 * it's used after an assignation operation
	 * @param completeState the new value to replace the older one
	 */
	public void updateLastCompleteStateValue(Object completeState){
		states.get(states.size() - 1).completeState = completeState;
		String stateNewValStr = "";
		if(completeState instanceof String[]){
			stateNewValStr = Arrays.toString((String[])completeState);
		} else {
			stateNewValStr = completeState.toString();
		}
		String description = " became " + stateNewValStr;
		FancyDDebugger.runtimeCauseEffectChain.updateLastCompleteStateValue(completeState, description);
	}

	public void updateLastDescription(String description){
		FancyDDebugger.runtimeCauseEffectChain.updateLastDescription(description);
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
