package debug;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import fr.univ_lille1.m2iagl.dd.CauseEffectChain;
import fr.univ_lille1.m2iagl.dd.ChainElement;


/**
 * Created by flori on 03/12/2016.
 * Our Implementation of CauseEffectChain
 */
public class DebugCauseEffectChain implements CauseEffectChain {
    //We have to use DebugChainElement to access to added attribute or methode
    //which aren't provided in the ChainElement interface
    public List<DebugChainElement> ourCauseEffectChain = new ArrayList<DebugChainElement>();

    /*
    return a sorted chainElement
    TODO: Sort also on iteration !!!
     */
    public List<ChainElement> getChain() {

        //Here we sort the chain element when we ask for the chain
        //TODO: Sort also on iteration !!!
        ourCauseEffectChain.sort(Comparator.comparing(DebugChainElement::getOnlyLine));

        List<ChainElement> chainElementList = new ArrayList<ChainElement>();
        chainElementList.addAll(ourCauseEffectChain);

        return chainElementList;
    }

    public void addChainList(List<DebugChainElement> chainElementList){
        ourCauseEffectChain.addAll(chainElementList);
    }
}
