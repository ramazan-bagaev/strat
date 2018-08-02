package Generation.CivilizationGenerator;

import Foundation.Elements.Village;
import Foundation.Field;

import java.util.Random;

public class VillageGenerator {

    private Field field;
    private Random random;

    public VillageGenerator(Random random){
        this.random = random;
    }

    public Village generateVillage(Field field){
        this.field = field;
        return createVillage();
    }

    private Village createVillage(){
        //Village village = new Village(field);
        return null;
    }
}
