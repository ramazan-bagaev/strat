package Foundation;

import Foundation.Elements.City;
import Foundation.Elements.Manor;
import Windows.FieldInfoWindow;
import Windows.CityInfoWindow;
import Windows.ArmyInfoWindow;
import Windows.WorkPlaceWindows.ManorInfoWindow;

public class MainWindow extends Window{

    private GameWindowElement gameWindowElement;
    private GameWindowHelperElement gameWindowHelperElement;

    public MainWindow(Coord pos, Coord size, Windows parent){
        super(pos, size, parent);
        getParent().getInput().getController().setActiveWindow(this);
        gameWindowElement = new GameWindowElement(pos, size, this);
        addWindowElement(gameWindowElement);
        gameWindowHelperElement = new GameWindowHelperElement(gameWindowElement);
        addWindowElement(gameWindowHelperElement);
        CameraConfiguration cameraConfiguration = new CameraConfiguration(0, 0, 1000, 1000, true);
        setCameraConfiguration(cameraConfiguration);
    }

    public GameWindowElement getGameWindowElement() {
        return gameWindowElement;
    }

    public void setGameWindowElement(GameWindowElement gameWindowElement) {
        this.gameWindowElement = gameWindowElement;
    }

    public GameWindowHelperElement getGameWindowHelperElement() {
        return gameWindowHelperElement;
    }

    public void addNewFieldInfoWindow(Field field){
        for(Window window: getParent().getWindows()){
            if (window.getClass() == FieldInfoWindow.class){
                FieldInfoWindow fieldInfoWindow = (FieldInfoWindow)window;
                fieldInfoWindow.setField(field);
                return;
            }
        }
        addWindow(new FieldInfoWindow(getParent(), field));

    }

    public void addNewCityInfoWindow(City city){
        for (Window window: getParent().getWindows()){
            if (window.getClass() == CityInfoWindow.class){
                CityInfoWindow cityInfoWindow = (CityInfoWindow)window;
                cityInfoWindow.setCity(city);
                return;
            }
        }
        addWindow(new CityInfoWindow(city, getParent()));
    }

    public void addNewArmyInfoWindow(Army army){
        for (Window window: getParent().getWindows()){
            if (window.getClass() == ArmyInfoWindow.class){
                ArmyInfoWindow armyInfoWindow = (ArmyInfoWindow)window;
                armyInfoWindow.setArmy(army);
                return;
            }
        }
        addWindow(new ArmyInfoWindow(army, getParent()));
    }

    public void addNewFarmInfoWindow(Manor manor){
        for (Window window: getParent().getWindows()){
            if (window.getClass() == ArmyInfoWindow.class){
                ManorInfoWindow manorInfoWindow = (ManorInfoWindow)window;
                manorInfoWindow.setManor(manor);
                return;
            }
        }
        addWindow(new ManorInfoWindow(manor, getParent()));
    }

    public void moveGameWindow(Coord delta){
        CameraConfiguration cameraConfiguration = getCameraConfiguration();
        cameraConfiguration.move(delta);
        gameWindowElement.setShapes();
        gameWindowHelperElement.setShapes();
    }

    @Override
    public void handleAction(Controller.Action action){
        switch (action){

            case MoveLeft:
                moveGameWindow(new Coord(-10, 0));
                break;
            case MoveRight:
                moveGameWindow(new Coord(10, 0));
                break;
            case MoveUp:
                moveGameWindow(new Coord(0, -10));
                break;
            case MoveDown:
                moveGameWindow(new Coord(0, 10));
                break;
            case None:
                break;
        }
    }
}
