package challenge;

import java.util.List;

import fr.univ_lille1.m2iagl.dd.Challenge;

public class test {

	public static void launchChallenge(Challenge c) {
		List<String> inp = (List<String>)c.getInputs();
		for(String input : inp) {
			try {
				System.out.println("input : " +input.length());
				c.challenge(input);

			System.out.println(input + " : PASS");
			} catch(Exception e) {
				System.out.println(input + " : FAIL");
			}
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test.launchChallenge(new ReverseChallenge());
		System.out.println("MARKUP CHALLENGE : ");
		test.launchChallenge(new MarkupChallenge());
		
	}

}
