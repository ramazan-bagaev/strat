package Foundation;

public class Coord {

    public int x;
    public int y;

    public enum Direction{
        Up, Down, Right, Left, None
    }

    public Coord(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Coord(Coord coord) {
        this.x = coord.x;
        this.y = coord.y;
    }

    public Coord add(Coord other){
        return new Coord(this.x + other.x, this.y + other.y);
    }

    public boolean inRectangle(Coord pos, Coord size){
        return ((pos.x < x) && ((pos.x + size.x) > x) && (pos.y < y) && ((pos.y + size.y) > y));

    }

    public boolean isNeighbor(Coord point){
        if (Math.abs(x - point.x) + Math.abs(y - point.y) == 1) return true;
        return false;
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
        if (getClass() != obj.getClass()) return false;
        Coord other = (Coord)obj;
        if (x != other.x) return false;
        if (y != other.y) return false;
        return true;
    }

    public Direction whatDirection(Coord other){
        if (other.y - y > 0) return Direction.Down;
        if (other.y - y < 0) return Direction.Up;
        if (other.x - x > 0) return Direction.Right;
        if (other.x - x < 0) return Direction.Left;
        return Direction.None;
    }
}
