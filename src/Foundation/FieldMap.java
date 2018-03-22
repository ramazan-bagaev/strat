package Foundation;

import Foundation.BasicShapes.BasicShape;
import Foundation.Elements.Element;
import Foundation.Runnable.SuperField;
import Utils.Index;
import Utils.Coord;

import java.util.*;

public class FieldMap {

    private int fieldSize;
    private int superFieldSize;
    private HashMap<Index, Field> map;
    private HashMap<Index, SuperField> superFieldMap;
    private GameEngine gameEngine;

    public FieldMap(int superFieldSize, int fieldSize, GameEngine gameEngine)
    {
        this.gameEngine = gameEngine;
        this.fieldSize = fieldSize;
        this.superFieldSize = superFieldSize;
        map = new HashMap<>();
        superFieldMap = new HashMap<>();
    }

    public Field getFieldByIndex(Index index){
        return map.getOrDefault(index, null);
    }

    public Field getFieldByPos(Coord coord) {
        Index index = new Index((int)(coord.x / fieldSize), (int)(coord.y / fieldSize));
        return getFieldByIndex(index);
    }

    public SuperField getSuperFieldByIndex(Index index){
        return superFieldMap.getOrDefault(index, null);
    }

    public SuperField getSuperFieldByPos(Coord coord){
        Index index = new Index((int)(coord.x / superFieldSize), (int) (coord.y / superFieldSize));
        return getSuperFieldByIndex(index);
    }

    public void addField(Index coord, Field field){
        if (getFieldByIndex(coord) != null){
            deleteField(field);
        }
        map.put(coord, field);
        Index index = new Index(coord.x * fieldSize / superFieldSize, coord.y * fieldSize / superFieldSize);
        SuperField superField = getSuperFieldByIndex(index);
        if (superField == null){
            SuperField newSuperField = new SuperField(index, this, field.getTime());
            superFieldMap.put(index, newSuperField);
            newSuperField.addField(field);
        }
        else{
            superField.addField(field);
        }
    }

    public void deleteField(Field field){
        Index pos = field.getFieldMapPos();
        SuperField superField = getSuperFieldByPos(new Coord(pos.x*fieldSize, pos.y * fieldSize));
        superField.deleteField(field);
    }

    public Collection<Field> getValues() {
        return map.values();
    }

    public void setMap(HashMap<Index, Field> map){
        this.map = map;
    }

    public int getFieldSize() {
        return fieldSize;
    }

    public int getSuperFieldSize(){
        return superFieldSize;
    }

    public ArrayList<BasicShape> getShapes(Index index, Index number){
        ArrayList<BasicShape> result = new ArrayList<>();
        if ( (Math.sqrt(number.x * number.y) * fieldSize ) / superFieldSize < 4){ // show all field that in this window
            for (int i = index.x; i <= number.x + index.x; i++){
                for (int j = index.y; j <= number.y + index.y; j++){
                    Field field = getFieldByIndex(new Index(i, j));
                    if (field == null) continue;
                    Element element = field.getGround();
                    if (element != null) result.addAll(element.getShapes());
                    element = field.getCity();
                    if (element != null) result.addAll(element.getShapes());
                    element = field.getArmyElement();
                    if (element != null) result.addAll(element.getShapes());
                    element = field.getTree();
                    if (element != null) result.addAll(element.getShapes());
                    element = field.getManor();
                    if (element != null) result.addAll(element.getShapes());
                    element = field.getVillage();
                    if (element != null) result.addAll(element.getShapes());
                    element = field.getRiver();
                    if (element != null) result.addAll(element.getShapes());
                    element = field.getFarm();
                    if (element != null) result.addAll(element.getShapes());
                    element = field.getSawmill();
                    if (element != null) result.addAll(element.getShapes());
                    element = field.getTrawler();
                    if (element != null) result.addAll(element.getShapes());
                    element = field.getMine();
                    if (element != null) result.addAll(element.getShapes());
                }
            }
        }
        else{ // show super fields
            Index supIndex = new Index(index.x * fieldSize / superFieldSize, index.y * fieldSize / superFieldSize);
            int xnum = (int)Math.ceil((double) (number.x * fieldSize) / superFieldSize);
            int ynum = (int)Math.ceil((double) (number.y * fieldSize) / superFieldSize);
            Index supNumber = new Index(xnum, ynum);
            for (int i = supIndex.x; i <= supNumber.x + supIndex.x; i++){
                for (int j = supIndex.y; j <= supNumber.y + supIndex.y; j++){
                    SuperField superField = getSuperFieldByIndex(new Index(i, j));
                    if (superField == null) continue;
                    result.addAll(superField.getShapes());
                }
            }
        }
        return result;
    }

    public GameEngine getGameEngine() {
        return gameEngine;
    }
}
