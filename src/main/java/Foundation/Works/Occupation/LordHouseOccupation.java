package Foundation.Works.Occupation;

import Foundation.Elements.Village;
import Foundation.FieldMap;
import Foundation.Field;
import Foundation.FieldObjects.BuildingObject.*;
import Foundation.FieldObjects.FieldObject;
import Foundation.FieldObjects.FieldObjects;
import Foundation.FieldObjects.NaturalObjects.CropFieldObject;
import Foundation.FieldObjects.NaturalObjects.ForestObject;
import Foundation.FieldObjects.NaturalObjects.NaturalObject;
import Foundation.FieldObjects.OccupationPiece;
import Foundation.Person.People;
import Foundation.Person.Person;
import Foundation.Territory;
import Foundation.Time.TimeDuration;
import Foundation.Works.WheatMakingWork;
import Foundation.Works.WoodMakingWork;
import Foundation.Works.Work;
import Generation.CivilizationGenerator.RoadGenerator;
import Utils.Geometry.Index;
import Utils.PathFinder;

import java.util.ArrayList;

public class LordHouseOccupation extends Occupation{

    private ManorObject manorObject;

    private ArrayList<CropFieldObject> cropFields;
    private ArrayList<WheatMakingWork> wheatMakingWorks;

    private ArrayList<MillObject> mills;
    private ArrayList<WoodMakingWork> woodMakingWorks;

    private People workingPeasants;

    public LordHouseOccupation(ManorObject manorObject) {
        super(manorObject.getParent().getMap().getGameEngine());
        this.manorObject = manorObject;
        this.cropFields = new ArrayList<>();
        this.wheatMakingWorks = new ArrayList<>();
        this.mills = new ArrayList<>();
        this.woodMakingWorks = new ArrayList<>();
        this.workingPeasants = new People();
        initWorks();
        gameEngine.addRunEntity(this);
    }

    public void initWorks(){
        People newPeasants = new People();
        People notWorking = new People(workingPeasants);
        for(Village village: manorObject.getParent().getManor().getVillages()) {
            FieldObjects fieldObjects = village.getParent().getFieldObjects();
            for (FieldObject fieldObject : fieldObjects.getArray()) {
                if (fieldObject.isBuilding()) {
                    BuildingObject buildingObject = (BuildingObject) fieldObject;
                    if (buildingObject.isLivingBuilding()) {
                        LivingBuildingObject livingBuildingObject = (LivingBuildingObject) buildingObject;
                        People buildingHabitats = livingBuildingObject.getPeople();
                        People newWorkers = workingPeasants.getPeopleNotRepresented(buildingHabitats);
                        notWorking.removePeople(buildingHabitats);
                        newPeasants.addPeople(newWorkers);
                    }
                }
            }
        }
        if (notWorking.getAmount() > 0){
            workingPeasants.removePeople(notWorking);
        }
        if (getCropAmount() < newPeasants.getAmount() + workingPeasants.getAmount()){
            distributeNewWorkers(newPeasants);
        }
    }

    private void distributeNewWorkers(People newPeople){
        if (manorObject.getParent().getRandom().nextBoolean()){
            distributeNewWheatWorkers(newPeople);
            if (newPeople.getAmount() == 0) return;
            makeNewCrops(newPeople);
        }
        else {
            distributeNewMillWorkers(newPeople);
            if (newPeople.getAmount() == 0) return;
            makeNewMills(newPeople);
        }
    }

    private void distributeNewWheatWorkers(People newPeople){
        ArrayList<Person> personArrayList = newPeople.getPersonArray();
        for(WheatMakingWork work: wheatMakingWorks){
            int delta = work.getFreePosition();
            while(delta > 0){
                if (personArrayList.size() == 0) return;
                Person person = personArrayList.get(personArrayList.size() - 1);
                personArrayList.remove(person);
                TimeDuration timeDuration = person.getSchedule().getFreeWeekTimeDuration(35);
                if (timeDuration == null) continue;
                work.addPerson(person, timeDuration);
                workingPeasants.addPerson(person);
                delta--;
            }
        }
    }

    private void distributeNewMillWorkers(People newPeople){
        ArrayList<Person> personArrayList = newPeople.getPersonArray();
        for(WoodMakingWork work: woodMakingWorks){
            int delta = work.getFreePositions();
            while(delta > 0){
                if (personArrayList.size() == 0) return;
                Person person = personArrayList.get(personArrayList.size() - 1);
                personArrayList.remove(person);
                TimeDuration timeDuration = person.getSchedule().getFreeWeekTimeDuration(35);
                if (timeDuration == null) continue;
                work.addPerson(person, timeDuration);
                workingPeasants.addPerson(person);
                delta--;
            }
        }
    }

