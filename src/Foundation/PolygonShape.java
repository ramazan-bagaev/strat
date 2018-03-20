package Foundation;

import java.util.ArrayList;

public class PolygonShape extends BasicShape {

    private ArrayList<Coord> verteces;

    public PolygonShape(ArrayList<Coord> verteces, Color color){
        setColor(color);
        setType(Type.Polygon);
        this.verteces = verteces;
    }

    @Override
    public void shift(Coord shift) {
        for(Coord vertix: verteces){
            vertix = vertix.add(shift);
        }

    }

    @Override
    public void changeSize(double alpha) {

    }

    public ArrayList<Coord> getVerteces() {
        return verteces;
    }
}
