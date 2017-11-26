package Foundation;

import java.util.ArrayList;

public class GameWindowHelperElement extends WindowElement {

    private GameWindowElement gameWindowElement;

    public GameWindowHelperElement(GameWindowElement gameWindowElement){
        super(gameWindowElement.getPos(), gameWindowElement.getSize(), gameWindowElement.getParent());
        setGameWindowElement(gameWindowElement);
        setBasicShapes(new ArrayList<>());
    }

    @Override
    public void click(Coord point) {
        gameWindowElement.click(point);

    }

    public GameWindowElement getGameWindowElement() {
        return gameWindowElement;
    }

    public void setGameWindowElement(GameWindowElement gameWindowElement) {
        this.gameWindowElement = gameWindowElement;
    }

    public void addFieldHoover(Coord point){
        CameraConfiguration cameraConfiguration = getParent().getCameraConfiguration();
        Coord mapPos = cameraConfiguration.transform(point);
        int fieldSize = gameWindowElement.getGameEngine().getFieldSize();
        Coord index = new Coord(mapPos.x / fieldSize, mapPos.y/ fieldSize);
        Coord pos = new Coord(index.x * fieldSize, index.y * fieldSize);
        Field field = gameWindowElement.getGameEngine().getField(index.x, index.y);
        Coord size = new Coord(field.getSize(), field.getSize());
        RectangleShape rectangleShape = new RectangleShape(pos, size, BasicShape.Color.Red, false);
        ArrayList<BasicShape> basicShapes = getBasicShapes();
        // really difficult stuff, thing a little bit
    }

    @Override
    public void run(){
    }
}
