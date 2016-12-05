package fr.univ.lille1.m2iagl.dd.impl;

import java.util.Arrays;
import java.util.List;

import fr.univ.lille1.m2iagl.dd.CauseEffectChain;
import fr.univ.lille1.m2iagl.dd.Challenge;
import fr.univ.lille1.m2iagl.dd.DDebugger;

public class Main {

	public static void main(String[] args) {
		DDebugger d = new DummyDDebugger();
		System.out.println(d.debug(new DummyChallenge()));
	}
}
