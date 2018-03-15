package Foundation;

import java.util.ArrayList;

import Foundation.Elements.City;
import Foundation.GameWindowHelper.HelperFieldMap;
import Foundation.GameWindowHelper.States.HelperState;
import Foundation.GameWindowHelper.States.StandartState;

public class GameWindowHelperElement extends WindowElement {

    private GameWindowElement gameWindowElement;

    private Coord fieldCoordKey;
    private City cityForWork;

    private Field chosenField;

    private Army chosenArmy;
    private City chosenCity;

    private HelperState currentState;

    private HelperFieldMap map;

    public GameWindowHelperElement(GameWindowElement gameWindowElement){
        super(gameWindowElement.getPos(), gameWindowElement.getSize(), gameWindowElement.getParent());
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

    public GameWindowElement getGameWindowElement() {
        return gameWindowElement;
    }

    public void setGameWindowElement(GameWindowElement gameWindowElement) {
        this.gameWindowElement = gameWindowElement;
    }


    public void setShapes(){
        ArrayList<BasicShape> basicShapes = getBasicShapes();
        basicShapes.clear();
        CameraConfiguration cameraConfiguration = getParent().getCameraConfiguration();

        int fieldSize = map.getFieldSize();

        float zoom = cameraConfiguration.getZoom();
        int fieldNumber = (int) Math.ceil(20 * zoom) + 1; // TODO: here magic constant, that is depend on size of window, make size related api
        float deltax = cameraConfiguration.getX() / fieldSize;
        float deltay = cameraConfiguration.getY() / fieldSize;
        int deltai = (int)Math.floor(deltax);
        int deltaj = (int)Math.floor(deltay);


        ArrayList<BasicShape> shapes = map.getShapes(new Coord(deltai, deltaj), new Coord(fieldNumber, fieldNumber));
        basicShapes.addAll(shapes);
    }

    public RectangleShape highlightFieldRectangle(Field field, Color color){
        int fieldSize = field.getSize();
        Coord pos = new Coord(field.getFieldMapPos().x * fieldSize, field.getFieldMapPos().y * fieldSize);
        Coord size = new Coord(fieldSize, fieldSize);
        RectangleShape rectangleShape = new RectangleShape(pos, size, color, false);
        return rectangleShape;
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

}
