package Foundation.Works.Occupation;

import Foundation.Elements.Village;
import Foundation.FieldMap;
import Foundation.Field;
import Foundation.FieldObjects.BuildingObject.AmbarObject;
import Foundation.FieldObjects.BuildingObject.BuildingObject;
import Foundation.FieldObjects.BuildingObject.LivingBuildingObject;
import Foundation.FieldObjects.BuildingObject.ManorObject;
import Foundation.FieldObjects.FieldObject;
import Foundation.FieldObjects.FieldObjects;
import Foundation.FieldObjects.NaturalObjects.CropFieldObject;
import Foundation.FieldObjects.NaturalObjects.ForestObject;
import Foundation.FieldObjects.OccupationPiece;
import Foundation.Person.People;
import Foundation.Person.Person;
import Foundation.Territory;
import Foundation.Time.TimeDuration;
import Foundation.Works.WheatMakingWork;
import Foundation.Works.Work;
import Generation.CivilizationGenerator.RoadGenerator;
import Utils.Geometry.Index;
import Utils.PathFinder;

import java.util.ArrayList;

public class LordHouseOccupation extends Occupation{

    private ManorObject manorObject;
    private ArrayList<CropFieldObject> cropFields;
    private People workingPeasants;
    private ArrayList<WheatMakingWork> wheatMakingWorks;

    public LordHouseOccupation(ManorObject manorObject) {
        super(manorObject.getParent().getMap().getGameEngine());
        this.manorObject = manorObject;
        this.cropFields = new ArrayList<>();
        this.wheatMakingWorks = new ArrayList<>();
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
        ArrayList<Person> personArrayList = newPeople.getPersonArray();
        for(WheatMakingWork work: wheatMakingWorks){
            People cropWorkers = work.getPeople();
            int delta = cropWorkers.getAmount() - work.getSize();
            while(delta > 0){
                Person person = personArrayList.get(personArrayList.size() - 1);
                personArrayList.remove(person);
                TimeDuration timeDuration = person.getSchedule().getFreeWeekTimeDuration(35);
                work.addPerson(person, timeDuration);
                workingPeasants.addPerson(person);
                delta--;
                if (personArrayList.size() == 0) return;
            }
        }
        if (personArrayList.size() > 0) makeNewCrops(newPeople);
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
            ambarObject = new AmbarObject(field, piece.pos.add(new Index(0, piece.pieceSize.y)), new Index(1, 1));
            CropFieldObject cropField = new CropFieldObject(field, piece.pos, piece.pieceSize.minus(new Index(0, 1)));
            WheatMakingWork wheatMakingWork = new WheatMakingWork(ambarObject.getStore(), cropField, this);
            for(Person person: newPeople.getPersonArray()){
                TimeDuration timeDuration = person.getSchedule().getFreeWeekTimeDuration(35); // TODO: get rid of constants
                if (timeDuration == null) continue;
                wheatMakingWork.addPerson(person, timeDuration);
            }
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
