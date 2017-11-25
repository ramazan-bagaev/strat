package Windows;

import Foundation.*;

public class MonitoredBroadcastLabel extends Label{

    private String preText;
    private String oldText;
    private Broadcaster broadcaster;
    private String key;

    public MonitoredBroadcastLabel(Coord pos, Coord size, String text, Broadcaster broadcaster, String key, Window parent) {
        super(pos, size, text, parent);
        setPreText(text);
        setBroadcaster(broadcaster);
        setKey(key);
        setOldText(broadcaster.getValue(key));
        StringShape stringShape = getStringShape();
        stringShape.setText(text + " " + broadcaster.getValue(key));
        setStringShape(stringShape);

    }

    @Override
    public void click(Coord point) {

    }

    public void renew(){
        String newText = broadcaster.getValue(key);
        if (!oldText.equals(newText)){
            oldText = newText;
            StringShape stringShape = getStringShape();
            stringShape.setText(preText + " " +newText);
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

    @Override
    public void run(){
        renew();
    }
}
