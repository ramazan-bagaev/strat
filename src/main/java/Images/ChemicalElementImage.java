package Images;

import Foundation.BasicShapes.RectangleShape;
import Foundation.BasicShapes.StringShape;
import Foundation.Color;
import Foundation.Products.RawMaterials.ChemicalElement;
import Foundation.Products.RawMaterials.ElementConcentrationItem;
import Utils.Geometry.Coord;
import WindowElementGroups.WindowElementGroup;

public class ChemicalElementImage extends Image {

    private ChemicalElement.Type type;

    public ChemicalElementImage(Coord pos, Coord size, ChemicalElement.Type type, WindowElementGroup group) {
        super(pos, size, group);
        this.type = type;
        setShapes();
    }

    public void setShapes(){
        clearBasicShapes();

        Color color = getColor(type);
        RectangleShape newShape = new RectangleShape(new Coord(0, 0),
                new Coord(getSize()), color, true, true);
        double gray = color.getGrayScale();
        Color textColor;
        if (gray < 0.5) textColor = new Color(Color.Type.White);
        else textColor = new Color(Color.Type.Black);
        String text = ChemicalElement.getShortName(type);
        double centerX = getSize().x/2 - (double)(text.length()) * getSize().y/4;
        StringShape stringShape = new StringShape(new Coord(centerX, getSize().y/4), new Coord(getSize().x, getSize().y/2),
                text, textColor, getParent().getFont("latin"));
        addBasicShape(newShape);
        addBasicShapes(stringShape.getBasicShapes());
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
