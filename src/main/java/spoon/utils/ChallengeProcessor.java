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
 * This Classis used to tranform the code of our challenge
 */
public class ChallengeProcessor {

    public Launcher launcher;
    public String challengeName;

    public ChallengeProcessor(Launcher launcher, String challengeName){
        this.launcher = launcher;
        this.challengeName = challengeName;
    }

    public Challenge process() throws Exception {

        //create an empty snippet
        CtCodeSnippetStatement snippet = launcher.getFactory().Core().createCodeSnippetStatement();
        //Load the CtClass of our challenge
        CtClass challenge = (CtClass) launcher.getFactory().Package().getRootPackage().getElements(new NameFilter(challengeName)).get(0);

        //Our futur class of the challenge
        Class<?> challengeClass = null;
        try {
            //Load and compile the inital file of the challenge
            challengeClass = InMemoryJavaCompiler.compile(challenge.getQualifiedName(), challenge.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Beginning of our process of code transformation !
        //So we gonna iterate on all methods of our challenge
        for(Object e : challenge.getElements(new TypeFilter(CtMethod.class))) {
            CtMethod method = (CtMethod)e;
            //If it's the method that we are looking for.. Here "doIt"
            if(method.getSimpleName().equals("challenge")){
                //We launch here all or different "process" to treat the case we need and transform the code

                new CtAssignmentOperations(method, launcher);
                new CtVariableOperations(method, launcher);
                new CtUnaryOperatorOperations(method, launcher);
                new CtVariableReadOperations(method, launcher);
                new CtLoopOperation(method, launcher);
                //System.out.println(method.getBody().toString());
            }
        }

        //then reload the modified challenge in our class
        challengeClass = InMemoryJavaCompiler.compile(challenge.getQualifiedName(), challenge.toString());
        //And create a new instance of it
        Challenge modifiedChallenge = (Challenge) challengeClass.newInstance();

        return modifiedChallenge;

    }
}
