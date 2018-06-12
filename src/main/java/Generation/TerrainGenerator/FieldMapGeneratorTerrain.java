package Generation.TerrainGenerator;

import Foundation.Elements.Ground;
import Foundation.FieldMap;
import Foundation.Field;
import Foundation.GameEngine;
import Foundation.Time;
import Utils.Geometry.Index;

import java.util.ArrayList;
import java.util.Random;

public class FieldMapGeneratorTerrain {

    private Random random;
    private FieldMap map;
    private Time time;
    private Index size;

    private EmptyFieldGenerator emptyFieldGenerator;
    private ContinentsGenerator continentsGenerator;
    private ReliefGenerator reliefGenerator;

    private ArrayList<Index> localLine;

    public FieldMapGeneratorTerrain(Random random){
        this.random = random;
        localLine = new ArrayList<>();
    }

    public FieldMap generate(Index size, int fieldSize, int superFieldSize, GameEngine gameEngine){
        init(size, fieldSize, superFieldSize, gameEngine);
        emptyFieldGenerator.generate(map, size);
        continentsGenerator.generate(map, size);
        reliefGenerator.generate(map, size);
        return map;
    }

    private void init(Index size, int fieldSize, int superFieldSize, GameEngine gameEngine){
        this.map = new FieldMap(superFieldSize, fieldSize, gameEngine);
        this.size = size;
        this.time = gameEngine.getTime();
        emptyFieldGenerator = new EmptyFieldGenerator();
        continentsGenerator = new ContinentsGenerator();
        reliefGenerator = new ReliefGenerator();
    }
}
