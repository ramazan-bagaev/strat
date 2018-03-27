package Foundation.Runnable;

import Foundation.*;
import Foundation.BasicShapes.BasicShape;
import Foundation.BasicShapes.RectangleShape;
import Foundation.Elements.City;
import Foundation.Elements.Ground;
import Utils.Index;
import Utils.Coord;

import java.util.ArrayList;

public class SuperField implements RunEntity {

    private Time time;
    private FieldMap fieldMap;

    private Index pos;
    private int size;

    private int numberOfWaterField;
    private int numberOfSoilField;

    private ArrayList<Index> cityPos;

    private ArrayList<Field> fields;

    private ArrayList<BasicShape> basicShapes;

    private int isWater; // 1 - water, 0 - ground

    public SuperField(Index pos, FieldMap fieldMap, Time time){
        this.time = time;
        this.pos = new Index(pos);
        this.size = fieldMap.getSuperFieldSize();
        this.fieldMap = fieldMap;
        this.fields = new ArrayList<>();
        this.cityPos = new ArrayList<>();
        this.numberOfSoilField = 0;
        this.numberOfWaterField = 0;
        this.basicShapes = new ArrayList<>();
        this.isWater = 1;
        basicShapes.add(new RectangleShape(new Coord(pos.x*size, pos.y*size), new Coord(size, size), new Color(Color.Type.Blue), true));
    }

    public void addField(Field field){
        fields.add(field);
        if (field.getGroundType() == Ground.GroundType.Water){
            numberOfWaterField += 1;
        }
        else{
            numberOfSoilField += 1;
        }
        recalibrate();
    }

    public void recalibrate(){
        if ((numberOfWaterField > numberOfSoilField && isWater == 0) || (numberOfWaterField < numberOfSoilField && isWater == 1)){
            basicShapes.clear();
            Color color;
            if (isWater == 0){
                color = new Color(Color.Type.Blue);
            }
            else {
                color = new Color(Color.Type.Green);
            }
            RectangleShape newShape = new RectangleShape(new Coord(pos.x*size, pos.y * size), new Coord(size, size), color, true);
            basicShapes.add(newShape);
        }
    }

    public ArrayList<BasicShape> getShapes(){
        return basicShapes;
    }

    public void deleteField(Field field){
        fields.remove(field);
        City city = field.getCity();
        if (city != null) System.out.println("Danger!!! at SuperField deleteField method"); // should delete this field info from cityPos
        if (city != null){
            /// here will be city destroy method
        }
        if (field.getGroundType() == Ground.GroundType.Water){
            numberOfWaterField--;
        }
        else{
            numberOfSoilField++;
        }
        recalibrate();
    }

    @Override  // RunEntity method
    public void run() {
        System.out.println("chyl");
    }

}
