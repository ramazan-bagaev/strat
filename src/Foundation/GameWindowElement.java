package Foundation;

import java.util.ArrayList;

import Windows.FieldInfoWindow;

public class GameWindowElement extends WindowElement{

    private GameEngine gameEngine;

    public GameWindowElement(Coord pos, Coord size, Window parent){
        super(pos, size, parent);
        gameEngine = new GameEngine(50, 100, this);
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
        basicShapes.add(new RectangleShape(getPos(), getSize(), new Color(Color.Type.Black), false));
        CameraConfiguration cameraConfiguration = getParent().getCameraConfiguration();

        FieldMap map = gameEngine.getMap();
        int fieldSize = gameEngine.getFieldSize();

        float zoom = cameraConfiguration.getZoom();
        int fieldNumber = (int) Math.ceil(20 * zoom) + 1; // TODO: here magic constant, that is depend on size of window, make size related api
        float deltax = cameraConfiguration.getX() / fieldSize;
        float deltay = cameraConfiguration.getY() / fieldSize;
        int deltai = (int)Math.floor(deltax);
        int deltaj = (int)Math.floor(deltay);

        ArrayList<BasicShape> shapes = map.getShapes(new Coord(deltai, deltaj), new Coord(fieldNumber, fieldNumber));
        basicShapes.addAll(shapes);
       /* for (int i = deltai; i <= fieldNumber + deltai; i++){
            for (int j = deltaj; j <= fieldNumber + deltaj; j++){
                Field field = map.getFieldByIndex(new Coord(i, j));
                if (field == null) continue;
                Element element = field.getGround();
                if (element != null) basicShapes.addAll(element.getShapes());
                element = field.getCity();
                if (element != null) basicShapes.addAll(element.getShapes());
                element = field.getArmy();
                if (element != null) basicShapes.addAll(element.getShapes());
            }
        }*/

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
        Field field = gameEngine.getFieldByPos(point);
        if (field == null) return;
        ((MainWindow)getParent()).addNewFieldInfoWindow(field);
        City city = field.getCity();
        if (city != null){
            ((MainWindow) getParent()).addNewCityInfoWindow(city);
        }
        Army army = field.getArmy();
        if (army != null){
            ((MainWindow)getParent()).addNewArmyInfoWindow(army);
        }
        Farm farm = field.getFarm();
        if (farm != null){
            ((MainWindow)getParent()).addNewFarmInfoWindow(farm);
        }

      ///  if (f)
    }

    @Override
    public void run(){
        CameraConfiguration cameraConfiguration = getParent().getCameraConfiguration();
        FieldMap map = gameEngine.getMap();
        int fieldSize = gameEngine.getFieldSize();

        float zoom = cameraConfiguration.getZoom();
        int fieldNumber = (int) Math.ceil(20 * zoom) + 1;

        /*float deltax = cameraConfiguration.getX() / fieldSize;
        float deltay = cameraConfiguration.getY() / fieldSize;
        int deltai = 0;
        int deltaj = 0;
        if (deltax < 0) deltai = (int)Math.floor(deltax);
        if (deltax >= 0) deltai = (int)Math.floor(deltax);
        if (deltay < 0) deltaj = (int)Math.floor(deltay);
        if (deltay >= 0) deltaj = (int)Math.floor(deltay);
        for (int i = deltai; i <= fieldNumber + deltai; i++) {
            for (int j = deltaj; j <= fieldNumber + deltaj; j++) {
                Field field = map.getFieldByIndex(new Coord(i, j));
                if (field == null) continue;
                if (field.isChanged()){
                    setShapes();
                    field.setChanged(false);
                    return;
                }
            }
        }*/
    }
}
