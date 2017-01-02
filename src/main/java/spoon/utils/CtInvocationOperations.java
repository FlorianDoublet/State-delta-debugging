package spoon.utils;

import spoon.Launcher;
import spoon.reflect.code.CtCodeSnippetExpression;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.visitor.filter.TypeFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Florian on 02/01/2017.
 */
public class CtInvocationOperations {
    private CtMethod method;
    private Launcher launcher;

    public CtInvocationOperations(CtMethod method, Launcher launcher) {
        this.method = method;
        this.launcher = launcher;
        process();

    }

    public void process(){

        for (CtInvocation invocation : this.method.getElements(new TypeFilter<>(CtInvocation.class))) {

            List<CtExpression> argsToReplace = new ArrayList<>();

                String name = invocation.getExecutable().getSimpleName();

                if (!invocation.getArguments().isEmpty()) {

                    for (Object arg : invocation.getArguments()) {
                        if (arg instanceof CtExpression) {
                            CtExpression argExpr = (CtExpression) arg;
                            String surrounded;
                            String targetName = invocation.getTarget().toString();
                            if(arg.toString().contains("\"")){
                                surrounded = "utils.DebugManipulation.captureArg(" + arg + ", "
                                        + invocation.getPosition().getLine() + "," + arg.toString() + ", \"" + name + "\", \"" + targetName + "\")";
                            } else {
                                surrounded = "utils.DebugManipulation.captureArg(" + arg + ", "
                                        + invocation.getPosition().getLine() + ", \"" + arg + "\", \"" + name + "\", \"" + targetName + "\")";
                            }

                            //Apply it
                            final CtCodeSnippetExpression statementMethod = launcher.getFactory().Code().createCodeSnippetExpression(surrounded);
                            argsToReplace.add(statementMethod);
                        }
                    }
                    invocation.setArguments(argsToReplace);
                }
            }




    }
}