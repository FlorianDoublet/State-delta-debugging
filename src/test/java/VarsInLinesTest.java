import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class VarsInLinesTest {

	@Test
	public void getVarsTest() {
		int line = 2;
		Object obj = new Object();
		List<Object> objs = new ArrayList<Object>();
		VarsInLines varsInLine = new VarsInLines(line, objs);
	}

}
