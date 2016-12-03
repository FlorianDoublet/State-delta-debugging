package spoon.utils;

import org.apache.log4j.Level;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtCatch;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FlorianDoublet on 03/12/2016.
 * This is a test of spoon implementation. Will be deleted later.
 * Get a try-catch block, see if the blck is empty
 */
public class CatchProcessor extends AbstractProcessor<CtCatch>{

    public final List<CtCatch> emptyCatchs = new ArrayList<CtCatch>();

    public boolean isToBeProcessed(CtCatch candidate) {
        return candidate.getBody().getStatements().size() >= 0;
    }

    public void process(CtCatch element) {
        getEnvironment().report(this, Level.WARN, element, "empty catch clause");
        emptyCatchs.add(element);
    }
}
