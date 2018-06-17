package Generation.TerrainGenerator;

import Foundation.FieldMap;
import Generation.FieldMapGenerator;
import Utils.Geometry.Index;

public class TerrainGenerator extends FieldMapGenerator {


    private EmptyFieldGenerator emptyFieldGenerator;
    private ContinentsGenerator continentsGenerator;
    private ReliefGenerator reliefGenerator;

    public TerrainGenerator(){
        emptyFieldGenerator = new EmptyFieldGenerator();
        continentsGenerator = new ContinentsGenerator();
        reliefGenerator = new ReliefGenerator();
    }

    @Override
    public void startGeneration() {
        System.out.println("empty field generation");
        emptyFieldGenerator.generate(map, size);
        System.out.println("continent generation");
        continentsGenerator.generate(map, size);
        System.out.println("relief generation");
        reliefGenerator.generate(map, size);
    }
}
