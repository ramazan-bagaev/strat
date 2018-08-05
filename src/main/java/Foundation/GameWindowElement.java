package Foundation;

import Foundation.BasicShapes.BasicShape;
import Foundation.BasicShapes.RectangleShape;
import Foundation.Elements.ArmyFieldElement;
import Foundation.Elements.City;
import Foundation.Elements.Manor;
import Foundation.Elements.Village;
import Foundation.FieldObjects.FieldObject;
import Utils.Geometry.Index;
import Utils.Geometry.Coord;
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
        int fieldNumber = (int) Math.ceil((getParent().getSize().x / (float)fieldSize) * zoom) + 1; // TODO: here magic constant, that is depend on pieceSize of window, make pieceSize related api
        double deltax = (cameraConfiguration.getWorldCameraPos().x) / fieldSize;
        double deltay = (cameraConfiguration.getWorldCameraPos().y) / fieldSize;
        int deltai = (int)Math.floor(deltax);
        int deltaj = (int)Math.floor(deltay);

        ArrayList<BasicShape> shapes = map.getShapes(new Index(deltai, deltaj), new Index(fieldNumber, fieldNumber), cameraConfiguration.getMode());
        basicShapes.addAll(shapes);
       /* for (int i = deltai; i <= fieldNumber + deltai; i++){
            for (int j = deltaj; j <= fieldNumber + deltaj; j++){
                Field field = map.getFieldByIndex(new Index(i, j));
                if (field == null) continue;
                FieldElement element = field.getGround();
                if (element != null) basicShapes.addAll(element.getShapes());
                element = field.getCity();
                if (element != null) basicShapes.addAll(element.getShapes());
                element = field.getArmy();
                if (element != null) basicShapes.addAll(element.getShapes());
            }
        }*/

        /*for (Field field: map.getValues()){
            FieldElement element = field.getGround();
            if (element != null) basicShapes.addAll(element.getShapes());
            element = field.getAdditionalElement();
            if (element != null) basicShapes.addAll(element.getShapes());
        }*/
        //setBasicShapes(basicShapes);
        setBasicShapes(basicShapes);
    }

    @Override
    public void click(Coord point) {
        MainWindowCameraConfiguration.Mode mode = mainWindow.getParent().getMode();
        if (mode == MainWindowCameraConfiguration.Mode.Normal) clickForNormalMode(point);
        if (mode == MainWindowCameraConfiguration.Mode.Detailed) clickForDetailedMode(point);
    }

    public void clickForDetailedMode(Coord point){
        FieldObject fieldObject = gameEngine.getFieldObjectByPos(point);
        if (fieldObject == null) return;
        Frame frame = getParent().getParent();
        Window infoWindow = fieldObject.getInfoWindow();
        if (infoWindow == null) return;
        frame.addSpecialWindow("element info window", infoWindow);
    }

    public void clickForNormalMode(Coord point){
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
        ArmyFieldElement armyElement = field.getArmyElement();
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
    }

    @Override
    public void draw(OpenGLBinder openGLBinder){
        super.draw(openGLBinder);

        System.out.println("Yohohohoho");
        FieldMap map = gameEngine.getMap();
        int fieldSize = gameEngine.getFieldSize();
        MainWindowCameraConfiguration cameraConfiguration = mainWindow.getCameraConfiguration();

        float zoom = cameraConfiguration.getZoom();
        int fieldNumber = (int) Math.ceil((getParent().getSize().x / (float)fieldSize) * zoom) + 1; // TODO: here magic constant, that is depend on pieceSize of window, make pieceSize related api
        double deltax = (cameraConfiguration.getWorldCameraPos().x) / fieldSize;
        double deltay = (cameraConfiguration.getWorldCameraPos().y) / fieldSize;
        int deltai = (int)Math.floor(deltax);
        int deltaj = (int)Math.floor(deltay);
        map.drawDynamicDrawable(new Index(deltai, deltaj), new Index(fieldNumber, fieldNumber), openGLBinder);
    }

}
