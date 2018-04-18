package Foundation;

import Foundation.Army.Army;
import Foundation.Runnable.RunEntity;

public class Battle implements RunEntity {

    private Army firstSide;
    private Army secondSide;

    public Battle(Army firstSide, Army secondSide){
        this.firstSide = firstSide;
        this.secondSide = secondSide;
    }

    @Override
    public void run() {

    }
}
