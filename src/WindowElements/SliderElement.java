package WindowElements;

import CharacterShape.Font;
import Foundation.*;

import java.util.ArrayList;

public class SliderElement extends WindowElement{

    private int begin;
    private int end;
    private boolean horizontal;
    private RectangleShape slider;
    private RectangleShape scale;
    private Coord pressedPos;

    public SliderElement(Coord pos, Coord size, boolean horizontal, int begin, int end, Window parent) {
        super(pos, size, parent);
        this.begin = begin;
        this.end = end;
        this.horizontal = horizontal;
        addSliderParts();
    }

    public SliderElement(Coord pos, Coord size, boolean horizontal, int begin, int end, WindowElementGroup groupParent, Window parent) {
        super(pos, size, groupParent, parent);
        this.begin = begin;
        this.end = end;
        this.horizontal = horizontal;
        addSliderParts();
    }

    public void addSliderParts(){
        Coord pos = getPos();
        Coord size = getSize();
        Font font = getParent().getFont("latin");
        if (horizontal) {
            RectangleShape scale = new RectangleShape(pos.add(new Coord(0, size.y / 2 - 2)), new Coord(size.x, 4), BasicShape.Color.Black, true);
            RectangleShape slider = new RectangleShape(pos.add(new Coord(0, size.y / 2 - 5)), new Coord(10, 20), BasicShape.Color.LightGray, true);
            StringShape minVal = new StringShape(pos.add(new Coord(0, size.y/2 - 20)), new Coord(50, 10), String.valueOf(begin),
                    BasicShape.Color.Black, font);
            StringShape maxVal = new StringShape(pos.add(new Coord( size.x - 50,size.y/2 - 20)), new Coord(50, 10), String.valueOf(end),
                    BasicShape.Color.Black, font);
            ArrayList<BasicShape> basicShapes = getBasicShapes();
            basicShapes.addAll(minVal.getBasicShapes());
            basicShapes.addAll(maxVal.getBasicShapes());
            basicShapes.add(scale);
            basicShapes.add(slider);
            this.slider = slider;
            this.scale = scale;
        }
    }

    @Override
    public void drag(Coord point){
        if (pressedPos == null){
            pressedPos = point;
            return;
        }
        if (!point.inRectangle(getPos(), getSize())) return;
        if (horizontal) {
            int minX = scale.getPos().x;
            int maxX = minX + scale.getSize().x + slider.getSize().x;
            Coord delta = new Coord(point.x - slider.getPos().x - slider.getSize().x/2, 0);
            Coord newPos = new Coord(slider.getPos().add(delta));
            if (newPos.x > maxX || newPos.x < minX) return;
            slider.setPos(newPos);
            pressedPos = point;
        }
    }

    @Override
    public void click(Coord point) {
        if (!point.inRectangle(slider.getPos(), slider.getSize())) return;
        pressedPos = point;
    }

    public int getValue(){
        return (int)(((float)(end - begin))/(float)(getSize().x - slider.getSize().x) * (slider.getPos().x - getPos().x));
    }
}
