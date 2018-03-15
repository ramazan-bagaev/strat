package Foundation.GameWindowHelper.Modes;

import Foundation.GameWindowHelperElement;

public abstract class Mode {

    protected GameWindowHelperElement gameWindowHelperElement;

    public Mode(GameWindowHelperElement gameWindowHelperElement){
        this.gameWindowHelperElement = gameWindowHelperElement;
    }

    public abstract void putHelpers();

    public abstract void removeHelpers();
}
