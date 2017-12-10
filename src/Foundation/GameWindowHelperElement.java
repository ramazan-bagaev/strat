package Foundation;

import java.util.ArrayList;
import Windows.CityWorkWindow;

public class GameWindowHelperElement extends WindowElement {

    private GameWindowElement gameWindowElement;

    private Coord fieldCoordKey;
    private City cityForWork;

    private Field chosenField;
    private Army chosenArmy;
    private City chosenCity;

    public GameWindowHelperElement(GameWindowElement gameWindowElement){
        super(gameWindowElement.getPos(), gameWindowElement.getSize(), gameWindowElement.getParent());
        setGameWindowElement(gameWindowElement);
        setBasicShapes(new ArrayList<>());
    }

    @Override
    public void click(Coord point) {
        point = getParent().getCameraConfiguration().transform(point);
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
                Field field = gameWindowElement.getGameEngine().getField(index.x, index.y);
                CityWorkWindow cityWorkWindow = new CityWorkWindow(cityForWork, field, getParent().getParent());
                getParent().addWindow(cityWorkWindow);
            }
        }
        gameWindowElement.click(point);
        addChosenField(point);
    }

    @Override
    public void click2(Coord point){
        if (chosenArmy == null) return;
        point = getParent().getCameraConfiguration().transform(point);
        int fieldSize = gameWindowElement.getGameEngine().getFieldSize();
        Coord index = new Coord(point.x / fieldSize,  point.y/ fieldSize);
        Field field = gameWindowElement.getGameEngine().getField(index.x, index.y);
        if (field.getCity() != null){
            
        }
        chosenArmy.move(field);
        setShapes();
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
        chosenField = gameWindowElement.getGameEngine().getField(fieldCoordKey.x, fieldCoordKey.y);
        if (chosenField != null){
            chosenArmy = chosenField.getArmy();
            chosenCity = chosenField.getCity();
        }
        else{
            chosenArmy = null;
            chosenCity = null;
        }
        setShapes();
        // really difficult stuff, thing a little bit
    }

    public void addCityWork(City city){
        cityForWork = city;
        setShapes();
    }

    public void setShapes(){
        ArrayList<BasicShape> basicShapes = getBasicShapes();
        basicShapes.clear();
        if (chosenField != null){
            basicShapes.add(highlightFieldRectangle(chosenField, BasicShape.Color.Red));
        }
        if (chosenCity != null){
            basicShapes.add(highlightFieldRectangle(chosenField, BasicShape.Color.Yellow));
        }
        if (chosenArmy != null){
            basicShapes.add(highlightFieldRectangle(chosenArmy.getParent(), BasicShape.Color.Blue));
        }

        if (cityForWork != null) {
            GameEngine gameEngine = gameWindowElement.getGameEngine();
            Field field = cityForWork.getParent();
            Coord cityFieldIndex = field.getFieldMapPos();
            for (int i = -2; i <= 2; i++) {
                for (int j = -2; j <= 2; j++) {
                    if (i == 0 && j == 0) continue;
                    Coord coord = new Coord(i, j).add(cityFieldIndex);
                    Field neighborField = gameEngine.getField(coord.x, coord.y);
                    if (neighborField == null) continue;
                    basicShapes.add(highlightFieldRectangle(neighborField, BasicShape.Color.Green));
                }
            }
        }
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
