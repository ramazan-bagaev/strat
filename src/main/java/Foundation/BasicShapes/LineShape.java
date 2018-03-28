package Foundation.BasicShapes;

import Foundation.Color;
import Graphic.Mesh;
import Utils.Coord;

import static org.lwjgl.opengl.GL11.GL_LINES;

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
       // initMesh();
    }

    public LineShape(Coord a, Coord b){
        super();
        setType(Type.Line);
        setColor(new Color(Color.Type.Black));
        posA = new Coord(a);
        posB = new Coord(b);
        width = standartWidth;
        //initMesh();
    }

    public LineShape(Coord a, Coord b, Color color){
        super();
        setType(Type.Line);
        setColor(color);
        posA = new Coord(a);
        posB = new Coord(b);
        width = standartWidth;
       // initMesh();
    }

    public LineShape(Coord a, Coord b, float width){
        super();
        setType(Type.Line);
        setColor(new Color(Color.Type.Black));
        posA = new Coord(a);
        posB = new Coord(b);
        this.width = width;
       // initMesh();
    }

    public LineShape(Coord a, Coord b, Color color, float width){
        super();
        setType(Type.Line);
        setColor(color);
        posA = new Coord(a);
        posB = new Coord(b);
        this.width = width;
        //initMesh();
    }

    public void initMesh(){
        if (mesh != null){
            mesh.cleanUp();
        }
        double[] pos = new double[6];
        double[] color = new double[8];
        int[] indices = new int[2];
        pos[0] = posA.x;
        pos[1] = posA.y;
        pos[2] = posA.z;
        pos[3] = posB.x;
        pos[4] = posB.y;
        pos[5] = posB.z;

        color[0] = this.color.r;
        color[1] = this.color.g;
        color[2] = this.color.b;
        color[3] = this.color.a;
        color[4] = this.color.r;
        color[5] = this.color.g;
        color[6] = this.color.b;
        color[7] = this.color.a;

        indices[0] = 0;
        indices[1] = 1;

        mesh = new Mesh(pos, color, indices, GL_LINES);
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
