package debug;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import fr.univ_lille1.m2iagl.dd.ChainElement;
import fr.univ_lille1.m2iagl.dd.Challenge;
import fr.univ_lille1.m2iagl.dd.DDebugger;
import spoon.Launcher;
import spoon.utils.ChallengeProcessor;
import utils.CapturedVar;
import utils.DebugManipulation;


/**
 * Created by FlorianDoublet on 03/12/2016.
 * Our Implementation of DDebugger
 */
public class FancyDDebugger implements DDebugger<String>{

    public static DebugCauseEffectChain runtimeCauseEffectChain = new DebugCauseEffectChain();

    public Object goodInput;
    public DebugCauseEffectChain goodChain = new DebugCauseEffectChain();
    public DebugCauseEffectChain badChain = new DebugCauseEffectChain();

    public DebugCauseEffectChain debug(Challenge modifiedChallenge) {


        //List of map of CapturedVar, will be used to store all CapturedVar Map instance of each run of the modified challenge
        List<Map<String, CapturedVar>> listMapCapturedVar = new ArrayList<>();


        //Then for each of our challengeInput tun the modifiedChallenge with it
        for (Object input: modifiedChallenge.getInputs()){

            System.out.println("Input : " + input + " : ");
            try{
                //Run the challenge
                modifiedChallenge.challenge(input);
                //Capture the static capturedVars fill by DebugManipulation for the challenge we just run
                listMapCapturedVar.add(DebugManipulation.capturedVars);
                goodInput = input;

                goodChain.ourCauseEffectChain = new ArrayList<>(runtimeCauseEffectChain.ourCauseEffectChain);

            } catch (Exception e){
                //same
                //If we are in the catch it mean that the challenge fail
                listMapCapturedVar.add(DebugManipulation.capturedVars);

                badChain.ourCauseEffectChain = new ArrayList<>(runtimeCauseEffectChain.ourCauseEffectChain);

            }
            //We reset the static map contained in DebugManipulation, for the next run of challenge
            DebugManipulation.capturedVars = new LinkedHashMap<>();
            runtimeCauseEffectChain.ourCauseEffectChain = new ArrayList<>();

        }


        //printGoodChain(goodChain);
        //printBadChain(badChain);

        Ddmin ddmin = new Ddmin(goodInput, modifiedChallenge);

        List<DebugChainElement> diffs = ddmin.getDiffs(goodChain.getDebugChain(), badChain.getDebugChain());

        //printdiffs(diffs);


        System.out.println("\n ******* CAUSE EFFECT CHAIN :  ******");
        List<DebugChainElement> causeEffectChain =  ddmin.process(diffs);
        for(DebugChainElement chainElement : causeEffectChain){
            System.out.println("line " + chainElement.getLine() + " the var " + chainElement.getVariable() + " " + chainElement.getDescription());
        }

        return null;
    }

    public void printGoodChain(DebugCauseEffectChain goodChain){
        System.out.println("\n ********  GOOD CHAIN ******* \n");
        for(ChainElement chainElement : goodChain.getChain()){
            System.out.println("line " + chainElement.getLine() + " the var " + chainElement.getVariable() + " " + chainElement.getDescription());
        }
    }

    public void printBadChain(DebugCauseEffectChain badChain){
        System.out.println("\n ********  BAD CHAIN ******* \n");
        for(ChainElement chainElement : badChain.getChain()){
            System.out.println("line " + chainElement.getLine() + " the var " + chainElement.getVariable() + " " + chainElement.getDescription());
        }
    }

    public void printdiffs(List<DebugChainElement> diffs){
        System.out.println("\n ********  DIFFS ******* \n");
        for(DebugChainElement diff : diffs){
            System.out.println("line " + diff.getLine() + " the var " + diff.getVariable() + " " + diff.getDescription());
        }
    }






    



}
