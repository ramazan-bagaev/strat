package Foundation.FieldObjects.BuildingObject;

import Foundation.BasicShapes.RectangleShape;
import Foundation.Color;
import Foundation.Economics.TradeShop;
import Foundation.Economics.Wallet;
import Foundation.Field;
import Foundation.Products.ProductBundle;
import Windows.Window;
import Utils.Geometry.Coord;
import Utils.Geometry.Index;

import java.util.ArrayList;

public class MarketObject extends BuildingObject {

    private ArrayList<TradeShop> tradeShops;

    public MarketObject(Field parent, Index cellPos) {
        super(parent, cellPos, new Index(4, 4));
        this.tradeShops = new ArrayList<>();
        setShapes();
    }

    public TradeShop addTradeShop(ProductBundle goods, Wallet traderWallet){
        TradeShop tradeShop = new TradeShop(traderWallet, goods, this);
        tradeShops.add(tradeShop);
        return tradeShop;
    }

    public void removeTradeShop(TradeShop tradeShop){
        tradeShops.remove(tradeShop);
    }

    @Override
    public void setShapes() {
        clearBasicShapes();

        double cellSize = getParent().getCellSize();

        RectangleShape rectangleShape = new RectangleShape(new Coord(), new Coord(size.x * cellSize, size.y * cellSize),
                new Color(Color.Type.Gray), true, true);

        addBasicShape(rectangleShape);

        rectangleShape = new RectangleShape(new Coord(size.x * cellSize/8, size.y * cellSize/8),
                new Coord(size.x * cellSize/4, size.y * cellSize/4), new Color(Color.Type.LightGray), true, true);

        addBasicShape(rectangleShape);

        rectangleShape = new RectangleShape(new Coord(5*size.x * cellSize/8, size.y * cellSize/8),
                new Coord(size.x * cellSize/4, size.y * cellSize/4), new Color(Color.Type.LightGray), true, true);

        addBasicShape(rectangleShape);

        rectangleShape = new RectangleShape(new Coord(size.x * cellSize/8, 5*size.y * cellSize/8),
                new Coord(size.x * cellSize/4, size.y * cellSize/4), new Color(Color.Type.LightGray), true, true);

        addBasicShape(rectangleShape);

        rectangleShape = new RectangleShape(new Coord(5*size.x * cellSize/8, 5*size.y * cellSize/8),
                new Coord(size.x * cellSize/4, size.y * cellSize/4), new Color(Color.Type.LightGray), true, true);

        addBasicShape(rectangleShape);
    }

    @Override
    public Window getInfoWindow() {
        return null;
    }
}
