package challenge;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CaesarCipherChallengeTest {

	CaesarCipherChallenge c;
	
	@Before
	public void setUp() throws Exception {
		c = new CaesarCipherChallenge();
	}

	@Test
	public void testGoodChain() {
		try {
			c.challenge(c.getInputs().get(0));
		} catch (Exception e){
			fail();
		}
	}
	
	@Test
	public void testBadChain() {
		try {
			c.challenge(c.getInputs().get(1));
			fail();
		} catch (Exception e){
			
		}
	}

}
