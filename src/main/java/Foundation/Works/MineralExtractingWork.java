package Foundation.Works;

import Foundation.FieldObjects.NaturalObjects.MineralConcentration;
import Foundation.FieldObjects.NaturalObjects.MineralConcentrationItem;
import Foundation.FieldObjects.NaturalObjects.StoneObject;
import Foundation.Products.Product;
import Foundation.Products.ProductStore;
import Foundation.Products.RawMaterials.MineralProduct;
import Foundation.Works.Occupation.Occupation;

import java.util.ArrayList;
import java.util.Random;

public class MineralExtractingWork extends ProductMakingWork {

    private StoneObject stoneObject;
    private int amount;
    private ArrayList<Integer> distribution;

    public MineralExtractingWork(ProductStore store, StoneObject stoneObject, Occupation occupation) {
        super(store, occupation);
        this.stoneObject = stoneObject;
        distribution = new ArrayList<>();
        endStage = 7;
    }

    public int getFreePositions(){
        return stoneObject.getStoneSize() - people.getAmount();
    }

    @Override
    public void makeProduct() {
        MineralConcentration mineralConcentration = stoneObject.getMineralConcentration();
        ArrayList<MineralConcentrationItem> items = mineralConcentration.getItems();
        for(int i = 0; i < items.size(); i++){
            Product product = new MineralProduct(distribution.get(i), items.get(i).elementConcentration);
            createdProducts.add(product);
        }
    }

    private void initDistribution(){
        distribution.clear();
        Random random = stoneObject.getParent().getRandom();
        MineralConcentration mineralConcentration = stoneObject.getMineralConcentration();
        for(MineralConcentrationItem item: mineralConcentration.getItems()) distribution.add(0);
        for(int i = 0; i < amount; i++){
            int level = random.nextInt(MineralConcentrationItem.granularity);
            int index = mineralConcentration.getInterval(level);
            distribution.add(index, 1);
        }
    }

    @Override
    public boolean initWork() {
        initDistribution();
        amount = Math.min(people.getAmount(), stoneObject.getStoneSize());
        return amount != 0;
    }
}
