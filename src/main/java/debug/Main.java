package debug;

import fr.univ_lille1.m2iagl.dd.CauseEffectChain;
import fr.univ_lille1.m2iagl.dd.ChainElement;
import fr.univ_lille1.m2iagl.dd.Challenge;
import spoon.Launcher;
import spoon.utils.ChallengeProcessor;
import utils.CapturedVar;

import java.util.Map;

import static utils.DebugManipulation.capturedVars;


/**
 * Created by FlorianDoublet on 03/12/2016.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        //this challenge is'nt really used now except to get the inputs
        //the challenge .java really used is located in resources/MarkupChallenge.java
        Challenge c = new MarkupChallenge();
        FancyDDebugger debugger = new FancyDDebugger();
        //call the debugger
        debugger.debug(c);

    }



}
