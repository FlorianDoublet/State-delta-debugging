package debug;

import fr.univ_lille1.m2iagl.dd.Challenge;
import fr.univ_lille1.m2iagl.dd.DDebugger;
import utils.CapturedVar;

import java.util.*;


/**
 * Created by FlorianDoublet on 03/12/2016.
 */
public class FancyDDebugger implements DDebugger<String>{


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




    



}
