package spoon.utils;

import debug.FancyDDebugger;
import fr.univ_lille1.m2iagl.dd.Challenge;
import org.mdkt.compiler.InMemoryJavaCompiler;
import spoon.Launcher;
import spoon.processing.AbstractProcessor;
import spoon.processing.ProcessingManager;
import spoon.reflect.code.*;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.factory.Factory;
import spoon.reflect.visitor.CtScanner;
import spoon.reflect.visitor.filter.NameFilter;
import spoon.reflect.visitor.filter.TypeFilter;
import spoon.support.QueueProcessingManager;

/**
 * Created by FlorianDoublet on 10/12/2016.
 */
public class ChallengeProcessor {

    public Launcher launcher;

    public ChallengeProcessor(Launcher launcher){
        this.launcher = launcher;
        try {
            this.process();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void process() throws Exception {

        CtCodeSnippetStatement snippet = launcher.getFactory().Core().createCodeSnippetStatement();


        CtClass challenge = (CtClass) launcher.getFactory().Package().getRootPackage().getElements(new NameFilter("MarkupChallenge")).get(0);

        //new ClassProcessor(foo, launcher);

        Class<?> challengeClass = null;
        try {
            challengeClass = InMemoryJavaCompiler.compile(challenge.getQualifiedName(), challenge.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        for(Object e : challenge.getElements(new TypeFilter(CtMethod.class))) {
            CtMethod method = (CtMethod)e;
            if(method.getSimpleName().equals("doIt")){

                new CtAssignmentOperations(method, launcher);
                //Todo : make it work
                new CtVariableOperations(method, launcher);
                //new CtVariableReadOperations(method, launcher);

            }
        }

        challengeClass = InMemoryJavaCompiler.compile(challenge.getQualifiedName(), challenge.toString());
        Challenge modifiedChallenge = (Challenge) challengeClass.newInstance();
        FancyDDebugger dDebugger = new FancyDDebugger();
        dDebugger.debug(modifiedChallenge);



    }
}
