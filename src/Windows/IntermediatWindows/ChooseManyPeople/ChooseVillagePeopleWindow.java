package Windows.IntermediatWindows.ChooseManyPeople;

import Foundation.Elements.Manor;
import Foundation.Elements.Village;
import Foundation.Frame;
import Foundation.Person.People;
import Foundation.Person.Person;

public class ChooseVillagePeopleWindow extends ChooseManyPeopleWindow {

    private Manor manor;
    private Village village;

    public ChooseVillagePeopleWindow(Village village, Frame parent) {
        super(village.getManor().getPeople(), parent);

        this.village = village;
        this.manor = village.getManor();
    }

    @Override
    public boolean rightPerson(Person person) {
        return (person.getKasta() == Person.Kasta.Low);
    }

    @Override
    public void choosePeople() {
        for(Person person: submittedPeople){
            manor.removePerson(person);
        }
        village.addPeople(submittedPeople);
        close();
    }
}
