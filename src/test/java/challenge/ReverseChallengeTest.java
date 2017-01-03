package challenge;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ReverseChallengeTest {

	ReverseChallenge c;
	
	@Before
	public void setUp() throws Exception {
		c = new ReverseChallenge();
	}

	@Test
	public void testBadChain() {
		try {
		c.challenge(c.getInputs().get(0));
		} catch (RuntimeException e){
			fail();
		}	
	}
	
	@Test
	public void testGoodChain() {
		try {
		c.challenge(c.getInputs().get(1));
		Assert.fail();
		} catch (RuntimeException e){
			
		}	
	}

}
