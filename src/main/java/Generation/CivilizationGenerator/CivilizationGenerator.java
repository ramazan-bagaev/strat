package Generation.CivilizationGenerator;

import Generation.FieldMapGenerator;

public class CivilizationGenerator extends FieldMapGenerator{

    private CountryGenerator countryGenerator;

    public CivilizationGenerator(){
        countryGenerator = new CountryGenerator();
    }

    @Override
    public void startGeneration() {
        countryGenerator.generate(map, size);
    }
}
