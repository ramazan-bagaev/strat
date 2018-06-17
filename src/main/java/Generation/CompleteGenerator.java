package Generation;

import Foundation.FieldMap;
import Generation.CivilizationGenerator.CivilizationGenerator;
import Generation.EnvironmentGenerator.EnvironmentGenerator;
import Generation.TerrainGenerator.TerrainGenerator;

public class CompleteGenerator extends FieldMapGenerator {

    private TerrainGenerator terrainGenerator;
    private EnvironmentGenerator environmentGenerator;
    private CivilizationGenerator civilizationGenerator;

    public CompleteGenerator(){
        terrainGenerator = new TerrainGenerator();
        environmentGenerator = new EnvironmentGenerator();
        civilizationGenerator = new CivilizationGenerator();
    }

    @Override
    public void startGeneration() {
        System.out.println("terrain generation");
        terrainGenerator.generate(map, size);
        System.out.println("environment generation");
        environmentGenerator.generate(map, size);
        System.out.println("civilization generation");
        civilizationGenerator.generate(map, size);
    }
}
