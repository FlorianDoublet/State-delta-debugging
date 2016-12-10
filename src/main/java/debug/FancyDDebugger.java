package debug;

import fr.univ_lille1.m2iagl.dd.Challenge;
import fr.univ_lille1.m2iagl.dd.DDebugger;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by FlorianDoublet on 03/12/2016.
 */
public class FancyDDebugger implements DDebugger<String>{

    public static List<CapturedVar> capturedVars = new ArrayList<CapturedVar>();



    public OurCauseEffectChain debug(Challenge<String> challenge) {
        for (String input: challenge.getInputs()){

            Boolean res;
            System.out.println("Input : " + input + " : ");
            try{
            	challenge.challenge(input);
            } catch (Exception e){
            	//Ya un soucis :'( 
            }


        }
        return null;
    }
    
    public static <T> T capture(T input, int line, String inputName){
        capturedVars.add(new CapturedVar(line, input, inputName, input.getClass()));
        return input;
    }
    
    public static <T> T convertInstanceOfObject(Object o, Class<T> clazz) {
        try {
            return clazz.cast(o);
        } catch(ClassCastException e) {
            return null;
        }
    }


}
