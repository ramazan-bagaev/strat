package Foundation;

public class TriangleShape extends BasicShape {

    private Coord posA;
    private Coord posB;
    private Coord posC;

    public TriangleShape(Coord a, Coord b, Coord c, Color color){
        setColor(color);
        setType(Type.Triangle);
        posA = new Coord(a);
        posB = new Coord(b);
        posC = new Coord(c);
    }

    @Override
    public void shift(Coord shift) {
        posA = posA.add(shift);
        posB = posB.add(shift);
        posC = posC.add(shift);
    }

    public Coord getPosA() {
        return posA;
    }

    public Coord getPosB() {
        return posB;
    }

    public Coord getPosC() {
        return posC;
    }
}
