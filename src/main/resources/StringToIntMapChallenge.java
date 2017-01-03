
import fr.univ_lille1.m2iagl.dd.Challenge;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


/**
 * Created by Florian Doublet
 * .Java file that will be transformed by our code !
 */
public class StringToIntMapChallenge implements Challenge<String>{
    public Class<String> getInputFormat() {
        return String.class;
    }

    public List<String> getInputs() {
        return Arrays.asList("1,2,3,4", "1,2,test,4");
    }


    /**
     * Transform string to integer into a map
     */
	public Object doIt(String input) {
		List<String> strList = Arrays.asList(input.split(","));
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		int i = 0;
		while(i < strList.size()){
			map.put(strList.get(i), Integer.valueOf(strList.get(i)));
			++i;
		}
		return map;
	}

	public void challenge(String input) {
		List<String> strList = Arrays.asList(input.split(","));
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		int i = 0;
		while(i < strList.size()){
			map.put(strList.get(i), Integer.valueOf(strList.get(i)));
			++i;
		}
	}
}
