import org.junit.Test;
import spoon.Launcher;
import spoon.processing.ProcessingManager;
import spoon.reflect.factory.Factory;
import spoon.support.QueueProcessingManager;
import spoon.utils.CatchProcessor;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import static org.junit.Assert.assertEquals;

/**
 * Created by FlorianDoublet on 03/12/2016.
 */
public class CatchProcessorTest {
    @Test
    public void testCatchProcessor() throws Exception{
        final String[] args = {
                "-i", "src/test/java/DummyProgramToTest.java",
                "-o", "target/spooned/"
        };

        final Launcher launcher = new Launcher();
        launcher.setArgs(args);
        launcher.run();

        final Factory factory = launcher.getFactory();
        final ProcessingManager processingManager = new QueueProcessingManager(factory);
        final CatchProcessor processor = new CatchProcessor();
        processingManager.addProcessor(processor);
        processingManager.process(factory.Class().getAll());

        assertEquals(2, processor.emptyCatchs.size());

        File file = new File("target/test-classes/");

        try {
            // Convert File to a URL
            URL url = file.toURL();          // file:/c:/myclasses/
            URL[] urls = new URL[]{url};

            // Create a new class loader with the directory
            ClassLoader cl = new URLClassLoader(urls);

            // Load in the class; MyClass.class should be located in
            // the directory file:/c:/myclasses/com/mycompany
            Class cls = cl.loadClass("DummyProgramToTest");
            DummyProgramToTest o = (DummyProgramToTest) cls.newInstance();
            DummyProgramToTest.main(new String[]{"coucou"});
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
