package Utils;

import java.util.ArrayList;

public class Index {

    public int x;
    public int y;

    public enum Direction{
        Up, Down, Right, Left, None
    }

    public Index(Direction direction){
        switch (direction){

            case Up:
                x = 0;
                y = -1;
                break;
            case Down:
                x = 0;
                y = 1;
                break;
            case Right:
                x = 1;
                y = 0;
                break;
            case Left:
                x = -1;
                y = 0;
                break;
            case None:
                x = 0;
                y = 0;
                break;
        }
    }

    public Index(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Index(Index index) {
        this.x = index.x;
        this.y = index.y;
    }

    public Index add(Index other){
        return new Index(this.x + other.x, this.y + other.y);
    }

    public Index minus(Index other){ return new Index(this.x - other.x, this.y - other.y); }

    public boolean inRectangle(Index pos, Index size){
        return ((pos.x < x) && ((pos.x + size.x) > x) && (pos.y < y) && ((pos.y + size.y) > y));

    }

    public boolean isNeighbor(Index point){
        if (Math.abs(x - point.x) + Math.abs(y - point.y) == 1) return true;
        return false;
    }

    public static ArrayList<Direction> getAllDirections(){
        ArrayList<Direction> directions = new ArrayList<>();
        directions.add(Direction.Up);
        directions.add(Direction.Down);
        directions.add(Direction.Right);
        directions.add(Direction.Left);
        return directions;
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
        Index other = (Index)obj;
        if (x != other.x) return false;
        if (y != other.y) return false;
        return true;
    }

    public Direction whatDirection(Index other){
        if (other.y - y > 0) return Direction.Down;
        if (other.y - y < 0) return Direction.Up;
        if (other.x - x > 0) return Direction.Right;
        if (other.x - x < 0) return Direction.Left;
        return Direction.None;
    }

    public Index multiply(double alpha){
        Index res = new Index(0, 0);
        res.x = (int) (x*alpha);
        res.y = (int) (y*alpha);
        return res;
    }

    public double distance(Index other){
        return Math.sqrt(  Math.pow(other.x - x, 2) + Math.pow(other.y - y, 2));
    }
}
