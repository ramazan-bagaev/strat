package Foundation.GameWindowHelper.States;

import Foundation.*;
import Foundation.Elements.Village;
import Foundation.GameWindowHelper.Modes.CoveringFieldMode;
import Utils.Geometry.Index;
import Utils.Geometry.Coord;
import WindowElements.GameWindowElements.GameWindowHelperElement;

import java.util.ArrayList;

public class AddTrawlerState extends HelperState {
    private Village village;

    private CoveringFieldMode coveringFieldMode;

    private ArrayList<Index> possible;
    private ArrayList<Index> impossible;


    public AddTrawlerState(Village village, GameWindowHelperElement gameWindowHelperElement) {
        super(gameWindowHelperElement);
        this.village = village;
        coveringFieldMode = new CoveringFieldMode(gameWindowHelperElement);
        possible = new ArrayList<>();
        impossible = new ArrayList<>();
    }

    @Override
    public boolean isProperMode(){
        return (gameWindowHelperElement.getParent().getParent().getMode() == MainWindowCameraConfiguration.Mode.Normal);
    }

    private void init(){
        Territory availableWater = village.getAvailableWater();
        for(Index local: village.getManor().getTerritory().getIndexArray())
        {
            if (!availableWater.contains(local)){
                coveringFieldMode.addCoveringFieldHelper(local, new Color(Color.Type.Red, 0.5f));
                impossible.add(local);
                continue;
            }
            coveringFieldMode.addCoveringFieldHelper(local, new Color(Color.Type.Green, 0.5f));
            possible.add(local);
        }
    }


    @Override
    public void putHelperElements() {
        coveringFieldMode.putHelpers();
        init();
    }

    @Override
    public void click(Coord point) {
        if (!isProperMode()) return;
        point = gameWindowHelperElement.getMainWindow().getCameraConfiguration().transform(point);
        Index index = new Index(0, 0);
        index.x = (int) (point.x / gameWindowHelperElement.getMap().getFieldSize());
        index.y = (int) (point.y / gameWindowHelperElement.getMap().getFieldSize());
        if (impossible.contains(index)) return;
        if (possible.contains(index)) {
            village.createTrawler(index);
            return;
        }
        gameWindowHelperElement.clearHelperElements();
        gameWindowHelperElement.setStandardState();
    }

    @Override
    public void click2(Coord point) {

    }

    @Override
    public void hoover(Coord point) {

    }

    @Override
    public void clearHelperElements() {
        coveringFieldMode.removeHelpers();
    }

    @Override
    public void run() {

    }
}
