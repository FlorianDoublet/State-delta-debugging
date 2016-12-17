package utils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by FlorianDoublet on 13/12/16.
 * This class is mainly used to provide method to implement in our modified challenge
 */
public class DebugManipulation {

    public static LinkedHashMap<String, CapturedVar> capturedVars = new LinkedHashMap<>();
    public static boolean waitForNewValue = false;
    public static LinkedHashMap<Integer, Integer> iterations = new LinkedHashMap<>();

    /**
     * Capture the value of the CtVariableOperation and return the input
     * this method need to surround the input we want to capture
     * it's here that we build the iteration string for the captured input
     * @param inputVal
     * @param line
     * @param inputName
     * @param <T>
     * @return
     */
    public static <T> T capture(T inputVal, int line, String inputName){
        if(capturedVars.containsKey(inputName)){
            capturedVars.get(inputName).addState(line, inputVal, buildIterationString());
        } else {
            capturedVars.put(inputName, new CapturedVar(line, inputVal, inputName, inputVal.getClass(), buildIterationString()));
        }
        return inputVal;
    }

    /**
     * Capture the value of the CtAssignement and return the input
     * this method need to surround the input we want to capture
     * it's here that we build the iteration string for the captured input
     * @param inputVal
     * @param line
     * @param inputName
     * @param binaryOperator
     * @param <T>
     * @return
     */
    public static <T> T capture(T inputVal, int line, String inputName, String binaryOperator){
        //if the operator of the assignment isn't  "=" then we set the waitForValue to true
        if(!binaryOperator.equals("=")){
            waitForNewValue = true;
        }
        if(capturedVars.containsKey(inputName)){
            capturedVars.get(inputName).addState(line, inputVal, binaryOperator, buildIterationString());
        } else {
            capturedVars.put(inputName, new CapturedVar(line, inputVal, inputName, inputVal.getClass(), buildIterationString()));
        }
        return inputVal;
    }

    /**
     * Used to capture the new value of an input after an assignment which isn't "="
     * @param inputVal
     * @param inputName
     */
    public static void captureNewVal(Object inputVal, String inputName ){
        capturedVars.get(inputName).changeLastNewValue(inputVal);
        waitForNewValue = false;
    }

    /**
     * Capture the line of the iteration for the first time, then increase the value of the iteration
     * @param line
     */
    public static void iterate(int line){
        if(iterations.containsKey(line)){
           iterations.replace(line, iterations.get(line)+1);
        } else {
            iterations.put(line, 1);
        }
    }

    /**
     * remove the last iteration
     */
    public static void resetIteration(){
        int last = iterations.size() - 1;
        int i = 0;
        Integer toRemove = null;
        for (Map.Entry<Integer, Integer> entry : iterations.entrySet()) {
            if(i == last) toRemove = entry.getKey();
            i++;
        }
        iterations.remove(toRemove);
    }

    /**
     * Build the iteration String with the stored iterations in the Map iterations
     * @return
     */
    public static String buildIterationString(){
        String iteration = "iteration( ";
        for (Map.Entry<Integer, Integer> entry : iterations.entrySet()) {
            Integer value = entry.getValue();
            iteration += value.toString() + " ";
        }
        if(iterations.isEmpty()){
            iteration += "1 ";
        }
        iteration += ")";
        return iteration;
    }
}
