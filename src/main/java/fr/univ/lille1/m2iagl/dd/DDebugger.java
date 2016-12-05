package fr.univ.lille1.m2iagl.dd;

public interface DDebugger<T> {
	CauseEffectChain debug(Challenge<T> c);
}

