package Generation.EnvironmentGenerator;

import Foundation.Elements.Ground;
import Foundation.Elements.River;
import Foundation.FieldMap;
import Foundation.Field;
import Generation.FieldMapGenerator;
import Utils.Geometry.Index;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

public class RiverGenerator extends FieldMapGenerator {

    private Random random;
    private ArrayList<Index> deadEnd;
    private ArrayList<Index> currentRiver;

    private HashMap<Index, Integer> depth;
    private LinkedList<Index> queue;
    private Index iter;
    private ArrayList<Index> startingPoints;

    @Override
    public void startGeneration() {
        generateRivers(45);
    }


    private void generateRivers(int number){
        for(int i = 0; i < number; i++){
            Index pos = getStartPos();
            if (pos == null) return;
            generateRiver(pos);
        }
    }



    private void generateRiver(Index pos){
        queue.clear();
        depth.clear();
        queue.add(pos);
        depth.put(pos, 0);
        while(true){
            if (queue.size() == 0) return;
            Index curIndex = queue.getFirst();
            queue.remove(curIndex);
            if (addNewIndexes(curIndex)){
                Index endIndex = queue.getLast();
                addRiverFrom(pos, endIndex);
                return;
            }
        }
    }

    private boolean addNewIndexes(Index pos){
        int curDepth = depth.get(pos);
        Field from = map.getFieldByIndex(pos);
        for(int y = -1; y < 2; y++){
            iter.y = y + pos.y;
            for(int x = -1; x < 2; x++) {
                if (x == 0 && y == 0) continue;
                if (x != 0 && y != 0) continue;
                iter.x = x + pos.x;
                Field field = map.getFieldByIndex(iter);
                if (field == null) continue;
                if (!isFree(from, field)) continue;
                int oldDepth = depth.getOrDefault(iter, curDepth + 2);
                if (oldDepth > curDepth + 1) {
                    Index newIndex = new Index(iter);
                    queue.add(newIndex);
                    depth.put(newIndex, curDepth + 1);
                    if (isEnd(newIndex)) return true;
                }
            }
        }
        return false;
    }

    private Index getStartPos(){
        if (startingPoints.size() == 0) return null;
        Index start = startingPoints.get(random.nextInt(startingPoints.size()));
        startingPoints.remove(start);
        return start;
    }

    private boolean isFree(Field from, Field to){
        if (to.getHeight() > from.getHeight()) return false;
        return true;
    }

    private boolean isEnd(Index index){
        Field field = map.getFieldByIndex(index);
        if (field.getRiver() != null) return true;
        if (field.getGroundType() == Ground.GroundType.Water) return true;
        return false;
    }

    private void addRiverFrom(Index start, Index finish){
        Index curIndex = finish;
        Index prevIndex;
        while(true){
            prevIndex = curIndex;
            curIndex = getNextPosForRiver(curIndex);
            addRiverToPos(prevIndex, curIndex);
            if (curIndex == null) return;
            if (curIndex.equals(start)) return;
        }
    }

    private Index getNextPosForRiver(Index curPos){
        int curDepth = depth.get(curPos);
        Field to = map.getFieldByIndex(curPos);
        for(int y = -1; y < 2; y++){
            iter.y = y + curPos.y;
            for(int x = -1; x < 2; x++){
                if (x == 0 && y == 0) continue;
                if (x != 0 && y != 0) continue;
                iter.x = x + curPos.x;
                Field field = map.getFieldByIndex(iter);
                if (field == null) continue;
                if (!isFree(field, to)) continue;
                if (depth.getOrDefault(iter, curDepth) == curDepth - 1) return new Index(iter);
            }
        }
        return null;
    }

    private void addRiverToPos(Index prevPos, Index pos){
        Field field = map.getFieldByIndex(pos);
        Field prevField = map.getFieldByIndex(prevPos);
        if (field == null) return;
        if (prevField == null) return;
        River river = field.getRiver();
        if (river == null){
            field.setRiver(new River(field, pos.whatDirection(prevPos)));
        }
        river = prevField.getRiver();
        if (river != null){
            river.addInStream(prevPos.whatDirection(pos));
        }
    }

    @Override
    public void init(FieldMap map, Index size){
        this.map = map;
        this.size = size;
        this.random = map.getRandom();
        currentRiver = new ArrayList<>();
        queue = new LinkedList<>();
        depth = new HashMap<>();
        iter = new Index(0, 0);
        initStartingPoints();
    }

    private void initStartingPoints(){
        startingPoints = new ArrayList<>();
        for(int y = 0; y < size.y; y++){
            iter.y = y;
            for(int x = 0; x < size.x; x++){
                iter.x = x;
                Field field = map.getFieldByIndex(iter);
                if (field.getHeight() > 2) startingPoints.add(field.getFieldMapPos());
            }
        }
    }
}
