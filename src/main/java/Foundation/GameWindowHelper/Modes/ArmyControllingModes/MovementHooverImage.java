package Foundation.GameWindowHelper.Modes.ArmyControllingModes;

import Foundation.BasicShapes.BasicShape;
import Images.ArrowImage;
import Images.Image;
import Utils.Geometry.Coord;
import Utils.Geometry.Index;

import java.util.ArrayList;

public class MovementHooverImage extends Image {

    public MovementHooverImage(Coord size){
        super(new Coord(), size, null);

        setShapes();
    }

    public void setShapes(){
        ArrayList<BasicShape> basicShapes = new ArrayList<>();
        basicShapes.addAll(new ArrowImage(new Coord(0, size.y/3), new Coord(size.x/3, size.y/3), Index.Direction.Right, null).getCopyOfBasicShapesWithoutShift());
        basicShapes.addAll(new ArrowImage(new Coord(2*size.x/3, size.y/3), new Coord(size.x/3, size.y/3), Index.Direction.Left, null).getCopyOfBasicShapesWithoutShift());
        basicShapes.addAll(new ArrowImage(new Coord(size.x/3, 2*size.y/3), new Coord(size.x/3, size.y/3), Index.Direction.Up, null).getCopyOfBasicShapesWithoutShift());
        basicShapes.addAll(new ArrowImage(new Coord(size.x/3, 0), new Coord(size.x/3, size.y/3), Index.Direction.Down, null).getCopyOfBasicShapesWithoutShift());

        setBasicShapes(basicShapes);
    }
}
