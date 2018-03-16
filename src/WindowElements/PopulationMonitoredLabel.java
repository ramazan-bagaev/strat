package WindowElements;

import Foundation.*;
import Foundation.Person.PopulationGroup;

import java.util.ArrayList;

public class PopulationMonitoredLabel extends Label{

    private ArrayList<String> oldTexts;
    private Broadcaster broadcaster;

    public PopulationMonitoredLabel(Coord pos, Coord size, PopulationGroup populationGroup, Window parent) {
        super(pos, size, "", parent);
        setBroadcaster(populationGroup);

        ArrayList<String> newTexts = getNewTexts();
        oldTexts = newTexts;
        String text = "";
        for (String newText: newTexts){
            text += newText + " ";
        }
        StringShape stringShape = getStringShape();
        stringShape.setText(text);
        setStringShape(stringShape);

    }


    public void renew(){
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
    }

    public ArrayList<String> getNewTexts(){
        ArrayList<String> newTexts = new ArrayList<>();
        newTexts.add(broadcaster.getValue("wealth"));
        newTexts.add(broadcaster.getValue("work"));
        newTexts.add(broadcaster.getValue("amount"));
        return newTexts;
    }

    public Broadcaster getBroadcaster() {
        return broadcaster;
    }

    public void setBroadcaster(Broadcaster broadcaster) {
        this.broadcaster = broadcaster;
    }

    @Override
    public void run(){
        renew();
    }
}