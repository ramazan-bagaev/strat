package Foundation.BasicShapes;

import Foundation.BasicShapes.CharacterShape.CharacterShape;
import Foundation.Color;
import Foundation.OpenGLBinder;
import Graphic.Mesh;
import Utils.Coord;
import org.joml.Matrix4f;
import org.joml.Vector3d;
import org.joml.Vector3f;

public abstract class BasicShape {

    public enum Type{
        Rectangle, Character, Line, Triangle, Polygon
    }


    private Type type;
    protected Color color;

    protected Mesh mesh;

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

    public static BasicShape getCopy(BasicShape basicShape){
        switch (basicShape.getType()){

            case Rectangle:
                RectangleShape rec = (RectangleShape)basicShape;
                RectangleShape copyRec = new RectangleShape(rec);
                return copyRec;
            case Character:
                CharacterShape ch = (CharacterShape)basicShape;
                CharacterShape copyCh = new CharacterShape(ch);
                return copyCh;
            case Line:
                LineShape ln = (LineShape)basicShape;
                LineShape copyLn = new LineShape(ln);
                return copyLn;
            case Triangle:
                TriangleShape tr = (TriangleShape)basicShape;
                TriangleShape copyTr = new TriangleShape(tr);
                return copyTr;
            case Polygon:
                return null;
        }
        return null;
    }

    public abstract void changeSize(double alpha);


    public Mesh getMesh() {
        return mesh;
    }
}
