package Foundation.Elements;

import Foundation.*;
import Foundation.Elements.City;
import Foundation.Elements.Element;
import Foundation.Runnable.RunableElement;
import Images.ManorImage;

import java.util.ArrayList;

public class Manor extends RunableElement {

    private ArrayList<Coord> farms;

    private ResourceStore resourceStore;
    private ArrayList<Coord> territory;
    private City city;

    public Manor(Time time, Field parent, FieldMap map, City city) {
        super(Type.Manor, time, parent, map);
        this.city = city;
        this.resourceStore = new ResourceStore();
        territory = new ArrayList<>();
        territory.add(parent.getFieldMapPos());
        farms = new ArrayList<>();
        Coord pos = new Coord(parent.getFieldMapPos());
        pos.x = pos.x * parent.getSize();
        pos.y = pos.y * parent.getSize();
        setBasicShapes(new ManorImage(pos, new Coord(parent.getSize(), parent.getSize()), null).getBasicShapes());
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

    public void createFarm(Coord point){
        System.out.println(city.getTerritory().size());
        if (!territory.contains(point)) return;
        Field field = map.getFieldByIndex(point);
        if (field.getFarm() != null) return; // TODO: check if there are other construction like fishing village, sawmill or mine
        Farm farm = new Farm(this, time, field, map);
        field.setFarm(farm);
        farms.add(point);
        map.getGameEngine().getGameWindowElement().setShapes();
        System.out.println(city.getTerritory().size());
    }

    public ResourceStore getResourceStore() {
        return resourceStore;
    }

    @Override
    public void run() {
        for(Coord pos: farms){
            Farm farm = map.getFieldByIndex(pos).getFarm();
            farm.getWork().doJob();
        }
    }
}
