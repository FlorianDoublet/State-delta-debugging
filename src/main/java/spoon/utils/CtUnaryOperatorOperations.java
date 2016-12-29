package spoon.utils;

import spoon.Launcher;
import spoon.reflect.code.*;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.visitor.filter.TypeFilter;


/**
 * Created by flori on 27/12/2016.
 */
public class CtUnaryOperatorOperations {
    private CtMethod method;
    private Launcher launcher;

    public CtUnaryOperatorOperations(CtMethod method, Launcher launcher) {
        this.method = method;
        this.launcher = launcher;
        process();
    }

    public void process() {
        //We iterate over all the CtAssignment present in our method
        for (CtUnaryOperator unaryOp : this.method.getElements(new TypeFilter<>(CtUnaryOperator.class))) {
            //We ignore UnaryOperator like !var because it's useless and we have a problem with it.
            if(unaryOp.getKind() == UnaryOperatorKind.NOT) continue;
            String surrounded = "utils.DebugManipulation.capture(" + unaryOp.toString() + ", "
                    + unaryOp.getPosition().getLine() + ", \"" + unaryOp.getOperand() + "\")";
            //Apply it
            final CtCodeSnippetStatement statementMethod = launcher.getFactory().Code().createCodeSnippetStatement(surrounded);
            unaryOp.replace(statementMethod);

        }
    }


}
