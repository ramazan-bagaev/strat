import java.util.ArrayList;

public class Element {

    private ArrayList<BasicShape> shapes;

    public Element(){
        this.shapes = new ArrayList<BasicShape>();
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
