package Foundation;

import Foundation.City;
import Foundation.Element;

import java.util.Random;


public class Field {

    public Ground.GroundType getGroundType() {
        return groundElement.getGroundType();
    }


    public void run() {
        groundElement.run();
        if (additionalElement != null) additionalElement.run();
    }

    public FieldMap getMap() {
        return map;
    }

    public void setMap(FieldMap map) {
        this.map = map;
    }

    public Ground getGround(){
        return groundElement;
    }

    public Element getAdditionalElement(){
        return additionalElement;
    }

    private Coord fieldMapPos;
    private int size;

    private Ground groundElement;
    private Element additionalElement;

    private FieldMap map;

    private Random random;


    public Field(Coord fieldMapPos, Coord globalPos, int size, Random random, FieldMap map){
        this.fieldMapPos = fieldMapPos;
        setSize(size);
        setMap(map);
        setRandom(random);
        additionalElement = null;
        int randNum = random.nextInt(5);
        Ground.GroundType tempType = Ground.GroundType.Soil;
        if (randNum == 0) tempType = Ground.GroundType.Soil;
        if (randNum == 1) tempType = Ground.GroundType.Sand;
        if (randNum == 2) tempType = Ground.GroundType.Water;
        if (randNum == 3) tempType = Ground.GroundType.Mud;
        if (randNum == 4) tempType = Ground.GroundType.Rock;
        groundElement = new Ground(globalPos.x, globalPos.y, getSize(), tempType, this);
        if (getGroundType() != Ground.GroundType.Water) {
            int elType = random.nextInt(3);
            if (elType == 0) additionalElement = createRock(random, globalPos);
            if (elType == 1) additionalElement = createTree(random, globalPos);
            if (elType == 2) additionalElement = createCity(random, globalPos);
        }
    }




    private Tree createTree(Random random, Coord pos){
        if (getGroundType() == Ground.GroundType.Sand && random.nextInt(10) > 8){
            int typeNum = random.nextInt(10);
            Tree.SizeType type = Tree.SizeType.Big;
            if (typeNum < 1) type = Tree.SizeType.Big;
            if (typeNum < 4 && typeNum > 0) type = Tree.SizeType.Middle;
            if (typeNum > 3) type = Tree.SizeType.Small;
            return new Tree(pos.x, pos.y, getSize(), type, this);
        }
        if ( getGroundType() == Ground.GroundType.Soil && random.nextInt(10) > 3) {
            int typeNum = random.nextInt(3);
            Tree.SizeType type = Tree.SizeType.Big;
            if (typeNum == 0) type = Tree.SizeType.Big;
            if (typeNum == 1) type = Tree.SizeType.Middle;
            if (typeNum == 2) type = Tree.SizeType.Small;
            return new Tree(pos.x, pos.y, getSize(), type, this);
        }
        return null;
    }

    private Rock createRock(Random random, Coord pos){
        if (getGroundType() == Ground.GroundType.Rock) {
            if (random.nextInt(10) > 7) {
                int typeNum = random.nextInt(3);
                Rock.SizeType type = Rock.SizeType.Big;
                if (typeNum == 0) type = Rock.SizeType.Big;
                if (typeNum == 1) type = Rock.SizeType.Middle;
                if (typeNum == 2) type = Rock.SizeType.Small;
                return new Rock(pos.x, pos.y, getSize(), type, this);
            }
        }
        return null;
    }

    private City createCity(Random random, Coord pos){
        if (getGroundType() == Ground.GroundType.Rock) return null;
        if (random.nextInt(10) > 8) {
            int typeNum = random.nextInt(10);
            City.SizeType type = City.SizeType.Big;
            if (typeNum == 0) type = City.SizeType.Big;
            if (typeNum == 1 || typeNum == 2) type = City.SizeType.Middle;
            if (typeNum > 2) type = City.SizeType.Small;
            return new City(pos.x, pos.y, getSize(), type, map, this);
        }
        return null;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public Coord getFieldMapPos() {
        return fieldMapPos;
    }

    public void setFieldMapPos(Coord fieldMapPos) {
        this.fieldMapPos = fieldMapPos;
    }
}
