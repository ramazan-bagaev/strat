package Foundation;

import Foundation.Fauna.Fauna;
import Foundation.Flora.Flora;

public class Ecosystem extends Element{

    private Fauna fauna;
    private Flora flora;
    private Climate climate;

    public Ecosystem(Coord pos, Coord size, Time time, Field parent) {
        super(Type.Ecosystem, time, parent);
        climate = new Climate(parent.getGround(), Climate.MID_TEMPERATURE);
        flora = new Flora(parent.getGround());
        fauna = new Fauna(this);
    }

    public void run(){
        flora.run();
        fauna.run();
    }

    public Fauna getFauna() {
        return fauna;
    }

    public Flora getFlora() {
        return flora;
    }

    public Climate getClimate() {
        return climate;
    }
}
