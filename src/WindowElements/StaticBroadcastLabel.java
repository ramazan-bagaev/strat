package WindowElements;

import Foundation.*;

public class StaticBroadcastLabel extends Label {

    public StaticBroadcastLabel(Coord pos, Coord size, String text, Broadcaster broadcaster, String key, Window parent) {
        super(pos, size, text, parent);
        StringShape stringShape = getStringShape();
        stringShape.setText(text + " " + broadcaster.getValue(key));
        setStringShape(stringShape);
    }
}
