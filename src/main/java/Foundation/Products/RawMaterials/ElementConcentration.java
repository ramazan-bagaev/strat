package Foundation.Products.RawMaterials;

import java.util.ArrayList;

public class ElementConcentration {

    private ArrayList<ElementConcentrationItem> items;

    public ElementConcentration(){
        items = new ArrayList<>();
    }

    public void addItem(ChemicalElement.Type element, int part){
        items.add(new ElementConcentrationItem(element, part));
    }

    @Override
    public boolean equals(Object object){
        if (this == object) return true;
        if (object == null) return false;
        if (getClass() != object.getClass()) return false;
        ElementConcentration elementConcentration = (ElementConcentration)object;
        return sameAs(elementConcentration);
    }

    private boolean sameAs(ElementConcentration elementConcentration){
        ArrayList<ElementConcentrationItem> otherItems = elementConcentration.items;
        for(ElementConcentrationItem otherItem: otherItems){
            boolean wasFound = false;
            for(ElementConcentrationItem item: items){
                if (item.element == otherItem.element){
                    if (item.part == otherItem.part){
                        wasFound = true;
                        break;
                    }
                    return false;
                }
            }
            if (!wasFound) return false;
        }
        return true;
    }

    public ArrayList<ElementConcentrationItem> getItems() {
        return items;
    }
}
