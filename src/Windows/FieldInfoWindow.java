package Windows;

import Foundation.*;
import Foundation.Elements.*;
import Images.ArmyImage;
import Images.CityImage;
import Images.GroundImage;
import Images.Image;
import Utils.Coord;
import WindowElements.MonitoredBroadcastLabel;
import WindowElements.StaticBroadcastLabel;
import Windows.ElementInfoWindow.EcosystemInfoWindow;

public class FieldInfoWindow extends ClosableWindow{

    private Field field;

    public FieldInfoWindow(Frame parent, Field field) {
        super(new Coord(0,0), new Coord(300, 200), parent);
        setField(field);
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
        removeWindowElements();
        addCloseButton();

        if (field == null) return;
        ArmyElement army = field.getArmyElement();
        Ground groundElement = field.getGround();
        Image groundImage = new GroundImage(new Coord(0, 0), groundElement.getGroundType(), this);
        Image finalImage = groundImage;
        if (field.getCity() != null){
            Image cityImage = new CityImage(new Coord(0, 0), this);
            groundImage.add(cityImage);//new Image(groundImage, cityImage, getPos(), this);
        }
        if (army != null) {
            Image armyImage = new ArmyImage(new Coord(0, 0), this);
            groundImage.add(armyImage);//new Image(groundImage, armyImage, getPos(), this);
        }
        addWindowElement(finalImage);

        // ground type for all field
        //String groundTypeString = groundElement.getValue("groundType");
        //Label groundTypeLabel = new Label(new Index(10, 55).add(getPos()), new Index(150, 10), "TYPE: " + groundTypeString, this);
        StaticBroadcastLabel groundLabel = new StaticBroadcastLabel(new Coord(10, 55), new Coord(150, 10),
                "TYPE:", groundElement, "groundType", this);
        addWindowElement(groundLabel);

        // ground type resource cause amount of fertility

        //Label groundFertilutyLabel = new Label(new Index(10, 75).add(getPos()),new Index(150, 10), "Ground fertility:", this);
        //addWindowElement(groundFertilutyLabel);
        //String groundResourceCauseAmountString = groundElement.getValue("resourceCause.capacity");
        //ChangableLabel groundResourceCauseAmountLabel = new ChangableLabel(new Index(200, 75).add(getPos()), new Index(50, 10),
         //       groundResourceCauseAmountString, groundElement, "resourceCause.capacity", this);

        MonitoredBroadcastLabel groundResourceCauseLabel = new MonitoredBroadcastLabel(new Coord(10, 75),new Coord(200, 10),
                "Ground fertility:", groundElement, "resourceCause.capacity", this);
        addWindowElement(groundResourceCauseLabel);


        Button ecoButton = new Button(new Coord(10, 115), new Coord(30, 20),this, "eco") {
            @Override
            public void click(Coord point) {
                FieldInfoWindow fieldInfoWindow = (FieldInfoWindow) getParent();
                Ecosystem ecosystem = fieldInfoWindow.getField().getEcosystem();
                getParent().getParent().addSpecialWindow("ecosystem info window", new EcosystemInfoWindow(ecosystem, getParent().getParent()));
            }
        };
        addWindowElement(ecoButton);


        City city = field.getCity();

        // additional element place
        if (city == null) return;

            //String cityTypeString = city.getValue("sizeType");
            //Label cityTypeLabel = new Label(new Index(10, 115).add(getPos()), new Index(300, 10), "City size: " + cityTypeString, this);
        StaticBroadcastLabel cityLabel = new StaticBroadcastLabel(new Coord(10, 140), new Coord(300, 10),
                "City size:", city, "sizeType", this);
        addWindowElement(cityLabel);

            /*Label cityPopulationLabel = new Label(new Index(10, 135).add(getPos()),new Index(150, 10), "City population:", this);
            addWindowElement(cityPopulationLabel);
            String cityPopulationString = city.getValue("population");
            ChangableLabel cityPopulationChangableLabel = new ChangableLabel(new Index(200, 135).add(getPos()), new Index(50, 10),
                    cityPopulationString, city, "population", this);
            addWindowElement(cityPopulationChangableLabel);

            Label cityFoodLabel = new Label(new Index(10, 155).add(getPos()), new Index(150, 10), "City food:", this);
            addWindowElement(cityFoodLabel);
            String cityFoodString = city.getValue("food");
            ChangableLabel cityFoodChangableLabel = new ChangableLabel(new Index(200, 155).add(getPos()), new Index(50, 10),
                    cityFoodString, city, "food", this);
            addWindowElement(cityFoodChangableLabel);*/
    }
}