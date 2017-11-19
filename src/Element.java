import java.util.ArrayList;

public class Element {

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setResourceCause(ResourceCause resourceCause) {
        this.resourceCause = resourceCause;
    }

    public ResourceCause getResourceCause(){
        return resourceCause;
    }

    public Field getParent() {
        return parent;
    }

    public void setParent(Field parent) {
        this.parent = parent;
    }

    public enum Type{
        Rock, Tree, City, Ground
    }

    private ResourceCause resourceCause;
    private Type type;
    private Field parent;

    private ArrayList<BasicShape> shapes;

    public Element(Type type, Field parent){
        this.shapes = new ArrayList<BasicShape>();
        setType(type);
        setParent(parent);
    }

    public void run(){

    };

    public void renewResourceCause(){
        resourceCause.renew();
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
