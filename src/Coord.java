public class Coord {

    public int x;
    public int y;

    public Coord(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Coord add(Coord other){
        Coord ret = new Coord(this.x + other.x, this.y + other.y);
        return ret;
    }

    public boolean inRectangle(Coord pos, Coord size){
        return (pos.x < x && pos.x + size.x > x && pos.y < y && pos.y + size.y < y);

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() == obj.getClass()) return false;
        Coord other = (Coord)obj;
        if (x != other.x) return false;
        if (y != other.y) return false;
        return true;
    }
}
