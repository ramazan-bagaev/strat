package Foundation;

import Foundation.BasicShape;

public class RectangleShape extends BasicShape {

    private Coord pos;
    private Coord size;

    private boolean boxes;

    public RectangleShape(Coord pos, Coord size, Color color){
        super();
        setColor(color);
        setType(Type.FilledRectangle);
        setPos(pos);
        setSize(size);
        boxes = true;
    }

    public RectangleShape(Coord pos, Coord size, Color color, boolean filled){
        super();
        setColor(color);
        if (filled) setType(Type.FilledRectangle);
        else setType(Type.Rectangle);
        setPos(pos);
        setSize(size);
        boxes = true;
    }

    public RectangleShape(Coord pos, Coord size, Color color, boolean boxes, boolean filled){
        super();
        setColor(color);
        if (filled) setType(Type.FilledRectangle);
        else setType(Type.Rectangle);
        setPos(pos);
        setSize(size);
        this.boxes = boxes;
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

    public boolean isBoxes() {
        return boxes;
    }
}
