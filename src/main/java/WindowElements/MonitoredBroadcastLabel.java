package WindowElements;

import Foundation.BasicShapes.StringShape;
import Utils.Broadcaster;
import Utils.Geometry.Coord;
import Utils.Subscription;
import WindowElementGroups.WindowElementGroup;
import Windows.Window;

public class MonitoredBroadcastLabel extends Label{

    private Subscription subscription;
    private String preText;
    private String oldText;
    private Broadcaster broadcaster;
    private String key;


    public MonitoredBroadcastLabel(Coord pos, Coord size, Broadcaster broadcaster, String key, WindowElementGroup windowElementGroup) {
        super(pos, size, "", windowElementGroup);
        setBroadcaster(broadcaster);
        setKey(key);
        setOldText(broadcaster.getValue(key));
        StringShape stringShape = getStringShape();
        stringShape.setText(broadcaster.getValue(key));
        setStringShape(stringShape);
        subscription = new Subscription() {

            @Override
            public void changed() {
                renew();
            }
        };
        broadcaster.subscribe(key, subscription);
    }

    public MonitoredBroadcastLabel(Coord pos, Coord size, String text, Broadcaster broadcaster, String key, Window parent) {
        super(pos, size, text, parent);
        setPreText(text);
        setBroadcaster(broadcaster);
        setKey(key);
        setOldText(broadcaster.getValue(key));
        StringShape stringShape = getStringShape();
        stringShape.setText(text + " " + broadcaster.getValue(key));
        setStringShape(stringShape);
        subscription = new Subscription() {

            @Override
            public void changed() {
                renew();
            }
        };
        broadcaster.subscribe(key, subscription);
    }

    public MonitoredBroadcastLabel(Coord pos, Coord size, String text, Broadcaster broadcaster, String key, WindowElementGroup windowElementGroup) {
        super(pos, size, text, windowElementGroup);
        setPreText(text);
        setBroadcaster(broadcaster);
        setKey(key);
        setOldText(broadcaster.getValue(key));
        StringShape stringShape = getStringShape();
        stringShape.setText(text + " " + broadcaster.getValue(key));
        setStringShape(stringShape);
        subscription = new Subscription() {

            @Override
            public void changed() {
                renew();
            }
        };
        broadcaster.subscribe(key, subscription);
    }

    @Override
    public void click(Coord point) {

    }

    public void renew(){
        String newText = broadcaster.getValue(key);
        if (!oldText.equals(newText)){
            oldText = newText;
            StringShape stringShape = getStringShape();
            if (preText == null) stringShape.setText(newText);
            else stringShape.setText(preText + " " + newText);
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

    public String getPreText() {
        return preText;
    }

    public void setPreText(String preText) {
        this.preText = preText;
    }

    public void unsubscribe(){
        broadcaster.unsubscribe(key, subscription);
    }
}
