package Foundation.BasicShapes;

import Foundation.Color;
import Graphic.Mesh;
import Utils.Coord;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;

public class RectangleShape extends BasicShape {

    private Coord pos;
    private Coord size;

    private boolean boxes;
    private boolean filled;

    public RectangleShape(RectangleShape rectangleShape){
        super();
        setColor(new Color(rectangleShape.getColor()));
        setType(Type.Rectangle);
        pos = new Coord(rectangleShape.getPos());
        size = new Coord(rectangleShape.getSize());
        filled = rectangleShape.isFilled();
        boxes = rectangleShape.isBoxes();
    }

    public RectangleShape(Coord pos, Coord size, Color color){
        super();
        setColor(color);
        setType(Type.Rectangle);
        filled = true;
        this.pos = new Coord(pos);
        this.size = new Coord(size);
        boxes = true;
    }

    public RectangleShape(Coord pos, Coord size, Color color, boolean filled){
        super();
        setColor(color);
        setType(Type.Rectangle);
        this.filled = filled;
        this.pos = new Coord(pos);
        this.size = new Coord(size);
        boxes = true;
        //initMesh();
    }

    public RectangleShape(Coord pos, Coord size, Color color, boolean boxes, boolean filled){
        super();
        setColor(color);
        setType(Type.Rectangle);
        this.pos = new Coord(pos);
        this.size = new Coord(size);
        this.boxes = boxes;
        this.filled = filled;
      //  initMesh();
    }

    public void initMesh(){
        if (mesh != null){
            mesh.cleanUp();
        }
        double[] positions = new double[12];
        double[] color = new double[16];
        int[] indices = new int[6];

        positions[0] = pos.x;
        positions[1] = pos.y;
        positions[2] = pos.z;

        positions[3] = pos.x + size.x;
        positions[4] = pos.y;
        positions[5] = pos.z;

        positions[6] = pos.x;
        positions[7] = pos.y + size.y;
        positions[8] = pos.z;

        positions[9] = pos.x + size.x;
        positions[10] = pos.y + size.y;
        positions[11] = pos.z;

        for(int i = 0; i < 4; i++) {
            color[i] = this.color.r;
            color[i + 1] = this.color.g;
            color[i + 2] = this.color.b;
            color[i + 3] = this.color.a;
        }

        indices[0] = 0;
        indices[1] = 1;
        indices[2] = 2;
        indices[3] = 2;
        indices[4] = 1;
        indices[5] = 3;

        mesh = new Mesh(positions, color, indices, GL_TRIANGLES);
    }

    public Coord getPos() {
        return pos;
    }

    public void setPos(Coord pos) {
        this.pos = pos;
    }

    public Coord getSize() {
        return size;
    }

    public void setSize(Coord size) {
        this.size = size;
    }

    @Override
    public void shift(Coord shift) {
        pos = pos.add(shift);
    }

    @Override
    public void changeSize(double alpha) {
        pos = pos.multiply(alpha);
        size = size.multiply(alpha);
    }

    public boolean isBoxes() {
        return boxes;
    }

    public boolean isFilled() {
        return filled;
    }
}
