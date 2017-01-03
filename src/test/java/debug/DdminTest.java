package debug;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import challenge.MarkupChallenge;
import challenge.ReverseChallenge;
import debug.Ddmin;
import debug.FancyDDebugger;
import fr.univ_lille1.m2iagl.dd.Challenge;
import spoon.utils.ChallengeProcessor;
import utils.CapturedVar;

public class DdminTest {

	Ddmin ddmin;
	
	@Before
	public void setUp() throws Exception {
		FancyDDebugger f = new FancyDDebugger();
		
		ChallengeProcessor challengeProcessor = new ChallengeProcessor(Main.createLauncher(), "challenge 1");
		Map<String,Boolean> resultOfChallengeByInput = new HashMap<String,Boolean>();
		resultOfChallengeByInput.put("<b>foo<b>", false);
		resultOfChallengeByInput.put("foo", true);
		List<Map<String, CapturedVar>> listMapCapturedVar = new ArrayList<>();
		//Challenge modifiedChallenge = Main.createModifiedChallenge("ReverseChallenge.java");
		
		ddmin = new Ddmin("good", new ReverseChallenge());
	}

	@Test
	public void testEmptyChallengeReturnNull() {
		DebugChainElement d = new DebugChainElement(33, "inputReverse", "tmp", " became x", "iteration( 1 )");
		DebugChainElement d2 = new DebugChainElement(33, "inputReverse", "test", " became \n", "iteration( 2 )");
		List<DebugChainElement> goodChain = new ArrayList<>();
		List<DebugChainElement> badChain = new ArrayList<>();
		goodChain.add(d);
		badChain.add(d2);
		List<DebugChainElement> diff = ddmin.getDiffs(goodChain, badChain);
		List<DebugChainElement> ret = ddmin.process(diff);
		assertTrue(ret.get(0).description.equals(" became \n"));
	}

}
