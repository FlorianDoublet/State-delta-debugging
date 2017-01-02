package utils;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StateOfVarTest {

	StateOfVar s;
	
	@Before
	public void setUp() throws Exception {
		s = new StateOfVar(1, 2, "iteration( 1 )");
	}
}
