package challenge;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import fr.univ_lille1.m2iagl.dd.Challenge;


/**
 * Created by Florian Doublet
 * .Java file that will be transformed by our code !
 */
public class CaesarCipherChallenge implements Challenge<String>{
    public Class<String> getInputFormat() {
        return String.class;
    }

    public List<String> getInputs() {
        return Arrays.asList("15 OPL", "26 OPL");
    }



    public Object doIt(String input) {
        String[] strTab = input.split(" ");
        String textToTransform = strTab[1];
        Scanner in = new Scanner(textToTransform);
        String plainText = in.nextLine();
        // key value should be from 0 to 25
        int key = Integer.valueOf(strTab[0]);
        plainText = plainText.toUpperCase();
        char[] plainTextChar = plainText.toCharArray();
        for(int i=0;i<plainTextChar.length;i++) {
            plainTextChar[i] = (char)(((int)plainTextChar[i]+key-65)%26 + 65);
        }
        return String.valueOf(plainTextChar);
    }

    public void challenge(String input) {
        String[] strTab = input.split(" ");
        String textToTransform = strTab[1];
        String plainText = textToTransform;
        // key value should be from 0 to 25
        int key = Integer.valueOf(strTab[0]);
        plainText = plainText.toUpperCase();
        char[] plainTextChar = plainText.toCharArray();
        for(int i=0;i<plainTextChar.length;i++) {
            plainTextChar[i] = (char)(((int)plainTextChar[i]+key-65)%26 + 65);
        }
        plainText = String.valueOf(plainTextChar);
        if(plainText.equals(textToTransform)){
            throw new RuntimeException("it's not encrypted! ");
        }
    }
}