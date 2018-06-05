package Foundation.GameWindowHelper.States;

import Foundation.GameWindowHelper.Modes.Mode;
import Foundation.GameWindowHelperElement;
import Utils.Coord;

public abstract class HelperState {

    protected GameWindowHelperElement gameWindowHelperElement;

    public HelperState(GameWindowHelperElement gameWindowHelperElement){
        this.gameWindowHelperElement = gameWindowHelperElement;
    }

    public boolean isProperMode(){
        return true;
    }

    public abstract void putHelperElements();

    public abstract void click(Coord point);

    public abstract void click2(Coord point);

    public abstract void hoover(Coord point);

    public boolean drag(Coord point, Coord pressedPos, boolean dragBegin){
        return false;
    }

    public abstract void clearHelperElements();

    public abstract void run();

}
