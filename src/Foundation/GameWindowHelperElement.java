package Foundation;

import java.util.ArrayList;

import Windows.CityWorkWIndow;

public class GameWindowHelperElement extends WindowElement {

    private GameWindowElement gameWindowElement;

    private Coord fieldCoordKey;
    private City cityForWork;

    public GameWindowHelperElement(GameWindowElement gameWindowElement){
        super(gameWindowElement.getPos(), gameWindowElement.getSize(), gameWindowElement.getParent());
        setGameWindowElement(gameWindowElement);
        setBasicShapes(new ArrayList<>());
    }

    @Override
    public void click(Coord point) {
        if (cityForWork != null){
            int fieldSize = gameWindowElement.getGameEngine().getFieldSize();
            Coord index = new Coord(point.x / fieldSize, point.y / fieldSize);
            if (index.x - fieldCoordKey.x > 2 || index.x - fieldCoordKey.x < -2){
                cityForWork = null;
                setShapes();
            }
            else if (index.y - fieldCoordKey.y > 2 || index.y - fieldCoordKey.y < -2){
                cityForWork = null;
                setShapes();
            }
            else{
                CityWorkWIndow cityWorkWIndow = new CityWorkWIndow(cityForWork, getParent().getParent());
                getParent().addWindow(cityWorkWIndow);
            }
        }
        gameWindowElement.click(point);
        addChosenField(point);
    }

    public GameWindowElement getGameWindowElement() {
        return gameWindowElement;
    }

    public void setGameWindowElement(GameWindowElement gameWindowElement) {
        this.gameWindowElement = gameWindowElement;
    }

    public void addChosenField(Coord point){
        int fieldSize = gameWindowElement.getGameEngine().getFieldSize();
        Coord index = new Coord(point.x / fieldSize,  point.y/ fieldSize);
        fieldCoordKey = index;
        setShapes();
        // really difficult stuff, thing a little bit
    }

    public void addCityWork(City city){
        cityForWork = city;
        setShapes();
    }

    public void addFieldWork(Field field, ArrayList<BasicShape> basicShapes){
        int fieldSize = field.getSize();
        Coord fieldIndex = field.getFieldMapPos();
        Coord pos = new Coord(fieldIndex.x * fieldSize, fieldIndex.y * fieldSize);
        Coord size = new Coord(fieldSize, fieldSize);
        RectangleShape rectangleShape = new RectangleShape(pos, size, BasicShape.Color.Green, false);
        basicShapes.add(rectangleShape);
    }

    public void setShapes(){
        ArrayList<BasicShape> basicShapes = getBasicShapes();
        basicShapes.clear();
        if (cityForWork != null){
            GameEngine gameEngine = gameWindowElement.getGameEngine();
            Field field = cityForWork.getParent();
            Coord cityFieldIndex = field.getFieldMapPos();
            for (int i = -2; i <= 2; i++){
                for (int j = -2; j <= 2; j++){
                    if (i == 0 && j == 0) continue;
                    Coord coord = new Coord(i, j).add(cityFieldIndex);
                    Field neighborField = gameEngine.getField(coord.x, coord.y);
                    if (neighborField == null) continue;
                    addFieldWork(neighborField, basicShapes);
                }
            }
        }
        if (fieldCoordKey != null){
            Field field = gameWindowElement.getGameEngine().getField(fieldCoordKey.x, fieldCoordKey.y);
            if (field == null){
                fieldCoordKey = null;
                return;
            }
            int fieldSize = field.getSize();
            Coord pos = new Coord(fieldCoordKey.x * fieldSize, fieldCoordKey.y * fieldSize);
            Coord size = new Coord(fieldSize, fieldSize);
            RectangleShape rectangleShape = new RectangleShape(pos, size, BasicShape.Color.Red, false);
            basicShapes.add(rectangleShape);
        }
    }

    @Override
    public void run(){
    }
}
