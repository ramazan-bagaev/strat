package Utils;

public class Coord {

    public double x;
    public double y;
    public double z;

    public Coord(){
        this.x = 0;
        this.y = 0;
        this.y = 0;
    }

    public Coord(Coord pos){
        this.x = pos.x;
        this.y = pos.y;
        this.z = pos.z;
    }

    public Coord(double x, double y){
        this.x = x;
        this.y = y;
        this.z = 0;
    }

    public Coord(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Coord add(Coord pos){
        return new Coord(this.x + pos.x, this.y + pos.y, this.z + pos.z);
    }

    public Coord multiply(double alpha){
        return new Coord(x*alpha, y*alpha,z*alpha);
    }

    public boolean inRectangle(Coord pos, Coord size){
        return ((pos.x < x) && ((pos.x + size.x) > x) && (pos.y < y) && ((pos.y + size.y) > y));

    }

    public void print(String string){
        System.out.println(string + ": x = " + x + "; y = " + y);
    }
}
