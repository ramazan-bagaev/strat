package Foundation.Runnable.Actors;

import Foundation.Elements.Village;
import Foundation.Elements.WorkFieldElement;
import Foundation.Person.People;
import Foundation.Person.Person;
import Foundation.Time;
import Utils.Geometry.Index;

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

    public void addWorkPeople(WorkFieldElement workElement, People people){
        workElement.addPeople(people);
    }
}
