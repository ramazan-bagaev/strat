package Generation.TerrainGenerator;

public class TectonicBoundary {

    public Plate firstPlate;
    public Plate secondPlate;
    public TerrainEdge edge;

    @Override
    public boolean equals(Object object){
        if (object == null) return false;
        if (object.getClass() != getClass()) return false;
        TectonicBoundary other = (TectonicBoundary)object;
        if (edge == other.edge) {
            if (other.firstPlate != firstPlate && other.firstPlate != secondPlate) return false;
            if (other.secondPlate != firstPlate && other.secondPlate != secondPlate) return false;
            return true;
        }
        return false;
    }
}
