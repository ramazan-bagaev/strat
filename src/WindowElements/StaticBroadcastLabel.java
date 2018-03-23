package WindowElements;

import Foundation.*;
import Foundation.BasicShapes.StringShape;
import Utils.Coord;

public class StaticBroadcastLabel extends Label {

    public StaticBroadcastLabel(Coord pos, Coord size, Broadcaster broadcaster, String key, WindowElementGroup windowElementGroup, Window parent) {
        super(pos, size, "", windowElementGroup,  parent);
        StringShape stringShape = getStringShape();
        stringShape.setText(broadcaster.getValue(key));
        setStringShape(stringShape);
    }

    public StaticBroadcastLabel(Coord pos, Coord size, Broadcaster broadcaster, String key, Window parent) {
        super(pos, size, "", parent);
        StringShape stringShape = getStringShape();
        stringShape.setText(broadcaster.getValue(key));
        setStringShape(stringShape);
    }

    public StaticBroadcastLabel(Coord pos, Coord size, String text, Broadcaster broadcaster, String key, Window parent) {
        super(pos, size, text, parent);
        StringShape stringShape = getStringShape();
        stringShape.setText(text + " " + broadcaster.getValue(key));
        setStringShape(stringShape);
    }
}
