package debug;

import fr.univ_lille1.m2iagl.dd.Challenge;
import spoon.Launcher;
import spoon.utils.ChallengeProcessor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by FlorianDoublet on 03/12/2016.
 */
public class Main {
    public static void main(String[] args) throws Exception {

        List<String> fileNames = getAllFilesNameFromRessourceDirectory();

        for(String fileName : fileNames){
            Challenge modifiedChallenge = createModifiedChallenge(fileName);
            System.out.println("\n ************ " + fileName + " CHALLENGE ! ************\n");
            FancyDDebugger debugger = new FancyDDebugger();
            debugger.debug(modifiedChallenge);
        }


    }

    /**
     * create the ModifiedChallenge
     * @param challengeName
     * @return
     */
    public static Challenge createModifiedChallenge(String challengeName){
        ChallengeProcessor challengeProcessor = new ChallengeProcessor(createLauncher(), challengeName);
        Challenge modifiedChallenge = null;
        try {
            //Our ChallengeProcessor create the modifiedChallenge
            modifiedChallenge = challengeProcessor.process();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modifiedChallenge;
    }

    /**
     * Create the launcher.
     * @return
     */
    public static Launcher createLauncher() {

        final Launcher launcher = new Launcher();
        launcher.setArgs(new String[] {"--source-classpath","target/classes"});
        /**
         * We say that we are going to pick-up our .java  challenges file in the resources directory
         */
        launcher.addInputResource("src/main/resources/");
        launcher.buildModel();

        return launcher;

    }

    public static List<String> getAllFilesNameFromRessourceDirectory(){
        List<String> results = new ArrayList<String>();


        File[] files = new File("src/main/resources/").listFiles();
        //If this pathname does not denote a directory, then listFiles() returns null.

        for (File file : files) {
            if (file.isFile()) {
                results.add(file.getName().substring(0, file.getName().length()-5));
            }
        }
        return results;
    }





}
