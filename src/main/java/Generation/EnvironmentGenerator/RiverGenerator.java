package Generation.EnvironmentGenerator;

import Foundation.Elements.Ground;
import Foundation.Elements.River;
import Foundation.FieldMap;
import Foundation.Field;
import Generation.FieldMapGenerator;
import Generation.FieldObjectGenerator.RiverFieldObjectsGenerator;
import Utils.Geometry.Index;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

public class RiverGenerator extends FieldMapGenerator {

    private Random random;
    private Index insideEnd;
    private Index insideStart;

    private HashMap<Index, Integer> depth;
    private LinkedList<Index> queue;
    private Index iter;
    private ArrayList<Index> startingPoints;

    private RiverFieldObjectsGenerator riverFieldObjectsGenerator;

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
        Index prevIndex = finish;
        Index curIndex = getNextPosForRiver(prevIndex);
        Index nextIndex = getNextPosForRiver(curIndex);
        insideStart = null;
        insideEnd = null;
        while(true){
            if (curIndex == null) return;
            if (curIndex.equals(start)) return;
            addRiverToPos(prevIndex, curIndex, nextIndex);
            prevIndex = curIndex;
            curIndex = nextIndex;
            nextIndex = getNextPosForRiver(nextIndex);
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

    private void addRiverToPos(Index prevPos, Index pos, Index nextPos){
        Field field = map.getFieldByIndex(pos);
        Field prevField = map.getFieldByIndex(prevPos);
        if (field == null) return;
        if (prevField != null) {
            River river = field.getRiver();
            if (river == null){
                field.setRiver(new River(field, pos.whatDirection(prevPos)));
            }
            river = prevField.getRiver();
            if (river != null) {
                river.addInStream(prevPos.whatDirection(pos));
            }
        }

        initInsideStart(pos, prevPos);

        if (nextPos == null){
            insideEnd = new Index(random.nextInt(100), random.nextInt(100));
        }
        else {
            switch (pos.whatDirection(nextPos)){
                case Up:
                    insideEnd = new Index(random.nextInt(100), 0);
                    break;
                case Down:
                    insideEnd = new Index(random.nextInt(100), 99);
                    break;
                case Right:
                    insideEnd = new Index(99, random.nextInt(100));
                    break;
                case Left:
                    insideEnd = new Index(0, random.nextInt(100));
                    break;
            }
        }
        riverFieldObjectsGenerator.generate(field, insideStart, insideEnd);
    }

    @Override
    public void init(FieldMap map, Index size){
        this.map = map;
        this.size = size;
        this.random = map.getRandom();
        queue = new LinkedList<>();
        depth = new HashMap<>();
        iter = new Index(0, 0);
        riverFieldObjectsGenerator = new RiverFieldObjectsGenerator();
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

    private void initInsideStart(Index curPos, Index prevPos){
        if (insideEnd == null){
            switch (curPos.whatDirection(prevPos)){
                case Up:
                    insideStart = new Index(random.nextInt(100), 0);
                    break;
                case Down:
                    insideStart = new Index(random.nextInt(100), 99);
                    break;
                case Right:
                    insideStart = new Index(99, random.nextInt(100));
                    break;
                case Left:
                    insideStart = new Index(0, random.nextInt(100));
                    break;
            }
            return;
        }
        int cellAmount = map.getFieldByIndex(curPos).getCellAmount();
        switch (curPos.whatDirection(prevPos)){

            case Up:
                insideStart.y = 0;
                insideStart.x = insideEnd.x;
                break;
            case Down:
                insideStart.y = cellAmount - 1;
                insideStart.x = insideEnd.x;
                break;
            case Right:
                insideStart.x = cellAmount - 1;
                insideStart.y = insideEnd.y;
                break;
            case Left:
                insideStart.x = 0;
                insideStart.y = insideEnd.y;
                break;
            case None:
                break;
        }
    }
}
