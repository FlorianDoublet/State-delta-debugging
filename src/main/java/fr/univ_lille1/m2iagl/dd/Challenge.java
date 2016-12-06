package fr.univ_lille1.m2iagl.dd;

import java.util.List;

import static javafx.scene.input.KeyCode.T;

/**
 * Defines a challenge asked to the others
 */
public interface Challenge<I> {
	/** Gives the actual value of I */
	public Class<? extends I> getInputFormat();

	/** Gives a list of inputs */
	public List<I> getInputs();

	/** perform the computation, without any assert */
	public Object doIt(I input);

	/** the core challenge */
	public void challenge(I input);

	/** contains the implementation of method challenge. a problem (eg crash) is always signaled by a Throwable exception (.
	 * Starts by public void challenge. */
	public String getJavaProgram();
}
