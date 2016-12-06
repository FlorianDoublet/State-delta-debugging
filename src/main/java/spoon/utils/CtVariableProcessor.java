package spoon.utils;

import org.apache.log4j.Level;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtComment;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtLocalVariable;
import spoon.reflect.cu.SourcePosition;
import spoon.reflect.declaration.*;
import spoon.reflect.factory.Factory;
import spoon.reflect.reference.CtTypeReference;
import spoon.reflect.visitor.CtVisitor;
import spoon.reflect.visitor.Filter;
import spoon.reflect.visitor.ReferenceFilter;
import spoon.support.reflect.code.CtExpressionImpl;
import spoon.testing.CtPackageAssert;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;

/**
 * Created by FlorianDoublet on 03/12/2016.
 * Capture all the var
 */
public class CtVariableProcessor extends AbstractProcessor<CtVariable> {
    public void process(CtVariable variable) {

        System.out.println(" name : " + variable.getSimpleName());

        if(variable instanceof CtLocalVariable){
            CtLocalVariable ctv = (CtLocalVariable) variable;
            System.out.println("\tdefault val : " + ctv.getDefaultExpression());
        } else if (variable instanceof CtParameter){
            CtParameter ctp = (CtParameter) variable;
            System.out.println("\tdefault val : " + ctp.getDefaultExpression());
        }
        System.out.println("\ttype : " + variable.getType());
        getFactory().getEnvironment().report(this, Level.TRACE, variable, "variable : " + variable);
    }
}
