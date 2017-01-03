package challenge;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class MarkupChallengeTest {

	MarkupChallenge c;
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@Before
	public void setUp() throws Exception {
		c = new MarkupChallenge();
	}

	@Test
	public void testBadChain() {
		exception.expect(RuntimeException.class);
		c.challenge(c.getInputs().get(0));
	}
	
	@Test
	public void testGoodChain() {
		try{
		c.challenge(c.getInputs().get(1));
		} catch (RuntimeException e){
			Assert.fail();
		}
		
	}

}
