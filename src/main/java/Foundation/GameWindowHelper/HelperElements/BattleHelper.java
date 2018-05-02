package Foundation.GameWindowHelper.HelperElements;

import Foundation.Army.Battle;
import Foundation.Army.Combatant;
import Foundation.BasicShapes.BasicShape;
import Foundation.BasicShapes.LineShape;
import Foundation.BasicShapes.RectangleShape;
import Foundation.BasicShapes.TriangleShape;
import Foundation.Color;
import Foundation.GameWindowHelper.HelperField;
import Utils.Coord;
import Utils.Index;

import java.util.ArrayList;

public class BattleHelper extends HelperElement{

    private Index.Direction direction;
    private Combatant.State state;

    public BattleHelper(HelperField helperField, Combatant.State state, Index.Direction direction) {
        super(helperField);
        this.direction = direction;
        this.state = state;
        setShapes();
    }

    public void setShapes(){
        clearBasicShapes();
        switch (state){

            case Attack:
                addAttackShapes();
                break;
            case Defend:
                addDefendShapes();
                break;
            default:
                break;
        }
    }

    public void addAttackShapes(){
        RectangleShape rect;
        TriangleShape tr;
        switch (direction){

            case Up:
                rect = new RectangleShape(new Coord(size.x/3, size.y/4), new Coord(size.x/3, size.y/4),
                        new Color(Color.Type.Red), false, true);
                tr = new TriangleShape(new Coord(size.x/2, 0), new Coord(size.x/6, size.y/4),
                        new Coord(5*size.x/6, size.y/4), new Color(Color.Type.Red));
                addShape(rect);
                addShape(tr);
                break;
            case Down:
                rect = new RectangleShape(new Coord(size.x/3, size.y/2), new Coord(size.x/3, size.y/4),
                        new Color(Color.Type.Red), false, true);
                tr = new TriangleShape(new Coord(size.x/6, 3*size.y/4), new Coord(size.x/2, size.y),
                        new Coord(5*size.x/6, 3*size.y/4), new Color(Color.Type.Red));
                addShape(rect);
                addShape(tr);
                break;
            case Right:
                rect = new RectangleShape(new Coord(size.x/2, size.y/3), new Coord(size.x/4, size.y/3),
                        new Color(Color.Type.Red), false, true);
                tr = new TriangleShape(new Coord(3*size.x/4, size.y/6), new Coord(size.x, size.y/2),
                        new Coord(3*size.x/4, 5*size.y/6), new Color(Color.Type.Red));
                addShape(rect);
                addShape(tr);
                break;
            case Left:
                rect = new RectangleShape(new Coord(size.x/4, size.y/3), new Coord(size.x/4, size.y/3),
                        new Color(Color.Type.Red), false, true);
                tr = new TriangleShape(new Coord(0, size.y/2), new Coord(size.x/4, size.y/6),
                        new Coord(size.x/4, 5*size.y/6), new Color(Color.Type.Red));
                addShape(rect);
                addShape(tr);
                break;
            case None:
                break;
        }
    }

    public void addDefendShapes(){
        LineShape line;
        switch (direction){

            case Up:
                line = new LineShape(new Coord(size.x/4, size.y/4), new Coord(3*size.x/4, size.y/4));
                addShape(line);
                line = new LineShape(new Coord(size.x/4, size.y/4), new Coord(size.x/4, 2*size.y/4));
                addShape(line);
                line = new LineShape(new Coord(3*size.x/4, size.y/4), new Coord(3*size.x/4, 2*size.y/4));
                addShape(line);
                break;
            case Down:
                line = new LineShape(new Coord(size.x/4, 3*size.y/4), new Coord(3*size.x/4, 3*size.y/4));
                addShape(line);
                line = new LineShape(new Coord(size.x/4, 3*size.y/4), new Coord(size.x/4, 2*size.y/4));
                addShape(line);
                line = new LineShape(new Coord(3*size.x/4, 3*size.y/4), new Coord(3*size.x/4, 2*size.y/4));
                addShape(line);
                break;
            case Right:
                line = new LineShape(new Coord(3*size.x/4, size.y/4), new Coord(3*size.x/4, 3*size.y/4));
                addShape(line);
                line = new LineShape(new Coord(3*size.x/4, size.y/4), new Coord(2*size.x/4, size.y/4));
                addShape(line);
                line = new LineShape(new Coord(3*size.x/4, 3*size.y/4), new Coord(2*size.x/4, 3*size.y/4));
                addShape(line);
                break;
            case Left:
                line = new LineShape(new Coord(size.x/4, size.y/4), new Coord(size.x/4, 3*size.y/4));
                addShape(line);
                line = new LineShape(new Coord(size.x/4, size.y/4), new Coord(2*size.x/4, size.y/4));
                addShape(line);
                line = new LineShape(new Coord(size.x/4, 3*size.y/4), new Coord(2*size.x/4, 3*size.y/4));
                addShape(line);
                break;
            case None:
                break;
        }
    }

}
