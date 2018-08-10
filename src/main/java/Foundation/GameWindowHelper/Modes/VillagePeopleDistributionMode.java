package Foundation.GameWindowHelper.Modes;

import Foundation.Color;
import Foundation.Elements.Village;
import Foundation.Elements.WorkFieldElement;
import Foundation.GameWindowHelper.HelperElements.HistogramHelper;
import Foundation.GameWindowHelper.HelperField;
import WindowElements.GameWindowElements.GameWindowHelperElement;
import Foundation.Person.Person;
import Utils.Geometry.Index;
import Utils.Subscription;

import java.util.ArrayList;

public class VillagePeopleDistributionMode extends Mode {

    private ArrayList<HistogramHelper> histogramHelpers;
    private Village village;

    private Subscription villageWorkSubscription;


    public VillagePeopleDistributionMode(Village village, GameWindowHelperElement gameWindowHelperElement) {
        super(gameWindowHelperElement);
        this.village = village;

    }

    public void init(){
        ArrayList<WorkFieldElement> workElements = village.getWorkElements();
        int total = village.getSociety().getAmount();
        for(WorkFieldElement workElement: workElements){
            int number = workElement.getPeople().getAmount();
            Index index = workElement.getParent().getFieldMapPos();
            HelperField helperField = gameWindowHelperElement.getMap().getFieldByIndex(index);
            if (helperField == null){
                helperField = new HelperField(workElement.getParent(), gameWindowHelperElement.getMap());
                gameWindowHelperElement.getMap().addByIndex(index, helperField);
            }
            HistogramHelper histogramHelper = new HistogramHelper(helperField, total, number);
            helperField.addHelperElement(histogramHelper);
            histogramHelpers.add(histogramHelper);
        }


        int noJob = 0;
        for (Person person: village.getSociety().getPeople().getPersonArray()){
            if (person.getWork() == null){
                noJob++;
            }
        }
        Index index = village.getParent().getFieldMapPos();
        HelperField helperField = gameWindowHelperElement.getMap().getFieldByIndex(index);
        if (helperField == null){
            helperField = new HelperField(village.getParent(), gameWindowHelperElement.getMap());
            gameWindowHelperElement.getMap().addByIndex(index, helperField);
        }
        HistogramHelper histogramHelper = new HistogramHelper(helperField, total, noJob, new Color(Color.Type.Brown, 0.9f));
        helperField.addHelperElement(histogramHelper);
        histogramHelpers.add(histogramHelper);
    }

    public boolean drag(double delta, HelperField helperField){
        if (helperField.getPos().equals(village.getParent().getFieldMapPos())) return false;
        for(HistogramHelper histogramHelper: histogramHelpers){
            if (histogramHelper.getParent().equals(helperField)){
                if (delta > 0) {
                    int number = (int) (((delta/helperField.getSize().y)*village.getSociety().getAmount()));
                    if (number == 0) return false;
                    village.removeWorkers(number, helperField.getPos());
                }
                if (delta < 0){
                    int number = -(int) ((delta/helperField.getSize().y)*village.getSociety().getAmount());
                    if (number == 0) return false;
                    village.addWorkers(number, helperField.getPos());
                }
                renewHelpers();
                return true;
            }
        }
        return false;
    }

    @Override
    public void putHelpers() {
        histogramHelpers = new ArrayList<>();
        villageWorkSubscription = new Subscription() {
            @Override
            public void changed() {
                renewHelpers();
            }
        };
        village.subscribe("work", villageWorkSubscription);
        init();
    }

    public void renewHelpers(){
        for(HistogramHelper histogramHelper: histogramHelpers){
            HelperField helperField = histogramHelper.getParent();
            helperField.removeHelperElement(histogramHelper);
            if (helperField.isEmpty()) helperField.delete();
        }
        histogramHelpers.clear();
        init();
        gameWindowHelperElement.setShapes();
    }

    @Override
    public void removeHelpers() {
        for(HistogramHelper histogramHelper: histogramHelpers){
            HelperField helperField = histogramHelper.getParent();
            helperField.removeHelperElement(histogramHelper);
            if (helperField.isEmpty()) helperField.delete();
        }
        histogramHelpers.clear();
        village.unsubscribe("work", villageWorkSubscription);
    }
}
