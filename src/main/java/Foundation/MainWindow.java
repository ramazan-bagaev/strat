package Foundation;

import Utils.Geometry.Coord;

public class MainWindow extends Window{

    private GameWindowElement gameWindowElement;
    private GameWindowHelperElement gameWindowHelperElement;

    private MainWindowCameraConfiguration cameraConfiguration;

    public MainWindow(Coord pos, Coord size, Frame parent){
        super(pos, size, parent);
        getParent().getInput().getController().setActiveWindow(this);
        cameraConfiguration = new MainWindowCameraConfiguration(new Coord(getShift()), new Coord(size));
        gameWindowElement = new GameWindowElement(new Coord(0, 0), size, this);
        addWindowElement(gameWindowElement);
        gameWindowHelperElement = new GameWindowHelperElement(gameWindowElement);
        addWindowElement(gameWindowHelperElement);
        gameWindowHelperElement.renewState();
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

    public MainWindowCameraConfiguration getCameraConfiguration() {
        return cameraConfiguration;
    }



    public void moveGameWindow(Coord delta){
        cameraConfiguration.move(delta);
        gameWindowElement.setShapes();
        gameWindowHelperElement.setShapes();
    }

    @Override
    public boolean drag(Coord pos, Coord pressedPos, boolean dragBegin){
        boolean res = gameWindowHelperElement.drag(pos, pressedPos, dragBegin);
        if (res) return true;
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

    @Override
    public void scroll(double delta, Coord pos){
        cameraConfiguration.scroll(delta, pos);
        gameWindowHelperElement.setShapes();
        gameWindowElement.setShapes();
    }

    @Override
    public void draw(OpenGLBinder openGLBinder){
        Camera camera = getParent().getCamera();
        CameraConfiguration currentConfiguration = camera.getCurrentConfiguration();
        camera.takeCameraView(cameraConfiguration);
        gameWindowElement.draw(openGLBinder);
        gameWindowHelperElement.draw(openGLBinder);
        camera.takeCameraView(currentConfiguration);
    }
}
