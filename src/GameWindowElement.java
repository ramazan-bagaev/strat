import java.util.ArrayList;

public class GameWindowElement extends WindowElement{

    private GameEngine gameEngine;

    public GameWindowElement(int x, int y, int sizeX, int sizeY, Window parent, GameEngine gameEngine){
        super(x, y, sizeX, sizeY, parent);
        setGameEngine(gameEngine);
    }

    public GameEngine getGameEngine() {
        return gameEngine;
    }

    public void setGameEngine(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @Override
    public ArrayList<BasicShape> getShapes() {
        ArrayList<BasicShape> result = new ArrayList<>();
        result.add(new RectangleShape(getX(), getY(), getSizeX(), getSizeY(), BasicShape.Color.Black, false));
        FieldMap map = gameEngine.getMap();
        for (Field field: map.getValues()){
            Element element = field.getGround();
            ArrayList<BasicShape> basicShapes = new ArrayList<>();
            if (element != null) basicShapes = element.getShapes();
            element = field.getAdditionalElement();
            if (element != null) basicShapes.addAll(element.getShapes());
            result.addAll(basicShapes);
        }
        return result;
    }
}
