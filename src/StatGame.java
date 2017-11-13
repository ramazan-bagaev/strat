import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;

import java.util.ArrayList;

public class StatGame extends BasicGame {

    int x = 0;
    int y = 0;

    GameEngine  gameEngine;
    SlickBinder slickBinder;
    MainWindow mainWindow;

    public StatGame(String title) {
        super(title);
        gameEngine = new GameEngine(40, 25);
        slickBinder = new SlickBinder();
        mainWindow = new MainWindow(1, 1, 1000, 1000, gameEngine, null);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {

    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        boolean left = gameContainer.getInput().isKeyDown(Input.KEY_A);
        boolean right = gameContainer.getInput().isKeyDown(Input.KEY_D);
        boolean up = gameContainer.getInput().isKeyDown(Input.KEY_W);
        boolean down = gameContainer.getInput().isKeyDown(Input.KEY_S);
        if (left) x -= 1;
        if (right) x += 1;
        if (up) y -= 1;
        if (down) y += 1;

        gameEngine.run();
    }

    @Override
    public void render(GameContainer gameContainer, Graphics g) throws SlickException {
        ArrayList<BasicShape> basicShapes = mainWindow.getShapes();
        for(BasicShape basicShape: basicShapes){
            slickBinder.draw(g, basicShape);
        }
        g.setColor(Color.gray);
        g.fill(new Rectangle(x,y,10,10));
        BasicShape shape = new StringShape(100, 100, "heyaaaaa", BasicShape.Color.White);
        slickBinder.draw(g, shape);
    }

    public static void main(String[] argv) {
        try {
            AppGameContainer container = new AppGameContainer(new StatGame("fist tests"));
            container.setDisplayMode(1000,1000,false);
            container.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}
