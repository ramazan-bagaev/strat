package Generation.TerrainGenerator;

import Foundation.Elements.Ground;
import Foundation.FieldMap;
import Foundation.Field;
import Generation.FieldMapGenerator;
import Utils.BypassIterator.FieldMapWidthBypassIterator;
import Utils.Geometry.Index;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

public class ContinentsGenerator extends FieldMapGenerator {

    private ArrayList<Plate> continents;

    private LinkedList<Index> queue;
    private HashMap<Index, Integer> flag;

    private TerrainMap terrainMap;
    private TerrainMapGenerator terrainMapGenerator;
    private PlateGenerator plateGenerator;
    private Random random;

    @Override
    public void startGeneration() {
        generateTerrainMap();
        generateContinentWithTerrainMap();
    }

    @Override
    public void init(FieldMap map, Index size){
        this.size = size;
        this.map = map;
        this.random = map.getRandom();
        terrainMapGenerator = new TerrainMapGenerator(random);
        plateGenerator = new PlateGenerator();
        continents = new ArrayList<>();
        queue = new LinkedList<>();
    }

    private void generateTerrainMap(){
        terrainMap = terrainMapGenerator.generate(size, 50);
        plateGenerator.generate(terrainMap);
    }

    private void generateContinentWithTerrainMap(){
        ArrayList<Plate> plates = terrainMap.getPlates();
        for(Plate plate: plates){
            if (freeForContinent(plate)){
                if (random.nextBoolean()) fillContinentBordersWith(plate, Ground.GroundType.Water);
                else{
                    fillContinentBordersWith(plate, Ground.GroundType.Soil);
                    continents.add(plate);
                }
            }
            else fillContinentBordersWith(plate, Ground.GroundType.Water);
        }
        for(Plate plate: plates){
            if (continents.contains(plate)) fillContinentInteriorWith(plate, Ground.GroundType.Soil);
            else fillContinentInteriorWith(plate, Ground.GroundType.Water);
        }
        fillRemainedVoid();
    }

    private void fillContinentBordersWith(Plate plate, Ground.GroundType type){
        Random myRandom = new Random();
        ArrayList<TerrainEdge> edges = plate.getEdges();
        for(TerrainEdge edge: edges){
            //LineIterator iter = new LineIterator(edge);
            if (map.getFieldByIndex(edge.getFirst().getPos()).getGround() == null || map.getFieldByIndex(edge.getSecond().getPos()).getGround() == null){
                BorderIterator iter = new BorderIterator(edge, myRandom);
            while(iter.hasNext()){
                Index index = iter.nextSameIndex();
                Field field = map.getFieldByIndex(index);
                if (field == null) continue;
                if (field.getGround() == null){
                    field.setGround(new Ground(type, field));
                    if (type == Ground.GroundType.Water) field.setHeight(-10);
                }
            }
            }
            
        }
    }

    private void fillContinentInteriorWith(Plate plate, Ground.GroundType type){
        ArrayList<TerrainNode> nodes = plate.getNodes();
        TerrainNode node0 = nodes.get(0);
        TerrainNode node1 = nodes.get(1);
        TerrainNode node2 = nodes.get(nodes.size() - 1);
        Index dir1 = node0.getPos().minus(node2.getPos());
        Index dir2 = node1.getPos().minus(node0.getPos());
        Index posA = node0.getPos();
        Index posB = dir2.minus(dir1);
        posB = posB.multiply(0.2); // TODO: magic constant
        if (Index.onLeft(dir1, dir2)){
            posB = posA.add(posB);
        }
        else{
            posB = posA.add(posB);
        }
        LineIterator iter = new LineIterator(posA, posB);
        ArrayList<TerrainEdge> edges = plate.getEdges();
        TerrainEdge edge1 = edges.get(0);
        TerrainEdge edge2 = edges.get(edges.size() - 1);
        Index insidePos = posB;
        /*while (iter.hasNext()){
            insidePos = iter.nextSameIndex();
            fillPosWith(insidePos, type);
            //if (!edge1.inside(insidePos) && !edge2.inside(insidePos)) break;
        }*/
        if (insidePos != null) fillSpaceWithinPlate(plate, insidePos, type);
    }

    private boolean freeForContinent(Plate plate){
        for(Plate continent: continents) {
            if (continent.isNeighbor(plate)) return false;
        }
        return true;
    }

    private void fillSpaceWithinPlate(Plate plate, Index pos, Ground.GroundType type){
        /*queue.clear();
        //flag.clear();
        //initFlag(plate);
        if (isFree(pos)) queue.add(pos);
        //flag.put(pos, 1);
        while (queue.size() != 0){
            Index curIndex = queue.getFirst();
            queue.remove(curIndex);
            addElementAround(curIndex, type);
        }*/
        FieldMapWidthBypassIterator iter = new FieldMapWidthBypassIterator(map, pos) {
            @Override
            public boolean isMapFree(Index index) {
                Field field = map.getFieldByIndex(index);
                if (field.getGround() == null) return true;
                return false;
            }
        };
        while(iter.hasNext()){
            Index index = iter.next();
            fillPosWith(index, type);
        }
    }

    private void initFlag(Plate plate){
        for(TerrainEdge edge: plate.edges){
            flag.put(edge.getFirst().getPos(), 2);
            flag.put(edge.getSecond().getPos(), 2);
            LineIterator iter = new LineIterator(edge);
            while(iter.hasNext()){
                Index newIndex = iter.nextSameIndex();
                flag.put(newIndex, 1);
            }
        }
    }

    private void addElementAround(Index index, Ground.GroundType type){
        Index iter = new Index(0, 0);
        for(int y = -1; y < 2; y++){
            iter.y = y;
            for(int x = -1; x < 2; x++){
                if ((x != 0 && y != 0) || (x == 0 && y == 0)) continue;
                iter.x = x;
                Index newIndex = index.add(iter);
                if (isFree(newIndex)){
                    queue.add(newIndex);
                    fillPosWith(newIndex, type);
                }
            }
        }
    }

    private void fillPosWith(Index pos, Ground.GroundType type){
        Field field = map.getFieldByIndex(pos);
        if (field == null){
            //System.out.println("errorrrrrrr");
            return;
        }
        if (type == Ground.GroundType.Water) field.setHeight(-10);
        field.setGround(new Ground(type, field));
    }

    private boolean isFree(Index pos){
        if (!isInMap(pos)) return false;
        Field field = map.getFieldByIndex(pos);
        if (field.getGround() == null) return true;
        return false;
    }

    private boolean isInMap(Index pos){
        if (pos.x >= size.x || pos.x < 0) return false;
        if (pos.y >= size.y || pos.y < 0) return false;
        return true;
    }

    private void fillRemainedVoid(){
        Index iter = new Index(0, 0);
        for(int y = 0; y < size.y; y++) {
            iter.y = y;
            for (int x = -1; x < size.x; x++) {
                iter.x = x;
                if (isFree(iter)){
                    fillPosWith(iter, Ground.GroundType.Sand);
                }
            }
        }
    }
}
