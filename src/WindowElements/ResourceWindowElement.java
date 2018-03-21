package WindowElements;

import Foundation.*;

import java.util.ArrayList;

public class ResourceWindowElement extends ScrollableElement {
    private Resource resource;

    public ResourceWindowElement(Coord pos, Coord size, Resource resource, Window parent) {
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
