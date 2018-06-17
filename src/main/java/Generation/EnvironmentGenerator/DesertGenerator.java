package Generation.EnvironmentGenerator;

import Foundation.Elements.Ground;
import Foundation.Field;
import Foundation.FieldMap;
import Generation.FieldMapGenerator;
import Utils.Boundary.EllipseBoundary;
import Utils.BypassIterator.FieldMapWidthBypassIterator;
import Utils.Distribution.EllipseDistribution;
import Utils.Geometry.Coord;
import Utils.Geometry.Index;

import java.util.ArrayList;
import java.util.Random;

public class DesertGenerator extends FieldMapGenerator {

    private Random random;
    private double mult;

    @Override
    public void startGeneration() {
        addRandomDeserts((int) (4*mult));
    }

    private void addRandomDeserts(int number){
        for(int i = 0; i < number; i++) addRandomDesert();
    }

    private void addRandomDesert(){
        int desertSize = (int) (10 * Math.sqrt(mult));
        Index forestPos = getIndexForDesert();
        if (forestPos == null) return;
        Coord center = new Coord(forestPos);
        Coord dir1 = new Coord(random.nextInt( desertSize*2 + 1) - desertSize, random.nextInt(desertSize*2+1) - desertSize);
        Coord dir2 = new Coord(random.nextInt( desertSize*2 + 1) - desertSize, random.nextInt(desertSize*2+1) - desertSize);
        Coord proj = dir2.projection(dir1);
        Coord norm = dir1.minus(proj);
        EllipseBoundary boundary = new EllipseBoundary(center, dir2, norm);
        FieldMapWidthBypassIterator iter = new FieldMapWidthBypassIterator(map, new Index(boundary.getCenter())) {
            @Override
            public boolean isMapFree(Index index) {
                return true;
            }
        };
        EllipseDistribution distribution = new EllipseDistribution(iter, random, boundary);
        ArrayList<Index> positions = distribution.realization();
        for(Index pos: positions) addSand(pos);

    }

    private void addSand(Index pos){
        Field field = map.getFieldByIndex(pos);
        if (isSuitableField(field)) {
            field.setGround(new Ground(Ground.GroundType.Sand, field));
        }
    }

    private boolean isSuitableField(Field field){
        if (field == null){
            //System.out.println("errorrrrrrr");
            return false;
        }
        if (field.getGroundType() == Ground.GroundType.Water) return false;
        if (field.getGroundType() == Ground.GroundType.Rock) return false;
        if (field.getGroundType() == Ground.GroundType.Mud) return false;
        if (field.getTree() != null) return false;
        return true;
    }

    private Index getIndexForDesert(){
        int k = 0;
        while (true){
            Index center = new Index(random.nextInt(size.x), random.nextInt(size.y));
            Field field = map.getFieldByIndex(center);
            if (isSuitableField(field)) return center;
            k++;
            if (k > 100) break;
        }
        return null;
    }

    @Override
    public void init(FieldMap map, Index size){
        this.map = map;
        this.size = size;
        this.random = map.getRandom();
        this.mult = size.x / 100;
    }
}
