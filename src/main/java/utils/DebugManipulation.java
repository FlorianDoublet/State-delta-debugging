package utils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by FlorianDoublet on 13/12/16.
 */
public class DebugManipulation {

    public static Map<String, CapturedVar> capturedVars = new LinkedHashMap<>();
    public static boolean waitForNewValue = false;
    public static Map<Integer, Integer> iterations = new LinkedHashMap<>();

    public static <T> T capture(T inputVal, int line, String inputName){
        if(capturedVars.containsKey(inputName)){
            capturedVars.get(inputName).addState(line, inputVal, buildIterationString());
        } else {
            capturedVars.put(inputName, new CapturedVar(line, inputVal, inputName, inputVal.getClass(), buildIterationString()));
        }
        return inputVal;
    }

    public static <T> T capture(T inputVal, int line, String inputName, String binaryOperator){
        System.out.println(line);
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


    public static <T> T convertInstanceOfObject(Object o, Class<T> clazz) {
        try {
            return clazz.cast(o);
        } catch(ClassCastException e) {
            return null;
        }
    }

    public static void iterate(int line){
        if(iterations.containsKey(line)){
           iterations.replace(line, iterations.get(line)+1);
        } else {
            iterations.put(line, 1);
        }
    }

    public static void resetIteration(){
        //Todo finir ca
        /*

        for (Map.Entry<Integer, Integer> entry : iterations.entrySet()) {
            Integer value = entry.getValue();
            iteration += value.toString() + " ";
        }*/
    }

    public static String buildIterationString(){
        String iteration = "iteration( ";
        for (Map.Entry<Integer, Integer> entry : iterations.entrySet()) {
            Integer value = entry.getValue();
            iteration += value.toString() + " ";
        }
        if(iterations.size() == 0){
            iteration += "1 ";
        }
        iteration += ")";
        return iteration;
    }
}
