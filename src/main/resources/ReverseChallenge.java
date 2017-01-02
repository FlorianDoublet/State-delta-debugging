package challenge;

import fr.univ_lille1.m2iagl.dd.Challenge;
import java.util.Arrays;
import java.util.List;


/**
 * Created by FlorianDoublet on 03/12/2016.
 * .Java file that will be transformed by our code !
 */
public class ReverseChallenge implements Challenge<String>{
    public Class<String> getInputFormat() {
        return String.class;
    }

    public List<String> getInputs() {
        return Arrays.asList("Cornichon de Bordeaux", "Carotte de Chamonix\n");
    }


    /**
     * Reverse string and check if last is equals to x
     */
	public Object doIt(String s) {
		return null;
	}

	public void challenge(String input) {
		String inputReverse = "";
		for(int i = input.length()-1 ; i >= 0 ; i --) {
			inputReverse += input.charAt(i);
		}
		assert inputReverse.substring(inputReverse.length()-1).equals("x");
	}

}
