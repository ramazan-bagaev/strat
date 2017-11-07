public class MainWindow extends Window{

    private GameWindowElement gameWindowElement;

    public MainWindow(int x, int y, int sizeX, int sizeY, GameEngine gameEngine, Window parent){
        super(x, y, sizeX, sizeY, parent);
        gameWindowElement = new GameWindowElement(x, y, sizeX, sizeY, this, gameEngine);
        addWindowElement(gameWindowElement);
    }

    public GameWindowElement getGameWindowElement() {
        return gameWindowElement;
    }

    public void setGameWindowElement(GameWindowElement gameWindowElement) {
        this.gameWindowElement = gameWindowElement;
    }
}
