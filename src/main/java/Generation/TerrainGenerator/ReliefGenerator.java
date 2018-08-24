package Generation.TerrainGenerator;

import Foundation.Elements.Ground;
import Foundation.Field;
import Foundation.FieldMap;
import Generation.FieldMapGenerator;
import Generation.FieldObjectGenerator.MountainRockGeneration;
import Utils.Boundary.EllipseBoundary;
import Utils.BypassIterator.FieldMapWidthBypassIterator;
import Utils.Distribution.EllipseDistribution;
import Utils.Geometry.Coord;
import Utils.Geometry.Index;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class ReliefGenerator extends FieldMapGenerator {

    private ArrayList<TectonicBoundary> boundaries;
    private HashMap<Plate, Coord> drivingDirections;
    private TerrainMap terrainMap;
    private TerrainMapGenerator terrainMapGenerator;
    private PlateGenerator plateGenerator;
    private Random random;

    private MountainRockGeneration mountainRockGeneration;

    @Override
    public void startGeneration() {
        generateTerrainMap();
        findBoundaries();
        generateMountains();
    }

    @Override
    public void init(FieldMap map, Index size){
        this.size = size;
        this.map = map;
        this.random = map.getRandom();
        terrainMapGenerator = new TerrainMapGenerator(random);
        plateGenerator = new PlateGenerator();
        drivingDirections = new HashMap<>();
        boundaries = new ArrayList<>();
        mountainRockGeneration = new MountainRockGeneration();
    }

    private void generateTerrainMap(){
        terrainMap = terrainMapGenerator.generate(size, 25);
        plateGenerator.generate(terrainMap);
        generateDrivingDirections();
    }

    private void generateDrivingDirections(){
        for(Plate plate: terrainMap.getPlates()){
            int x = random.nextInt(11) - 5;
            int y = random.nextInt(11) - 5;
            drivingDirections.put(plate, new Coord(x, y));
        }
    }

    private void findBoundaries(){
        for(Plate plate: terrainMap.getPlates()){
            ArrayList<TerrainEdge> platesEdge = plate.getEdges();
            for(Plate otherPlate: terrainMap.getPlates()){
                if (plate.equals(otherPlate)) continue;
                for(TerrainEdge edge: otherPlate.getEdges()){
                    if (platesEdge.contains(edge)) addBoundary(plate, otherPlate, edge);
                }
            }
        }
    }

    private void generateMountains(){
        for(TectonicBoundary tectonicBoundary : boundaries){
            Coord dir1 = drivingDirections.getOrDefault(tectonicBoundary.firstPlate, null);
            Coord dir2 = drivingDirections.getOrDefault(tectonicBoundary.secondPlate, null);
            Coord relDir = dir2.minus(dir1);
            if (dir1 == null || dir2 == null) continue;
            TerrainEdge edge = tectonicBoundary.edge;
            Coord edgeDir = new Coord(edge.getSecond().getPos().minus(edge.getFirst().getPos()));
            Coord proj = edgeDir.projection(relDir);
            Coord norm = relDir.minus(proj);
            Index center = edge.getFirst().getPos().add(edge.getSecond().getPos());
            center = center.multiply(0.5);
            edgeDir = edgeDir.multiply(0.5);
            norm = norm.multiply(0.5);
            EllipseBoundary boundary = new EllipseBoundary(new Coord(center), edgeDir, norm);
            boolean edgeOnFirstPlateLeft = tectonicBoundary.firstPlate.onLeft(edge);
            boolean relOnLeft = (edgeDir.vectorMulti(relDir).z <= 0);
            if (edgeOnFirstPlateLeft && relOnLeft || !(edgeOnFirstPlateLeft || relOnLeft)) higherFieldsWithinBoundary(boundary);
            else lowerFieldsWithinBoundary(boundary);
        }
    }

    private void higherFieldsWithinBoundary(EllipseBoundary boundary){
        boundary.increaseVolume(2);
        changeHeightWithinBoundary(boundary, 10);
        boundary.increaseVolume(0.5);
        addRockGroundWithinBoundary(boundary);
    }

    private void lowerFieldsWithinBoundary(EllipseBoundary boundary){
        boundary.increaseVolume(2);
        changeHeightWithinBoundary(boundary, -10);
        //addWaterWithinBoundary(boundary);
    }

    private void changeHeightWithinBoundary(EllipseBoundary boundary, int maxDelta){
        FieldMapWidthBypassIterator iter = new FieldMapWidthBypassIterator(map, new Index(boundary.getCenter())) {
            @Override
            public boolean isMapFree(Index index) {
                return true;
            }
        };
        EllipseDistribution distribution = new EllipseDistribution(iter, random, boundary);
        ArrayList<Index> positions = distribution.realization();
        for(Index pos: positions){
            double prob = distribution.getProbability(pos);
            int maxDeltaLocal = (int)(prob*Math.abs(maxDelta));
            if (maxDeltaLocal == 0) continue;
            int delta = (int) (Math.signum(maxDelta)*random.nextInt(maxDeltaLocal));
            changeFieldHeight(pos, delta);
        }
    }

    private void addRockGroundWithinBoundary(EllipseBoundary boundary){
        FieldMapWidthBypassIterator iter = new FieldMapWidthBypassIterator(map, new Index(boundary.getCenter())) {
            @Override
            public boolean isMapFree(Index index) {
                return true;
            }
        };
        EllipseDistribution distribution = new EllipseDistribution(iter, random, boundary);
        ArrayList<Index> positions = distribution.realization();
        for(Index pos: positions){
            addRockGround(pos);
        }
    }

    private void addRockGround(Index pos){
        Field field = map.getFieldByIndex(pos);
        if (field == null){
            //System.out.println("errorrrrrrr");
            return;
        }
        if (field.getGroundType() == Ground.GroundType.Water && field.getHeight() < 0) return;
        field.setGround(new Ground(Ground.GroundType.Rock, field));
        mountainRockGeneration.generate(field);
    }

    private void changeFieldHeight(Index pos, int delta){
        Field field = map.getFieldByIndex(pos);
        if (field == null){
            //System.out.println("errorrrrrrr");
            return;
        }
        int oldHeight = field.getHeight();
        field.setHeight(oldHeight + delta);
        if (field.getGroundType() == Ground.GroundType.Water){
            if (oldHeight + delta >= 0) addRockGround(pos);
        }
        else{
            if (oldHeight + delta < 0){
                field.setGround(new Ground(Ground.GroundType.Water, field));
            }
        }
        

    }

    private void addBoundary(Plate first, Plate second, TerrainEdge edge){
        TectonicBoundary tectonicBoundary = new TectonicBoundary();
        tectonicBoundary.firstPlate = first;
        tectonicBoundary.secondPlate = second;
        tectonicBoundary.edge = edge;
        if (boundaries.contains(tectonicBoundary)) return;
        boundaries.add(tectonicBoundary);
    }

}
