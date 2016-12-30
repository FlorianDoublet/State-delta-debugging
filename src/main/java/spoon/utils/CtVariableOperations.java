package spoon.utils;

import spoon.Launcher;
import spoon.reflect.code.*;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtVariable;
import spoon.reflect.visitor.filter.TypeFilter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by FlorianDoublet on 13/12/2016.
 * Class used to capture all the variable operation in our code.
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

        //Iterate of all the CtLocalVaraible of the method
        for (Object obj : this.method.getElements(new TypeFilter<>(CtLocalVariable.class))) {

            CtLocalVariable variable = (CtLocalVariable) obj;
            //if it's NOT an instance of a foreach
           if(!(variable.getParent() instanceof CtForEach)){

               String surrounded = "utils.DebugManipulation.capture(" + variable.getAssignment() + ", "
                       + variable.getPosition().getLine() + ", \"" + variable.getSimpleName() + "\")";
               //Apply it
               final CtCodeSnippetExpression statementMethod = launcher.getFactory().Code().createCodeSnippetExpression(surrounded);
               variable.setAssignment(statementMethod);
           } else {
               //if it's a foreach var , like e in "for(Character e : s.ToCharCHain)" we need a special treatment
               //it's different 'cause we need to get the simpleName and not the assignement
               //and can't surround it, the we put this line afert the affectation
               CtForEach foreach = (CtForEach) variable.getParent();
               String addedCapture = "utils.DebugManipulation.capture(" + variable.getSimpleName() + ", "
                       + variable.getPosition().getLine() + ", \"" + variable.getSimpleName() +"\")";
               final CtCodeSnippetStatement statementMethod = launcher.getFactory().Code().createCodeSnippetStatement(addedCapture);
               variable.insertBefore(statementMethod);
           }

        }

    }
}
