package Foundation;

public class LineShape extends BasicShape {


    private Coord posA;
    private Coord posB;

    private float width;

    private final float standartWidth = 1.5f;

    public LineShape(Coord a, Coord b, Color color){
        super();
        setType(Type.Line);
        setColor(color);
        posA = new Coord(a);
        posB = new Coord(b);
        width = standartWidth;
    }

    public LineShape(Coord a, Coord b, Color color, float width){
        super();
        setType(Type.Line);
        setColor(color);
        posA = new Coord(a);
        posB = new Coord(b);
        this.width = width;
    }

    @Override
    public void shift(Coord shift) {
        posA = posA.add(shift);
        posB = posB.add(shift);
    }

    public float getWidth() {
        return width;
    }

    public Coord getPosA() {
        return posA;
    }

    public Coord getPosB() {
        return posB;
    }
}
