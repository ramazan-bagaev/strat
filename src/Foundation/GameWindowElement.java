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
        CameraConfiguration cameraConfiguration = getParent().getCameraConfiguration();

        FieldMap map = gameEngine.getMap();
        int fieldSize = gameEngine.getFieldSize();

        float zoom = cameraConfiguration.getZoom();
        int fieldNumber = (int) Math.ceil(20 * zoom) + 1; // TODO: here magic constant, that is depend on size of window, make size related api
        float deltax = cameraConfiguration.getX() / fieldSize;
        float deltay = cameraConfiguration.getY() / fieldSize;
        int deltai = 0;
        int deltaj = 0;
        if (deltax < 0) deltai = (int)Math.floor(deltax);
        if (deltax >= 0) deltai = (int)Math.floor(deltax);
        if (deltay < 0) deltaj = (int)Math.floor(deltay);
        if (deltay >= 0) deltaj = (int)Math.floor(deltay);
        for (int i = deltai; i <= fieldNumber + deltai; i++){
            for (int j = deltaj; j <= fieldNumber + deltaj; j++){
                Field field = map.getField(new Coord(i, j));
                if (field == null) continue;
                Element element = field.getGround();
                if (element != null) basicShapes.addAll(element.getShapes());
                element = field.getAdditionalElement();
                if (element != null) basicShapes.addAll(element.getShapes());
            }
        }
        /*for (Field field: map.getValues()){
            Element element = field.getGround();
            if (element != null) basicShapes.addAll(element.getShapes());
            element = field.getAdditionalElement();
            if (element != null) basicShapes.addAll(element.getShapes());
        }*/
        //setBasicShapes(basicShapes);
    }

    @Override
    public void click(Coord point) {
        int x = (point.x / gameEngine.getFieldSize()) * gameEngine.getFieldSize();
        int y = (point.y / gameEngine.getFieldSize()) * gameEngine.getFieldSize();
        int i = x / gameEngine.getFieldSize();
        int j = y / gameEngine.getFieldSize();
        Field field = gameEngine.getField(i, j);
        if (field == null) return;
        ((MainWindow)getParent()).addNewFieldInfoWindow(field);
    }
}
