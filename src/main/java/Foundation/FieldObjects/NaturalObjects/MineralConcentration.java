package Foundation.FieldObjects.NaturalObjects;

import Foundation.Products.RawMaterials.ElementConcentration;

import java.util.ArrayList;

public class MineralConcentration {

    private ArrayList<MineralConcentrationItem> items;

    public MineralConcentration(){
        items = new ArrayList<>();
    }

    public void addMineral(ElementConcentration elementConcentration, int part){
        items.add(new MineralConcentrationItem(elementConcentration, part));
    }

    public ArrayList<MineralConcentrationItem> getItems() {
        return items;
    }

    public int getInterval(int level){
        int rightLimit = 0;
        int leftLimit;
        for(int i = 0; i < items.size(); i++){
            leftLimit = rightLimit;
            rightLimit += items.get(i).part;
            if (level >= leftLimit && level < rightLimit) return i;
        }
        return items.size() - 1;
    }
}
