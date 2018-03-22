package Foundation;

import Foundation.BasicShapes.StringShape;
import Utils.Coord;

public class ChangableLabel extends Label{

    private String oldText;
    private Broadcaster broadcaster;
    private String key;

    public ChangableLabel(Coord pos, Coord size, String text, Broadcaster broadcaster, String key, Window parent) {
        super(pos, size, text, parent);
        setBroadcaster(broadcaster);
        setKey(key);
        setOldText(text);
    }

    public void renew(){
        String newText = broadcaster.getValue(key);
        if (!oldText.equals(newText)){
            oldText = newText;
            StringShape stringShape = getStringShape();
            stringShape.setText(newText);
            setStringShape(stringShape);
        }
    }

    public String getOldText() {
        return oldText;
    }

    public void setOldText(String oldText) {
        this.oldText = oldText;
    }

    public Broadcaster getBroadcaster() {
        return broadcaster;
    }

    public void setBroadcaster(Broadcaster broadcaster) {
        this.broadcaster = broadcaster;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public void run(){
        renew();
    }
}
