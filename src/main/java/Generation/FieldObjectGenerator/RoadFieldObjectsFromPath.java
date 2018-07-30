package Generation.FieldObjectGenerator;

import Foundation.Field;
import Foundation.FieldObjects.FieldObject;
import Foundation.FieldObjects.NaturalObjects.WaterFlowObject;
import Foundation.FieldObjects.TransportObjects.*;
import Foundation.TransportInfrostructure.TransportNetEdge;
import Foundation.TransportInfrostructure.TransportNetNode;
import Utils.Geometry.Index;

import java.util.ArrayList;

public class RoadFieldObjectsFromPath {

    private ArrayList<Index> path;
    private Index.Direction curDir;
    private Index curEdgePos;
    private Index curEdgeSize;
    private ArrayList<TransportNetObject> transportNetObjects;
    private Field parent;
    private RoadType roadType;

    public ArrayList<TransportNetObject> getTransportNetObject(ArrayList<Index> path, Field parent, RoadType roadType){
        this.path = path;
        this.parent = parent;
        this.roadType = roadType;
        if (path.size() < 2) return null;
        transportNetObjects = new ArrayList<>();
        curDir = path.get(0).whatDirection(path.get(1));
        addNewNode(path.get(0));
        initNewEdge(path.get(1));
        for(int i = 2; i < path.size(); i++){
            /*if (curDir == null){
                curDir = path.get(i-1).whatDirection(path.get(i));
            }*/
           // if ()
            FieldObject curObject = parent.getFieldObject(path.get(i));
            FieldObject prevObject = parent.getFieldObject(path.get(i - 1));
            if (curObject != null && prevObject != null){
                if (curObject.isTransportNetObject()) continue;
                //WTF!!!!!
                continue;
            }
            if (curObject != null && prevObject == null){
                if (curObject.isTransportNetObject()) {
                    if (!path.get(i-1).whatDirection(path.get(i)).equals(curDir)){
                        removePosFromCurEdge();
                        addNewEdge();
                        addNewNode(path.get(i - 1));
                        continue;
                    }
                    TransportNetObject transportNetObject = (TransportNetObject)curObject;
                    if (transportNetObject.isNode()){
                        //removePosFromCurEdge();
                        addNewEdge();
                        continue;
                    }
                    else{
                        int position = 0;
                        TransportNetEdgeObject edgeObject = (TransportNetEdgeObject)transportNetObject;
                        if (edgeObject.isVertical()){
                            position = path.get(i).y - curObject.getCellPos().y;
                        }
                        else {
                            position = path.get(i).x - curObject.getCellPos().x;
                        }
                        parent.getFieldObjects().splitRoad(edgeObject, position);
                        //removePosFromCurEdge();
                        addNewEdge();
                        continue;
                    }
                }
                // WTF!!!!
                continue;
            }
            if (prevObject != null){
                if (prevObject.isTransportNetObject()) {
                    TransportNetObject transportNetObject = (TransportNetObject)prevObject;
                    if (transportNetObject.isNode()){
                        curDir = path.get(i-1).whatDirection(path.get(i));
                        initNewEdge(path.get(i));
                        continue;
                    }
                    else{
                        int position = 0;
                        TransportNetEdgeObject edgeObject = (TransportNetEdgeObject)transportNetObject;
                        if (edgeObject.isVertical()){
                            position = path.get(i - 1).y - prevObject.getCellPos().y;
                        }
                        else {
                            position = path.get(i - 1).x - prevObject.getCellPos().x;
                        }
                        parent.getFieldObjects().splitRoad(edgeObject, position);
                        curDir = path.get(i-1).whatDirection(path.get(i));
                        initNewEdge(path.get(i));
                        continue;
                    }
                }
                // WTF!!!!
                continue;
            }
            if (path.get(i-1).whatDirection(path.get(i)).equals(curDir)){
                addPosToCurEdge();
            }
            else {
                removePosFromCurEdge();
                addNewEdge();
                addNewNode(path.get(i - 1));
                initNewEdge(path.get(i));
                curDir = path.get(i-1).whatDirection(path.get(i));
            }
        }
        if (curEdgePos != null && curDir != null){
            removePosFromCurEdge();
            addNewEdge();
            addNewNode(path.get(path.size() - 1));
            curDir = null;
        }
        return transportNetObjects;
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
        if (curEdgeSize.x == 0 || curEdgeSize.y == 0){
            return;
        }
        transportNetObjects.add(getNewTransportNetEdge(curEdgePos, curEdgeSize, Index.isVertical(curDir)));
        curEdgePos = null;
        curEdgeSize = null;
        //curDir = null;
    }

    private void initNewEdge(Index pos){
        curEdgeSize = new Index(1, 1);
        curEdgePos = new Index(pos);
    }

    private void addNewNode(Index curPos){
        transportNetObjects.add(getNewTransportNetNode(curPos, new Index(new Index(1, 1))));
    }

    private TransportNetEdgeObject getNewTransportNetEdge(Index pos, Index size, boolean vertical){
        if (roadType.isPavement()){
            return new PavementRoadObject(parent, new Index(pos), new Index(size), vertical);
        }
        if (roadType.isPriming()){
            return new PrimingRoadObject(parent, new Index(pos), new Index(size), vertical);
        }
        return null;
    }

    private TransportNetNodeObject getNewTransportNetNode(Index pos, Index size){
        if (roadType.isPavement()){
            return new PavementRoadCrossObject(parent, new Index(pos), new Index(size));
        }
        if (roadType.isPriming()){
            return new PrimingRoadCrossObject(parent, new Index(pos), new Index(size));
        }
        return null;
    }
}
