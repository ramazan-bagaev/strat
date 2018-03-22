package Foundation;

import java.util.ArrayList;

import Foundation.BasicShapes.BasicShape;
import Foundation.GameWindowHelper.HelperFieldMap;
import Foundation.GameWindowHelper.States.HelperState;
import Foundation.GameWindowHelper.States.StandartState;
import Utils.Index;
import Utils.Coord;

public class GameWindowHelperElement extends WindowElement {

    private GameWindowElement gameWindowElement;
    private MainWindow mainWindow;


    private HelperState currentState;

    private HelperFieldMap map;

    public GameWindowHelperElement(GameWindowElement gameWindowElement){
        super(gameWindowElement.getPos(), gameWindowElement.getSize(), gameWindowElement.getParent());
        this.mainWindow = gameWindowElement.getMainWindow();
        setGameWindowElement(gameWindowElement);
        map = new HelperFieldMap(gameWindowElement.getGameEngine().getMap(), this);
        setBasicShapes(new ArrayList<>());
        currentState = new StandartState(this);
    }

    @Override
    public void click(Coord point) {
        currentState.click(point);
    }

    @Override
    public void click2(Coord point){
        currentState.click2(point);
    }

    @Override
    public void hoover(Coord point){
        currentState.hoover(point);
    }

    @Override
    public boolean drag(Coord point, Coord pressedPos, boolean dragBegin){
        return false;
    }

    public GameWindowElement getGameWindowElement() {
        return gameWindowElement;
    }

    public void setGameWindowElement(GameWindowElement gameWindowElement) {
        this.gameWindowElement = gameWindowElement;
    }


    public void setShapes(){
        clearBasicShapes();
        ArrayList<BasicShape> basicShapes = new ArrayList<>();
        basicShapes.clear();
        MainWindowCameraConfiguration cameraConfiguration = mainWindow.getCameraConfiguration();

        int fieldSize = map.getFieldSize();

        float zoom = cameraConfiguration.getZoom();
        int fieldNumber = (int) Math.ceil(getParent().getParent().getSize().x/fieldSize * zoom) + 1; // TODO: here magic constant, that is depend on size of window, make size related api
        double deltax = cameraConfiguration.getWorldCameraPos().x / fieldSize;
        double deltay = cameraConfiguration.getWorldCameraPos().y / fieldSize;
        int deltai = (int)Math.floor(deltax);
        int deltaj = (int)Math.floor(deltay);


        ArrayList<BasicShape> shapes = map.getShapes(new Index(deltai, deltaj), new Index(fieldNumber, fieldNumber));
        basicShapes.addAll(shapes);
        setBasicShapes(basicShapes);
    }

    @Override
    public void run(){
    }

    public HelperFieldMap getMap() {
        return map;
    }

    public void clearHelperElements(){
        currentState.clearHelperElements();
    }

    public void setState(HelperState helperState){
        currentState = helperState;
        setShapes();
    }

    public void setStandartState(){
        currentState = new StandartState(this);
        setShapes();
    }

    public HelperState getCurrentState() {
        return currentState;
    }

    public MainWindow getMainWindow() {
        return mainWindow;
    }
}
