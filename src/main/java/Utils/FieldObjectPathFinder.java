package Utils;

import Foundation.Field;
import Foundation.FieldObjects.FieldObjects;
import Utils.Geometry.Index;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class FieldObjectPathFinder {

    private Field field;
    private FieldObjects fieldObjects;
    private int pathWidth;
    private Index start;
    private Index finish;

    private Rectangle iter;

    private ArrayList<Rectangle> path;

    private HashMap<Rectangle, Integer> depth;
    private LinkedList<Rectangle> queue;

    public FieldObjectPathFinder(Field field){
        this.field = field;
        this.fieldObjects = field.getFieldObjects();
        depth = new HashMap<>();
        queue = new LinkedList<>();
        iter = new Rectangle(new Index(0, 0), new Index(0, 0));
    }

    public ArrayList<Rectangle> getPath(int pathWidth, Index start, Index finish){
        this.pathWidth = pathWidth;
        this.start = start;
        this.finish = finish;
        this.iter.size.x = pathWidth;
        this.iter.size.y = pathWidth;
    }

    private void findPath(){
        this.path = new ArrayList<>();
        queue.clear();
        depth.clear();
        queue.add(start);
    }

    private void visitSurroundings(Rectangle curZone){
        int curDepth = depth.get(curZone);
        for(Index.Direction dir: Index.getAllDirections()){
            Rectangle neighbour = getNeighbour(curZone, dir);
            if (neighbour.contains(finish)){
                queue.addFirst(neighbour);
            }
            int oldDepth = depth.getOrDefault(neighbour, curDepth + pathWidth + 1);
            if (oldDepth > curDepth + pathWidth){
                Rectangle newRect = new Rectangle(neighbour);
                depth.put(newRect, curDepth + pathWidth);
                queue.add(newRect);
            }
        }
    }

    private Rectangle getNeighbour(Rectangle curZone, Index.Direction dir){
        switch (dir){

            case Up:
                iter.pos.x = curZone.pos.x;
                iter.pos.y = curZone.pos.y - pathWidth;
                return iter;
            case Down:
                iter.pos.x = curZone.pos.x;
                iter.pos.y = curZone.pos.y + pathWidth;
                return iter;
            case Right:
                iter.pos.x = curZone.pos.x + pathWidth;
                iter.pos.y = curZone.pos.y;
                return iter;
            case Left:
                iter.pos.x = curZone.pos.x - pathWidth;
                iter.pos.y = curZone.pos.y;
                return iter;
        }
        return curZone;
    }
}
