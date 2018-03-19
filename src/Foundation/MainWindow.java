package Foundation;

public class MainWindow extends Window{

    private GameWindowElement gameWindowElement;
    private GameWindowHelperElement gameWindowHelperElement;

    public MainWindow(Coord pos, Coord size, Frame parent){
        super(pos, size, parent);
        getParent().getInput().getController().setActiveWindow(this);
        gameWindowElement = new GameWindowElement(new Coord(0, 0), size, this);
        addWindowElement(gameWindowElement);
        gameWindowHelperElement = new GameWindowHelperElement(gameWindowElement);
        addWindowElement(gameWindowHelperElement);
        CameraConfiguration cameraConfiguration = new CameraConfiguration(new Coord(0, 0) ,new Coord(getShift()), new Coord(size), true);
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




    public void moveGameWindow(Coord delta){
        CameraConfiguration cameraConfiguration = getCameraConfiguration();
        cameraConfiguration.move(delta);
        gameWindowElement.setShapes();
        gameWindowHelperElement.setShapes();
    }

    @Override
    public boolean drag(Coord pos, Coord pressedPos, boolean dragBegin){
        CameraConfiguration cameraConfiguration = getCameraConfiguration();
        cameraConfiguration.drag(pos, pressedPos, dragBegin);
        gameWindowElement.setShapes();
        gameWindowHelperElement.setShapes();
        return true;
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
