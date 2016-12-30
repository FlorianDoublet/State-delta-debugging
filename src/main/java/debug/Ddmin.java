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
	private Challenge challenge;
	
	public Ddmin(ChallengeProcessor processor, Map<String, Boolean> resultOfFirstChallengeByInput, List<Map<String, CapturedVar>> listMapCapturedVar,Challenge challenge) {
		this.processor = processor;
		this.debugManipulation = new DebugManipulation();
		this.listMapCapturedVar = listMapCapturedVar;
		this.resultOfFirstChallengeByInput = resultOfFirstChallengeByInput;
		this.challenge = challenge;
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Pair<Pair<CapturedVar,StateOfVar>,Pair<CapturedVar,StateOfVar>>> 
	diffCauseEffectChain(List<CapturedVar> currentVarOfGoodChallenge, List<CapturedVar> currentVarOfFailedChallenge) {
		
		Pair<Pair<CapturedVar,StateOfVar>,Pair<CapturedVar,StateOfVar>> tmpDiffElement;
		Pair<CapturedVar,StateOfVar> tmpVarFailedChallenge;
		Pair<CapturedVar,StateOfVar> tmpVarGoodChallenge;
		List<Pair<Pair<CapturedVar,StateOfVar>,Pair<CapturedVar,StateOfVar>>> diffs = 
				new ArrayList<Pair<Pair<CapturedVar,StateOfVar>,Pair<CapturedVar,StateOfVar>>>();
		
		for(CapturedVar variableOfGoodChallenge : currentVarOfGoodChallenge) {
			for(CapturedVar variableOfFailedChallenge : currentVarOfFailedChallenge) {
				if(variableOfGoodChallenge.equals(variableOfFailedChallenge)) {
					for(StateOfVar stateVariableOfGoodChallenge : variableOfGoodChallenge.states) {
						for(StateOfVar stateVariableOfFailedChallenge : variableOfFailedChallenge.states) {
							if(stateVariableOfGoodChallenge.isStateComparableTo(stateVariableOfFailedChallenge) &&
									!stateVariableOfGoodChallenge.newVal.equals(stateVariableOfFailedChallenge.newVal)) {
								System.out.println("Found difference between : " + variableOfGoodChallenge.name
										+ " on line (" + stateVariableOfGoodChallenge.line + ")" + " => Good challenge :"
										+ stateVariableOfGoodChallenge.newVal + " / Bad Challenge : " + stateVariableOfFailedChallenge.newVal);
								// We add diff because 1/ Comparable 2/ assignation differente
								tmpVarFailedChallenge = new ImmutablePair<CapturedVar,StateOfVar>(variableOfFailedChallenge, stateVariableOfFailedChallenge);
								tmpVarGoodChallenge = new ImmutablePair<CapturedVar,StateOfVar>(variableOfGoodChallenge, stateVariableOfGoodChallenge);
								tmpDiffElement = new ImmutablePair(tmpVarGoodChallenge,tmpVarFailedChallenge);
								diffs.add(tmpDiffElement);
							}
						}	
					}
				}
			}
		}
		return diffs;
	}

	public Pair<StateOfVar,StateOfVar> process() {
		// Iteration on result from all the input
		List<Pair<Pair<CapturedVar,StateOfVar>,Pair<CapturedVar,StateOfVar>>> diffs;
		int indexOfFirstResult = 0, indexOfSecondResult = 0;
		Map<String, CapturedVar> currentVarOfFailedChallenge, currentVarOfGoodChallenge;
		Pair<CapturedVar,StateOfVar> tmpGoodVarState;
		Pair<CapturedVar,StateOfVar> tmpFailedVarState;
		CapturedVar tmpCapturedVar;
		boolean isPassed = false;
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
						System.out.println("Challenge OK, input :" + currentResult.getKey());
						System.out.println("Challenge KO, input :" + currentResult2.getKey());
						System.out.println("Lancement des différences...");
						diffs = diffCauseEffectChain(
								new ArrayList<CapturedVar>(currentVarOfGoodChallenge.values()),
								new ArrayList<CapturedVar>(currentVarOfFailedChallenge.values()));
						for(Pair<Pair<CapturedVar,StateOfVar>,Pair<CapturedVar,StateOfVar>> diff : diffs) {
							tmpGoodVarState = diff.getLeft();
							tmpFailedVarState = diff.getRight();
							// apply diff from good result to fail result and run failed input
							tmpCapturedVar = tmpFailedVarState.getLeft();
							tmpCapturedVar.states.clear();
							tmpCapturedVar.states.add(tmpGoodVarState.getRight());
							// We put VAR in list to apply this update
							System.out.println("On insère le changement VAR NAME : " + tmpFailedVarState.getLeft().name + " VAR : " + tmpCapturedVar);
							DebugManipulation.capturedVarsToReplaceValues.put(tmpFailedVarState.getLeft().name,tmpCapturedVar);
							// HERE PROCESS CHALLENGE WITH NEW VALUE
							try {
							challenge.challenge(currentResult2.getKey());
							System.out.println("Challenge modifié pas planté !");
							System.out.println("Le problème est causé par : " + tmpFailedVarState.getLeft() +" ETAT : " + tmpFailedVarState.getRight());
							break;
							} catch(Exception e) {
								System.out.println("Challenge modifié planté, on passe à la prochaine différence");
							}
							isPassed = true;
						}

					}
					indexOfSecondResult += 1;
				}
			}
			indexOfFirstResult += 1;
			indexOfSecondResult = 0;
			if(isPassed) 
				break;
		}
		return null;
	}
	//
	//	private CapturedVar getFailedCapturedVarWithoutLastDiff(Pair<CapturedVar,StateOfVar> goodVarState,Pair<CapturedVar,StateOfVar> failedVarState) {
	//		CapturedVar capturedVar;
	//		for(StateOfVar goodState : goodVarState.getLeft().states)Â {
	//			
	//		}
	//		return null;
	//	}
}
