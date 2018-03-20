package Foundation;

import Foundation.Elements.ArmyElement;
import Foundation.Elements.City;
import Foundation.Elements.Manor;
import Foundation.Elements.Village;
import Windows.ElementInfoWindow.ArmyInfoWindow;
import Windows.ElementInfoWindow.CityInfoWindow;
import Windows.ElementInfoWindow.VillageInfoWindow;
import Windows.FieldInfoWindow;
import Windows.ElementInfoWindow.ManorInfoWindow;

import java.util.ArrayList;

public class GameWindowElement extends WindowElement{

    private GameEngine gameEngine;

    public GameWindowElement(Coord pos, Coord size, Window parent){
        super(pos, size, parent);
        gameEngine = new GameEngine(50, 100, this);
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

    public void setShapes(){
        clearBasicShapes();
        ArrayList<BasicShape> basicShapes = new ArrayList<>();
        basicShapes.add(new RectangleShape(new Coord(0, 0), getSize(), new Color(Color.Type.Black), false));
        CameraConfiguration cameraConfiguration = getParent().getCameraConfiguration();

        FieldMap map = gameEngine.getMap();
        int fieldSize = gameEngine.getFieldSize();


        float zoom = cameraConfiguration.getZoom();
        int fieldNumber = (int) Math.ceil((getParent().getSize().x / (float)fieldSize) * zoom) + 1; // TODO: here magic constant, that is depend on size of window, make size related api
        float deltax = (cameraConfiguration.getWorldPos().x) / fieldSize;
        float deltay = (cameraConfiguration.getWorldPos().y) / fieldSize;
        int deltai = (int)Math.floor(deltax);
        int deltaj = (int)Math.floor(deltay);

        ArrayList<BasicShape> shapes = map.getShapes(new Coord(deltai, deltaj), new Coord(fieldNumber, fieldNumber));
        basicShapes.addAll(shapes);
       /* for (int i = deltai; i <= fieldNumber + deltai; i++){
            for (int j = deltaj; j <= fieldNumber + deltaj; j++){
                Field field = map.getFieldByIndex(new Coord(i, j));
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
        setBasicShapesWithoutShift(basicShapes);
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
        CameraConfiguration cameraConfiguration = getParent().getCameraConfiguration();
        FieldMap map = gameEngine.getMap();
        int fieldSize = gameEngine.getFieldSize();

        float zoom = cameraConfiguration.getZoom();
        int fieldNumber = (int) Math.ceil(20 * zoom) + 1;

        /*float deltax = cameraConfiguration.getX() / fieldSize;
        float deltay = cameraConfiguration.getY() / fieldSize;
        int deltai = 0;
        int deltaj = 0;
        if (deltax < 0) deltai = (int)Math.floor(deltax);
        if (deltax >= 0) deltai = (int)Math.floor(deltax);
        if (deltay < 0) deltaj = (int)Math.floor(deltay);
        if (deltay >= 0) deltaj = (int)Math.floor(deltay);
        for (int i = deltai; i <= fieldNumber + deltai; i++) {
            for (int j = deltaj; j <= fieldNumber + deltaj; j++) {
                Field field = map.getFieldByIndex(new Coord(i, j));
                if (field == null) continue;
                if (field.isChanged()){
                    setShapes();
                    field.setChanged(false);
                    return;
                }
            }
        }*/
    }
}
