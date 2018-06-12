package Utils.Geometry;

public class Triangle extends GeometryShape {

    public Index posA;
    public Index posB;
    public Index posC;

    public Triangle(Index posA, Index posB, Index posC){
        this.posA = posA;
        this.posB = posB;
        this.posC = posC;
    }

}
