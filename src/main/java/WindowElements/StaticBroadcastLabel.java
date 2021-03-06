package WindowElements;

import Foundation.BasicShapes.StringShape;
import Utils.Broadcaster;
import Utils.Geometry.Coord;
import WindowElementGroups.WindowElementGroup;
import Windows.Window;

public class StaticBroadcastLabel extends Label {

    public StaticBroadcastLabel(Coord pos, Coord size, Broadcaster broadcaster, String key, WindowElementGroup windowElementGroup) {
        super(pos, size, "", windowElementGroup);
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
