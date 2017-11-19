import java.util.ArrayList;

public class GameWindowElement extends WindowElement{

    private GameEngine gameEngine;

    public GameWindowElement(Coord pos, Coord size, Window parent, GameEngine gameEngine){
        super(pos, size, parent);
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
        result.add(new RectangleShape(getPos().x, getPos().y, getSize().x, getSize().y, BasicShape.Color.Black, false));
        FieldMap map = gameEngine.getMap();
        for (Field field: map.getValues()){
            //if (!field.isNeedToDraw()) continue;
           // Element element = field.getGround();
            ArrayList<BasicShape> basicShapes = new ArrayList<>();
            //if (element != null) basicShapes = element.getShapes();
            Element element = field.getAdditionalElement();
            if (element != null) basicShapes.addAll(element.getShapes());
            result.addAll(basicShapes);
            //field.setNeedToDraw(false);
        }
        return result;
    }

    @Override
    public void click() {
        return;
    }
}
