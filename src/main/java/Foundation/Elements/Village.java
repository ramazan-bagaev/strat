package Foundation.Elements;

import Foundation.*;
import Foundation.Person.People;
import Foundation.Person.Person;
import Foundation.Runnable.AI.AI;
import Foundation.Runnable.AI.StupidAI.StupidVillageAI;
import Foundation.Runnable.Actors.VillageActor;
import Images.VillageImage;
import Utils.Content;
import Utils.Index;
import Utils.Coord;
import Utils.Subscription;

import java.util.ArrayList;
import java.util.LinkedList;

public class Village extends HabitableFieldElement {

    private Content workContent;
    private Manor manor;
    private Person steward;

    private VillageActor actor;
    private ArrayList<WorkFieldElement> workElements;


    private Territory availableWater;

    public Village(Time time, Field parent, FieldMap map, Person steward, Manor manor) {
        super(Type.Village, time, parent, map);
        this.manor = manor;
        this.steward = steward;
        workElements = new ArrayList<>();
        actor = new VillageActor(steward, this, time);
        AI villageAI = new StupidVillageAI(actor, time);
        actor.setAi(villageAI);
        parent.getMap().getGameEngine().addRunEntity(villageAI);
        society.addPerson(steward);
        availableWater = new Territory();
        int size = parent.getSize();
        workContent = new Content();
        setBasicShapes(new VillageImage(new Coord(0, 0), new Coord(size, size), parent.getRandom(), null).getBasicShapesRemoveAndShiftBack());
    }

    public void createFarm(Index point){
        if (!getManor().getTerritory().contains(point)) return;
        Field field = map.getFieldByIndex(point);
        if (field.getFarm() != null) return; // TODO: check if there are other construction like fishing village, sawmill or mine
        Farm farm = new Farm(this, new People(), time, field, map);
        field.setFarm(farm);
        workElements.add(farm);
        workContent.changed();
        map.getGameEngine().getGameWindowElement().setShapes();
    }

    public void createSawmill(Index point){
        if (!getManor().getTerritory().contains(point)) return;
        Field field = map.getFieldByIndex(point);
        if (field.getSawmill() != null) return; // TODO: check if there are other construction like fishing village, sawmill or mine
        Sawmill sawmill = new Sawmill(this, new People(), time, field, map);
        field.setSawmill(sawmill);
        workElements.add(sawmill);
        workContent.changed();
        map.getGameEngine().getGameWindowElement().setShapes();
    }

    public void createTrawler(Index point){
        if (!getManor().getTerritory().contains(point)) return;
        Field field = map.getFieldByIndex(point);
        if (field.getTrawler() != null) return; // TODO: check if there are other construction like fishing village, sawmill or mine
        Trawler trawler = new Trawler(this, new People(), time, field, map);
        field.setTrawler(trawler);
        workElements.add(trawler);
        workContent.changed();
        map.getGameEngine().getGameWindowElement().setShapes();
    }

    public void createMine(Index point){
        if (!getManor().getTerritory().contains(point)) return;
        Field field = map.getFieldByIndex(point);
        if (field.getMine() != null) return; // TODO: check if there are other construction like fishing village, sawmill or mine
        Mine mine = new Mine(this, new People(), time, field, map);
        field.setMine(mine);
        workElements.add(mine);
        workContent.changed();
        map.getGameEngine().getGameWindowElement().setShapes();
    }

    public Manor getManor() {
        return manor;
    }

    public void markAvailableWater(){
        availableWater.clear();
        Index pos = parent.getFieldMapPos();
        Territory territory = new Territory(manor.getTerritory());
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

    public Territory getAvailableWater() {
        markAvailableWater();
        return availableWater;
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

    public ArrayList<WorkFieldElement> getWorkElements() {
        return workElements;
    }

    public void removeWorkers(int number, Index index){
        for(WorkFieldElement workElement: workElements){
            if (workElement.getParent().getFieldMapPos().equals(index)){
                workElement.removeRandomPeople(number);
                return;
            }
        }
    }

    public void addWorkers(int number, Index index){
        for(WorkFieldElement workElement: workElements){
            if (workElement.getParent().getFieldMapPos().equals(index)){
                People people = society.getPeople();
                LinkedList<Person> rightPeople = new LinkedList<>();
                for(Person person: people.getPersonArray()){
                    if (person.getWork() != null) continue;
                    rightPeople.add(person);
                }
                if (rightPeople.size() == 0) return;
                People resultPeople = new People();
                if (number > rightPeople.size()) number = rightPeople.size();
                if (number <= 0) return;
                for(int i = 0; i < number; i++){
                    int ind = getParent().getRandom().nextInt(rightPeople.size());
                    Person person = rightPeople.get(ind);
                    resultPeople.addPerson(person);
                    rightPeople.remove(person);
                }
                workElement.addPeople(resultPeople);
                return;
            }
        }
    }


    @Override
    public void run() {
        super.run();
        for(WorkFieldElement workElement: workElements) workElement.getWork().doJob();
    }

    @Override
    public void subscribe(String key, Subscription subscription){
        switch (key){
            case "work":
                workContent.subscribe(subscription);
                break;
        }
    }

    @Override
    public void unsubscribe(String key, Subscription subscription){
        switch (key){
            case "work":
                workContent.unsubscribe(subscription);
                break;
        }
    }
}
