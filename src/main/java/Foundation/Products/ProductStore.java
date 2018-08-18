package Foundation.Products;

import Foundation.FieldObjects.BuildingObject.BuildingObject;
import Utils.Content;
import Utils.Subscription;

import java.util.ArrayList;
import java.util.Iterator;

public class ProductStore extends ProductContainer {

    private BuildingObject storePlace;

    public ProductStore(BuildingObject storePlace){
        this.storePlace = storePlace;
    }

    public BuildingObject getStorePlace() {
        return storePlace;
    }

    public void setStorePlace(BuildingObject buildingObject){
        this.storePlace = buildingObject;
    }

}
