package Foundation.Runnable.Actors;

import Foundation.Elements.Ground;
import Foundation.Elements.Village;
import Foundation.Elements.WorkElement;
import Foundation.Field;
import Foundation.FieldMap;
import Foundation.Person.People;
import Foundation.Person.Person;
import Foundation.Time;
import Utils.Index;
import Utils.TimeMeasurer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class VillageActor extends Actor {

    private Village village;


    public VillageActor(Person actorPerson, Village village, Time time) {
        super(actorPerson);
        this.village = village;
    }

    public Village getVillage() {
        return village;
    }

    public void addFarm(Index pos){
        village.createFarm(pos);
    }

    public void addSawmill(Index pos){
        village.createSawmill(pos);
    }

    public void addMine(Index pos){
        village.createMine(pos);
    }

    public void addTrawler(Index pos){
        village.createTrawler(pos);
    }

    public void addWorkPeople(WorkElement workElement, People people){
        workElement.addPeople(people);
    }
}
