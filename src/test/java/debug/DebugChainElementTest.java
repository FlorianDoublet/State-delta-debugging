package debug;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DebugChainElementTest {

	DebugChainElement d;
	
	@Before
	public void setUp() throws Exception {
		d = new DebugChainElement(6, "cpt", 8, "was initialized to 8", "iteration ( 1 )");
	}

	@Test
	public void testEquals() {
		DebugChainElement d1 = new DebugChainElement(6, "cpt", 8, "became 8", "iteration ( 1 )");
		assertTrue(d.equals(d1));
	}
	
	@Test
	public void testNotEqualsLine() {
		DebugChainElement d1 = new DebugChainElement(7, "cpt", 8, "became 8", "iteration ( 2 )");
		assertFalse(d.equals(d1));
	}
	
	@Test
	public void testNotEqualsValue() {
		DebugChainElement d1 = new DebugChainElement(6, "cpt", 9, "became 8", "iteration ( 2 )");
		assertFalse(d.equals(d1));
	}
	
	@Test
	public void testNotEqualsVarName() {
		DebugChainElement d1 = new DebugChainElement(6, "tmp", 8, "became 8", "iteration ( 2 )");
		assertFalse(d.equals(d1));
	}
	
	@Test
	public void testNotEqualsAtAll() {
		DebugChainElement d1 = new DebugChainElement(7, "tmp", 4, "became 8", "iteration ( 1 )");
		assertFalse(d.equals(d1));
	}
	
	@Test
	public void testNotEqualsNotADebugChainElement() {
		assertFalse(d.equals("Fake"));
	}

}
