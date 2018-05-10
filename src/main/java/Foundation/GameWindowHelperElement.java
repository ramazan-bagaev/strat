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
        setStandartState();
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
        return currentState.drag(point, pressedPos, dragBegin);
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


        ArrayList<BasicShape> shapes = map.getShapes(new Index(deltai, deltaj), new Index(fieldNumber, fieldNumber), cameraConfiguration.getMode());
        basicShapes.addAll(shapes);
        setBasicShapes(basicShapes);
    }

    public HelperFieldMap getMap() {
        return map;
    }

    public void clearHelperElements(){
        currentState.clearHelperElements();
    }

    public void setState(HelperState helperState){
        currentState = helperState;
        currentState.putHelperElements();
        setShapes();
    }

    public void setStandartState(){
        currentState = new StandartState(this);
        currentState.putHelperElements();
        setShapes();
    }

    public HelperState getCurrentState() {
        return currentState;
    }

    public MainWindow getMainWindow() {
        return mainWindow;
    }

    public void renewState(){
        currentState.clearHelperElements();
        currentState.putHelperElements();
    }

    @Override
    public void draw(OpenGLBinder openGLBinder){
        super.draw(openGLBinder);


        HelperFieldMap map = getMap();
        int fieldSize = map.getFieldSize();
        MainWindowCameraConfiguration cameraConfiguration = mainWindow.getCameraConfiguration();

        float zoom = cameraConfiguration.getZoom();
        int fieldNumber = (int) Math.ceil((getParent().getSize().x / (float)fieldSize) * zoom) + 1; // TODO: here magic constant, that is depend on size of window, make size related api
        double deltax = (cameraConfiguration.getWorldCameraPos().x) / fieldSize;
        double deltay = (cameraConfiguration.getWorldCameraPos().y) / fieldSize;
        int deltai = (int)Math.floor(deltax);
        int deltaj = (int)Math.floor(deltay);
        map.drawDynamicDrawable(new Index(deltai, deltaj), new Index(fieldNumber, fieldNumber), openGLBinder);
    }
}
