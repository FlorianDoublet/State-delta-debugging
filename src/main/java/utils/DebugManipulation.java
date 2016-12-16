package utils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by FlorianDoublet on 13/12/16.
 */
public class DebugManipulation {

    public static LinkedHashMap<String, CapturedVar> capturedVars = new LinkedHashMap<>();
    public static boolean waitForNewValue = false;
    public static LinkedHashMap<Integer, Integer> iterations = new LinkedHashMap<>();

    public static <T> T capture(T inputVal, int line, String inputName){
        if(capturedVars.containsKey(inputName)){
            capturedVars.get(inputName).addState(line, inputVal, buildIterationString());
        } else {
            capturedVars.put(inputName, new CapturedVar(line, inputVal, inputName, inputVal.getClass(), buildIterationString()));
        }
        return inputVal;
    }

    public static <T> T capture(T inputVal, int line, String inputName, String binaryOperator){
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

    public static void captureNewVal(Object inputVal, String inputName ){
        capturedVars.get(inputName).changeLastNewValue(inputVal);
        waitForNewValue = false;
    }


    public static void iterate(int line){
        if(iterations.containsKey(line)){
           iterations.replace(line, iterations.get(line)+1);
        } else {
            iterations.put(line, 1);
        }
    }

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
