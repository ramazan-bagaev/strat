package Foundation;

import Foundation.Coord;
import Foundation.Field;

import java.util.*;

public class FieldMap {

    private int fieldSize;
    private int superFieldSize;
    private HashMap<Coord, Field> map;
    private HashMap<Coord, SuperField> superFieldMap;

    public FieldMap(int superFieldSize, int fieldSize)
    {
        this.fieldSize = fieldSize;
        this.superFieldSize = superFieldSize;
        map = new HashMap<>();
        superFieldMap = new HashMap<>();
    }

    public Field getFieldByIndex(Coord index){
        return map.getOrDefault(index, null);
    }

    public Field getFieldByPos(Coord coord) {
        Coord index = new Coord(coord.x / fieldSize, coord.y / fieldSize);
        return getFieldByIndex(index);
    }

    public SuperField getSuperFieldByIndex(Coord index){
        return superFieldMap.getOrDefault(index, null);
    }

    public SuperField getSuperFieldByPos(Coord coord){
        Coord index = new Coord(coord.x / superFieldSize, coord.y / superFieldSize);
        return getSuperFieldByIndex(index);
    }

    public void addField(Coord coord, Field field){
        if (getFieldByIndex(coord) != null){
            deleteField(field);
        }
        map.put(coord, field);
        Coord index = new Coord(coord.x * fieldSize / superFieldSize, coord.y * fieldSize / superFieldSize);
        SuperField superField = getSuperFieldByIndex(index);
        if (superField == null){
            Coord pos = new Coord(index.x * superFieldSize, index.y * superFieldSize);
            SuperField newSuperField = new SuperField(pos, this);
            superFieldMap.put(index, newSuperField);
            newSuperField.addField(field);
        }
        else{
            superField.addField(field);
        }
    }

    public void deleteField(Field field){
        Coord pos = field.getFieldMapPos();
        SuperField superField = getSuperFieldByPos(new Coord(pos.x*fieldSize, pos.y * fieldSize));
        superField.deleteField(field);
    }

    public Collection<Field> getValues() {
        return map.values();
    }

    public void setMap(HashMap<Coord, Field> map){
        this.map = map;
    }

    public int getFieldSize() {
        return fieldSize;
    }

    public int getSuperFieldSize(){
        return superFieldSize;
    }

    public ArrayList<BasicShape> getShapes(Coord index, Coord number){
        ArrayList<BasicShape> result = new ArrayList<>();
        if ( (number.x * number.y * fieldSize * fieldSize) / (superFieldSize * superFieldSize) < 4){ // show all field that in this window
            for (int i = index.x; i <= number.x + index.x; i++){
                for (int j = index.y; j <= number.y + index.y; j++){
                    Field field = getFieldByIndex(new Coord(i, j));
                    if (field == null) continue;
                    Element element = field.getGround();
                    if (element != null) result.addAll(element.getShapes());
                    element = field.getCity();
                    if (element != null) result.addAll(element.getShapes());
                    element = field.getArmy();
                    if (element != null) result.addAll(element.getShapes());
                    element = field.getRiver();
                    if (element != null) result.addAll(element.getShapes());
                    element = field.getTree();
                    if (element != null) result.addAll(element.getShapes());
                }
            }
        }
        else{ // show super fields
            Coord supIndex = new Coord(index.x * fieldSize / superFieldSize, index.y * fieldSize / superFieldSize);
            int xnum = (int)Math.ceil((double) (number.x * fieldSize) / superFieldSize);
            int ynum = (int)Math.ceil((double) (number.y * fieldSize) / superFieldSize);
            Coord supNumber = new Coord(xnum, ynum);
            for (int i = supIndex.x; i <= supNumber.x + supIndex.x; i++){
                for (int j = supIndex.y; j <= supNumber.y + supIndex.y; j++){
                    SuperField superField = getSuperFieldByIndex(new Coord(i, j));
                    if (superField == null) continue;
                    result.addAll(superField.getShapes());
                }
            }
        }
        return result;
    }
}
