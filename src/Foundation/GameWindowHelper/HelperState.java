package Foundation.GameWindowHelper;


import Foundation.Coord;
import Foundation.GameWindowHelperElement;

import java.util.ArrayList;

public abstract class HelperState {

    protected GameWindowHelperElement gameWindowHelperElement;

    public HelperState(GameWindowHelperElement gameWindowHelperElement){
        this.gameWindowHelperElement = gameWindowHelperElement;
    }

    public abstract void click(Coord point);

    public abstract void click2(Coord point);

    public abstract void hoover(Coord point);

    public abstract void clearHelperElements();

}
