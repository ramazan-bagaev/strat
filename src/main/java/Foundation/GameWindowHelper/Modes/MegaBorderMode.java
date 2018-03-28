package Foundation.GameWindowHelper.Modes;

import Foundation.*;
import Foundation.Elements.City;
import Foundation.Elements.Manor;
import Foundation.Runnable.Country;

import java.util.ArrayList;
import java.util.Random;

public class MegaBorderMode extends Mode {

    private ArrayList<BorderMode> borderModes;

    public MegaBorderMode(GameWindowHelperElement gameWindowHelperElement) {
        super(gameWindowHelperElement);
        borderModes = new ArrayList<>();
    }

    public void init(){
        ArrayList<Country> countries = gameWindowHelperElement.getMap().getFieldMap().getGameEngine().getCountries();
        for(Country country: countries) {
            Random random = country.getCapital().getParent().getRandom();
            Color color = new Color(random.nextInt(256) / 256f, random.nextInt(256) / 256f, random.nextInt(256) / 256f);
            BorderMode borderMode = new BorderMode(gameWindowHelperElement, country.getTerritory(), color, 6);
            borderModes.add(borderMode);
            for (City city : country.getCities()) {

                borderMode = new BorderMode(gameWindowHelperElement, city.getTerritory(), color, 2);

                borderModes.add(borderMode);
                ArrayList<Manor> manors = city.getManors();
                for (Manor manor : manors) {
                    borderMode = new BorderMode(gameWindowHelperElement, manor.getTerritory(), color, 0.5f);
                    borderModes.add(borderMode);
                }
            }
        }
    }

    @Override
    public void putHelpers() {
        init();
        for(BorderMode borderMode: borderModes){
            borderMode.putHelpers();
        }
    }

    @Override
    public void removeHelpers() {
        for(BorderMode borderMode: borderModes){
            borderMode.removeHelpers();
        }
        borderModes.clear();
    }
}
