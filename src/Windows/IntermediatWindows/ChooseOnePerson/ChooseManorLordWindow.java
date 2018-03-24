package Windows.IntermediatWindows.ChooseOnePerson;

import Foundation.*;
import Foundation.Elements.City;
import Foundation.GameWindowHelper.States.AddManorState;
import Foundation.Person.Person;

public class ChooseManorLordWindow extends ChooseOnePersonWindow {

    private City city;

    public ChooseManorLordWindow(City city, Frame parent) {
        super(city.getPeople(), parent);
        this.city = city;
    }


    @Override
    public boolean rightPerson(Person person) {
        return (person.getKasta() == Person.Kasta.High);
    }

    @Override
    public void choosePerson(Person person) {
        GameWindowHelperElement gameWindowHelperElement = getParent().getMainWindow().getGameWindowHelperElement();
        gameWindowHelperElement.clearHelperElements();
        AddManorState addManorState = new AddManorState(gameWindowHelperElement, city, person);
        gameWindowHelperElement.setState(addManorState);
        close();
    }
}
