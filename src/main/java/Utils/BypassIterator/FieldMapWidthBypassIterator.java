package Utils.BypassIterator;

import Foundation.FieldMap;
import Utils.Boundary.Boundary;
import Utils.Boundary.MapBoundary;
import Utils.Geometry.Index;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public abstract class FieldMapWidthBypassIterator implements BypassIterator{

    private FieldMap map;
    private LinkedList<Index> queue;

    private LinkedList<Index> available;

    private ArrayList<Boundary> boundaries;
    private HashMap<Index, Boolean> flag;

    public FieldMapWidthBypassIterator(FieldMap map, Index start){
        this.map = map;
        queue = new LinkedList<>();
        available = new LinkedList<>();
        boundaries = new ArrayList<>();
        flag = new HashMap<>();
        addBoundary(new MapBoundary(map));
        if (isFree(start)) {
            queue.add(start);
            available.add(start);
            flag.put(start, true);
        }
    }


    private void addNewElementToAvailable(Index index){
        Index iter = new Index(0, 0);
        for(int y = -1; y < 2; y++){
            iter.y = y;
            for(int x = -1; x < 2; x++){
                if ((x != 0 && y != 0) || (x == 0 && y == 0)) continue;
                iter.x = x;
                Index newIndex = index.add(iter);
                if (isFree(newIndex)){
                    queue.add(newIndex);
                    available.add(newIndex);
                    flag.put(newIndex, true);
                }
            }
        }
    }

    private Index getNextAvailable(){
        if (available.size() != 0){
            Index next = available.getFirst();
            available.remove(next);
            return next;
        }
        return null;
    }

    private void addToAvailable(){
        while(available.size() == 0){
            if (queue.size() == 0) return;
            Index queueIndex = queue.getFirst();
            queue.remove(queueIndex);
            addNewElementToAvailable(queueIndex);
        }
    }

    public boolean hasNext(){
        if (available.size() == 0 && queue.size() == 0) return false;
        return true;
    }

    public Index next(){
        if (available.size() != 0) return getNextAvailable();
        if (queue.size() == 0) return null;
        addToAvailable();
        return getNextAvailable();
    }

    public void addBoundary(Boundary boundary){
        boundaries.add(boundary);
    }

    public boolean wasHere(Index index){
        return flag.getOrDefault(index, false);
    }

    public abstract boolean isMapFree(Index index);

    public boolean isFree(Index index){
        for(Boundary boundary: boundaries){
            if (boundary.contains(index)) continue;
            return false;
        }
        if (wasHere(index)) return false;
        if (!isMapFree(index)) return false;
        return true;
    }
}
