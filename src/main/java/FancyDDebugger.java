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
            res = challenge.oracle(input);

            System.out.println("\t\t" + res);
        }
        return null;
    }

}
