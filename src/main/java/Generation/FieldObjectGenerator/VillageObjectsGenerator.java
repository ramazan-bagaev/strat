package Generation.FieldObjectGenerator;

import Foundation.Field;
import Foundation.FieldObjects.BuildingObject.*;
import Foundation.FieldObjects.FieldObjects;
import Foundation.FieldObjects.OccupationPiece;
import Foundation.FieldObjects.TransportObjects.PrimingRoadCrossObject;
import Foundation.Person.Person;
import Foundation.Runnable.AI.PeasantHouseHoldAI;
import Utils.Geometry.Index;

import java.util.ArrayList;
import java.util.Random;

public class VillageObjectsGenerator {

    private Field field;
    private FieldObjects fieldObjects;

    private Random random;


    public void generate(Field field){
        this.field = field;
        this.fieldObjects = field.getFieldObjects();
        this.random = field.getRandom();
        generateFirstRoads();
        generateStewardObject();
        generateBuildings();
        generateMarket();
    }

    private void generateFirstRoads(){
        Index size = new Index(2, 2);
        OccupationPiece piece = fieldObjects.getSpace(size);
        if (piece == null) return;
        Index pos = piece.getCellPos().add(new Index(random.nextInt(piece.getSize().x - 2 + 1), random.nextInt(piece.getSize().y - 2 + 1)));
        PrimingRoadCrossObject primingRoadCrossObject = new PrimingRoadCrossObject(field, pos, size);
        fieldObjects.addTransportNetElement(primingRoadCrossObject);
        fieldObjects.prolongTransportSystem();
        fieldObjects.prolongTransportSystem();
        field.addEntryPoints(pos);
    }

    private void generateStewardObject(){
        Index size = new Index(3, 3);
        Index pos = fieldObjects.getPosForBuilding(size);
        if (pos == null) return;
        StewardBuildingObject stewardBuildingObject = new StewardBuildingObject(field, pos);
        field.addFieldObject(stewardBuildingObject);
    }

    private void generateBuildings(){
        int populace = field.getVillage().getSociety().getAmount();
        ArrayList<Person> people = field.getVillage().getSociety().getPeople().getPersonArray();
        int k = 0;
        while(true){
            k++;
            //x = random.nextInt(cellAmount);
            //y = random.nextInt(cellAmount);
            int sizeX = random.nextInt(3) + 2;
            int sizeY = random.nextInt(3) + 2;
            //Index pos = new Index(x, y);
            Index size = new Index(sizeX, sizeY);
            //OccupationPiece piece = field.getFieldObjects().getMinSpace(size);
            Index pos = field.getFieldObjects().getPosForBuilding(size);
            if (pos != null){
                LivingBuildingObject buildingObject = new PeasantHouseObject(field, pos, size);
                for(int i = 0; i < sizeX * sizeY; i++){
                    populace--;
                    if (populace < 0) break;
                    buildingObject.addPerson(people.get(populace));
                }
                PeasantHouseHoldAI ai = new PeasantHouseHoldAI();
                ai.setHouseHold(buildingObject.getHouseHold());
                fieldObjects.addBuilding(buildingObject);
            }
            if (populace <= 0) break;
            if (k > 100) break;
        }
    }

    private void generateMarket(){
        Index size = new Index(4, 4);
        Index pos = fieldObjects.getPosForBuilding(size);
        if (pos == null) return;
        MarketObject marketObject = new MarketObject(field, pos);
        fieldObjects.addBuilding(marketObject);
        field.getInfo().setMarketObject(marketObject);
    }
}
