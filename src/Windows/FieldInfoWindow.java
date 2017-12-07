package Windows;

import Foundation.*;
import Images.CityImage;
import Images.GroundImage;
import WindowElements.CloseButton;
import WindowElements.MonitoredBroadcastLabel;
import WindowElements.StaticBroadcastLabel;

public class FieldInfoWindow extends ClosableWindow{

    private Field field;

    public FieldInfoWindow(Windows parent, Field field) {
        super(new Coord(0,0), new Coord(300, 200), parent);
        setField(field);
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
        removeWindowElements();
        CloseButton closeButton = new CloseButton(new Coord(getPos().x + getSize().x - 15, getPos().y), new Coord(15, 15), this);
        addWindowElement(closeButton);

        if (field == null) return;
        Element additionalElement = field.getAdditionalElement();
        Ground groundElement = field.getGround();
        Image groundImage = new GroundImage(getPos(), groundElement.getGroundType(), this);
        Image finalImage = groundImage;
        if (additionalElement != null) {
            switch (additionalElement.getType()) {
                case City:
                    City city = (City) additionalElement;
                    Image cityImage = new CityImage(getPos(), city.getSizeType(), this);
                    finalImage = new Image(groundImage, cityImage, getPos(), this);
                    break;
                case Ground:
                    break;
            }
        }
        addWindowElement(finalImage);

        // ground type for all field
        //String groundTypeString = groundElement.getValue("groundType");
        //Label groundTypeLabel = new Label(new Coord(10, 55).add(getPos()), new Coord(150, 10), "TYPE: " + groundTypeString, this);
        StaticBroadcastLabel groundLabel = new StaticBroadcastLabel(new Coord(new Coord(10, 55).add(getPos())), new Coord(150, 10),
                "TYPE:", groundElement, "groundType", this);
        addWindowElement(groundLabel);

        // ground type resource cause amount of fertility

        //Label groundFertilutyLabel = new Label(new Coord(10, 75).add(getPos()),new Coord(150, 10), "Ground fertility:", this);
        //addWindowElement(groundFertilutyLabel);
        //String groundResourceCauseAmountString = groundElement.getValue("resourceCause.capacity");
        //ChangableLabel groundResourceCauseAmountLabel = new ChangableLabel(new Coord(200, 75).add(getPos()), new Coord(50, 10),
         //       groundResourceCauseAmountString, groundElement, "resourceCause.capacity", this);

        MonitoredBroadcastLabel groundResourceCauseLabel = new MonitoredBroadcastLabel(new Coord(10, 75).add(getPos()),new Coord(200, 10),
                "Ground fertility:", groundElement, "resourceCause.capacity", this);
        addWindowElement(groundResourceCauseLabel);


        Button ecoButton = new Button(new Coord(10, 115).add(getPos()), new Coord(30, 20),this, "eco") {
            @Override
            public void click(Coord point) {
                FieldInfoWindow fieldInfoWindow = (FieldInfoWindow) getParent();
                Ecosystem ecosystem = fieldInfoWindow.getField().getEcosystem();
                fieldInfoWindow.addEcosystemInfoWindow(ecosystem);
            }
        };
        addWindowElement(ecoButton);


        // additional element place
        if (additionalElement == null) return;

        if (additionalElement.getType() == Element.Type.City){
            City city = (City)additionalElement;
            Windows windows = getParent();
            //String cityTypeString = city.getValue("sizeType");
            //Label cityTypeLabel = new Label(new Coord(10, 115).add(getPos()), new Coord(300, 10), "City size: " + cityTypeString, this);
            StaticBroadcastLabel cityLabel = new StaticBroadcastLabel(new Coord(10, 140).add(getPos()), new Coord(300, 10),
                    "City size:", city, "sizeType", this){

                @Override
                public void click(Coord points){
                    FieldInfoWindow fieldInfoWindow = (FieldInfoWindow) getParent();
                    City city = (City)fieldInfoWindow.getField().getAdditionalElement();
                    fieldInfoWindow.addCityInfoWindow(city);
                }
            };
            addWindowElement(cityLabel);

            /*Label cityPopulationLabel = new Label(new Coord(10, 135).add(getPos()),new Coord(150, 10), "City population:", this);
            addWindowElement(cityPopulationLabel);
            String cityPopulationString = city.getValue("population");
            ChangableLabel cityPopulationChangableLabel = new ChangableLabel(new Coord(200, 135).add(getPos()), new Coord(50, 10),
                    cityPopulationString, city, "population", this);
            addWindowElement(cityPopulationChangableLabel);

            Label cityFoodLabel = new Label(new Coord(10, 155).add(getPos()), new Coord(150, 10), "City food:", this);
            addWindowElement(cityFoodLabel);
            String cityFoodString = city.getValue("food");
            ChangableLabel cityFoodChangableLabel = new ChangableLabel(new Coord(200, 155).add(getPos()), new Coord(50, 10),
                    cityFoodString, city, "food", this);
            addWindowElement(cityFoodChangableLabel);*/

        }
    }

    public void addCityInfoWindow(City city){
        for (Window window: getParent().getWindows()){
            if (window.getClass() == CityInfoWindow.class){
                CityInfoWindow cityInfoWindow = (CityInfoWindow)window;
                cityInfoWindow.setCity(city);
                return;
            }
        }
        addWindow(new CityInfoWindow(city, getParent()));
    }

    public void addEcosystemInfoWindow(Ecosystem ecosystem){
        for (Window window: getParent().getWindows()){
            if (window.getClass() == EcosystemInfoWindow.class){
                EcosystemInfoWindow ecosystemInfoWindow = (EcosystemInfoWindow)window;
               ecosystemInfoWindow.setEcosystem(ecosystem);
                return;
            }
        }
        addWindow(new EcosystemInfoWindow(ecosystem, getParent()));
    }

}
