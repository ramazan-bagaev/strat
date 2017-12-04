package WindowElements;

import Foundation.*;
import Windows.StaticBroadcastLabel;

import java.util.ArrayList;

public class ResourceWorkTypeLabel extends WindowElement{

    private ResourceCause resourceCause;
    private boolean clicked;

    public ResourceWorkTypeLabel(Coord pos, Coord size, ResourceCause resourceCause, Window parent){
        super(pos, size, parent);
        this.resourceCause = resourceCause;
        clicked = false;
        setShapes();
    }

    public void setShapes(){
        if (resourceCause == null) return;
        Resource.Type type = resourceCause.getType();
        Image image = Resource.getImage(getPos(), new Coord(getSize().x / 5, getSize().y), getParent(), type);
        StaticBroadcastLabel name = new StaticBroadcastLabel(getPos().add(new Coord(getSize().x/5, 0)), new Coord(3 * getSize().x / 5, getSize().y),
                "", resourceCause, "type", getParent());
        ArrayList<BasicShape> basicShapes = getBasicShapes();
        basicShapes.clear();
        if (clicked){
            RectangleShape clickedShaped = new RectangleShape(getPos(), getSize(), BasicShape.Color.LightGray, true);
            basicShapes.add(clickedShaped);
        }
        if (image != null) basicShapes.addAll(image.getBasicShapes());
        basicShapes.addAll(name.getBasicShapes());
    }

    @Override
    public void click(Coord point){
        clicked = true;
        setShapes();
    }
}
