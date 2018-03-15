package Foundation.Elements;

import Foundation.Coord;
import Foundation.Elements.City;
import Foundation.Elements.Element;
import Foundation.Field;
import Foundation.FieldMap;
import Foundation.Time;
import Images.ManorImage;

import java.util.ArrayList;

public class Manor extends Element {

    private ArrayList<Coord> farms;

    private ArrayList<Coord> territory;
    private City city;

    public Manor(Time time, Field parent, FieldMap map, City city) {
        super(Type.Manor, time, parent, map);
        this.city = city;
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
        map.getGameWindowElement().setShapes();
        System.out.println(city.getTerritory().size());
    }
}
