package Foundation;

public class Climate {

    public static final int LOW_TEMPERATURE = 0;
    public static final int MID_TEMPERATURE = 1;
    public static final int HIGH_TEMPERATURE = 2;

    private Ground.GroundType groundType;
    private int temperature;

    public Climate(Ground ground, int temperature){
        this.groundType = ground.getGroundType();
        this.temperature = temperature;
    }

    public Ground.GroundType getGroundType() {
        return groundType;
    }
}
