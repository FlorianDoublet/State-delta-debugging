package debug;

import fr.univ_lille1.m2iagl.dd.Challenge;
import spoon.Launcher;
import spoon.utils.ChallengeProcessor;


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
        for(CapturedVar cpt : debugger.capturedVars){
            System.out.println(cpt.toString());
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
