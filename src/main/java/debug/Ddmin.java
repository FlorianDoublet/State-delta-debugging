package debug;

import java.util.ArrayList;
import java.util.LinkedHashMap;
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

	private Challenge challenge;

	private Object input;


	public Ddmin(Object input, Challenge challenge) {
		this.input = input;
		this.challenge = challenge;
	}

	public List<DebugChainElement> process(List<DebugChainElement> diffs) {
		List<DebugChainElement> causes = new ArrayList<>();
		if (diffs.isEmpty()) return causes;

		List<List<DebugChainElement>> diffsByIterationList = new ArrayList<>();

		String previousIteration = diffs.get(0).iteration;
		diffsByIterationList.add(new ArrayList<>());
		int i = 0;
		//build the List of diff by iteration
		for (DebugChainElement diff : diffs) {
			String iteration = diff.iteration;
			if (iteration.equals(previousIteration)) {
				diffsByIterationList.get(i).add(diff);
			} else {
				i++;
				previousIteration = iteration;

				List<DebugChainElement> diffsForIt = new ArrayList<>();
				diffsForIt.add(diff);
				diffsByIterationList.add(diffsForIt);
			}
		}

		for (List<DebugChainElement> diffsByIt : diffsByIterationList) {
			//call subprocess and fill the cause array
			causes.addAll(subProcess(diffsByIt));
		}

		return causes;
	}

	public List<DebugChainElement> subProcess(List<DebugChainElement> diffs) {
		List<DebugChainElement> causes = new ArrayList<>();

		List<DebugChainElement> diffsToTest = new ArrayList<>(diffs);

		DebugChainElement temporaryRemoved = null;
		while (!diffsToTest.isEmpty()) {
			Boolean programFail = !test(diffsToTest);
			if (!programFail) {
				break; // if the program don't fail, we break the loop
			} else {
				if(temporaryRemoved != null){
					causes.add(temporaryRemoved);
				}
				temporaryRemoved = diffsToTest.remove(0);
				programFail = !test(diffsToTest);
			}

		}
		if (temporaryRemoved != null) {
			causes.add(temporaryRemoved);
		}

		return causes;
	}


	public Boolean test(List<DebugChainElement> diffs) {
		//apply diffs here
		applyDiffs(diffs);
		try {
			this.challenge.challenge(this.input);
			return true;
		} catch (Exception e) {
			if (e instanceof RuntimeException) {
				//we only considere runtimeException as a failure
				return false;
			} else {
				return true;
			}
		}
	}

	public void applyDiffs(List<DebugChainElement> diffs) {
		//empty the linkedList from potential previous value
		DebugManipulation.capturedVarsToReplaceValues = new LinkedHashMap<>();

		for (DebugChainElement diff : diffs) {
			CapturedVar diffToApply = new CapturedVar(diff.line, diff.value, diff.varName, diff.value.getClass(), diff.iteration);
			//add to the linkedList
			DebugManipulation.capturedVarsToReplaceValues.put(diff.varName, diffToApply);
		}
	}


	/**
	 * Get the diffs beetween the good run and the bad run.
	 *
	 * @param goodChain
	 * @param badChain
	 * @return the badChain diffs (element which potentially break the program)
	 */
	public List<DebugChainElement> getDiffs(List<DebugChainElement> goodChain, List<DebugChainElement> badChain) {
		List<DebugChainElement> diffs = new ArrayList<>();
		for (DebugChainElement chainElement : badChain) {
			if (!goodChain.contains(chainElement)) {
				diffs.add(chainElement);
			}
		}
		return diffs;
	}
}