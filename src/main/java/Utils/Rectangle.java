package Utils;

import Utils.Geometry.Index;

public class Rectangle {

    protected Index pos;
    protected Index size;

    public Rectangle(Index pos, Index size){
        this.pos = pos;
        this.size = size;
    }

    public Rectangle(Rectangle origin){
        this.pos = new Index(origin.pos);
        this.size = new Index(origin.size);
    }

    public boolean contains(Index index) {
        if (index.x < pos.x || index.x >= pos.x + size.x) return false;
        if (index.y < pos.y || index.y >= pos.y + size.y) return false;
        return true;
    }

    public boolean contains(Rectangle other){
        return (this.pos.x <= other.pos.x && this.pos.y <= other.pos.y &&
                this.pos.x + this.size.x >= other.pos.x + other.size.x &&
                this.pos.y + this.size.y >= other.pos.y + other.size.y);
    }

    public boolean isIntersects(Rectangle rectangle){
        Index a1 = this.pos;
        Index a2 = this.pos.add(this.size).minus(new Index(1, 1));
        Index b1 = rectangle.pos;
        Index b2 = rectangle.pos.add(rectangle.size).minus(new Index(1, 1));
        if (a1.x > b2.x) return false;
        if (a2.x < b1.x) return false;
        if (a1.y > b2.y) return false;
        if (a2.y < b1.y) return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + pos.x;
        result = prime * result + pos.y;
        result = prime * result + size.x;
        result = prime * result + size.y;
        return result;
    }

    @Override
    public boolean equals(Object object){
        if (this == object) return true;
        if (object == null) return false;
        if (object.getClass() != getClass()) return false;
        Rectangle rectangle = (Rectangle)object;
        return (rectangle.pos.equals(pos) && rectangle.size.equals(size));

    }

}
