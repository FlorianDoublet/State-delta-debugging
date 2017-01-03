import fr.univ_lille1.m2iagl.dd.Challenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by Jonathan WADIN
 * .Java file that will be transformed by our code !
 */
public class WhereIsMyCatChallenge implements Challenge<String>{
    public Class<String> getInputFormat() {
        return String.class;
    }

    public List<String> getInputs() {
        return Arrays.asList("Bureau,Chambre,Salle � manger,Cuisine", "Jardin,Garage,Salon,Veranda");
    }


    /**
     * Where is my cat ?
     */
	public Object doIt(String input) {
		List<Boolean> result = new ArrayList<Boolean>();
		List<String> pieces = Arrays.asList(input.split(","));
		for(String piece : pieces) {
			if(piece.equals("Garage")) {
				result.add(true);
			} else {
				result.add(false);
			}
		}
		return result;
	}

	public void challenge(String input) {
		List<Boolean> result = new ArrayList<Boolean>();
		List<String> pieces = Arrays.asList(input.split(","));
		for(String piece : pieces) {
			if(piece.equals("Garage")) {
				result.add(true);
			} else {
				result.add(false);
			}
		}
		if(result.contains(true)) {
			return;
		} else {
			throw new RuntimeException("Cat not found");
		}
	}
}
