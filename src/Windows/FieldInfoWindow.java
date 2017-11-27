package Windows;

import Foundation.*;
import Images.CityImage;
import Images.GroundImage;
import Images.RockImage;
import Images.TreeImage;

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
                case Rock:
                    Rock rock = (Rock) additionalElement;
                    Image rockImage = new RockImage(getPos(), rock.getSizeType(), this);
                    finalImage = new Image(groundImage, rockImage, getPos(), this);
                    break;
                case Tree:
                    Tree tree = (Tree) additionalElement;
                    Image treeImage = new TreeImage(getPos(), tree.getSizeType(), this);
                    finalImage = new Image(groundImage, treeImage, getPos(), this);
                    break;
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

        // additional element place
        if (additionalElement == null) return;

        if (additionalElement.getType() == Element.Type.City){
            City city = (City)additionalElement;
            Windows windows = getParent();
            //String cityTypeString = city.getValue("sizeType");
            //Label cityTypeLabel = new Label(new Coord(10, 115).add(getPos()), new Coord(300, 10), "City size: " + cityTypeString, this);
            StaticBroadcastLabel cityLabel = new StaticBroadcastLabel(new Coord(10, 115).add(getPos()), new Coord(300, 10),
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
        if (additionalElement.getType() == Element.Type.Rock){
            Rock rock = (Rock)additionalElement;
            //String rockTypeString = rock.getValue("sizeType");
            //Label rockTypeLabel = new Label(new Coord(10, 115).add(getPos()), new Coord(300, 10), "Rock size: " + rockTypeString, this);
            StaticBroadcastLabel rockLabel = new StaticBroadcastLabel(new Coord(10, 115).add(getPos()), new Coord(300, 10),
                    "Rock size:", rock, "sizeType",this);
            addWindowElement(rockLabel);

            /*Label rockCapacityLabel = new Label(new Coord(10, 135).add(getPos()),new Coord(150, 10), "Rock capacity:", this);
            addWindowElement(rockCapacityLabel);
            String rockCapacityString = rock.getValue("resourceCause.capacity");
            ChangableLabel rockCapacityChangableLabel = new ChangableLabel(new Coord(200, 135).add(getPos()), new Coord(50, 10),
                    rockCapacityString, rock, "resourceCause.capacity", this);
            addWindowElement(rockCapacityChangableLabel);*/
        }
        if (additionalElement.getType() == Element.Type.Tree){
            Tree tree = (Tree)additionalElement;
            //String treeTypeString = tree.getValue("sizeType");
            //Label treeTypeLabel = new Label(new Coord(10, 115).add(getPos()), new Coord(300, 10), "Tree size: " + treeTypeString, this);
            StaticBroadcastLabel treeLabel = new StaticBroadcastLabel(new Coord(10, 115).add(getPos()), new Coord(300, 10),
                    "Tree size:", tree, "sizeType",this);
            addWindowElement(treeLabel);

            /*Label treeCapacityLabel = new Label(new Coord(10, 135).add(getPos()),new Coord(150, 10), "Tree capacity:", this);
            addWindowElement(treeCapacityLabel);
            String treeCapacityString = tree.getValue("resourceCause.capacity");
            ChangableLabel treeCapacityChangableLabel = new ChangableLabel(new Coord(200, 135).add(getPos()), new Coord(50, 10),
                    treeCapacityString, tree, "resourceCause.capacity", this);
            addWindowElement(treeCapacityChangableLabel);*/
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

}