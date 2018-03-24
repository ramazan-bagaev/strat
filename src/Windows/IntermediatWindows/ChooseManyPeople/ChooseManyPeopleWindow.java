package Windows.IntermediatWindows.ChooseManyPeople;

import Foundation.Button;
import Foundation.Color;
import Foundation.Elements.City;
import Foundation.Elements.Manor;
import Foundation.Frame;
import Foundation.Label;
import Foundation.Person.People;
import Foundation.Person.Person;
import Utils.Coord;
import WindowElementGroups.PeopleElementGroup;
import WindowElements.ScrollElements.PersonScrolledRow;
import WindowElements.ScrollElements.ScrollableRow;
import Windows.ClosableWindow;

import java.util.ArrayList;

public abstract class ChooseManyPeopleWindow extends ClosableWindow{

    protected ArrayList<Person> submittedPeople;

    protected People people;

    public ChooseManyPeopleWindow(People people, Frame parent) {
        super(new Coord(300, 400), new Coord(400, 400), parent);

        submittedPeople = new ArrayList<>();
        this.people = people;

        setElements();
    }

    public void setElements(){
        removeWindowElements();


        PeopleElementGroup peopleElementGroup = new PeopleElementGroup(new Coord(0, 40),
                new Coord(390, 300), people,this ){

            @Override
            public void initRowElements() {
                initCameraConfiguration();
                ArrayList<Person> peopleArray = people.getPersonArray();
                scrollableRows.clear();
                for(Person person: peopleArray) {
                    if (!rightPerson(person)) continue;

                    PersonScrolledRow personScrolledRow = new PersonScrolledRow(person, new Coord(getSize().x, 20), this);
                    //if (submittedPeople.contains(person)) personScrolledRow.setColor(new Color(Color.Type.LightGray));
                    addScrollableRow(personScrolledRow);
                }
            }

            @Override
            public void click(Coord point){
                point = cameraConfiguration.transform(point);
                for(ScrollableRow scrollableRow: scrollableRows){
                    if (!scrollableRow.contain(point)) continue;
                    PersonScrolledRow personScrolledRow = (PersonScrolledRow)scrollableRow;
                    Person person = personScrolledRow.getPerson();
                    if (!submittedPeople.contains(person)){
                        submittedPeople.add(person);
                        personScrolledRow.setColor(new Color(Color.Type.LightGray));
                    }
                    else{
                        submittedPeople.remove(person);
                        personScrolledRow.setColor(new Color(Color.Type.White));
                    }
                }
            }

            @Override
            public void hoover(Coord point){
                point = cameraConfiguration.transform(point);
                for(ScrollableRow scrollableRow: scrollableRows){
                    PersonScrolledRow personScrolledRow = (PersonScrolledRow)scrollableRow;
                    Person person = personScrolledRow.getPerson();
                    if (submittedPeople.contains(person)) continue;
                    personScrolledRow.deactivate();
                    if (scrollableRow.contain(point)){
                        personScrolledRow.activate();
                    }
                }
            }

            @Override
            public void deactivate(){
                for(ScrollableRow scrollableRow: scrollableRows){
                    PersonScrolledRow personScrolledRow = (PersonScrolledRow)scrollableRow;
                    Person person = personScrolledRow.getPerson();
                    if (submittedPeople.contains(person)) continue;
                    personScrolledRow.deactivate();
                }
            }
        };

        addWindowElementGroup(peopleElementGroup);

        double x = peopleElementGroup.getScrollWindowSize().x;
        Label label = new Label(new Coord(15, 10), new Coord(x/2 - 5, 20), "name", this);
        addWindowElement(label);
        label = new Label(new Coord(x/2+20, 10), new Coord(x/2 -5, 20), "kasta", this);
        addWindowElement(label);

        Button submitButton = new Button(new Coord(size.x/2-60, size.y- 30), new Coord(40, 20), "ok", this){

            @Override
            public void click(Coord point){
                choosePeople();
            }
        };

        addWindowElement(submitButton);

        Button closeButton = new Button(new Coord(size.x/2, size.y - 30), new Coord(80, 20),"cancel", this){

            @Override
            public void click(Coord point){
                close();
            }
        };
        addWindowElement(closeButton);


    }

    public abstract boolean rightPerson(Person person);

    public abstract void choosePeople();


}
