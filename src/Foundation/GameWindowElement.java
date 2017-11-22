package Foundation;

import java.util.ArrayList;

import Windows.FieldInfoWindow;

public class GameWindowElement extends WindowElement{

    private GameEngine gameEngine;

    public GameWindowElement(Coord pos, Coord size, Window parent, GameEngine gameEngine){
        super(pos, size, parent);
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
        ArrayList<BasicShape> basicShapes = getBasicShapes();
        basicShapes.clear();
        basicShapes.add(new RectangleShape(getPos(), getSize(), BasicShape.Color.Black, false));
        FieldMap map = gameEngine.getMap();
        for (Field field: map.getValues()){
            Element element = field.getGround();
            if (element != null) basicShapes.addAll(element.getShapes());
            element = field.getAdditionalElement();
            if (element != null) basicShapes.addAll(element.getShapes());
        }
        //setBasicShapes(basicShapes);
    }

    @Override
    public void click(Coord point) {
        int i = (point.x / gameEngine.getFieldSize()) * gameEngine.getFieldSize();
        int j = (point.y / gameEngine.getFieldSize()) * gameEngine.getFieldSize();
        Field field = gameEngine.getField(i, j);

        ((MainWindow)getParent()).addNewFieldInfoWindow(field);
    }
}
