package Foundation;

public abstract class BasicShape {

    public enum Type{
        Rectangle, FilledRectangle, Character, Line, Triangle, Polygone
    }


    private Type type;
    private Color color;


    public BasicShape() {
        setType(Type.Rectangle);
        setColor(new Color(Color.Type.White));
    }

    public BasicShape(Type type, Color color){
        setType(type);
        setColor(color);
    }



    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public abstract void shift(Coord shift);
}
