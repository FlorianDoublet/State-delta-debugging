package spoon.utils;

import org.apache.log4j.Level;
import spoon.Launcher;
import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtAssignment;
import spoon.reflect.code.CtCodeSnippetExpression;
import spoon.reflect.code.CtOperatorAssignment;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.visitor.filter.TypeFilter;

/**
 * Created by FlorianDoublet and wadinj on 10/12/2016.
 * Class used to be used in ChallengeProcessor in the CtAssignment Visitor.
 */
public class CtAssignmentOperations {
    private CtMethod method;
    private Launcher launcher;

    public CtAssignmentOperations(CtMethod method, Launcher launcher) {
        this.method = method;
        this.launcher = launcher;
        process();

    }

    public void process() {

        for (Object obj : this.method.getElements(new TypeFilter<>(CtAssignment.class))) {

            CtAssignment assignment = (CtAssignment) obj;

            /*System.out.println("l(" + assignment.getPosition().getLine() + ") : " +
                    assignment.getAssigned().toString() + " " + getOperator(assignment) + " " + assignment.getAssignment().toString() +
                    " (" + assignment.getType() + ")");*/

            //Surround the assignement with our method
            String surrounded = "debug.FancyDDebugger.capture(" + assignment.getAssignment() + ", "
                    + assignment.getPosition().getLine() + ", \"" + assignment.getAssignment().toString() + "\")";
            //Apply it
            final CtCodeSnippetExpression statementMethod = launcher.getFactory().Code().createCodeSnippetExpression(surrounded);
            assignment.setAssignment(statementMethod);

        }
    }


    private String getOperator(CtAssignment assignment) {
        String equalsOperator = "=";
        if (assignment instanceof CtOperatorAssignment<?, ?>){
            CtOperatorAssignment ctp = (CtOperatorAssignment) assignment;
            return binaryOperatorToString(ctp.getKind()) + equalsOperator;
        }
        return equalsOperator;
    }

    private String binaryOperatorToString(BinaryOperatorKind operator) {
        switch(operator) {
            case PLUS :
                return "+";
            case MINUS :
                return "-";
            case DIV :
                return "/";
            case MUL :
                return "*";
            default :
                return operator.name();
        }
    }


}
