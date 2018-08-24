package Foundation.Elements;

import Foundation.*;
import Foundation.FieldObjects.BuildingObject.PeasantHouseObject;
import Foundation.FieldObjects.BuildingObject.StewardBuildingObject;
import Foundation.FieldObjects.TransportObjects.PrimingRoadCrossObject;
import Foundation.Person.People;
import Foundation.Person.Person;
import Foundation.Runnable.AI.AI;
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

    private ArrayList<WorkFieldElement> workElements;


    private Territory availableWater;

    public Village(Field parent, Manor manor) {
        super(Type.Village, parent);
        this.manor = manor;
        manor.addVillage(this);
        parent.setVillage(this);
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

    public void setSteward(Person steward){
        this.steward = steward;
        society.addPerson(steward);
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

    public ArrayList<WorkFieldElement> getWorkElements() {
        return workElements;
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
