package Foundation;

import java.util.ArrayList;

import Foundation.GameWindowHelper.HelperFieldMap;
import Windows.CityWorkWindow;

public class GameWindowHelperElement extends WindowElement {

    private GameWindowElement gameWindowElement;

    private Coord fieldCoordKey;
    private City cityForWork;

    private Field chosenField;

    private Army chosenArmy;
    private City chosenCity;

    private HelperFieldMap map;

    public GameWindowHelperElement(GameWindowElement gameWindowElement){
        super(gameWindowElement.getPos(), gameWindowElement.getSize(), gameWindowElement.getParent());
        setGameWindowElement(gameWindowElement);
        map = new HelperFieldMap(gameWindowElement.getGameEngine().getMap(), this);
        setBasicShapes(new ArrayList<>());
    }

    @Override
    public void click(Coord point) {
        point = getParent().getCameraConfiguration().transform(point);
        gameWindowElement.click(point);
    }

    @Override
    public void click2(Coord point){
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

    public RectangleShape highlightFieldRectangle(Field field, BasicShape.Color color){
        int fieldSize = field.getSize();
        Coord pos = new Coord(field.getFieldMapPos().x * fieldSize, field.getFieldMapPos().y * fieldSize);
        Coord size = new Coord(fieldSize, fieldSize);
        RectangleShape rectangleShape = new RectangleShape(pos, size, color, false);
        return rectangleShape;
    }

    @Override
    public void run(){
    }
}
