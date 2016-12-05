import java.util.Arrays;
import java.util.List;

import fr.univ.lille1.m2iagl.dd.Challenge;

/**
 * Created by FlorianDoublet on 03/12/2016.
 */
public class MarkupChallenge implements Challenge<String>{
    public Class<String> getInputFormat() {
        return String.class;
    }

    public List<String> getInputs() {
        return Arrays.asList("\"<b>foo</b>\"", "\'<b>foo</b>\'");
    }

    public boolean oracle(String input) {
        String res = removeHtmlMarkup(input);
        //could be deleted, it's to print it for the example
        System.out.println("\toperation return : " + res);
        if(res.contains("<") || res.contains(">")){
            return false;
        }else{
            return true;
        }
    }

    public String getJavaProgram() {
        return "foo";
    }

    private String removeHtmlMarkup(String s){
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
}
