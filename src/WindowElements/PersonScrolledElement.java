package WindowElements;

import Foundation.*;
import Foundation.BasicShapes.StringShape;
import Foundation.Person.Person;
import Utils.Coord;

import java.util.ArrayList;

public class PersonScrolledElement extends ScrollableElement{

    //private ArrayList<String> oldTexts;
    private Broadcaster broadcaster;

    public PersonScrolledElement(Coord pos, Coord size, Person person, Window parent) {
        super(pos, size, parent);
        broadcaster = person;
        String text = getNewText();
        StringShape stringShape = getStringShape();
        stringShape.setText(text);
        setStringShape(stringShape);

    }


    /*public void renew(){
        boolean changed = false;
        ArrayList<String> newTexts = getNewTexts();
        for (int i = 0; i < newTexts.size(); i++){
            if (newTexts.get(i).equals(oldTexts.get(i))){
                oldTexts.set(i, newTexts.get(i));
                changed = true;
            }
        }
        if (!changed) return;
        String text = "";
        for (String newText: newTexts){
            text += newText + " ";
        }

        StringShape stringShape = getStringShape();
        stringShape.setText(text);
        setStringShape(stringShape);
    }*/


    @Override
    public String getNewText() {
        String text = "";
        text += broadcaster.getValue("name");
        return text;
    }
}