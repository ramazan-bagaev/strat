package Foundation;

import Foundation.BasicShapes.BasicShape;
import Foundation.BasicShapes.RectangleShape;
import Foundation.Elements.ArmyElement;
import Foundation.Elements.City;
import Foundation.Elements.Manor;
import Foundation.Elements.Village;
import Utils.Index;
import Utils.Coord;
import Windows.ElementInfoWindow.ArmyInfoWindow;
import Windows.ElementInfoWindow.CityInfoWindow;
import Windows.ElementInfoWindow.VillageInfoWindow;
import Windows.FieldInfoWindow;
import Windows.ElementInfoWindow.ManorInfoWindow;

import java.util.ArrayList;

public class GameWindowElement extends WindowElement{

    private GameEngine gameEngine;
    private MainWindow mainWindow;

    public GameWindowElement(Coord pos, Coord size, MainWindow parent){
        super(pos, size, parent);
        this.mainWindow = parent;
        gameEngine = new GameEngine(100, 100, this);
        setGameEngine(gameEngine);
        setBasicShapes(new ArrayList<>());
        setShapes();
    }

    public GameEngine getGameEngine() {
        return gameEngine;
    }

    public void setGameEngine(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }


    public MainWindow getMainWindow() {
        return mainWindow;
    }


    public void setShapes(){
        clearBasicShapes();
        ArrayList<BasicShape> basicShapes = new ArrayList<>();
        basicShapes.add(new RectangleShape(new Coord(0, 0), getSize(), new Color(Color.Type.Black), false));
        MainWindowCameraConfiguration cameraConfiguration = mainWindow.getCameraConfiguration();

        FieldMap map = gameEngine.getMap();
        int fieldSize = gameEngine.getFieldSize();

        float zoom = cameraConfiguration.getZoom();
        int fieldNumber = (int) Math.ceil((getParent().getSize().x / (float)fieldSize) * zoom) + 1; // TODO: here magic constant, that is depend on size of window, make size related api
        double deltax = (cameraConfiguration.getWorldCameraPos().x) / fieldSize;
        double deltay = (cameraConfiguration.getWorldCameraPos().y) / fieldSize;
        int deltai = (int)Math.floor(deltax);
        int deltaj = (int)Math.floor(deltay);

        ArrayList<BasicShape> shapes = map.getShapes(new Index(deltai, deltaj), new Index(fieldNumber, fieldNumber));
        basicShapes.addAll(shapes);
       /* for (int i = deltai; i <= fieldNumber + deltai; i++){
            for (int j = deltaj; j <= fieldNumber + deltaj; j++){
                Field field = map.getFieldByIndex(new Index(i, j));
                if (field == null) continue;
                Element element = field.getGround();
                if (element != null) basicShapes.addAll(element.getShapes());
                element = field.getCity();
                if (element != null) basicShapes.addAll(element.getShapes());
                element = field.getArmy();
                if (element != null) basicShapes.addAll(element.getShapes());
            }
        }*/

        /*for (Field field: map.getValues()){
            Element element = field.getGround();
            if (element != null) basicShapes.addAll(element.getShapes());
            element = field.getAdditionalElement();
            if (element != null) basicShapes.addAll(element.getShapes());
        }*/
        //setBasicShapes(basicShapes);
        setBasicShapes(basicShapes);
    }

    @Override
    public void click(Coord point) {
        Field field = gameEngine.getFieldByPos(point);
        if (field == null) return;
        //((MainWindow)getParent()).addNewFieldInfoWindow(field);
        Frame frame = getParent().getParent();
        FieldInfoWindow fieldInfoWindow = new FieldInfoWindow(frame, field);
        frame.addSpecialWindow("field info window", fieldInfoWindow);

        City city = field.getCity();
        if (city != null){
            CityInfoWindow cityInfoWindow = new CityInfoWindow(city, frame);
            frame.addSpecialWindow("element info window", cityInfoWindow);
        }
        ArmyElement armyElement = field.getArmyElement();
        if (armyElement != null){
            ArmyInfoWindow armyInfoWindow = new ArmyInfoWindow(armyElement.getArmy(), frame);
            frame.addSpecialWindow("element info window", armyInfoWindow);
        }
        Manor manor = field.getManor();
        if (manor != null){
            ManorInfoWindow manorInfoWindow = new ManorInfoWindow(manor, frame);
            frame.addSpecialWindow("element info window", manorInfoWindow);
        }
        Village village = field.getVillage();
        if (village != null){
            VillageInfoWindow villageInfoWindow = new VillageInfoWindow(village, frame);
            frame.addSpecialWindow("element info window", villageInfoWindow);
        }

      ///  if (f)
    }

    @Override
    public void run(){

    }
}
