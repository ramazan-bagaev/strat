package Foundation;

import Foundation.BasicShapes.CharacterShape.Font;
import Foundation.BasicShapes.BasicShape;
import Foundation.BasicShapes.RectangleShape;
import Foundation.BasicShapes.StringShape;
import Utils.Coord;

import java.util.ArrayList;

public class Button extends WindowElement {

    private StringShape text;
    private RectangleShape carcas;
    private double delta = 0.1;

    public Button(Coord pos, Coord size, String str, Window parent){
        super(pos, size, parent);
        Font font = parent.getFont("latin");
        double sizeX = str.length() * size.y/2;
        text = new StringShape(new Coord(sizeX * delta, size.y/4), new Coord(sizeX, size.y/2), str, new Color(Color.Type.Black), font);
        carcas = new RectangleShape(new Coord(0, 0), new Coord(sizeX*(1+2*delta), size.y), new Color(Color.Type.White), true);
        ArrayList<BasicShape> basicShapes = new ArrayList<>();
        basicShapes.add(carcas);
        basicShapes.addAll(text.getBasicShapes());
        setBasicShapes(basicShapes);
    }

    public Button(Coord pos, Coord size, WindowElementGroup groupParent, String str, Window parent){
        super(pos, size, groupParent, parent);
        Font font = parent.getFont("latin");
        double sizeX = str.length() * size.y;
        text = new StringShape(new Coord(sizeX * delta, size.y/4), new Coord(sizeX, size.y/2), str, new Color(Color.Type.Black), font);
        carcas = new RectangleShape(new Coord(0, 0), new Coord(sizeX*(1+2*delta), size.y), new Color(Color.Type.White), true);
        ArrayList<BasicShape> basicShapes = new ArrayList<>();
        basicShapes.add(carcas);
        basicShapes.addAll(text.getBasicShapes());
        setBasicShapes(basicShapes);
    }

    public StringShape getText() {
        return text;
    }

    public void setText(StringShape text) {
        this.text = text;
    }

    public RectangleShape getCarcas() {
        return carcas;
    }

    public void setCarcas(RectangleShape carcas) {
        this.carcas = carcas;
    }

    @Override
    public void activate(){
        super.activate();
        carcas.setColor(new Color(Color.Type.Snow));
    }

    @Override
    public void deactivate(){
        super.deactivate();
        carcas.setColor(new Color(Color.Type.White));
    }
}
