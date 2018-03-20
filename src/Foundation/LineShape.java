package Foundation;

import javax.sound.sampled.Line;

public class LineShape extends BasicShape {


    private Coord posA;
    private Coord posB;

    private float width;

    private final float standartWidth = 1.5f;


    public LineShape(LineShape lineShape){
        this.posA = new Coord(lineShape.getPosA());
        this.posB = new Coord(lineShape.getPosB());
        this.width = lineShape.getWidth();
        setColor(new Color(lineShape.getColor()));
        setType(Type.Line);
    }

    public LineShape(Coord a, Coord b){
        super();
        setType(Type.Line);
        setColor(new Color(Color.Type.Black));
        posA = new Coord(a);
        posB = new Coord(b);
        width = standartWidth;
    }

    public LineShape(Coord a, Coord b, Color color){
        super();
        setType(Type.Line);
        setColor(color);
        posA = new Coord(a);
        posB = new Coord(b);
        width = standartWidth;
    }

    public LineShape(Coord a, Coord b, float width){
        super();
        setType(Type.Line);
        setColor(new Color(Color.Type.Black));
        posA = new Coord(a);
        posB = new Coord(b);
        this.width = width;
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

    @Override
    public void changeSize(double alpha) {
        posA = posA.multiply(alpha);
        posB = posB.multiply(alpha);
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
