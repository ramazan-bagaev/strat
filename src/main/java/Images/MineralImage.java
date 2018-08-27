package Images;

import Foundation.BasicShapes.RectangleShape;
import Foundation.BasicShapes.StringShape;
import Foundation.Color;
import Foundation.FieldObjects.NaturalObjects.MineralConcentration;
import Foundation.FieldObjects.NaturalObjects.MineralConcentrationItem;
import Foundation.Products.RawMaterials.ChemicalElement;
import Foundation.Products.RawMaterials.ElementConcentration;
import Foundation.Products.RawMaterials.ElementConcentrationItem;
import Utils.Geometry.Coord;
import WindowElementGroups.WindowElementGroup;
import WindowElements.Label;

public class MineralImage extends Image{

    private ElementConcentration elementConcentration;

    public MineralImage(Coord pos, Coord size, ElementConcentration elementConcentration, WindowElementGroup group){
        super(pos, size, group);
        this.elementConcentration = elementConcentration;
        setShapes();
    }

    public void setShapes(){
        clearBasicShapes();
        double x = 0;
        double sizeX = getSize().x;
        double sizeY = getSize().y;
        for(ElementConcentrationItem item: elementConcentration.getItems()) {
            double width = sizeX * item.part/ElementConcentrationItem.granularity;
            Color color = getColor(item.element);
            RectangleShape newShape = new RectangleShape(new Coord(x, 0),
                    new Coord(width, sizeY), getColor(item.element), true, true);
            double gray = color.getGrayScale();
            Color textColor;
            if (gray < 0.5) textColor = new Color(Color.Type.White);
            else textColor = new Color(Color.Type.Black);
            String text = ChemicalElement.getShortName(item.element);
            double centerX = x + width/2 - (double)(text.length()) * sizeY/4;
            StringShape stringShape = new StringShape(new Coord(centerX, sizeY/4), new Coord(width, sizeY/2),
                    text, textColor, getParent().getFont("latin"));
            addBasicShape(newShape);
            addBasicShapes(stringShape.getBasicShapes());
            x += width;
        }
    }

    private Color getColor(ChemicalElement.Type type){
        switch (type){
            case Copper:
                return new Color(Color.Type.Copper);
            case Carbon:
                return new Color(Color.Type.Carbon);
            case Oxygen:
                return new Color(Color.Type.Oxygen);
            case Silicon:
                return new Color(Color.Type.Silicon);
            case Iron:
                return new Color(Color.Type.Iron);
            case Silver:
                return new Color(Color.Type.Silver);
            case Gold:
                return new Color(Color.Type.Gold);
            case Lead:
                return new Color(Color.Type.Lead);
            case Aluminium:
                return new Color(Color.Type.Aluminium);
        }
        return null;
    }
}
