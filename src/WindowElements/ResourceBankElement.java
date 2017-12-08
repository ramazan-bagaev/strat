package WindowElements;

import Foundation.*;

import java.util.ArrayList;

public class ResourceBankElement extends ScrollableElement {
    private ResourceBank resourceBank;

    public ResourceBankElement(Coord pos, Coord size, ResourceBank resourceBank, Window parent) {
        super(pos, size, parent);
        this.resourceBank = resourceBank;
        initText();
    }

    public String getNewText(){
        String result = "";
        result += resourceBank.getValue("type") + " ";
        if (resourceBank.getType() == Resource.Type.Food) {
            result += resourceBank.getValue("foodType") + " ";
        }
        result += resourceBank.getValue("capacity");
        return result;
    }
}
