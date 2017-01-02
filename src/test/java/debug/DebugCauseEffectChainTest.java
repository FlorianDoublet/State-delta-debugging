package debug;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DebugCauseEffectChainTest {

	DebugCauseEffectChain d;
	
	@Before
	public void setUp() throws Exception {
		d = new DebugCauseEffectChain();
		DebugChainElement chain = new DebugChainElement(6, "cpt", 8, "was initialized to 8", "iteration ( 1 )");
		DebugChainElement chain1 = new DebugChainElement(9, "cpt", 7, " became 7", "iteration ( 2 )");
		d.add(chain);
		d.add(chain1);
	}

	@Test
	public void testUpdateLastCompleteStateValue() {
		int newVal = 9;
		String newDesc = " became 9";
		d.updateLastCompleteStateValue(newVal, newDesc);
		assertTrue((Integer)d.ourCauseEffectChain.get(d.ourCauseEffectChain.size() - 1 ).completeState == newVal 
				   && d.ourCauseEffectChain.get(d.ourCauseEffectChain.size() - 1 ).description == newDesc );
	}
	


}
