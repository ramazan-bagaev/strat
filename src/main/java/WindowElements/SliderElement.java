package WindowElements;

import Foundation.BasicShapes.CharacterShape.Font;
import Foundation.*;
import Foundation.BasicShapes.RectangleShape;
import Foundation.BasicShapes.StringShape;
import Utils.Broadcaster;
import Utils.Geometry.Coord;

public class SliderElement extends WindowElement{

    private int begin;
    private int end;
    private boolean horizontal;
    private Coord sliderPos;
    private Coord sliderSize;
    private Coord scalePos;
    private Coord scaleSize;
    private Coord pressedPos;

    public SliderElement(Coord pos, Coord size, boolean horizontal, int begin, int end, Window parent) {
        super(pos, size, parent);
        this.begin = begin;
        this.end = end;
        this.horizontal = horizontal;
        setSliderParts(new Coord(0, size.y/2 - 5));
    }

    public SliderElement(Coord pos, Coord size, boolean horizontal, int begin, int end, WindowElementGroup groupParent, Window parent) {
        super(pos, size, groupParent, parent);
        this.begin = begin;
        this.end = end;
        this.horizontal = horizontal;
        setSliderParts(new Coord(0,size.y / 2 - 5 ));
    }

    public void setSliderParts(Coord slPos){
        Coord size = getSize();
        Font font = getParent().getFont("latin");
        sliderPos = new Coord(slPos);
        sliderSize = new Coord(10, 20);
        scalePos = new Coord(0, size.y / 2 - 2);
        scaleSize = new Coord(size.x, 4);
        if (horizontal) {
            RectangleShape scale = new RectangleShape(new Coord(scalePos),
                    new Coord(scaleSize), new Color(Color.Type.Black), true);
            RectangleShape slider = new RectangleShape(new Coord(sliderPos),
                    new Coord(sliderSize), new Color(Color.Type.LightGray), true);
            StringShape minVal = new StringShape(new Coord(0, size.y/2 - 20), new Coord(50, 10), String.valueOf(begin),
                    new Color(Color.Type.Black), font);
            StringShape maxVal = new StringShape(new Coord(size.x - 50,size.y/2 - 20), new Coord(50, 10), String.valueOf(end),
                    new Color(Color.Type.Black), font);
            clearBasicShapes();
            addBasicShapes(minVal.getBasicShapes());
            addBasicShapes(maxVal.getBasicShapes());
            addBasicShape(scale);
            addBasicShape(slider);
        }
    }

    @Override
    public boolean drag(Coord point, Coord pressedPosition, boolean dragBegin){
        if (pressedPos == null){
            pressedPos = point;
            return true;
        }
        if (!point.inRectangle(new Coord(0, 0), getSize())) return true;
        if (horizontal) {
            double minX = scalePos.x;
            double maxX = minX + scaleSize.x + sliderSize.x;
            Coord delta = new Coord(point.x - sliderPos.x - sliderSize.x/2, 0);
            Coord newPos = sliderPos.add(delta);
            if (newPos.x > maxX || newPos.x < minX) return true;
            setSliderParts(newPos);
            pressedPos = point;
        }
        return true;
    }

    @Override
    public void click(Coord point) {
        if (!point.inRectangle(sliderPos, sliderSize)) return;
        pressedPos = point;
    }

    public int getNumber(){
        return (int)(((float)(end - begin))/(float)(getSize().x - sliderSize.x) * (sliderPos.x - getPos().x));
    }

    @Override
    public String getValue(String key){
        if (key.equals("number")){
            return String.valueOf(getNumber());
        }
        return Broadcaster.noResult;
    }
}
