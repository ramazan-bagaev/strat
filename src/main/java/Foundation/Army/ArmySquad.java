package Foundation.Army;

import Foundation.Person.People;
import Foundation.Person.Person;

import java.util.Random;

public class ArmySquad {

    private Army army;

    private Person mainOfficer;

    private People people;

    public ArmySquad(Person mainOfficer, People people){
        this.mainOfficer = mainOfficer;
        this.people = people;
    }

    public static ArmySquad RandomArmySquad(People people){
        if (people.getAmount() == 0) return null;
        Person officer = people.getPersonArray().get(new Random().nextInt(people.getAmount()));
        ArmySquad armySquad = new ArmySquad(officer, people);
        return armySquad;
    }


    public People getPeople() {
        return people;
    }

    public Person getMainOfficer() {
        return mainOfficer;
    }

    public Army getArmy(){
        return army;
    }

    public void setArmy(Army army){
        this.army = army;
    }
}
