public class StringShape extends BasicShape{

    private String text;
    private int x;
    private int y;

    public StringShape(int x, int y, String text, BasicShape.Color color){
        super(Type.String, color);
        setX(x);
        setY(y);
        setText(text);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
