import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Rectangle;

public class SlickBinder{

    public SlickBinder(){

    }

    public Rectangle getRectangle(RectangleShape rectangleShape){
        Rectangle rectangle = new Rectangle(rectangleShape.getX(), rectangleShape.getY(), rectangleShape.getWidth(), rectangleShape.getHeight());
        return rectangle;
    }

    public Color getColor(RectangleShape rectangleShape){
        switch(rectangleShape.getColor()){
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
