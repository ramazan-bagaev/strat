package Foundation;

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
}
