package Utils.Geometry;

import java.util.ArrayList;
import java.util.Random;

public class Index{

    public int x;
    public int y;



    public enum Direction{
        Up, Down, Right, Left, None
    }

    public Index(Coord pos){
        this.x = (int)pos.x;
        this.y = (int)pos.y;
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

    public double norm(){
        return Math.sqrt(x*x + y*y);
    }

    public boolean inRectangle(Index pos, Index size){
        return ((pos.x <= x) && ((pos.x + size.x) > x) && (pos.y <= y) && ((pos.y + size.y) > y));

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

    public static ArrayList<Direction> getAllDirectionsRandom(Random random){
        ArrayList<Direction> directions = Index.getAllDirections();
        while(true){
            if (random.nextInt(4) == 0) break;
            Direction dir = directions.get(random.nextInt(4));
            directions.remove(dir);
            directions.add(dir);
        }
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

    public static Direction opposite(Index.Direction direction){
        switch (direction){

            case Up:
                return Direction.Down;
            case Down:
                return Direction.Up;
            case Right:
                return Direction.Left;
            case Left:
                return Direction.Right;
            case None:
                break;
        }
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

    public Coord toCoord(){
        Coord result = new Coord();
        result.x = this.x;
        result.y = this.y;
        return result;
    }

    public int scaralMult(Index vec){
        return x*vec.x + y*vec.y;
    }

    public int vectorMult(Index vec){
        return x*vec.y - y*vec.x;
    }

    public static boolean onLeft(Index dir1, Index dir2){
        return (dir1.x * dir2.y - dir1.y * dir2.x <= 0);
    }

    public static Direction turnRight(Direction direction){
        switch (direction){

            case Up:
                return Direction.Right;
            case Down:
                return Direction.Left;
            case Right:
                return Direction.Down;
            case Left:
                return Direction.Up;
            case None:
                return Direction.None;
        }
        return Direction.None;
    }

    public static boolean isVertical(Direction direction){
        switch (direction){

            case Up:
            case Down:
                return true;
            case Right:
            case Left:
                return false;
            case None:
                break;
        }
        return false;
    }

    public static Index getUnitIndex(Direction direction){
        switch (direction){

            case Up:
                return new Index(0, -1);
            case Down:
                return new Index(0, 1);
            case Right:
                return new Index(1, 0);
            case Left:
                return new Index(-1, 0);
            case None:
                return new Index(0, 0);
        }
        return null;
    }

    public double projection(Index index){
        return (double)(this.scaralMult(index)) / (norm());
    }
}
