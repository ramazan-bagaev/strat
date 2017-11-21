package Foundation;

import Foundation.BasicShape;

public class RectangleShape extends BasicShape {

    private Coord pos;
    private Coord size;

    public RectangleShape(Coord pos, Coord size, BasicShape.Color color){
        super();
        setColor(color);
        setType(Type.FilledRectangle);
        setPos(pos);
        setSize(size);
    }

    public RectangleShape(Coord pos, Coord size, BasicShape.Color color, boolean filled){
        super();
        setColor(color);
        if (filled) setType(Type.FilledRectangle);
        else setType(Type.Rectangle);
        setPos(pos);
        setSize(size);
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
}
