import fr.univ.lille1.m2iagl.dd.Challenge;
import spoon.Launcher;
import spoon.processing.ProcessingManager;
import spoon.reflect.factory.Factory;
import spoon.support.QueueProcessingManager;
import spoon.utils.VariableProcessor;

/**
 * Created by FlorianDoublet on 03/12/2016.
 */
public class Main {
    public static void main(String[] args) {
        Challenge c = new MarkupChallenge();
        FancyDDebugger debugger = new FancyDDebugger();
        debugger.debug(c);
        test();
    }

    public static void test(){
        final String[] args = {
                "-i", "src/main/java/MarkupChallenge.java",
                "-o", "target/spooned/"
        };

        final Launcher launcher = new Launcher();
        launcher.setArgs(args);
        launcher.run();

        final Factory factory = launcher.getFactory();
        final ProcessingManager processingManager = new QueueProcessingManager(factory);
        final VariableProcessor processor = new VariableProcessor();
        processingManager.addProcessor(processor);
        processingManager.process(factory.Class().getAll());
    }
}