package Foundation.Runnable;

import Foundation.*;
import Foundation.Elements.City;
import Foundation.Elements.Ground;

import java.util.ArrayList;

public class SuperField implements RunEntity {

    private Time time;
    private FieldMap fieldMap;

    private Coord globalPos;
    private int size;

    private int numberOfWaterField;
    private int numberOfSoilField;

    private ArrayList<Coord> cityPos;

    private ArrayList<Field> fields;

    private ArrayList<BasicShape> basicShapes;

    private int isWater; // 1 - water, 0 - ground

    public SuperField(Coord globalPos, FieldMap fieldMap, Time time){
        this.time = time;
        this.globalPos = new Coord(globalPos);
        this.size = fieldMap.getSuperFieldSize();
        this.fieldMap = fieldMap;
        this.fields = new ArrayList<>();
        this.cityPos = new ArrayList<>();
        this.numberOfSoilField = 0;
        this.numberOfWaterField = 0;
        this.basicShapes = new ArrayList<>();
        this.isWater = 1;
        basicShapes.add(new RectangleShape(globalPos, new Coord(size, size), new Color(Color.Type.Blue), true));
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
            RectangleShape newShape = new RectangleShape(globalPos, new Coord(size, size), color, true);
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
            city.destroy();
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

    }

}
