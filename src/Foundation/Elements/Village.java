package Foundation.Elements;

import Foundation.*;
import Foundation.Person.People;
import Foundation.Person.Person;
import Foundation.Runnable.RunableElement;
import Images.VillageImage;
import Utils.Index;
import Utils.Coord;

import java.util.ArrayList;
import java.util.LinkedList;

public class Village extends RunableElement{

    private ResourceStore resourceStore;

    private Manor manor;
    private Person steward;
    private People people;

    private ArrayList<Index> farms;
    private ArrayList<Index> sawmills;
    private ArrayList<Index> trawlers;
    private ArrayList<Index> mines;

    private ArrayList<Index> availableWater;

    public Village(Time time, Field parent, FieldMap map, Person steward, Manor manor) {
        super(Type.Village, time, parent, map);
        this.manor = manor;
        this.resourceStore = new ResourceStore();
        people = new People(parent);
        this.steward = steward;
        people.addPerson(steward);
        farms = new ArrayList<>();
        sawmills = new ArrayList<>();
        trawlers = new ArrayList<>();
        availableWater = new ArrayList<>();
        mines = new ArrayList<>();
        int size = parent.getSize();
        setBasicShapes(new VillageImage(new Coord(0, 0), new Coord(size, size), parent.getRandom(), null).getBasicShapesRemoveAndShiftBack());
    }

    public void createFarm(Index point){
        if (!getManor().getTerritory().contains(point)) return;
        Field field = map.getFieldByIndex(point);
        if (field.getFarm() != null) return; // TODO: check if there are other construction like fishing village, sawmill or mine
        Farm farm = new Farm(this, time, field, map);
        field.setFarm(farm);
        farms.add(point);
        map.getGameEngine().getGameWindowElement().setShapes();
    }

    public void createSawmill(Index point){
        if (!getManor().getTerritory().contains(point)) return;
        Field field = map.getFieldByIndex(point);
        if (field.getSawmill() != null) return; // TODO: check if there are other construction like fishing village, sawmill or mine
        Sawmill sawmill = new Sawmill(this, time, field, map);
        field.setSawmill(sawmill);
        sawmills.add(point);
        map.getGameEngine().getGameWindowElement().setShapes();
    }

    public void createTrawler(Index point){
        if (!getManor().getTerritory().contains(point)) return;
        Field field = map.getFieldByIndex(point);
        if (field.getTrawler() != null) return; // TODO: check if there are other construction like fishing village, sawmill or mine
        Trawler trawler = new Trawler(this, time, field, map);
        field.setTrawler(trawler);
        trawlers.add(point);
        map.getGameEngine().getGameWindowElement().setShapes();
    }

    public void createMine(Index point){
        if (!getManor().getTerritory().contains(point)) return;
        Field field = map.getFieldByIndex(point);
        if (field.getMine() != null) return; // TODO: check if there are other construction like fishing village, sawmill or mine
        Mine mine = new Mine(this, time, field, map);
        field.setMine(mine);
        mines.add(point);
        map.getGameEngine().getGameWindowElement().setShapes();
    }

    public Manor getManor() {
        return manor;
    }

    public People getPeople() {
        return people;
    }

    public ResourceStore getResourceStore() {
        return resourceStore;
    }

    public void addPeople(ArrayList<Person> people){
        this.people.addPeople(people);
    }

    public void removePerson(Person person){
        this.people.removePerson(person);
    }

    public void markAvailableWater(){
        availableWater.clear();
        Index pos = parent.getFieldMapPos();
        ArrayList<Index> territory = new ArrayList<>(manor.getTerritory());
        LinkedList<Index> que = new LinkedList<>();
        que.addFirst(pos);
        territory.remove(pos);
        while(que.size() != 0){
            Index curPos = que.pop();
            for(Index.Direction direction: Index.getAllDirections()) {
                Index newPos = curPos.add(new Index(direction));
                //if (!manor.getTerritory().contains(newPos)) continue;
                if (!territory.contains(newPos)) continue;
                if (map.getFieldByIndex(newPos).getGroundType() == Ground.GroundType.Water) {
                    que.add(newPos);
                    availableWater.add(newPos);
                    territory.remove(newPos);
                }
            }

        }
    }

    public ArrayList<Index> getAvailableWater() {
        markAvailableWater();
        return availableWater;
    }

    @Override
    public void run() {
        for(Index pos: farms){
            Farm farm = map.getFieldByIndex(pos).getFarm();
            farm.getWork().doJob();
        }
        for(Index pos: sawmills){
            Sawmill sawmill = map.getFieldByIndex(pos).getSawmill();
            sawmill.getWork().doJob();
        }
        for(Index pos: trawlers){
            Trawler trawler = map.getFieldByIndex(pos).getTrawler();
            trawler.getWork().doJob();
        }
        for(Index pos: mines){
            Mine mine = map.getFieldByIndex(pos).getMine();
            mine.getWork().doJob();
        }
    }
}
