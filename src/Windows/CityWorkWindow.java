package Windows;

import Foundation.*;
import WindowElementGroups.ScrollableGroup;
import WindowElements.RadioButton;
import WindowElements.ResourceWorkTypeLabel;
import WindowElements.SliderElement;

import java.util.ArrayList;

public class CityWorkWindow extends ClosableWindow {

    private City city;
    private Field field;

    public CityWorkWindow(City city, Field field, Windows parent) {
        super(new Coord(400, 400), new Coord(200, 200), parent);
        this.city = city;
        this.field = field;

        ArrayList<WindowElement> scrollableElements = new ArrayList<>();
        scrollableElements.add(new ResourceWorkTypeLabel(new Coord(getPos().add(new Coord(0, 30))), new Coord(getSize().x, 20), field.getGround().getResourceCause(), this));
        Element additionalElement = field.getAdditionalElement();
        if (additionalElement != null) {
            scrollableElements.add(new ResourceWorkTypeLabel(new Coord(getPos().add(new Coord(0, 60))), new Coord(getSize().x, 20),
                    additionalElement.getResourceCause(), this));
        }
        ScrollableGroup scrollableGroup = new ScrollableGroup(getPos().add(new Coord(0, 30)), getSize().x, 2, 30, scrollableElements, this){

            @Override
            public void click(Coord point){
                for (WindowElement windowElement: getScrollableElements()) {
                    if (windowElement.contain(point)) windowElement.click(point);
                }
            }
        };
        addWindowElementGroup(scrollableGroup);
    }
}
