package Foundation.Products.RawMaterials;

public class ElementConcentrationItem {

    public static int granularity = 100;

    public ChemicalElement.Type element;
    public int part;

    public ElementConcentrationItem(ChemicalElement.Type element, int part){
        this.element = element;
        this.part = part;
    }
}
