package challenge;

import challenge.ReverseChallenge;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReverseChallenge c = new ReverseChallenge();
		for(String input : c.getInputs()) {
			System.out.println("INPUT " + input);
			try {
			c.challenge(input);
			System.out.println("PASS");
			} catch(Exception e) {
				System.out.println("FAIL");
			}
		}
	}

}
