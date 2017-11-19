import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;

import java.util.ArrayList;

public class StatGame extends BasicGame {

    int x = 0;
    int y = 0;

    GameEngine  gameEngine;
    SlickBinder slickBinder;

    MainWindow mainWindow;
    Windows windows;

    static Image bigTree;
    static Graphics gra;

    public StatGame(String title) {
        super(title);
        gameEngine = new GameEngine(20, 50);
        slickBinder = new SlickBinder();
        mainWindow = new MainWindow(new Coord(1,1), new Coord(1000, 1000), gameEngine, null);
        //windows = new Windows(mainWindow);
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
        if (left) x -= 2;
        if (right) x += 2;
        if (up) y -= 2;
        if (down) y += 2;

        gameEngine.run();
    }

    @Override
    public void render(GameContainer gameContainer, Graphics g) throws SlickException {
        ArrayList<BasicShape> basicShapes = mainWindow.getShapes();
        for(BasicShape basicShape: basicShapes){
            slickBinder.draw(g, basicShape);
        }

        RectangleShape shape = new RectangleShape(x, y, 30, 30, BasicShape.Color.Gray);
        slickBinder.draw(g, shape);

        /*Image image = new Image(256,256);
        Graphics graphics = image.getGraphics();
        graphics.setBackground(new Color(0,0,0,0));
        graphics.clear();
        graphics.setColor(Color.green);
        graphics.drawRect(10, 10, 20 , 20);
        graphics.flush();*/
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
