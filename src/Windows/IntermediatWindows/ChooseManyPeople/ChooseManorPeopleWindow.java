package Windows.IntermediatWindows.ChooseManyPeople;


import Foundation.Elements.City;
import Foundation.Elements.Manor;
import Foundation.Frame;
import Foundation.Person.Person;

public class ChooseManorPeopleWindow extends ChooseManyPeopleWindow {

    private City city;
    private Manor manor;

    public ChooseManorPeopleWindow(Manor manor, Frame parent) {
        super(manor.getCity().getPeople(), parent);

        this.city = manor.getCity();
        this.manor = manor;

    }


    @Override
    public boolean rightPerson(Person person) {
        return (person.getKasta() != Person.Kasta.High);
    }

    @Override
    public void choosePeople() {
        for(Person person: submittedPeople){
            city.removePerson(person);
        }
        manor.addPeople(submittedPeople);
        close();
    }
}
