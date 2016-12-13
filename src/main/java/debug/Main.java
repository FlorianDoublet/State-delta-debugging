package debug;

import fr.univ_lille1.m2iagl.dd.CauseEffectChain;
import fr.univ_lille1.m2iagl.dd.ChainElement;
import fr.univ_lille1.m2iagl.dd.Challenge;
import spoon.Launcher;
import spoon.utils.ChallengeProcessor;
import utils.CapturedVar;

import java.util.Map;


/**
 * Created by FlorianDoublet on 03/12/2016.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Challenge c = new MarkupChallenge();
        FancyDDebugger debugger = new FancyDDebugger();
        debugger.debug(c);
        test();
        System.out.println("\n*** print of capturedVar list ***\n");
        DebugCauseEffectChain causeEffectChain = new DebugCauseEffectChain();
        for (Map.Entry<String, CapturedVar> entry : debugger.capturedVars.entrySet()) {
            String key = entry.getKey();
            CapturedVar value = entry.getValue();
            causeEffectChain.addChainList(value.buildChainElementList());
        }
        for(ChainElement chaineElement : causeEffectChain.getChain()){
            System.out.println("line " + chaineElement.getLine() + " the var " + chaineElement.getVariable() + " " + chaineElement.getDescription());
        }
    }

    public static void test() throws Exception {


        final Launcher launcher = new Launcher();
        launcher.setArgs(new String[] {"--source-classpath","target/classes"});
        launcher.addInputResource("src/main/resources/");
        launcher.buildModel();

        ChallengeProcessor challengeProcessor = new ChallengeProcessor(launcher);


    }

}
