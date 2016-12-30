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

    public void add(DebugChainElement chainElement){
        ourCauseEffectChain.add(chainElement);
    }

    public void changeLastValue(Object value, String description){
        ourCauseEffectChain.get(ourCauseEffectChain.size() - 1).value = value;
        ourCauseEffectChain.get(ourCauseEffectChain.size() - 1).description = description;
    }

    public List<ChainElement> getChain() {

        List<ChainElement> chainElementList = new ArrayList<ChainElement>();
        chainElementList.addAll(ourCauseEffectChain);

        return chainElementList;
    }

    public List<DebugChainElement> getDebugChain(){
        return ourCauseEffectChain;
    }

    public void addChainList(List<DebugChainElement> chainElementList){
        ourCauseEffectChain.addAll(chainElementList);
    }
}
