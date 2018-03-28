package Foundation.BasicShapes;

import Foundation.Color;
import Utils.Coord;

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

    public TriangleShape(TriangleShape triangleShape){
        setColor(new Color(triangleShape.getColor()));
        setType(Type.Triangle);
        posA = new Coord(triangleShape.getPosA());
        posB = new Coord(triangleShape.getPosB());
        posC = new Coord(triangleShape.getPosC());
    }

    public void initMesh(){
        if (mesh != null){
            mesh.cleanUp();
        }
        double[] positions = new double[12];
        double[] color = new double[16];
        int[] indices = new int[6];

        positions[0] = posA.x;
        positions[1] = posA.y;
        positions[2] = posA.z;

        positions[3] = posB.x;
        positions[4] = posB.y;
        positions[5] = posB.z;

        positions[6] = posC.x;
        positions[7] = posC.y;
        positions[8] = posC.z;
    }

    @Override
    public void shift(Coord shift) {
        posA = posA.add(shift);
        posB = posB.add(shift);
        posC = posC.add(shift);
    }

    @Override
    public void changeSize(double alpha) {
        posA = posA.multiply(alpha);
        posB = posB.multiply(alpha);
        posC = posC.multiply(alpha);
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
