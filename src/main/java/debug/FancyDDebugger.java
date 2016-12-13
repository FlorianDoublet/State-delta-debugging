package debug;

import fr.univ_lille1.m2iagl.dd.Challenge;
import fr.univ_lille1.m2iagl.dd.DDebugger;
import utils.CapturedVar;

import java.util.*;


/**
 * Created by FlorianDoublet on 03/12/2016.
 */
public class FancyDDebugger implements DDebugger<String>{

    public static Map<String, CapturedVar> capturedVars = new LinkedHashMap<>();
    public static boolean waitForNewValue = false;


    public DebugCauseEffectChain debug(Challenge<String> challenge) {
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




    
    public static <T> T capture(T inputVal, int line, String inputName){
        if(capturedVars.containsKey(inputName)){
            capturedVars.get(inputName).addState(line, inputVal);
        } else {
            capturedVars.put(inputName, new CapturedVar(line, inputVal, inputName, inputVal.getClass()));
        }
        return inputVal;
    }

    public static <T> T capture(T inputVal, int line, String inputName, String binaryOperator){
        System.out.println(line);
        if(!binaryOperator.equals("=")){
            waitForNewValue = true;
        }
        if(capturedVars.containsKey(inputName)){
            capturedVars.get(inputName).addState(line, inputVal, binaryOperator);
        } else {
            capturedVars.put(inputName, new CapturedVar(line, inputVal, inputName, inputVal.getClass()));
        }
        return inputVal;
    }

    public static void captureNewVal(Object inputVal, String inputName ){
        capturedVars.get(inputName).changeLastNewValue(inputVal);
        waitForNewValue = false;
    }
    
    public static <T> T convertInstanceOfObject(Object o, Class<T> clazz) {
        try {
            return clazz.cast(o);
        } catch(ClassCastException e) {
            return null;
        }
    }


}
