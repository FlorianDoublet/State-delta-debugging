package utils;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DebugManipulationTest {
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCaptureEmptyCapturedVars() {
		int ret = DebugManipulation.capture(8, 5, "test");
		assertTrue(DebugManipulation.capturedVars.get("test").lastVal.equals(ret));
		
		
	}
	
	@Test
	public void testCaptureNotEmptyCapturedVars() {
		DebugManipulation.capture(8, 5, "test");
		int ret = DebugManipulation.capture(7, 10, "test1");
		assertTrue(DebugManipulation.capturedVars.get("test1").lastVal.equals(ret));
		
	}

	
	@Test
	public void testCaptureNotEmptyCapturedVarsBinaryOperatorEqual() {
		DebugManipulation.resetAll();
		int ret = DebugManipulation.capture(7, 15, "test2", "=");
		assertTrue(DebugManipulation.capturedVars.get("test2").lastVal.equals(ret));
		
	}
	

	@Test
	public void testCaptureNotEmptyCapturedVarsBinaryOperator() {
		DebugManipulation.resetAll();
		DebugManipulation.capture(8, 5, "test", "+");
		int ret = DebugManipulation.capture(7, 10, "test", "+");
		assertFalse(DebugManipulation.capturedVars.get("test").lastVal.equals(ret));
		
	}
	
	@Test
	public void testCaptureNewVal() {
		int newVal = 9;
		DebugManipulation.resetAll();
		DebugManipulation.capture(8, 5, "test", "+");
		DebugManipulation.captureNewVal(newVal, "test" );
		assertEquals(DebugManipulation.capturedVars.get("test").states.get(DebugManipulation.capturedVars.get("test").states.size() - 1).completeState, newVal);
		
	}
	
	@Test
	public void testIterate() {
		DebugManipulation.resetAll();
		DebugManipulation.iterate(5);
		assertTrue(DebugManipulation.iterations.get(5).intValue() == 1);
	}
	
	@Test
	public void testIterateAlreadyExists() {
		DebugManipulation.iterate(5);
		DebugManipulation.iterate(5);
		assertFalse(DebugManipulation.iterations.get(5).intValue() == 2);
	}	
	
	@Test
	public void testResetIteration() {
		DebugManipulation.iterate(5);
		DebugManipulation.iterate(6);
		DebugManipulation.resetIteration();
		assertTrue(DebugManipulation.iterations.get(DebugManipulation.iterations.size() - 1) == null);
	}
	
	@Test
	public void compareTwoStateNotEqualsLine() {
		StateOfVar s1 = new StateOfVar(6, 45, "iteration( 1 )");
		StateOfVar s2 = new StateOfVar(5, 45, "iteration( 1 )");
		assertFalse(DebugManipulation.compareTwoState(s1, s2));
	}
	

	@Test
	public void compareTwoStateNotEqualsIteration() {
		StateOfVar s1 = new StateOfVar(6, 45, "iteration( 1 )");
		StateOfVar s2 = new StateOfVar(6, 45, "iteration( 2 )");
		assertFalse(DebugManipulation.compareTwoState(s1, s2));
	}
	
	@Test
	public void compareTwoStateEqual() {
		StateOfVar s1 = new StateOfVar(6, 45, "iteration( 1 )");
		StateOfVar s2 = new StateOfVar(6, 45, "iteration( 1 )");
		assertTrue(DebugManipulation.compareTwoState(s1, s2));
	}

	@Test
	public void iterationIsNotBigger() {
		String it1 = "iteration( 1 )";
		String it2 = "iteration( 2 )";
		assertFalse(DebugManipulation.iterationIsBigger(it1, it2));
	}
	
	@Test
	public void iterationIsBigger() {
		String it1 = "iteration( 12 )";
		String it2 = "iteration( 1 )";
		assertTrue(DebugManipulation.iterationIsBigger(it1, it2));
	}
	
	@Test
	public void iterationIsEqual() {
		String it1 = "iteration( 12 )";
		String it2 = "iteration( 12 )";
		assertFalse(DebugManipulation.iterationIsBigger(it1, it2));
	}
	
	

}
