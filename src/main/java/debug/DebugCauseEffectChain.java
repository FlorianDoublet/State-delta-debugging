package debug;

import java.util.ArrayList;
import java.util.List;

import fr.univ_lille1.m2iagl.dd.CauseEffectChain;
import fr.univ_lille1.m2iagl.dd.ChainElement;


/**
 * Created by flori on 03/12/2016.
 */
public class DebugCauseEffectChain implements CauseEffectChain {

    public List<ChainElement> ourCauseEffectChain = new ArrayList<ChainElement>();

    public List<ChainElement> getChain() {
        return ourCauseEffectChain;
    }

    public void addChainList(List<ChainElement> chainElementList){
        ourCauseEffectChain.addAll(chainElementList);
    }
}
