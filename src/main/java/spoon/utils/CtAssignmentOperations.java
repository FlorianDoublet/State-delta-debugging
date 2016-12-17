package spoon.utils;

import org.apache.log4j.Level;
import spoon.Launcher;
import spoon.reflect.code.*;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.visitor.filter.TypeFilter;

/**
 * Created by FlorianDoublet and wadinj on 10/12/2016.
 * Class used to capture all the assignment operation in our code.
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
        //We iterate over all the CtAssignment present in our method
        for (Object obj : this.method.getElements(new TypeFilter<>(CtAssignment.class))) {

            CtAssignment assignment = (CtAssignment) obj;

            ///Surround the assignment with our method
            String surrounded = "utils.DebugManipulation.capture(" + assignment.getAssignment() + ", "
                    + assignment.getPosition().getLine() + ", \"" + assignment.getAssigned().toString() + "\", \"" + getOperator(assignment) + "\")";
            //Apply it
            final CtCodeSnippetExpression statementMethod = launcher.getFactory().Code().createCodeSnippetExpression(surrounded);
            assignment.setAssignment(statementMethod);


            //Then we gonna add a new line to capture the new value of the object. Maybe the previous line should be replaced by this one
            String captureNewValue = "utils.DebugManipulation.captureNewVal(" + assignment.getAssigned() + ", \"" + assignment.getAssigned() + "\")";
            final CtCodeSnippetStatement captureNewValSnippet = launcher.getFactory().Code().createCodeSnippetStatement(captureNewValue);
            //Then apply it
            assignment.insertAfter(captureNewValSnippet);

        }
    }

    //Used to get the operator of the assignment
    private String getOperator(CtAssignment assignment) {
        String equalsOperator = "=";
        if (assignment instanceof CtOperatorAssignment<?, ?>){
            CtOperatorAssignment ctp = (CtOperatorAssignment) assignment;
            return binaryOperatorToString(ctp.getKind()) + equalsOperator;
        }
        return equalsOperator;
    }

    //Transform a binaryOperator to a String
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
