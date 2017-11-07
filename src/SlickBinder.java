import com.sun.org.apache.regexp.internal.RE;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class SlickBinder{

    public SlickBinder(){

    }

    public void draw(Graphics graphics, BasicShape basicShape){
        BasicShape.Type type = basicShape.getType();
        switch (type){
            case Rectangle: {
                RectangleShape rect = (RectangleShape)basicShape;
                graphics.setColor(getColor(basicShape));
                graphics.draw(getRectangle(rect));
                break;
            }
            case FilledRectangle: {
                RectangleShape rect = (RectangleShape)basicShape;
                Rectangle slickRect = getRectangle(rect);
                graphics.setColor(getColor(basicShape));
                graphics.fill(slickRect);
                graphics.setColor(Color.black);
                graphics.draw(slickRect);
                break;
            }
            case String:{
                StringShape str = (StringShape)basicShape;
                graphics.setColor(getColor(basicShape));
                graphics.drawString(str.getText(), str.getX(), str.getY());
                break;
            }
        }
    }

    public Rectangle getRectangle(RectangleShape rectangleShape){
        Rectangle rectangle = new Rectangle(rectangleShape.getX(), rectangleShape.getY(), rectangleShape.getWidth(), rectangleShape.getHeight());
        return rectangle;
    }

    public Color getColor(BasicShape shape){
        switch(shape.getColor()){
            case Black:
                return Color.black;
            case Green:
                return Color.green;
            case Blue:
                return Color.blue;
            case Red:
                return Color.red;
            case White:
                return Color.white;
            case Yellow:
                return Color.yellow;
            case Green2:
                return Color.cyan;
            case Gray:
                return Color.gray;
            case LightGray:
                return Color.lightGray;
            default:
                return Color.white;
        }
    }

    public Rectangle getRectangle(Field field){
        RectangleShape rectangleShape = field.getRectangleShape();
        return getRectangle(rectangleShape);
    }

    public Color getColor(Field field){
        RectangleShape rectangleShape = field.getRectangleShape();
        return getColor(rectangleShape);
    }
}
