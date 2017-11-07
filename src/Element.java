import java.util.ArrayList;

public class Element {

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public enum Type{
        Rock, Tree, City, None
    }

    private Type type;
    private ArrayList<BasicShape> shapes;

    public Element(Type type){
        this.shapes = new ArrayList<BasicShape>();
        setType(type);
    }


    public ArrayList<BasicShape> getShapes() {
        return shapes;
    }

    public void setShapes(ArrayList<BasicShape> shapes) {
        this.shapes = shapes;
    }

    public void addShape(BasicShape shape){
        shapes.add(shape);
    }
}
