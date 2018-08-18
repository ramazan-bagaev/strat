package Foundation.FieldObjects.BuildingObject;

import Foundation.BasicShapes.RectangleShape;
import Foundation.Color;
import Foundation.Economics.TradeShop;
import Foundation.Economics.Wallet;
import Foundation.Field;
import Foundation.Person.Person;
import Foundation.Products.ProductBundle;
import Utils.Broadcaster;
import Utils.Content;
import Utils.Subscription;
import Windows.FieldObjectWindow.MarketInfoWindow;
import Windows.Window;
import Utils.Geometry.Coord;
import Utils.Geometry.Index;

import java.util.ArrayList;

public class MarketObject extends BuildingObject implements Broadcaster {

    private ArrayList<TradeShop> tradeShops;
    private Content tradeShopsContent;

    public MarketObject(Field parent, Index cellPos) {
        super(parent, cellPos, new Index(4, 4));
        this.tradeShops = new ArrayList<>();
        tradeShopsContent = new Content();
        setShapes();
    }

    public ArrayList<TradeShop> getTradeShops() {
        return tradeShops;
    }

    public TradeShop addTradeShop(ProductBundle goods, Person trader){
        TradeShop tradeShop = new TradeShop(trader, goods, this);
        tradeShops.add(tradeShop);
        tradeShopsContent.changed();
        return tradeShop;
    }

    public void removeTradeShop(TradeShop tradeShop){
        tradeShops.remove(tradeShop);
        tradeShopsContent.changed();
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
        return new MarketInfoWindow(this, parent.getMap().getGameEngine().getGameWindowElement().getParent().getParent());
    }


    @Override
    public String getValue(String key) {
        switch (key){
            case "shopsAmount": return String.valueOf(tradeShops.size());
        }
        return noResult;
    }

    @Override
    public void subscribe(String key, Subscription subscription) {
        switch (key) {
            case "shopsAmount": tradeShopsContent.subscribe(subscription);
        }
        return;
    }

    @Override
    public void unsubscribe(String key, Subscription subscription) {
        switch (key) {
            case "shopsAmount": tradeShopsContent.unsubscribe(subscription);
        }
        return;
    }
}
