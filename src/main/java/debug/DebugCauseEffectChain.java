package debug;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import fr.univ_lille1.m2iagl.dd.CauseEffectChain;
import fr.univ_lille1.m2iagl.dd.ChainElement;


/**
 * Created by flori on 03/12/2016.
 */
public class DebugCauseEffectChain implements CauseEffectChain {

    public List<DebugChainElement> ourCauseEffectChain = new ArrayList<DebugChainElement>();

    public List<ChainElement> getChain() {

        ourCauseEffectChain.sort(Comparator.comparing(DebugChainElement::getOnlyLine));
        List<ChainElement> chainElementList = new ArrayList<ChainElement>();
        chainElementList.addAll(ourCauseEffectChain);

        return chainElementList;
    }

    public void addChainList(List<DebugChainElement> chainElementList){
        ourCauseEffectChain.addAll(chainElementList);
    }
}
