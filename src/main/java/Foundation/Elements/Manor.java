package Foundation.Elements;

import Foundation.*;
import Foundation.Person.Person;
import Foundation.Runnable.AI.AI;
import Foundation.Runnable.AI.StupidAI.StupidManorAI;
import Foundation.Runnable.Actors.ManorActor;
import Images.ManorImage;
import Utils.Index;
import Utils.Coord;

import java.util.ArrayList;

public class Manor extends HabitableFieldElement {


    private ArrayList<Index> villages;

    private Person lord;
    private Territory territory;
    private City city;

    private ManorActor actor;

    public Manor(Time time, Field parent, FieldMap map, City city) {
        super(Type.Manor, time, parent, map);
        this.city = city;
        territory = new Territory();
        territory.add(parent.getFieldMapPos());
        villages = new ArrayList<>();
        setBasicShapes(new ManorImage(new Coord(0, 0), new Coord(parent.getSize(), parent.getSize()), null).getBasicShapesRemoveAndShiftBack());
    }

    public Manor(Time time, Field parent, FieldMap map, City city, Person lord) {
        super(Type.Manor, time, parent, map);
        this.city = city;
        this.lord = lord;
        this.actor = new ManorActor(lord, this);
        AI manorAI = new StupidManorAI(actor, time);
        actor.setAi(manorAI);
        parent.getMap().getGameEngine().addRunEntity(manorAI);
        society.addPerson(lord);
        territory = new Territory();
        territory.add(parent.getFieldMapPos());
        villages = new ArrayList<>();
        setBasicShapes(new ManorImage(new Coord(0, 0), new Coord(parent.getSize(), parent.getSize()), null).getBasicShapesRemoveAndShiftBack());
    }

    public void addTerritory(Index pos) {
        territory.add(pos);
    }

    public Territory getTerritory() {
        return territory;
    }

    public City getCity() {
        return city;
    }

    public void createVillage(Index point, Person steward){
        if (!territory.contains(point)) return;
        Field field = map.getFieldByIndex(point);
        if (field.getVillage() != null) return; // TODO: check if there are other construction like fishing village, sawmill or mine
        society.removePerson(steward);
        Village village = new Village(time, field, map, steward, this);
        field.setVillage(village);
        villages.add(point);
        map.getGameEngine().getGameWindowElement().setShapes();
        map.getGameEngine().getGameWindowElement().getGameEngine().addRunEntity(village);
    }


    @Override
    public void run() {
        super.run();
        for(Index pos: villages){
            Village village = map.getFieldByIndex(pos).getVillage();
            ArrayList<Resource> resources = village.getPartOfResource(0.9);
            for(Resource resource: resources) resourceStore.addResource(resource);
        }
    }

    public Person getLord() {
        return lord;
    }

    public void setLord(Person lord) {
        this.lord = lord;
        society.addPerson(lord);
    }

    public ArrayList<Resource> getPartOfResource(double part){
        if (part > 1) part = 1;
        if (part < 0) part = 0;
        ArrayList<Resource> result = new ArrayList<>();
        for(Resource resource: resourceStore.getResources()){
            Resource share = resource.getResource((int) (resource.getAmount()*part));
            result.add(share);
        }
        return result;
    }


    public ArrayList<Index> getVillages() {
        return villages;
    }
}
