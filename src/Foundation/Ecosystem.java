package Foundation;

import Foundation.Fauna.Fauna;
import Foundation.Flora.Flora;

public class Ecosystem extends Element{

    private Fauna fauna;
    private Flora flora;

    public Ecosystem(Coord pos, Coord size, Time time, Field parent) {
        super(Type.Ecosystem, time, parent);
        flora = new Flora(parent.getGround());
        fauna = new Fauna(flora);


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
}
