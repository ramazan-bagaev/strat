package Foundation.Army;

import Foundation.BasicShapes.RectangleShape;
import Foundation.Color;
import Foundation.DynamicDrawable;
import Foundation.FieldMap;
import Foundation.OpenGLBinder;
import Foundation.Runnable.RunEntity;
import Utils.Coord;
import Utils.Index;

public class ArmyAtom implements RunEntity, DynamicDrawable{

    private RectangleShape rectangleShape;

    private Index fieldIndex;
    private Coord fieldCoord;
    private int fieldSize;

    private Index armyPosIndex; // relative position index, not Field Index!!!!

    private FieldMap fieldMap;

    private Army army;

    public ArmyAtom(FieldMap fieldMap, Index fieldIndex, Coord fieldCoord){
        this.fieldMap = fieldMap;
        this.fieldSize = fieldMap.getFieldSize();
        this.fieldIndex = new Index(fieldIndex);
        this.fieldCoord = new Coord(fieldCoord);
        rectangleShape = new RectangleShape(new Coord(), new Coord(10,10), new Color(Color.Type.Blue),false, true);
        fieldMap.getFieldByIndex(fieldIndex).addDynamicDrawable(this);
    }

    public ArmyAtom(Army army, Index fieldIndex, Coord fieldCoord){
        this.army = army;
        this.fieldMap = army.getFieldMap();
        this.fieldIndex = new Index(fieldIndex);
        this.fieldCoord = new Coord(fieldCoord);
        this.fieldSize = fieldMap.getFieldSize();
        rectangleShape = new RectangleShape(new Coord(), new Coord(10,10), new Color(Color.Type.Blue),false, true);
        fieldMap.getFieldByIndex(fieldIndex).addDynamicDrawable(this);
    }

    public void move(){
    }

    @Override
    public void run() {

    }

    public Coord getFieldCoord() {
        return fieldCoord;
    }

    public void setFieldCoord(Coord fieldCoord) {
        this.fieldCoord = fieldCoord;
    }

    public Index getFieldIndex() {
        return fieldIndex;
    }

    public void setFieldIndex(Index fieldIndex) {
        this.fieldIndex = fieldIndex;
    }

    public Index getArmyPosIndex() {
        return armyPosIndex;
    }

    public void setArmyPosIndex(Index armyPosIndex) {
        this.armyPosIndex = armyPosIndex;
    }

    @Override
    public void draw(OpenGLBinder openGLBinder) {
        rectangleShape.setPos(fieldCoord.add(fieldIndex.multiply(fieldSize).toCoord()));
        openGLBinder.draw(rectangleShape);
        System.out.println("we are here");
    }
}
