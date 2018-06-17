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

public class SwampGenerator extends FieldMapGenerator {

    private Random random;
    private double mult;

    @Override
    public void startGeneration() {
        addRandomSwamps((int) (4  * mult));
    }

    private void addRandomSwamps(int number){
        for(int i = 0; i < number; i++) addRandomSwamp();
    }

    private void addRandomSwamp(){
        int swampSize = (int) (10*Math.sqrt(mult));
        Index forestPos = getIndexForMud();
        if (forestPos == null) return;
        Coord center = new Coord(forestPos);
        Coord dir1 = new Coord(random.nextInt(swampSize*2 + 1) - swampSize, random.nextInt(swampSize* 2 + 1) - swampSize);
        Coord dir2 = new Coord(random.nextInt(swampSize*2 + 1) - swampSize, random.nextInt(swampSize* 2 + 1) - swampSize);
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
        for(Index pos: positions) addMud(pos);

    }

    private void addMud(Index pos){
        Field field = map.getFieldByIndex(pos);
        if (field == null){
            //System.out.println("errorrrrrrr");
            return;
        }
        if (field.getGroundType() == Ground.GroundType.Water) return;
        if (field.getGroundType() == Ground.GroundType.Rock) return;
        if (field.getGroundType() == Ground.GroundType.Sand) return;

        field.setGround(new Ground(Ground.GroundType.Mud, field));
    }

    private Index getIndexForMud(){
        int k = 0;
        while (true){
            Index center = new Index(random.nextInt(size.x), random.nextInt(size.y));
            Ground.GroundType type = map.getFieldByIndex(center).getGroundType();
            if (type != Ground.GroundType.Water && type != Ground.GroundType.Rock && type != Ground.GroundType.Sand) return center;
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
        this.mult = size.x/100;
    }
}
