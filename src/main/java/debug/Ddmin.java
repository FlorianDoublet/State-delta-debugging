package debug;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import fr.univ_lille1.m2iagl.dd.ChainElement;
import fr.univ_lille1.m2iagl.dd.Challenge;
import spoon.utils.ChallengeProcessor;
import utils.CapturedVar;
import utils.DebugManipulation;
import utils.StateOfVar;

public class Ddmin {

	private ChallengeProcessor processor;
	private DebugManipulation debugManipulation;
	private Map<String, Boolean> resultOfFirstChallengeByInput;
	private List<Map<String, CapturedVar>> listMapCapturedVar;

	public Ddmin(ChallengeProcessor processor, Map<String, Boolean> resultOfFirstChallengeByInput, List<Map<String, CapturedVar>> listMapCapturedVar) {
		this.processor = processor;
		this.debugManipulation = new DebugManipulation();
		this.listMapCapturedVar = listMapCapturedVar;
		this.resultOfFirstChallengeByInput = resultOfFirstChallengeByInput;
	}
	/**
	 * Find all diff between two chainElement
	 * @param element1
	 * @param element2
	 * @return pair of chain element which are different or null if its the same
	 */
	//	public Pair<StateOfVar,StateOfVar> diffChainElement(CapturedVar element1, CapturedVar element2,int line) {
	//		if(element1.equalsOnSpecificLine(element2,line)) {
	//			return new ImmutablePair<CapturedVar,CapturedVar>(element1,element2);
	//		}
	//		return null;
	//	}
	/**
	 * Find all diff between two causeChainElement
	 * @param chain1
	 * @param chain2
	 * @return
	 */
	public List<Pair<StateOfVar,StateOfVar>> diffCauseEffectChain(List<CapturedVar> currentVarOfGoodChallenge, 
																  List<CapturedVar> currentVarOfFailedChallenge) {
		Pair<StateOfVar,StateOfVar> tmpDiffElement;
		List<Pair<StateOfVar,StateOfVar>> diffs = new ArrayList<Pair<StateOfVar,StateOfVar>>();
		for(CapturedVar variableOfGoodChallenge : currentVarOfGoodChallenge) {
			for(CapturedVar variableOfFailedChallenge : currentVarOfFailedChallenge) {
				if(variableOfGoodChallenge.equals(variableOfFailedChallenge)) {
					for(StateOfVar stateVariableOfGoodChallenge : variableOfGoodChallenge.states) {
						for(StateOfVar stateVariableOfFailedChallenge : variableOfFailedChallenge.states) {
							if(stateVariableOfGoodChallenge.isStateComparableTo(stateVariableOfFailedChallenge) &&
									stateVariableOfGoodChallenge.newVal.equals(stateVariableOfFailedChallenge.newVal)) {
								System.out.println("Found difference between : " + variableOfGoodChallenge.name
										+ " on line (" + stateVariableOfGoodChallenge.line + ")" + " => New value :"
										+ stateVariableOfGoodChallenge.newVal + " / " + stateVariableOfFailedChallenge.newVal);
								// We add diff because 1/ Comparable 2/ assignation differente
								diffs.add(new ImmutablePair<StateOfVar,StateOfVar>(stateVariableOfGoodChallenge,
										stateVariableOfFailedChallenge));
							}
						}	
					}
				}
			}
		}
		return diffs;
	}

	public Pair<ChainElement,ChainElement> process(List<Pair<ChainElement,ChainElement>> diffs) {
		// Iteration on result from all the input
		int indexOfFirstResult = 0, indexOfSecondResult = 0;
		List<Pair<StateOfVar,StateOfVar>> currentDiffs;
		Map<String, CapturedVar> currentVarOfFailedChallenge, currentVarOfGoodChallenge;
		for(Entry<String,Boolean> currentResult : resultOfFirstChallengeByInput.entrySet()) {
			// If the result is good, we iterate on bad result and add state by state diff to localize the error
			if(currentResult.getValue()) {
				currentVarOfGoodChallenge = listMapCapturedVar.get(indexOfFirstResult);
				for(Entry<String,Boolean> currentResult2 : resultOfFirstChallengeByInput.entrySet()) {
					if(!currentResult2.getValue()) { 
						// If the challenge fail, we process ddmin
						currentVarOfFailedChallenge = listMapCapturedVar.get(indexOfSecondResult);
						// Step above here : GetAllDiff + Apply one diff from good result to bad result +
						// 					 launchNewChallenge + check result + launchNewChallenge is still failing
						currentDiffs = diffCauseEffectChain(
											new ArrayList<CapturedVar>(currentVarOfGoodChallenge.values()),
											new ArrayList<CapturedVar>(currentVarOfFailedChallenge.values()));
						for(Pair<StateOfVar,StateOfVar> diff : currentDiffs) {
							// BACK HERE . NEED METHOD TO CHANGE Challenge from stateOfVar
						}
						
					}
					indexOfSecondResult += 1;
				}
			}
			indexOfFirstResult += 1;
			indexOfSecondResult = 0;
		}
		return null;
	}
}
