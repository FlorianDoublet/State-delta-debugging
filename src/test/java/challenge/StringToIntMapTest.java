package challenge;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StringToIntMapTest {

	StringToIntMapChallenge s;
	
	@Before
	public void setUp() throws Exception {
		s = new StringToIntMapChallenge();
	}

	@Test
	public void testBadChain() {
		try {
		s.challenge(s.getInputs().get(1));
		fail();
		} catch (Exception e){
			
		}
		
	}
	

	@Test
	public void testGoodChain() {
		try {
		s.challenge(s.getInputs().get(0));
		
		} catch (Exception e){
			fail();
		}
		
	}
}
