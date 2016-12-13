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

           CtForEach foreach = variable.getParent(new TypeFilter<>(CtForEach.class));
           if(foreach != null){
               System.out.println(" *********** coucou ************");
               System.out.println(foreach.getPosition().getLine());
               System.out.println(foreach.getVariable());
               System.out.println(variable.getSimpleName());


               String surround = foreach.toString();
               String pattern = "\\{";
               Pattern r = Pattern.compile(pattern);

               String addedCapture = "utils.DebugManipulation.capture(" + foreach.getVariable().getSimpleName() + ", "
                       + foreach.getPosition().getLine() + ", \"" + foreach.getVariable().getSimpleName() +"\");";
               addedCapture += "utils.DebugManipulation.iterate(" + foreach.getPosition().getLine() + ");";
               surround = r.matcher(surround).replaceFirst("\\{" + addedCapture);



               //Apply it
               final CtCodeSnippetStatement statementMethod = launcher.getFactory().Code().createCodeSnippetStatement(surround);
               foreach.replace(statementMethod);

           } else {
               String surrounded = "utils.DebugManipulation.capture(" + variable.getAssignment() + ", "
                       + variable.getPosition().getLine() + ", \"" + variable.getSimpleName() + "\")";
               //Apply it
               final CtCodeSnippetExpression statementMethod = launcher.getFactory().Code().createCodeSnippetExpression(surrounded);
               variable.setAssignment(statementMethod);
           }


        }

    }
}
