package Foundation.GameWindowHelper.States;

import Foundation.GameWindowHelperElement;
import Utils.Coord;

public abstract class HelperState {

    protected GameWindowHelperElement gameWindowHelperElement;

    public HelperState(GameWindowHelperElement gameWindowHelperElement){
        this.gameWindowHelperElement = gameWindowHelperElement;
    }

    public abstract void click(Coord point);

    public abstract void click2(Coord point);

    public abstract void hoover(Coord point);

    public abstract void clearHelperElements();

    public abstract void run();

}
