package Foundation;

import java.util.ArrayList;

import Windows.FieldInfoWindow;

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
        result.add(new RectangleShape(getPos(), getSize(), BasicShape.Color.Black, false));
        FieldMap map = gameEngine.getMap();
        for (Field field: map.getValues()){
            //if (!field.isNeedToDraw()) continue;
            Element element = field.getGround();
            ArrayList<BasicShape> basicShapes = new ArrayList<>();
            if (element != null) basicShapes = element.getShapes();
            element = field.getAdditionalElement();
            if (element != null) basicShapes.addAll(element.getShapes());
            result.addAll(basicShapes);
            //field.setNeedToDraw(false);
        }
        return result;
    }

    @Override
    public void click(Coord point) {
        int i = (point.x / gameEngine.getFieldSize()) * gameEngine.getFieldSize();
        int j = (point.y / gameEngine.getFieldSize()) * gameEngine.getFieldSize();
        Field field = gameEngine.getField(i, j);

        ((MainWindow)getParent()).addNewFieldInfoWindow(field);
    }
}
