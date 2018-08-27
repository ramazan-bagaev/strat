package Foundation.FieldObjects.NaturalObjects;

import Foundation.Products.RawMaterials.ElementConcentration;

public class MineralConcentrationItem {

    public static int granularity = 100;

    public ElementConcentration elementConcentration;
    public int part;

    public MineralConcentrationItem(ElementConcentration elementConcentration, int part){
        this.elementConcentration = elementConcentration;
        this.part = part;
    }
}
