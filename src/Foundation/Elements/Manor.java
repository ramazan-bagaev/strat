package Foundation.Elements;

import Foundation.*;
import Foundation.Person.People;
import Foundation.Runnable.RunableElement;
import Images.ManorImage;
import Utils.Index;
import Utils.Coord;

import java.util.ArrayList;

public class Manor extends RunableElement {

    private ArrayList<Index> villages;

    private People people;
    private ResourceStore resourceStore;
    private ArrayList<Index> territory;
    private City city;

    public Manor(Time time, Field parent, FieldMap map, City city) {
        super(Type.Manor, time, parent, map);
        this.city = city;
        this.resourceStore = new ResourceStore();
        this.people = city.getPeople();
        territory = new ArrayList<>();
        territory.add(parent.getFieldMapPos());
        villages = new ArrayList<>();
        setBasicShapes(new ManorImage(new Coord(0, 0), new Coord(parent.getSize(), parent.getSize()), null).getBasicShapesRemoveAndShiftBack());
    }

    public void addTerritory(Index pos) {
        territory.add(pos);
    }

    public ArrayList<Index> getTerritory() {
        return territory;
    }

    public City getCity() {
        return city;
    }

    public void createVillage(Index point){
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
        for(Index pos: villages){
            Village village = map.getFieldByIndex(pos).getVillage();
            //village.getWork().doJob();
        }
    }

    public People getPeople() {
        return people;
    }
}
