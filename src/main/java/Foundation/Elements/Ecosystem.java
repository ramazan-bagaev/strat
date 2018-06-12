package Foundation.Elements;

import Foundation.*;
import Foundation.Fauna.Fauna;
import Foundation.Flora.Flora;

public class Ecosystem extends FieldElement {

    private Fauna fauna;
    private Flora flora;
    private Climate climate;

    public Ecosystem(Field parent) {
        super(Type.Ecosystem, parent);
        climate = new Climate(parent.getGround(), Climate.MID_TEMPERATURE);
        flora = new Flora(this, parent.getGround());
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
