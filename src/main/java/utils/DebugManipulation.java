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
    public static LinkedHashMap<String, CapturedVar> capturedVarsToReplaceValues = new LinkedHashMap<>();
    public static boolean waitForNewValue = false;
    public static LinkedHashMap<Integer, Integer> iterations = new LinkedHashMap<>();

    public static void resetAll(){
        capturedVars = new LinkedHashMap<>();
        capturedVarsToReplaceValues = new LinkedHashMap<>();
        waitForNewValue = false;
        iterations = new LinkedHashMap<>();
    }

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
            capturedVars.put(inputName, new CapturedVar(line, inputVal, inputName, buildIterationString()));
        }
        //return the input OR if it exist, another value to replace it
        return replaceValueIfExist(inputVal, capturedVars.get(inputName), false);
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
            capturedVars.put(inputName, new CapturedVar(line, inputVal, inputName, buildIterationString()));
        }
        //return the input OR if it exist, another value to replace it
        return replaceValueIfExist(inputVal, capturedVars.get(inputName), false);
    }

    /**
     * Used to capture the new value of an input after an assignment which isn't "="
     * @param inputVal
     * @param inputName
     */
    public static void captureNewVal(Object inputVal, String inputName ){
        capturedVars.get(inputName).updateLastCompleteStateValue(inputVal);
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

    /**
     * Used to replace the input by the potential existing value we want to return instead
     * @param input
     * @param inputCapturedVar
     * @param <T>
     * @return
     */
    public static <T> T replaceValueIfExist(T input, CapturedVar inputCapturedVar, boolean isReading){
        //if their is no special value to send instead of the orginal input, then return the original input
        if(!capturedVarsToReplaceValues.containsKey(inputCapturedVar.name)) return input;

        //else search for the good state to replace the input value
        CapturedVar replacementVar = capturedVarsToReplaceValues.get(inputCapturedVar.name);
        StateOfVar lastState = inputCapturedVar.states.get(inputCapturedVar.states.size() - 1);

        String it1 = lastState.iteration;
        //then if a state is the same for line and iteration that the existing one of the input
        //then return is value
        for(StateOfVar replacementState : replacementVar.states){
            if(compareTwoState(lastState, replacementState)){
                replacementState.reached = true;
                return (T)replacementState.newVal;
            } else {
                String it2 = replacementState.iteration;
                if(iterationIsBigger(it1, it2) && replacementState.reached == false){
                    if(isReading == false) replacementState.reached = true;
                    return (T)replacementState.newVal;
                }
            }
        }

        return input;
    }

    /**
     * Used to compare two state on some criteria (here line and iterations)
     * @param state1
     * @param state2
     * @return
     */
    public static boolean compareTwoState(StateOfVar state1, StateOfVar state2){
        if(state1.line != state2.line) return false;
        if(!(state1.iteration.equals(state2.iteration))) return false;
        return true;
    }

    public static <T> T readVar(T inputVal, int line, String inputName){
        CapturedVar c = new CapturedVar(line, inputVal, inputName, inputVal.getClass(), buildIterationString(), true);
        return replaceValueIfExist(inputVal, c, true);
    }



    //check if it1 is bigger than it2
    public static Boolean iterationIsBigger(String it1, String it2) {
        it1 = it1.substring(11, it1.length()-2);
        it2 = it2.substring(11, it2.length()-2);
        String[] it1A = it1.split("\\s");
        String[] it2A = it2.split("\\s");
        for (int i = 0; i < it1A.length; i++) {
            int it1I = Integer.valueOf(it1A[i]);
            int it2I = Integer.valueOf(it2A[i]);
            if (it1I > it2I) {
                return true;
            } else if (it1I < it2I) {
                return false;
            }
        }
        // here it mean they are the same
        return false;
    }
}
