package spoon.utils;

import spoon.Launcher;
import spoon.reflect.code.*;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.visitor.filter.TypeFilter;

/**
 * Created by FlorianDoublet on 10/12/2016.
 * NOT USED ANYMORE (We probably gonna delete it latter)
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
            if(variableRead.getParent() instanceof CtInvocation) {
                CtInvocation invocation = (CtInvocation) variableRead.getParent();
                String name = invocation.getExecutable().getSimpleName();

                if (!invocation.getArguments().isEmpty()) {
                    for (Object arg : invocation.getArguments()) {
                        if (arg instanceof CtExpression) {
                            CtExpression argExpr = (CtExpression) arg;
                            String surrounded;
                            if(arg.toString().contains("\"")){
                                surrounded = "utils.DebugManipulation.captureArg(" + arg + ", "
                                        + invocation.getPosition().getLine() + "," + arg.toString() + ", \"" + name + "\", \"" + variableRead + "\")";
                            } else {
                                surrounded = "utils.DebugManipulation.captureArg(" + arg + ", "
                                        + invocation.getPosition().getLine() + "," + arg + ", \"" + name + "\", \"" + variableRead + "\")";
                            }

                            //Apply it
                            final CtCodeSnippetExpression statementMethod = launcher.getFactory().Code().createCodeSnippetExpression(surrounded);
                            argExpr.replace(statementMethod);
                        }
                    }
                    return;
                }
            }


            String surrounded = "utils.DebugManipulation.readVar(" + variableRead + ", "
                    + variableRead.getPosition().getLine() + ", \"" + variableRead.toString() + "\")";
            //Apply it
            final CtCodeSnippetExpression statementMethod = launcher.getFactory().Code().createCodeSnippetExpression(surrounded);
            variableRead.replace(statementMethod);


        }

    }
}
