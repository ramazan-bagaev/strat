package Generation.FieldObjectGenerator;

import Foundation.FieldObjects.NaturalObjects.WaterFlowObject;
import Utils.Geometry.Index;

import java.util.ArrayList;
import Foundation.Field;

public class WaterFlowObjectsFromPath {

    private ArrayList<Index> path;
    private Index.Direction curDir;
    private Index curEdgePos;
    private Index curEdgeSize;
    private ArrayList<WaterFlowObject> waterFlowObjects;
    private Field parent;

    public ArrayList<WaterFlowObject> getWaterFlowObjects(ArrayList<Index> path, Field parent){
        this.path = path;
        this.parent = parent;
        if (path.size() < 2) return null;
        waterFlowObjects = new ArrayList<>();
        initNewEdge(path.get(0));
        for(int i = 1; i < path.size(); i++){
            if (curEdgePos == null){
                initNewEdge(path.get(i));
                continue;
            }
            if (curDir == null){
                curDir = path.get(i-1).whatDirection(path.get(i));
            }
            if (path.get(i-1).whatDirection(path.get(i)).equals(curDir)){
                addPosToCurEdge();
            }
            else {
                //removePosFromCurEdge();
                addNewEdge();
                addNewNode(path.get(i));
            }
        }
        if (curEdgePos != null && curDir != null){
            //removePosFromCurEdge();
            addNewEdge();
            addNewNode(path.get(path.size() - 1));
        }
        return waterFlowObjects;
    }

    private void addPosToCurEdge(){
        switch (curDir){
            case Up:
                curEdgePos.y -= 1;
                curEdgeSize.y += 1;
                break;
            case Down:
                curEdgeSize.y += 1;
                break;
            case Right:
                curEdgeSize.x += 1;
                break;
            case Left:
                curEdgePos.x -= 1;
                curEdgeSize.x += 1;
                break;
        }
    }

    private void removePosFromCurEdge(){
        switch (curDir){
            case Up:
                curEdgePos.y += 1;
                curEdgeSize.y -= 1;
                break;
            case Down:
                curEdgeSize.y -= 1;
                break;
            case Right:
                curEdgeSize.x -= 1;
                break;
            case Left:
                curEdgePos.x += 1;
                curEdgeSize.x -= 1;
                break;
        }
    }

    private void addNewEdge(){
        if (curEdgeSize.x == 0 && curEdgeSize.y == 0) return;
        waterFlowObjects.add(getNewWaterFlowObject(curEdgePos, curEdgeSize));
        curEdgePos = null;
        curEdgeSize = null;
        curDir = null;
    }

    private void initNewEdge(Index pos){
        curEdgeSize = new Index(1, 1);
        curEdgePos = pos;
    }

    private void addNewNode(Index curPos){
        waterFlowObjects.add(getNewWaterFlowObject(curPos, new Index(new Index(1, 1))));
    }

    private WaterFlowObject getNewWaterFlowObject(Index pos, Index size){
        return new WaterFlowObject(parent, pos, size);
    }
}
