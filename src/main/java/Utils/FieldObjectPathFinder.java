package Utils;

import Foundation.Field;
import Foundation.FieldObjects.FieldObjects;
import Utils.Geometry.Index;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public abstract class FieldObjectPathFinder {

    private Field field;
    protected FieldObjects fieldObjects;
    private Index start;

    private Index iter;

    private ArrayList<Index> path;

    private HashMap<Index, Integer> depth;
    private LinkedList<Index> queue;
    private LinkedList<Index.Direction> directOrder;

    public FieldObjectPathFinder(Field field){
        this.field = field;
        this.fieldObjects = field.getFieldObjects();
        depth = new HashMap<>();
        queue = new LinkedList<>();
        directOrder = new LinkedList<>();
        initNewDirectOrder();
        iter = new Index(0, 0);
    }


    private void initNewDirectOrder(){
        directOrder.clear();
        ArrayList<Index.Direction> directs = Index.getAllDirections();
        for(int i = 0; i < 4; i++){
            Index.Direction dir = directs.get(field.getRandom().nextInt(directs.size()));
            directOrder.add(dir);
            directs.remove(dir);
        }
    }

    public ArrayList<Index> getPath(Index start){
        this.start = start;
        findPath();
        return path;
    }

    private void findPath(){
        this.path = new ArrayList<>();
        queue.clear();
        depth.clear();
        queue.add(start);
        depth.put(start, 0);
        while (true){
            if (queue.size() == 0) return;
            Index curPos = queue.getFirst();
            if (curPos == null) return;
            if (isFinish(curPos)){
                constructPath(curPos);
                return;
            }
            queue.remove(curPos);
            visitSurroundings(curPos);
        }
    }

    private void constructPath(Index finish){
        Index curPos = finish;
        while (true){
            if (curPos == null) return;
            path.add(curPos);
            int curDepth = depth.get(curPos);
            if (curDepth == 0) return;
            curPos = getNextPosForPath(curPos);
            if (field.getRandom().nextInt(100) < 10) initNewDirectOrder();
        }
    }

    private Index getNextPosForPath(Index curPos){
        int curDepth = depth.get(curPos);
        for(Index.Direction dir: directOrder){
            if (dir == Index.Direction.None) continue;
            Index nextPos = getNeighbour(curPos, dir);
            int newDepth = depth.getOrDefault(nextPos, -1);
            if (newDepth == -1) continue;
            if (newDepth < curDepth) return new Index(nextPos);
        }
        return null;
    }

    private void visitSurroundings(Index curPos){
        int curDepth = depth.get(curPos);
        for(Index.Direction dir: Index.getAllDirections()){
            if (dir == Index.Direction.None) continue;
            Index neighbour = getNeighbour(curPos, dir);
            int dist = getDistance(neighbour);
            if (isFinish(neighbour)){
                queue.addFirst(neighbour);
                depth.put(neighbour,curDepth + dist);
                return;
            }
            if (!isFree(neighbour)) continue;
            int oldDepth = depth.getOrDefault(neighbour, curDepth + dist + 1);
            if (oldDepth > curDepth + dist){
                depth.put(neighbour, curDepth + dist);
                queue.add(neighbour);
            }
        }
    }

    private Index getNeighbour(Index curPos, Index.Direction dir){
        switch (dir){

            case Up:
                return new Index(curPos.x, curPos.y - 1);
            case Down:
                return new Index(curPos.x, curPos.y + 1);
            case Right:
                return new Index(curPos.x + 1, curPos.y);
            case Left:
                return new Index(curPos.x - 1, curPos.y);
            case None:
                return null;
        }
        return null;
    }

    public abstract boolean isFree(Index index);

    public abstract boolean isFinish(Index pos);

    public abstract int getDistance(Index pos);
}
