package spoon.utils;

import spoon.Launcher;
import spoon.reflect.code.CtCodeSnippetExpression;
import spoon.reflect.code.CtLocalVariable;
import spoon.reflect.code.CtVariableRead;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtVariable;
import spoon.reflect.visitor.filter.TypeFilter;

/**
 * Created by FlorianDoublet on 13/12/2016.
 */
public class CtVariableOperations {


    private CtMethod method;
    private Launcher launcher;

    public CtVariableOperations(CtMethod method, Launcher launcher) {
        this.method = method;
        this.launcher = launcher;
        process();

    }

    public void process(){

        for (Object obj : this.method.getElements(new TypeFilter<>(CtLocalVariable.class))) {

            CtLocalVariable variable = (CtLocalVariable) obj;

            String surrounded = "debug.FancyDDebugger.capture(" + variable.getAssignment() + ", "
                    + variable.getPosition().getLine() + ", \"" + variable + "\")";
            //Apply it
            final CtCodeSnippetExpression statementMethod = launcher.getFactory().Code().createCodeSnippetExpression(surrounded);
            variable.replace(statementMethod);

            /*System.out.println("l(" + variableRead.getPosition().getLine()+ ") : " +
                    variableRead.getVariable().toString());*/

        }

    }
}
