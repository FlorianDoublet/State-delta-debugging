package debug;

import java.util.Arrays;
import java.util.List;
import fr.univ_lille1.m2iagl.dd.Challenge;


/**
 * Created by FlorianDoublet on 03/12/2016.
 *  WARKING !!! This file is'nt used for the transformation, it's the file present in resources !!
 */
public class MarkupChallenge implements Challenge<String>{
    public Class<String> getInputFormat() {
        return String.class;
    }

    public List<String> getInputs() {
        return Arrays.asList("\"<b>foo</b>\"", "\'<b>foo</b>\'");
    	//return Arrays.asList("Y","M");
    }


    /**
     * Remove Html Markup method
     */
	public Object doIt(String s) {
//		String mars = "ARS";
//		System.out.println("ATTENTION MARS ARRIVE");
//		mars = s + mars;
//		System.out.println("MARS :"  + mars);
//		if(!mars.equals("MARS")) {
//			throw new RuntimeException("done");
//		}
//		return "coucou";
				Boolean tag = false;
        Boolean quote = false;
        String out = "";
        for(Character c : s.toCharArray()){
            if(c == '<' && !quote){
                tag = true;
            } else if (c == '>' && !quote){
                tag = false;
            } else if(c == '\"' || (c == '\'' && tag)){
                quote = !quote;
            } else if (!tag){
                out += c;
            }
        }
        return out;
	}

	public void challenge(String input) {
		String res = (String) doIt(input);
        //could be deleted, it's to print it for the example
        System.out.println("\toperation return : " + res);
        if(res.contains("<") || res.contains(">")){
            throw new RuntimeException();
        }
		
	}

}
