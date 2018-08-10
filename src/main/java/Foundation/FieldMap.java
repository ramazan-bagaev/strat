package Foundation;

import Foundation.BasicShapes.BasicShape;
import Foundation.Elements.ArmyFieldElement;
import Foundation.Elements.FieldElement;
import Foundation.FieldObjects.FieldObject;
import Foundation.Runnable.SuperField;
import Foundation.Time.Time;
import Generation.GeneratedObjects.Continent;
import Utils.Geometry.Index;
import Utils.Geometry.Coord;

import java.util.*;

public class FieldMap {

    private int fieldSize;
    private int superFieldSize;
    private HashMap<Index, Field> map;
    private HashMap<Index, SuperField> superFieldMap;
    private GameEngine gameEngine;
    private Random random;
    private Time time;

    private ArrayList<Continent> continents;

    public FieldMap(int superFieldSize, int fieldSize, GameEngine gameEngine)
    {
        this.gameEngine = gameEngine;
        this.fieldSize = fieldSize;
        this.superFieldSize = superFieldSize;
        this.time = gameEngine.getTime();
        map = new HashMap<>();
        superFieldMap = new HashMap<>();
        random = new Random();
        continents = new ArrayList<>();
    }

    public Field getFieldByIndex(Index index){
        return map.getOrDefault(index, null);
    }

    public Field getFieldByPos(Coord coord) {
        Index index = new Index((int)(coord.x / fieldSize), (int)(coord.y / fieldSize));
        return getFieldByIndex(index);
    }

    public FieldObject getFieldObjectByPos(Coord coord){
        //Index fieldMapIndex = new Index((int)(coord.x / fieldSize), (int)(coord.y / fieldSize));
        Index fieldIndex = new Index((int)(coord.x % fieldSize), (int)(coord.y % fieldSize));
        return getFieldByPos(coord).getFieldObject(fieldIndex);
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

    public ArrayList<BasicShape> getShapes(Index index, Index number, MainWindowCameraConfiguration.Mode mode){
        ArrayList<BasicShape> result = new ArrayList<>();
        Index iter = new Index(0, 0);
        if (mode == MainWindowCameraConfiguration.Mode.Detailed){
            for (int i = index.x; i <= number.x + index.x; i++) {
                for (int j = index.y; j <= number.y + index.y; j++) {
                    iter.x = i;
                    iter.y = j;
                    Field field = getFieldByIndex(iter);
                    if (field == null) continue;

                    FieldElement fieldElement = field.getGround();
                    if (fieldElement != null) result.addAll(fieldElement.getShapes());

                    ArmyFieldElement armyElement = field.getArmyElement();
                    if (armyElement != null) result.addAll(armyElement.getFormationShapes());

                    for (FieldObject fieldObject : field.getFieldObjects().getArray()) {
                        result.addAll(fieldObject.getBasicShapes());
                    }

                    for (FieldObject fieldObject: field.getFieldObjects().getTempArray()){
                        result.addAll(fieldObject.getBasicShapes());
                    }
                }
            }
        }
        if ( mode == MainWindowCameraConfiguration.Mode.Normal){ // show all field that in this window
            for (int i = index.x; i <= number.x + index.x; i++){
                for (int j = index.y; j <= number.y + index.y; j++){
                    iter.x = i;
                    iter.y = j;
                    Field field = getFieldByIndex(iter);
                    if (field == null) continue;
                    FieldElement fieldElement = field.getGround();
                    if (fieldElement != null) result.addAll(fieldElement.getShapes());
                    fieldElement = field.getRiver();
                    if (fieldElement != null) result.addAll(fieldElement.getShapes());
                    fieldElement = field.getRoad();
                    if (fieldElement != null) result.addAll(fieldElement.getShapes());
                    fieldElement = field.getCity();
                    if (fieldElement != null) result.addAll(fieldElement.getShapes());
                    fieldElement = field.getTree();
                    if (fieldElement != null) result.addAll(fieldElement.getShapes());
                    fieldElement = field.getManor();
                    if (fieldElement != null) result.addAll(fieldElement.getShapes());
                    fieldElement = field.getVillage();
                    if (fieldElement != null) result.addAll(fieldElement.getShapes());
                    fieldElement = field.getFarm();
                    if (fieldElement != null) result.addAll(fieldElement.getShapes());
                    fieldElement = field.getSawmill();
                    if (fieldElement != null) result.addAll(fieldElement.getShapes());
                    fieldElement = field.getTrawler();
                    if (fieldElement != null) result.addAll(fieldElement.getShapes());
                    fieldElement = field.getMine();
                    if (fieldElement != null) result.addAll(fieldElement.getShapes());
                    fieldElement = field.getArmyElement();
                    if (fieldElement != null) result.addAll(fieldElement.getShapes());


                }
            }
        }
        if (mode == MainWindowCameraConfiguration.Mode.SuperBlock){ // show super fields
            Index supIndex = new Index(index.x * fieldSize / superFieldSize, index.y * fieldSize / superFieldSize);
            int xnum = (int)Math.ceil((double) (number.x * fieldSize) / superFieldSize);
            int ynum = (int)Math.ceil((double) (number.y * fieldSize) / superFieldSize);
            Index supNumber = new Index(xnum, ynum);
            for (int i = supIndex.x; i <= supNumber.x + supIndex.x; i++){
                for (int j = supIndex.y; j <= supNumber.y + supIndex.y; j++){
                    iter.x = i;
                    iter.y = j;
                    SuperField superField = getSuperFieldByIndex(iter);
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

    public void drawDynamicDrawable(Index index, Index number, OpenGLBinder openGLBinder){
        Index iter = new Index(0, 0);
        for (int i = index.x; i <= number.x + index.x; i++) {
            for (int j = index.y; j <= number.y + index.y; j++) {
                iter.x = i;
                iter.y = j;
                Field field = getFieldByIndex(iter);
                if (field == null) continue;
                field.drawDynamicDrawable(openGLBinder);
            }
        }
    }

    public Random getRandom() {
        return random;
    }

    public ArrayList<Continent> getContinents() {
        return continents;
    }

    public void addContinent(Continent continent){
        continents.add(continent);
    }

    public Time getTime() {
        return time;
    }
}