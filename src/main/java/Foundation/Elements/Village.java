package Foundation.Elements;

import Foundation.*;
import Foundation.FieldObjects.BuildingObject.PeasantHouseObject;
import Foundation.FieldObjects.BuildingObject.StewardBuildingObject;
import Foundation.FieldObjects.TransportObjects.PrimingRoadCrossObject;
import Foundation.Person.People;
import Foundation.Person.Person;
import Foundation.Recources.Resource;
import Foundation.Runnable.AI.AI;
import Foundation.Runnable.AI.StupidAI.StupidVillageAI;
import Foundation.Runnable.Actors.VillageActor;
import Generation.NameGenerator;
import Images.VillageImage;
import Utils.Content;
import Utils.Geometry.Index;
import Utils.Geometry.Coord;
import Utils.Subscription;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Village extends HabitableFieldElement {

    private Content workContent;
    private Manor manor;
    private Person steward;

    private VillageActor actor;
    private ArrayList<WorkFieldElement> workElements;


    private Territory availableWater;

    public static Village constructEmptyVillageWithSteward(Field parent, Person steward, Manor manor){
        Village res = new Village(parent, manor);
        res.setSteward(steward);
        res.fillField();
        return res;
    }

    public static Village constructVillageWithRandomPeople(Field parent, Manor manor){
        Village res = new Village(parent, manor);
        res.addRandomPeople();
        res.fillField();
        return res;
    }

    public Village(Field parent, Manor manor) {
        super(Type.Village, parent);
        this.manor = manor;
        workElements = new ArrayList<>();
        //addRandomPeople();
        availableWater = new Territory();
        int size = parent.getSize();
        workContent = new Content();
        //fillField();
        setBasicShapes(new VillageImage(new Coord(0, 0), new Coord(size, size), parent.getRandom(), null).getBasicShapesRemoveAndShiftBack());
    }

    public void addRandomPeople(){
        NameGenerator nameGenerator = new NameGenerator(parent.getRandom());
        Person steward = new Person(nameGenerator.generate(), null, Person.Kasta.Middle);
        setSteward(steward);
        Random random = parent.getRandom();
        int amount = random.nextInt(300);
        for(int i = 0; i < amount; i++){
            Person person = new Person(nameGenerator.generate(), null, Person.Kasta.Low);
            society.addPerson(person);
        }
    }

    public void addFirstRoadPiece(){
        int cellAmount = parent.getCellAmount();
        int x = cellAmount/2 - 1;
        int y = cellAmount/2 - 1;
        Index pos = new Index(x, y);
        Index size = new Index(2, 2);
        PrimingRoadCrossObject primingRoadCrossObject = new PrimingRoadCrossObject(parent, pos, size);
        parent.getFieldObjects().addTransportNetElement(primingRoadCrossObject);
        parent.getFieldObjects().prolongTransportSystem();
        parent.getFieldObjects().prolongTransportSystem();
    }

    public void fillField(){
        addFirstRoadPiece();
        Random random = parent.getRandom();
        int cellAmount = parent.getCellAmount();

        int x = random.nextInt(cellAmount);
        int y = random.nextInt(cellAmount);
        Index stPos = parent.getFieldObjects().getPosForBuilding(new Index(3,3));
        if (stPos != null) {
            StewardBuildingObject stewardBuildingObject = new StewardBuildingObject(parent, stPos);
            parent.getFieldObjects().addBuilding(stewardBuildingObject);
        }
        People people = society.getPeople();
        ArrayList<Person> personArray = people.getPersonArray();
        int populace = people.getAmount()-1;
        int k = 0;
        while(populace >= 0){
            int sizeX = random.nextInt(3)+3;
            int sizeY = random.nextInt(3)+3;
            Index size = new Index(sizeX, sizeY);
            Index pos = parent.getFieldObjects().getPosForBuilding(size);
            if (pos != null){
                PeasantHouseObject peasantHouseObject = new PeasantHouseObject(parent, pos, size);
                int peasantNum = random.nextInt(4) + 3;
                for(int i = 0; i < peasantNum; i++){
                    peasantHouseObject.addPerson(personArray.get(populace));
                    populace--;
                    if (populace < 0) break;
                }
                peasantHouseObject.distributeWork();
                parent.getFieldObjects().addBuilding(peasantHouseObject);
            }
            k++;
            if (k > 500) break;
            //if (populace < 0) break;
        }
        System.out.println("populace " + populace);
    }

    public void setSteward(Person steward){
        this.steward = steward;
        society.addPerson(steward);
        actor = new VillageActor(steward, this, time);
        AI villageAI = new StupidVillageAI(actor, time);
        actor.setAi(villageAI);
        parent.getMap().getGameEngine().addRunEntity(villageAI);
    }

    public void createFarm(Index point){
        if (!getManor().getTerritory().contains(point)) return;
        Field field = map.getFieldByIndex(point);
        if (field.getFarm() != null) return; // TODO: check if there are other construction like fishing village, sawmill or mine
        Farm farm = new Farm(this, new People(), field);
        field.setFarm(farm);
        workElements.add(farm);
        workContent.changed();
        map.getGameEngine().getGameWindowElement().setShapes();
    }

    public void createSawmill(Index point){
        if (!getManor().getTerritory().contains(point)) return;
        Field field = map.getFieldByIndex(point);
        if (field.getSawmill() != null) return; // TODO: check if there are other construction like fishing village, sawmill or mine
        Sawmill sawmill = new Sawmill(this, new People(), field);
        field.setSawmill(sawmill);
        workElements.add(sawmill);
        workContent.changed();
        map.getGameEngine().getGameWindowElement().setShapes();
    }

    public void createTrawler(Index point){
        if (!getManor().getTerritory().contains(point)) return;
        Field field = map.getFieldByIndex(point);
        if (field.getTrawler() != null) return; // TODO: check if there are other construction like fishing village, sawmill or mine
        Trawler trawler = new Trawler(this, new People(), field);
        field.setTrawler(trawler);
        workElements.add(trawler);
        workContent.changed();
        map.getGameEngine().getGameWindowElement().setShapes();
    }

    public void createMine(Index point){
        if (!getManor().getTerritory().contains(point)) return;
        Field field = map.getFieldByIndex(point);
        if (field.getMine() != null) return; // TODO: check if there are other construction like fishing village, sawmill or mine
        Mine mine = new Mine(this, new People(), field);
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
                //if (!manor.getIndexArray().contains(newPos)) continue;
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
        //for(WorkFieldElement workElement: workElements) workElement.getWork().doJob();
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
