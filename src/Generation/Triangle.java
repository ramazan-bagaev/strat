package Generation;

import Foundation.Coord;

public class Triangle {

    /// not clock wise

    public Coord a;
    public Coord b;
    public Coord c;

    public Triangle(Coord a, Coord b, Coord c){
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public boolean inTrinangle(Coord point){
        Line x = new Line(a, b);
        Line y = new Line(b, c);
        Line z = new Line(c, a);
        if (x.relativePosition(point) != Line.RIGHT && y.relativePosition(point) != Line.RIGHT && z.relativePosition(point) != Line.RIGHT)
            return true;
        return false;
    }

    public Coord shareSide(Triangle triangle){
        int ashare = 0;
        int bshare = 0;
        int cshare = 0;

        if (a.equals(triangle.a)) ashare = 1;
        if (a.equals(triangle.b)) bshare = 1;
        if (a.equals(triangle.c)) cshare = 1;

        boolean nota = false;
        if (ashare + bshare + cshare == 1) nota = true;

        if (b.equals(triangle.a)) ashare = 1;
        if (b.equals(triangle.b)) bshare = 1;
        if (b.equals(triangle.c)) cshare = 1;

        if (ashare + bshare + cshare == 0) return null;
        if (ashare + bshare + cshare == 2) return c;

        if (b.equals(triangle.a)) ashare = 1;
        if (b.equals(triangle.b)) bshare = 1;
        if (b.equals(triangle.c)) cshare = 1;

        if (ashare + bshare + cshare == 2){
            if (nota) return b;
            return a;
        }

        return null;
    }


}
