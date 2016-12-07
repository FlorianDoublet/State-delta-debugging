package spoon.utils;

import org.apache.log4j.Level;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtAssignment;
import spoon.reflect.code.CtOperatorAssignment;

/**
 * Created by FlorianDoublet on 03/12/2016.
 * Capture all the var
 */
public class CtAssignementProcessor<T, T2> extends AbstractProcessor<CtAssignment<T, T>> {

	public void process(CtAssignment<T, T> assignement) {
		
        System.out.println("l(" + assignement.getPosition().getLine()+ ") : " + 
        					assignement.getAssigned().toString() + " " + getOperator(assignement) + " " + assignement.getAssignment().toString() +
        					" ("+assignement.getType() + ")");

        getFactory().getEnvironment().report(this, Level.TRACE, assignement, "variable : " + assignement);		
	}

	private String getOperator(CtAssignment<T, T> assignement) {
        String equalsOperator = "="; 
		if (assignement instanceof CtOperatorAssignment<?, ?>){
        	CtOperatorAssignment ctp = (CtOperatorAssignment) assignement;
        	return binaryOperatorToString(ctp.getKind().PLUS) + equalsOperator;
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
