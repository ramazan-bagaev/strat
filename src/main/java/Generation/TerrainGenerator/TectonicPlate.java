package Generation.TerrainGenerator;

import Utils.Geometry.Index;

public class TectonicPlate extends Plate {

    private Index drivingDirection;

    public TectonicPlate(Plate plate, Index drivingDirection){
        super(plate);
        this.drivingDirection = drivingDirection;
    }

    public Index getDrivingDirection() {
        return drivingDirection;
    }
}
