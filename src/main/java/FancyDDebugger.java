import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.univ_lille1.m2iagl.dd.CauseEffectChain;
import fr.univ_lille1.m2iagl.dd.Challenge;
import fr.univ_lille1.m2iagl.dd.DDebugger;


/**
 * Created by FlorianDoublet on 03/12/2016.
 */
public class FancyDDebugger implements DDebugger<String>{
	
	

    public OurCauseEffectChain debug(Challenge<String> challenge) {
        for (String input: challenge.getInputs()){

            Boolean res;
            System.out.println("Input : " + input + " : ");
            try{
            	challenge.challenge(input);
            } catch (Exception e){
            	//Ya un soucis :'( 
            }
            
            String x = test("lol");
            Integer z = test(15);

            /*for(Tuple c : classList){
            	
            	c.cls.cast(c.obj);
            }*/
            
        }
        return null;
    }
    
    public <T> T test(T input){
    	
    	
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
