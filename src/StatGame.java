import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;

import java.util.ArrayList;

public class StatGame extends BasicGame {

    int x = 0;
    int y = 0;

    GameEngine  gameEngine;
    SlickBinder slickBinder;

    public StatGame(String title) {
        super(title);
        gameEngine = new GameEngine(40, 25);
        slickBinder = new SlickBinder();
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
    }

    @Override
    public void render(GameContainer gameContainer, Graphics g) throws SlickException {

        ArrayList<Field> fields = gameEngine.getFields();
        for(Field field: fields){
            Rectangle fieldBackground = slickBinder.getRectangle(field);
            Color fieldColor = slickBinder.getColor(field);
            g.setColor(fieldColor);
            g.fill(fieldBackground);
            g.setColor(Color.black);
            g.draw(fieldBackground);

            Element element = field.getElement();
            ArrayList<BasicShape> shapes;
            if (element != null) shapes = element.getShapes();
            else shapes = new ArrayList<>();
            for(BasicShape shape: shapes){
                BasicShape.Type type = shape.getType();

                switch (type){
                    case Rectangle: {
                        RectangleShape rect = (RectangleShape)shape;
                        Rectangle slickRect = slickBinder.getRectangle(rect);
                        Color color = slickBinder.getColor(rect);
                        g.setColor(color);
                        g.fill(slickRect);
                        g.setColor(Color.black);
                        g.draw(slickRect);
                        break;
                    }
                    case None:
                        break;
                }
            }
        }
        g.setColor(Color.gray);
        g.fill(new Rectangle(x,y,10,10));
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
