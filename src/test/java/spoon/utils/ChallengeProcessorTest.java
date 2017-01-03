package spoon.utils;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import debug.Main;
import spoon.Launcher;

public class ChallengeProcessorTest {

	ChallengeProcessor cp;
	
	@Before
	public void setUp() throws Exception {
		cp = new ChallengeProcessor(Main.createLauncher(), "MarkupChallenge.java");
	}

	@Test
	public void test() {
		try {
			cp.process();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
