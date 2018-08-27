package Generation;

import Foundation.Products.RawMaterials.ChemicalElement;
import Foundation.Products.RawMaterials.ElementConcentration;
import Foundation.Products.RawMaterials.ElementConcentrationItem;

import java.util.ArrayList;
import java.util.Random;

public class ElementConcentrationGenerator {

    private Random random;
    private ArrayList<ChemicalElement.Type> allElements;
    private ArrayList<Integer> distribution;

    public ElementConcentrationGenerator(Random random){
        this.random = random;
        allElements = new ArrayList<>();
        distribution = new ArrayList<>();
        initChemicalElements();
    }

    private void initChemicalElements(){
        allElements.clear();
        distribution.clear();
        allElements.add(ChemicalElement.Type.Aluminium);
        distribution.add(80);
        allElements.add(ChemicalElement.Type.Carbon);
        distribution.add(40);
        allElements.add(ChemicalElement.Type.Copper);
        distribution.add(14);
        allElements.add(ChemicalElement.Type.Gold);
        distribution.add(1);
        allElements.add(ChemicalElement.Type.Iron);
        distribution.add(50);
        allElements.add(ChemicalElement.Type.Lead);
        distribution.add(10);
        allElements.add(ChemicalElement.Type.Silver);
        distribution.add(5);
        allElements.add(ChemicalElement.Type.Silicon);
        distribution.add(800);
    }

    public ElementConcentration generate(){
        ElementConcentration result = new ElementConcentration();
        int amount = getRandomElementAmount();
        int maxPart = ElementConcentrationItem.granularity;
        int index = 0;
        for(int i = 0; i < amount; i++){
            ChemicalElement.Type type = getRandomChemicalElement();
            result.addItem(type, 0);
        }
        ArrayList<ElementConcentrationItem> items = result.getItems();
        while(true){
            index %= amount;
            if (maxPart == 0) break;
            int part = random.nextInt(maxPart) + 1;
            maxPart -= part;
            items.get(index).part += part;
            index++;
        }
        initChemicalElements();
        return result;
    }

    private int getRandomElementAmount(){
        double number = random.nextGaussian()  + 3;
        int res = (int)number;
        if (res <= 0) res = 3;
        if (res > allElements.size()) res = allElements.size();
        return res;
    }

    private ChemicalElement.Type getRandomChemicalElement(){
        int level = random.nextInt(getGranularity());
        int left;
        int right = 0;
        for(int i = 0; i < allElements.size(); i++){
            left = right;
            right += distribution.get(i);
            if (level >= left && level < right){
                ChemicalElement.Type res = allElements.get(i);
                allElements.remove(res);
                distribution.remove(i);
                return res;
            }
        }
        int index = allElements.size() - 1;
        ChemicalElement.Type res = allElements.get(allElements.size() - 1);
        allElements.remove(res);
        distribution.remove(index);
        return res;
    }

    private int getGranularity(){
        int gran = 0;
        for(Integer part: distribution){
            gran += part;
        }
        return gran;
    }
}
