package Windows.IntermediatWindows.ChooseOnePerson;

import Foundation.Elements.Manor;
import Foundation.Frame;
import Foundation.GameWindowHelper.States.AddVillageState;
import WindowElements.GameWindowElements.GameWindowHelperElement;
import Foundation.Person.Person;

public class ChooseVillageStewardWindow extends ChooseOnePersonWindow {

    private Manor manor;

    public ChooseVillageStewardWindow(Manor manor, Frame parent) {
        super(manor.getSociety().getPeople(), parent);
        this.manor = manor;
    }

    @Override
    public boolean rightPerson(Person person) {
        return (person.getKasta() == Person.Kasta.Middle);
    }

    @Override
    public void choosePerson(Person person) {
        GameWindowHelperElement gameWindowHelperElement = getParent().getMainWindow().getGameWindowHelperElement();
        gameWindowHelperElement.clearHelperElements();
        AddVillageState addVillageState = new AddVillageState(manor, person, gameWindowHelperElement);
        gameWindowHelperElement.setState(addVillageState);
        close();
    }
}
