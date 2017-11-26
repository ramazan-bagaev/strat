package Foundation;

public class GameWindowHelperElement extends WindowElement {

    private GameWindowElement gameWindowElement;

    public GameWindowHelperElement(GameWindowElement gameWindowElement){
        super(gameWindowElement.getPos(), gameWindowElement.getSize(), gameWindowElement.getParent());
        setGameWindowElement(gameWindowElement);
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
}
