package debug;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CapturedVar {
	
	public int line;
	public Object var;
	public String name;
	public Class varClass;

	public CapturedVar(int line, Object var, String name, Class varClass) {
		this.line = line;
		this.var = var;
		this.name = name;
		this.varClass = varClass;
	}

	@Override
	public String toString(){
		String s = "";
		s += "l(" + line + ") "
				+ name + " (equals) " + var;
		return s;
	}
}
