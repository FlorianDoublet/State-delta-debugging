package spoon.utils;

import spoon.Launcher;
import spoon.reflect.code.*;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.visitor.filter.TypeFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FlorianDoublet on 10/12/2016.
 */
public class CtVariableReadOperations implements CtOperationItf {


    private CtMethod method;
    private Launcher launcher;

    public CtVariableReadOperations(CtMethod method, Launcher launcher) {
        this.method = method;
        this.launcher = launcher;
        process();

    }

    public void process(){

        for (Object obj : this.method.getElements(new TypeFilter<>(CtVariableRead.class))) {

            CtVariableRead variableRead = (CtVariableRead) obj;

            String surrounded = "utils.DebugManipulation.readVar(" + variableRead + ", "
                    + variableRead.getPosition().getLine() + ", \"" + variableRead.toString() + "\")";
            //Apply it
            final CtCodeSnippetExpression statementMethod = launcher.getFactory().Code().createCodeSnippetExpression(surrounded);
            variableRead.replace(statementMethod);




        }

    }
}
