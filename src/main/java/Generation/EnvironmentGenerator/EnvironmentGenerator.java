package Generation.EnvironmentGenerator;

import Generation.FieldMapGenerator;

public class EnvironmentGenerator  extends FieldMapGenerator {

    private ForestGenerator forestGenerator;
    private SwampGenerator swampGenerator;
    private DesertGenerator desertGenerator;
    private RiverGenerator riverGenerator;

    public EnvironmentGenerator(){
        forestGenerator = new ForestGenerator();
        swampGenerator = new SwampGenerator();
        desertGenerator = new DesertGenerator();
        riverGenerator = new RiverGenerator();
    }

    @Override
    public void startGeneration() {
        System.out.println("river generation");
        riverGenerator.generate(map, size);
        System.out.println("forest generation");
        forestGenerator.generate(map, size);
        System.out.println("swamp generation");
        swampGenerator.generate(map, size);
        System.out.println("desert generation");
        desertGenerator.generate(map, size);
    }
}
