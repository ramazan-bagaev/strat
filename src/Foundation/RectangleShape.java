package Foundation;

import Foundation.BasicShape;

public class RectangleShape extends BasicShape {

    private Coord pos;
    private Coord size;

    private boolean boxes;
    private boolean filled;

    public RectangleShape(RectangleShape rectangleShape){
        super();
        setColor(new Color(rectangleShape.getColor()));
        setType(Type.Rectangle);
        setPos(new Coord(rectangleShape.getPos()));
        setSize(new Coord(rectangleShape.getSize()));
        filled = rectangleShape.isFilled();
        boxes = rectangleShape.isBoxes();
    }

    public RectangleShape(Coord pos, Coord size, Color color){
        super();
        setColor(color);
        setType(Type.Rectangle);
        filled = true;
        setPos(pos);
        setSize(size);
        boxes = true;
    }

    public RectangleShape(Coord pos, Coord size, Color color, boolean filled){
        super();
        setColor(color);
        setType(Type.Rectangle);
        this.filled = filled;
        setPos(pos);
        setSize(size);
        boxes = true;
    }

    public RectangleShape(Coord pos, Coord size, Color color, boolean boxes, boolean filled){
        super();
        setColor(color);
        setType(Type.Rectangle);
        setPos(pos);
        setSize(size);
        this.boxes = boxes;
        this.filled = filled;
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
