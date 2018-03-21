package Foundation.Elements;

import Foundation.*;
import Foundation.Elements.City;
import Foundation.Elements.Element;
import Foundation.Runnable.RunableElement;
import Images.ManorImage;

import java.util.ArrayList;

public class Manor extends RunableElement {

    private ArrayList<Coord> villages;

    private ResourceStore resourceStore;
    private ArrayList<Coord> territory;
    private City city;

    public Manor(Time time, Field parent, FieldMap map, City city) {
        super(Type.Manor, time, parent, map);
        this.city = city;
        this.resourceStore = new ResourceStore();
        territory = new ArrayList<>();
        territory.add(parent.getFieldMapPos());
        villages = new ArrayList<>();
        setBasicShapes(new ManorImage(new Coord(0, 0), new Coord(parent.getSize(), parent.getSize()), null).getBasicShapesRemoveAndShiftBack());
    }

    public void addTerritory(Coord pos) {
        territory.add(pos);
    }

    public ArrayList<Coord> getTerritory() {
        return territory;
    }

    public City getCity() {
        return city;
    }

    public void createVillage(Coord point){
        if (!territory.contains(point)) return;
        Field field = map.getFieldByIndex(point);
        if (field.getVillage() != null) return; // TODO: check if there are other construction like fishing village, sawmill or mine
        Village village = new Village(time, field, map, this);
        field.setVillage(village);
        villages.add(point);
        map.getGameEngine().getGameWindowElement().setShapes();
        map.getGameEngine().getGameWindowElement().getGameEngine().addRunEntity(village);
    }

    public ResourceStore getResourceStore() {
        return resourceStore;
    }

    @Override
    public void run() {
        for(Coord pos: villages){
            Village village = map.getFieldByIndex(pos).getVillage();
            //village.getWork().doJob();
        }
    }
}
