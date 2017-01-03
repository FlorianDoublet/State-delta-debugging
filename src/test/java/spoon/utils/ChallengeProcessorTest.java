package spoon.utils;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import debug.Main;
import fr.univ_lille1.m2iagl.dd.Challenge;
import spoon.Launcher;

public class ChallengeProcessorTest {

	ChallengeProcessor cp;
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@Before
	public void setUp() throws Exception {
		cp = new ChallengeProcessor(Main.createLauncher(), "MarkupChallenge");
	}

	@Test
	public void testProcessWork() {
		try {
			Challenge modifiedChallenge = null;
			modifiedChallenge = cp.process();
			assertTrue(modifiedChallenge != null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
