
import fr.univ_lille1.m2iagl.dd.Challenge;
import java.util.Arrays;
import java.util.List;


/**
 * Created by FlorianDoublet on 03/12/2016.
 * .Java file that will be transformed by our code !
 */
public class MarkupChallenge implements Challenge<String>{
    public Class<String> getInputFormat() {
        return String.class;
    }

    public List<String> getInputs() {
        return Arrays.asList("\"<b>foo</b>\"", "\'<b>foo</b>\'");
    }


    /**
     * Remove Html Markup method
     */
	public Object doIt(String s) {
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
        Boolean tag = false;
        Boolean quote = false;
        String out = "";
        for(Character c : input.toCharArray()){
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
        assert !out.contains("<");

	}

}
