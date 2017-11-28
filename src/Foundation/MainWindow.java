package Foundation;

import Windows.FieldInfoWindow;

public class MainWindow extends Window{

    private GameWindowElement gameWindowElement;
    private GameWindowHelperElement gameWindowHelperElement;

    public MainWindow(Coord pos, Coord size, GameEngine gameEngine, Windows parent){
        super(pos, size, parent);
        getParent().getInput().getController().setActiveWindow(this);
        gameWindowElement = new GameWindowElement(pos, size, this, gameEngine);
        addWindowElement(gameWindowElement);
        gameWindowHelperElement = new GameWindowHelperElement(gameWindowElement);
        addWindowElement(gameWindowHelperElement);
        CameraConfiguration cameraConfiguration = new CameraConfiguration(0, 0, 1000, 1000, true);
        setCameraConfiguration(cameraConfiguration);
    }

    public GameWindowElement getGameWindowElement() {
        return gameWindowElement;
    }

    public void setGameWindowElement(GameWindowElement gameWindowElement) {
        this.gameWindowElement = gameWindowElement;
    }

    public GameWindowHelperElement getGameWindowHelperElement() {
        return gameWindowHelperElement;
    }

    public void addNewFieldInfoWindow(Field field){
        for(Window window: getParent().getWindows()){
            if (window.getClass() == FieldInfoWindow.class){
                FieldInfoWindow fieldInfoWindow = (FieldInfoWindow)window;
                fieldInfoWindow.setField(field);
                return;
            }
        }
        addWindow(new FieldInfoWindow(getParent(), field));

    }

    public void moveGameWindow(Coord delta){
        CameraConfiguration cameraConfiguration = getCameraConfiguration();
        cameraConfiguration.move(delta);
        gameWindowElement.setShapes();
        gameWindowHelperElement.setShapes();
    }

    @Override
    public void handleAction(Controller.Action action){
        switch (action){

            case MoveLeft:
                moveGameWindow(new Coord(-10, 0));
                break;
            case MoveRight:
                moveGameWindow(new Coord(10, 0));
                break;
            case MoveUp:
                moveGameWindow(new Coord(0, -10));
                break;
            case MoveDown:
                moveGameWindow(new Coord(0, 10));
                break;
            case None:
                break;
        }
    }
}
