package Foundation.GameWindowHelper.States;

import Foundation.*;
import Foundation.Elements.Village;
import Foundation.GameWindowHelper.Modes.CoveringFieldMode;
import Utils.Index;
import Utils.Coord;

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
        coveringFieldMode.putHelpers();
        possible = new ArrayList<>();
        impossible = new ArrayList<>();
        init();
    }

    private void init(){
        ArrayList<Index> availableWater = village.getAvailableWater();
        System.out.println("available");
        for(Index c: availableWater) System.out.println(c.x + " " + c.y);
        for(Index local: village.getManor().getTerritory())
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
    public void click(Coord point) {
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
        gameWindowHelperElement.setStandartState();
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