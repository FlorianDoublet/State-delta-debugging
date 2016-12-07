import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VarsInLines {
	
	private HashMap<Integer, List<Object>> lineVarsMap = new HashMap<Integer, List<Object>>();
	
	public VarsInLines(int line, List<Object> objs){
		lineVarsMap.put(Integer.valueOf(line), objs);
	}
	
	public List<Object> getVars(int line){
		Integer l = Integer.valueOf(line);
		
		if(lineVarsMap.containsKey(l)){
			return lineVarsMap.get(l);
		} else {
			return null;
		}
	}
	
	public void addVars(int line, List<Object> objs){
		List<Object> listObjs = this.getVars(line);
		if(listObjs!=null){
			listObjs.addAll(objs);
		}else{
			lineVarsMap.put(Integer.valueOf(line), objs);
		}
	}
	
	public void addVar(int line, Object obj){
		List<Object> listObjs = this.getVars(line);
		if(listObjs!=null){
			listObjs.add(obj);
		}else{
			List<Object> objs = new ArrayList<Object>();
			objs.add(obj);
			lineVarsMap.put(Integer.valueOf(line),objs);
		}
	}

}