    private void makeNewCrops(People newPeople){
        // TODO: this method won't work if there are many new people, rewrite correctly
        Territory territory = manorObject.getParent().getManor().getTerritory();
        FieldMap map = gameEngine.getMap();
        AmbarObject ambarObject = null;
        int square = newPeople.getAmount();
        for(Index pos: territory.getIndexArray()){
            Field field = map.getFieldByIndex(pos);
            if (field.getManor() != null) continue;
            FieldObjects fieldObjects = field.getFieldObjects();
            Index size = getSizeFromSquare(square);
            size.y += 1;
            ArrayList<OccupationPiece> pieces = fieldObjects.getFreeSpace(size);
            if (pieces.size() == 0) continue;
            OccupationPiece piece = pieces.get(0);
            ambarObject = new AmbarObject(field, piece.pos.add(new Index(0, size.y)), new Index(1, 1));
            CropFieldObject cropField = new CropFieldObject(field, piece.pos, size);
            WheatMakingWork wheatMakingWork = new WheatMakingWork(ambarObject.getStore(), cropField.getCropField(),this);
            for(Person person: newPeople.getPersonArray()){
                TimeDuration timeDuration = person.getSchedule().getFreeWeekTimeDuration(35); // TODO: get rid of constants
                if (timeDuration == null) continue;
                wheatMakingWork.addPerson(person, timeDuration);
                workingPeasants.addPerson(person);
            }
            newPeople.clear();
            fieldObjects.addBuilding(ambarObject);
            fieldObjects.addFieldObject(cropField);
            addWork(wheatMakingWork);
            wheatMakingWorks.add(wheatMakingWork);
            break;
        }
        if (ambarObject == null) return;
        Index nearAmbarPos = ambarObject.getCellPos().add(new Index(1, 0));
        Field manorField = manorObject.getParent();
        RoadGenerator roadGenerator = new RoadGenerator(gameEngine.getMap());
        roadGenerator.generateRoad(manorField, manorField.getFirstEntryPoint(), ambarObject.getParent(), nearAmbarPos);
    }

    private void makeNewMills(People newPeople){
        Territory territory = manorObject.getParent().getManor().getTerritory();
        FieldMap map = gameEngine.getMap();
        MillObject millObject = null;
        for(Index pos: territory.getIndexArray()) {
            Field field = map.getFieldByIndex(pos);
            if (field.getManor() != null) continue;
            if (field.getTree() == null) continue;
            FieldObjects fieldObjects = field.getFieldObjects();
            OccupationPiece piece = fieldObjects.getMinSpace(new Index(3, 1));
            ForestObject nearest = null;
            double dist = 10000;
            for(FieldObject fieldObject: fieldObjects.getArray()){
                if (!fieldObject.isNaturalObject()) continue;
                NaturalObject naturalObject = (NaturalObject)fieldObject;
                if (!naturalObject.isForestObject()) continue;
                ForestObject forestObject = (ForestObject)naturalObject;
                double newDist = pos.distance(forestObject.getCellPos());
                if (newDist < dist){
                    nearest = forestObject;
                    dist = newDist;
                }
            }
            if (nearest == null) continue;
            millObject = new MillObject(field, piece.pos);
            fieldObjects.addFieldObject(millObject);
            WoodMakingWork woodMakingWork = new WoodMakingWork(millObject.getStore(), nearest,this);
            woodMakingWorks.add(woodMakingWork);
            addWork(woodMakingWork);
            int amount = nearest.getWoodAmount();
            ArrayList<Person> personArrayList = newPeople.getPersonArray();
            for(int i = 0; i < amount; i++){
                if (personArrayList.size() == 0) break;
                Person person = personArrayList.get(personArrayList.size() - 1);
                personArrayList.remove(person);
                TimeDuration timeDuration = person.getSchedule().getFreeWeekTimeDuration(35); // TODO: get rid of constants
                if (timeDuration == null) continue;
                woodMakingWork.addPerson(person, timeDuration);
                workingPeasants.addPerson(person);
            }
            break;
        }
        if (millObject == null) return;
        Field manorField = manorObject.getParent();
        Index near = millObject.getCellPos().add(new Index(2, 0));
        RoadGenerator roadGenerator = new RoadGenerator(gameEngine.getMap());
        roadGenerator.generateRoad(manorField, manorField.getFirstEntryPoint(), millObject.getParent(), near);
    }

    private Index getSizeFromSquare(int square){
        int a1 = (int) Math.floor(Math.sqrt(square));
        int a2 = (int) Math.ceil((double)square / (double)a1);
        return new Index(a1, a2);
    }

    private int getCropAmount(){
        int amount = 0;
        for(CropFieldObject cropField: cropFields){
            Index size = cropField.getSize();
            amount += size.x * size.y;
        }
        return amount;
    }

}
