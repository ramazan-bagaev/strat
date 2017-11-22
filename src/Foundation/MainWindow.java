package Foundation;

import Windows.FieldInfoWindow;

public class MainWindow extends Window{

    private GameWindowElement gameWindowElement;

    public MainWindow(Coord pos, Coord size, GameEngine gameEngine, Windows parent){
        super(pos, size, parent);
        gameWindowElement = new GameWindowElement(pos, size, this, gameEngine);
        addWindowElement(gameWindowElement);
    }

    public GameWindowElement getGameWindowElement() {
        return gameWindowElement;
    }

    public void setGameWindowElement(GameWindowElement gameWindowElement) {
        this.gameWindowElement = gameWindowElement;
    }

    @Override
    public void click(Coord point){
        gameWindowElement.click(point);
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
}
