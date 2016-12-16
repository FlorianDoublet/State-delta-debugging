package debug;

import fr.univ_lille1.m2iagl.dd.ChainElement;
import fr.univ_lille1.m2iagl.dd.Challenge;
import fr.univ_lille1.m2iagl.dd.DDebugger;
import spoon.Launcher;
import spoon.utils.ChallengeProcessor;
import utils.CapturedVar;
import utils.DebugManipulation;

import java.util.*;

import static utils.DebugManipulation.captureNewVal;
import static utils.DebugManipulation.capturedVars;


/**
 * Created by FlorianDoublet on 03/12/2016.
 */
public class FancyDDebugger implements DDebugger<String>{


    public DebugCauseEffectChain debug(Challenge<String> challenge) {

        ChallengeProcessor challengeProcessor = new ChallengeProcessor(this.createLauncher());
        Challenge modifiedChallenge = null;
        List<Map<String, CapturedVar>> listMapCapturedVar = new ArrayList<>();

        try {
            modifiedChallenge = challengeProcessor.process();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (String input: challenge.getInputs()){

            Boolean res;
            System.out.println("Input : " + input + " : ");
            try{
                modifiedChallenge.challenge(input);
                listMapCapturedVar.add(DebugManipulation.capturedVars);
            } catch (Exception e){
                listMapCapturedVar.add(DebugManipulation.capturedVars);
            	//Ya un soucis :'(
            }
            DebugManipulation.capturedVars = new LinkedHashMap<>();

        }

        for(Map<String, CapturedVar> capturedMap : listMapCapturedVar){
            DebugCauseEffectChain causeEffectChain = new DebugCauseEffectChain();

            for (Map.Entry<String, CapturedVar> entry : capturedMap.entrySet()) {
                CapturedVar value = entry.getValue();
                causeEffectChain.addChainList(value.buildChainElementList());
            }

            System.out.println("\n\n******** PROGRAM LAUNCH **********\n");
            for(ChainElement chaineElement : causeEffectChain.getChain()){
                System.out.println("line " + chaineElement.getLine() + " the var " + chaineElement.getVariable() + " " + chaineElement.getDescription());
            }
        }









        return null;
    }

    public Launcher createLauncher() {

        final Launcher launcher = new Launcher();
        launcher.setArgs(new String[] {"--source-classpath","target/classes"});
        launcher.addInputResource("src/main/resources/");
        launcher.buildModel();

        return launcher;

    }




    



}
