package WindowElements;

import Foundation.*;
import Utils.Coord;

public class ResourceScrollElement extends ScrollableElement {
    private Resource resource;

    public ResourceScrollElement(Coord pos, Coord size, Resource resource, Window parent) {
        super(pos, size, parent);
        this.resource = resource;
        initText();
    }

    public String getNewText(){
        String result = "";
        result += resource.getValue("type") + " ";
        result += resource.getValue("name") + " ";
        result += resource.getValue("amount");
        return result;
    }
}
