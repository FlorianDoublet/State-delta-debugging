package debug;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import debug.Ddmin;
import debug.FancyDDebugger;
import spoon.utils.ChallengeProcessor;
import utils.CapturedVar;

public class DdminTest {

	@Before
	public void setUp() throws Exception {
		FancyDDebugger f = new FancyDDebugger();
		
		ChallengeProcessor challengeProcessor = new ChallengeProcessor(Main.createLauncher(), "challenge 1");
		Map<String,Boolean> resultOfChallengeByInput = new HashMap<String,Boolean>();
		resultOfChallengeByInput.put("<b>foo<b>", false);
		resultOfChallengeByInput.put("foo", true);
		List<Map<String, CapturedVar>> listMapCapturedVar = new ArrayList<>();
		
		//Ddmin ddmin = new Ddmin(challengeProcessor,resultOfChallengeByInput,listMapCapturedVar);
	}

	@Test
	public void test() {
		assertTrue(true);
	}

}
