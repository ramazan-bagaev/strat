package Foundation.GameWindowHelper.States;

import Foundation.*;
import Foundation.Elements.Ground;
import Foundation.Elements.Village;
import Foundation.GameWindowHelper.Modes.CoveringFieldMode;

import java.util.ArrayList;

public class AddTrawlerState extends HelperState {
    private Village village;

    private CoveringFieldMode coveringFieldMode;

    private ArrayList<Coord> possible;
    private ArrayList<Coord> impossible;


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
        ArrayList<Coord> availableWater = village.getAvailableWater();
        System.out.println("available");
        for(Coord c: availableWater) System.out.println(c.x + " " + c.y);
        for(Coord local: village.getManor().getTerritory())
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
        point = gameWindowHelperElement.getParent().getCameraConfiguration().transform(point);
        point.x = point.x / gameWindowHelperElement.getMap().getFieldSize();
        point.y = point.y / gameWindowHelperElement.getMap().getFieldSize();
        if (impossible.contains(point)) return;
        if (possible.contains(point)) {
            village.createTrawler(point);
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
