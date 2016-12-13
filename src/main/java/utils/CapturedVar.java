package utils;

import debug.DebugChainElement;
import fr.univ_lille1.m2iagl.dd.ChainElement;

import java.util.ArrayList;
import java.util.List;

public class CapturedVar {
	

	public String name;
	public Class varClass;
	public Object lastVal;
	public List<StateOfVar> states = new ArrayList<>();
	public List<ChainElement> chainElementList = new ArrayList<ChainElement>();


	public CapturedVar(int line, Object val, String name, Class varClass) {
		this.name = name;
		this.varClass = varClass;
		this.lastVal = val;
		states.add(new StateOfVar(line, val));
	}

	public void addState(int line, Object newVal){
		states.add(new StateOfVar(line, this.lastVal, newVal));
	}

	public void addState(int line, Object newVal, String binaryOperator){
		states.add(new StateOfVar(line, this.lastVal, newVal, binaryOperator));
	}


	public List<ChainElement> buildChainElementList(){
		for(StateOfVar state : states){
			int line = state.line;
			String description = "";
			if(state.newVal == null){
				description += "was initialized to " + state.oldVal.toString();
			} else {
				description += " became " + state.newVal.toString();
			}
			chainElementList.add(new DebugChainElement(line, name, description));
		}
		return chainElementList;

	}

	public void changeLastNewValue(Object newVal){
		states.get(states.size() - 1).newVal = newVal;
	}


	@Override
	public int hashCode(){
		return name.hashCode();
	}

	@Override
	public boolean equals(Object other){
		if(other instanceof CapturedVar){
			return this.name.equals(((CapturedVar) other).name);
		} else {
			return false;
		}
	}
}
