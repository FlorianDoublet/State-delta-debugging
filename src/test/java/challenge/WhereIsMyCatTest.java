package challenge;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import challenge.WhereIsMyCatChallenge;

public class WhereIsMyCatTest {

	WhereIsMyCatChallenge c;
	
	@Before
	public void setUp() throws Exception {
		c = new WhereIsMyCatChallenge();
	}

	@Test
	public void testBadChain() {
		try {
			c.challenge(c.getInputs().get(0));
			fail();
		} catch (RuntimeException e){
			
		}
	}
	
	@Test
	public void testGoodChain() {
		try {
			c.challenge(c.getInputs().get(1));
		} catch (RuntimeException e){
			fail();
		}
	}

}
