package Generation;

import Foundation.FieldObjects.NaturalObjects.MineralConcentration;
import Foundation.FieldObjects.NaturalObjects.MineralConcentrationItem;
import Foundation.Products.RawMaterials.ElementConcentration;

import java.util.ArrayList;
import java.util.Random;

public class MineralConcentrationGenerator {

    private Random random;
    private ElementConcentrationGenerator generator;

    public MineralConcentrationGenerator(Random random){
        this.random = random;
        this.generator = new ElementConcentrationGenerator(random);
    }

    public MineralConcentration generate(){
        MineralConcentration result = new MineralConcentration();
        int amount = getRandomElementAmount();
        int maxPart = MineralConcentrationItem.granularity;
        for(int i = 0; i < amount; i++){
            ElementConcentration concentration = generator.generate();
            result.addMineral(concentration, 0);
        }
        int index = 0;
        ArrayList<MineralConcentrationItem> items = result.getItems();
        while (true){
            index %= amount;
            if (maxPart == 0) break;
            int part = random.nextInt(maxPart) + 1;
            items.get(index).part += part;
            maxPart -= part;
            index++;
        }
        return result;
    }

    private int getRandomElementAmount(){
        double number = random.nextGaussian()*0.5 + 2;
        int res = (int)number;
        if (res <= 0) res = 2;
        if (res > 4) res = 2;
        return res;
    }
}
