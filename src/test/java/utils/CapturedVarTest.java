package utils;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import debug.DebugChainElement;
import utils.CapturedVar;

public class CapturedVarTest {

	CapturedVar var;
	
	@Before
	public void setUp() throws Exception {
		var = new CapturedVar(5, false, "test", "test".getClass(), "iteration( 1 )");
	}

	@Test
	public void testBuildChainElementListNoNewState() {
		DebugChainElement l  = var.buildChainElement(var.states.get(0));
		DebugChainElement d = new DebugChainElement(5, "test", "this is a test", "was initialized to false", "iteration ( 1 )");
		assertTrue(l.description.equals(d.description));
	}
	
	@Test
	public void testBuildChainElementListWithNewState() {
		var.addState(5, "new Test", "iteration( 1 )");
		DebugChainElement l1  = var.buildChainElement(var.states.get(1));
		DebugChainElement d2 = new DebugChainElement(5, "test", "this is a test", " became new Test", "iteration ( 1 )");
		assertTrue(l1.description.equals(d2.description));
	}
	
	@Test
	public void testBuildChainElementListWithNewStateBynaryOperator() {
		var.addState(5, "new Test", "", "iteration( 1 )");
		DebugChainElement l1  = var.buildChainElement(var.states.get(1));
		DebugChainElement d2 = new DebugChainElement(5, "test", "this is a test"," became new Test", "iteration ( 1 )");
		assertTrue(l1.description.equals(d2.description));
	}

	@Test
	public void testUpdateLastCompleteStateValue(){
		var.addState(5, true, "iteration ( 2 )");
		var.updateLastCompleteStateValue(false);
		
		DebugChainElement l  = var.buildChainElement(var.states.get(1));
		DebugChainElement d = new DebugChainElement(5, "test", "this is a test", " became true", "iteration ( 1 )");
		assertTrue(l.description.equals(d.description));
		
	}
	
	@Test
	public void testEqualsOnSpecificLine(){
		CapturedVar var2 = new CapturedVar(5, false, "test", "test".getClass(), "iteration( 1 )");
		assertFalse(var.equalsOnSpecificLine(var2, 5));
	}
	
	@Test
	public void testEquals(){
		CapturedVar var2 = new CapturedVar(5, false, "test", "test".getClass(), "iteration( 1 )");
		assertTrue(var.equals(var2));
	}
	
	@Test
	public void testNotEquals(){
		CapturedVar var2 = new CapturedVar(5, false, "not test", "test".getClass(), "iteration( 1 )");
		assertFalse(var.equals(var2));
	}
	
	@Test
	public void testNotEqualsBecauseNotACapturedVar(){
		assertFalse(var.equals("fake"));
	}
}
