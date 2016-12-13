package debug;

import fr.univ_lille1.m2iagl.dd.ChainElement;

/**
 * Created by FlorianDoublet on 13/12/16.
 */
public class DebugChainElement implements ChainElement {

    public int line;
    public String iteration;
    public String varName;
    public String description;

    public DebugChainElement(int line, String varName, String description, String iteration){
        this.line = line;
        this.varName = varName;
        this.description = description;
        this.iteration = iteration;
    }

    @Override
    public String getLine() {
        return String.valueOf(this.line) + " " + iteration;
    }

    @Override
    public String getVariable() {
        return this.varName;
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}
