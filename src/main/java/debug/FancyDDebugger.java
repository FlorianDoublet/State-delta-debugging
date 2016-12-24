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


    public DebugCauseEffectChain debug(Challenge<String> challenge) {

        ChallengeProcessor challengeProcessor = new ChallengeProcessor(this.createLauncher());
        Challenge modifiedChallenge = null;
        //List of map of CapturedVar, will be used to store all CapturedVar Map instance of each run of the modified challenge
        List<Map<String, CapturedVar>> listMapCapturedVar = new ArrayList<>();
        // Result of each challenge
        Map<String,Boolean> resultOfChallengeByInput = new HashMap<String,Boolean>();
        try {
            //Our ChallengeProcessor create the modifiedChallenge
            modifiedChallenge = challengeProcessor.process();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Then for each of our challengeInput tun the modifiedChallenge with it
        for (String input: challenge.getInputs()){

            System.out.println("Input : " + input + " : ");
            try{
                //Run the challenge
                modifiedChallenge.challenge(input);
                //Capture the static capturedVars fill by DebugManipulation for the challenge we just run
                listMapCapturedVar.add(DebugManipulation.capturedVars);
                resultOfChallengeByInput.put(input, true);
            } catch (Exception e){
                //same
                //If we are in the catch it mean that the challenge fail
                listMapCapturedVar.add(DebugManipulation.capturedVars);
                resultOfChallengeByInput.put(input, false);
            }
            //We reset the static map contained in DebugManipulation, for the next run of challenge
            DebugManipulation.capturedVars = new LinkedHashMap<>();

        }
        Ddmin ddmin = new Ddmin(challengeProcessor,resultOfChallengeByInput,listMapCapturedVar);
        
        //then we wll build the CauseEffectChain for each previous run
        //thanks to their CapturedVar LinkedMap
        for(Map<String, CapturedVar> capturedMap : listMapCapturedVar){
            DebugCauseEffectChain causeEffectChain = new DebugCauseEffectChain();
            //build the cause effect chain
            for (Map.Entry<String, CapturedVar> entry : capturedMap.entrySet()) {
                CapturedVar value = entry.getValue();
                causeEffectChain.addChainList(value.buildChainElementList());
            }

            //print it
            System.out.println("\n\n******** PROGRAM LAUNCH **********\n");
            for(ChainElement chaineElement : causeEffectChain.getChain()){
                System.out.println("line " + chaineElement.getLine() + " the var " + chaineElement.getVariable() + " " + chaineElement.getDescription());
            }
        }




        return null;
    }

    /**
     * Create the launcher.
     * @return
     */
    public Launcher createLauncher() {

        final Launcher launcher = new Launcher();
        launcher.setArgs(new String[] {"--source-classpath","target/classes"});
        /**
         * We say that we are going to pick-up our .java  challenges file in the resources directory
         */
        launcher.addInputResource("src/main/resources/");
        launcher.buildModel();

        return launcher;

    }




    



}
