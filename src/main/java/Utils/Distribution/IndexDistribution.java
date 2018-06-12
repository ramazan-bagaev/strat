package Utils.Distribution;

import Utils.BypassIterator.BypassIterator;
import Utils.Geometry.Index;

import java.util.ArrayList;
import java.util.Random;

public abstract class IndexDistribution {

    protected BypassIterator iterator;
    private Random random;

    public IndexDistribution(BypassIterator iterator, Random random){
        this.iterator = iterator;
        this.random = random;
    }

    public ArrayList<Index> realization(){
        ArrayList<Index> result = new ArrayList<>();
        while (iterator.hasNext()){
            Index index = iterator.next();
            if (index == null) break;
            if (decideOnIndex(index)) result.add(index);
        }
        return result;
    }

    private boolean decideOnIndex(Index index){
        int base = 1000;
        return (random.nextInt(base) < getProbability(index)*base);
    }

    public abstract double getProbability(Index index); // from 0 to 1
}
