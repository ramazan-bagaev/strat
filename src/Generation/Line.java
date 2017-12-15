package Generation;

import Foundation.Coord;

public class Line {

    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int INTERSECT = 2;

    public Coord a;
    public Coord b;

    public int alpha;
    public int beta;
    public int gama;

    public Line(Coord a, Coord b){
        this.a = a;
        this.b = b;

        alpha = b.x - a.x;
        beta = a.y - b.y;
        gama = - ( alpha * a.x + beta * b.y);
    }

    public int relativePosition(Coord point){
        int s = alpha * point.x + beta * point.y + gama;
        if (s > 0) return LEFT;
        if (s < 0) return RIGHT;
        if (s == 0) return INTERSECT;
        return 0;
    }

    public boolean isNotClockWise(Line line){
        if (alpha * line.beta - beta * line.alpha < 0) return true;
        return false;
    }

    public boolean intersect(Line line){
        int deter = alpha * line.beta - line.alpha * beta;
        if (deter == 0){
            if (a.equals(line.a) || a.equals(line.b) || b.equals(line.a) || b.equals(line.b)) return true;
            return false;
        }

        float t1 = (float)(alpha * (line.a.y - a.y) + beta * (line.a.x - a.x))/ (float)(deter);
        float t = (float)((line.a.x - a.x) + line.alpha * t1)/(float)alpha;
        //float t = (float)(beta * (line.a.x - a.x) + line.beta * (line.a.y - a.y)) / (float)(deter);
        //float t1 = (float)(line.alpha * (line.a.x - a.x) + alpha * (line.a.y - a.y))/ (float)(deter);
        System.out.println(t + " " + t1);
        float x = a.x + (b.x - a.x) * t;
        float y = a.y + (b.y - a.y) * t;
        System.out.println("x = " + x  + "; y = " + y);
        if (t >= 0 && t <= 1 && t1 >= 0 && t1 <= 1) return true;
        return false;
    }

    public static void main(String[] argv){
        Line line1 = new Line(new Coord(2, 7), new Coord(3, 5));
        Line line2 = new Line(new Coord(1, 1), new Coord(3, 5));
        System.out.print(line1.intersect(line2));
    }
}
