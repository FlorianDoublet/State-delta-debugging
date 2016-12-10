package spoon.utils;

import spoon.Launcher;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtBlock;
import spoon.reflect.code.CtCodeSnippetStatement;
import spoon.reflect.declaration.*;
import spoon.reflect.factory.Factory;
import spoon.reflect.reference.CtTypeReference;

import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Parameter;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by flori on 10/12/2016.
 * Used to add a method into a class.
 * Not used anymore for the moment.
 */
public class ClassProcessor {

    private Launcher launcher;
    private CtClass ctClass;

    public ClassProcessor(CtClass ctClass, Launcher launcher){
        this.launcher = launcher;
        this.ctClass = ctClass;
        process(ctClass);
    }


    public void process(CtClass ctClass) {


        //Create parameter
        final CtParameter<?> parameter = getFactory().Core().createParameter();
        parameter.setSimpleName("T input");

        //Create method type
        final CtTypeReference<GenericDeclaration> methodCtTypeReference = getFactory().Code().createCtTypeReference(GenericDeclaration.class);

        // Creates method.
        final CtCodeSnippetStatement statementMethod = getFactory().Code().createCodeSnippetStatement("return input");
        final CtBlock<?> ctBlockOfMethod = getFactory().Code().createCtBlock(statementMethod);
        final CtMethod method = getFactory().Core().createMethod();
        method.setBody(ctBlockOfMethod);



        method.setParameters(Collections.<CtParameter<?>>singletonList(parameter));
        method.addModifier(ModifierKind.PUBLIC);
        method.setSimpleName("<T> T test");

        // Apply transformation.
        ctClass.addMethod(method);

        System.out.println("PRINT LA CLASSE : " + ctClass);
    }


    private Factory getFactory(){return launcher.getFactory();}

}
